package com.company;

public class CRC32 extends Object implements Checksum
{
    private int crc = 0;
    private static int[] crc_table = Checksum.makeCrcTable();

    public CRC32() {}

    public void update (int b)
    {
        int c = ~crc;
        c = crc_table[(c ^ b) & 0xff] ^ (c >>> 8);
        crc = ~c;
    }

    public void update (byte[] b, int off, int len)
    {
        int c = ~crc;
        while (--len >= 0)
            c = crc_table[(c ^ b[off++]) & 0xff] ^ (c >>> 8);
        crc = ~c;
    }

    public void update (byte[] b)
    {
        update(b, 0, b.length);
    }

    public void reset () {
        crc = 0;
    }

    public long getValue ()
    {
         return (long) crc & 0xffffffffL;
    }
}
