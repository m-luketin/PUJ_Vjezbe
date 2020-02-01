package org.apimqtt.services.Filters;

import io.jsonwebtoken.ExpiredJwtException;
import org.apimqtt.services.Client.ClientService;
import org.apimqtt.services.Client.JWTService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final ClientService clientService;

    public JwtRequestFilter(
            JWTService jwtService,
            ClientService clientService
    ) {
        super();
        this.jwtService = jwtService;
        this.clientService = clientService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = httpServletRequest.getHeader("Authorization");

        String email = null;
        String jwtToken = null;
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
             jwtToken = requestTokenHeader.substring(7);

            try {
                email = jwtService.getEmailFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.warn("Unable to get JWT token");
            } catch ( ExpiredJwtException e) {
                logger.warn("JWT token expired");
            }
        } else {
            logger.warn("JWT token does not start with bearer string");
        }

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails client = this.clientService.loadUserByUsername(email);

            if(jwtService.validateToken(jwtToken, client)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(client, null, new ArrayList<>());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
