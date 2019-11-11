package com.company;
import org.junit.Test;

public class CRC32Test {
    @Test
    public void testUpdate()
    {
        CRC32 testedClass = new CRC32();
        testedClass.update("Generate CRC32 Checksum For Byte Array Example".getBytes());
        assert(testedClass.getValue() == 3510043186L);
    }

    @Test
    public void testMultipleArgumentUpdate()
    {
        CRC32 testedClass = new CRC32();
        testedClass.update(
                "Generate CRC32 Checksum For Byte Array Example".getBytes(),
                0,
                "Generate CRC32 Checksum For Byte Array Example".length()
        );
        assert(testedClass.getValue() == 3510043186L);
    }

    @Test
    public void testReset()
    {
        CRC32 testedClass = new CRC32();
        testedClass.update("Generate CRC32 Checksum For Byte Array Example".getBytes());
        testedClass.reset();
        assert(testedClass.getValue() == 0);
    }
}
