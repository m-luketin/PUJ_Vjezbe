package com.company;

public class Main {
    public static void main(String args[])
    {
        String str = "Generate CRC32 Checksum For Byte Array Example";

        byte bytes[] = str.getBytes();
        Checksum checksum = new CRC32();
        checksum.update(bytes,0,bytes.length);
        long lngChecksum = checksum.getValue();
        System.out.println("CRC32 checksum for byte array is:" + lngChecksum);
    }
}