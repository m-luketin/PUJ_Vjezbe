package Vjezba4_PUJ;

import Vjezba4_PUJ.classes.Watermeter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class App 
{
    public static void main (String args[]) throws IOException
    {
        String filePath = "C:/Users/mluketin/Desktop/PUJ_Vjezbe/Vjezba4/src/main/java/Vjezba4_PUJ/jsonConfig/configuration.json";
        Watermeter watermeter = Watermeter.createWatermeter(filePath);
        watermeter.publish();
    }
}
