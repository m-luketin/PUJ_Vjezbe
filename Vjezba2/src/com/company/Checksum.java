package com.company;

public interface Checksum
{
    int calculatedChecksum = 0;
    int[] crcTable = makeCrcTable();

    static int[] makeCrcTable ()
    {
        int[] crcTable = new int[256];

        for (int n = 0; n < 256; n++)
        {
            int c = n;
            for (int k = 8;  --k >= 0; )
            {
                if ((c & 1) != 0)
                    c = 0xedb88320 ^ (c >>> 1);
                else
                    c = c >>> 1;
            }
            crcTable[n] = c;
        }
        return crcTable;
    }
    void update (byte[] bytes, int start, int length);
    long getValue();
}
