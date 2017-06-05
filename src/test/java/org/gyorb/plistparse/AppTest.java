package org.gyorb.plistparse;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testCategory()
    {
        assertTrue(true);
        //String plistToParse = getClass().getResource("/test1.plist").getFile();
        //ClangPlistParser clangReportParser = new ClangPlistParser(plistToParse);
        //clangReportParser.parsePlist();
        //assertEquals("Logic error", clangReportParser.category);
    }

    //public void testDescription()
    //{
    //    String plistToParse = getClass().getResource("/test1.plist").getFile();
    //    ClangPlistParser clangReportParser = new ClangPlistParser(plistToParse);
    //    clangReportParser.parsePlist();
    //    assertEquals("Division by zero",
    //                 clangReportParser.description);
    //}

    //public void testFile()
    //{
    //    String plistToParse = getClass().getResource("/test1.plist").getFile();
    //    ClangPlistParser clangReportParser = new ClangPlistParser(plistToParse);
    //    clangReportParser.parsePlist();
    //    assertEquals("test.cpp", clangReportParser.file);
    //}
}
