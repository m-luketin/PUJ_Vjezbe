package com.company;

public class Main {

    public static void main(String[] args)
    {
        CRC32 newCRC = new CRC32();

        System.out.print("Checksum starts as: " + newCRC.getValue() + "\n");

        String str = "Generate CRC32 Checksum For Byte Array Example";
        newCRC.update(str.getBytes());
        System.out.print("String gets turned into: " + newCRC.getValue() + "\n");

        newCRC.reset();
        newCRC.update(str.getBytes(), 0, str.length());
        System.out.print("Then into: " + newCRC.getValue() + "\n");

        newCRC.reset();
        newCRC.update((byte) 'c');
        System.out.print("Then into: " + newCRC.getValue() + "\n");

        newCRC.reset();
        System.out.print("After being reset: " + newCRC.getValue() + "\n");


        CRC32Test testerClass = new CRC32Test();
        testerClass.testUpdate();
        testerClass.testMultipleArgumentUpdate();
        testerClass.testReset();
    }
}
