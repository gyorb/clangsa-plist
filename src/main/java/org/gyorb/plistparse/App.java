package org.gyorb.plistparse;


/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        ClangPlistParser clangReportParser = new ClangPlistParser("Info.plist");
        clangReportParser.parsePlist();
        System.out.println(clangReportParser.description);
        System.out.println(clangReportParser.category);
        System.out.println(clangReportParser.line);
        System.out.println(clangReportParser.col);
        System.out.println(clangReportParser.file);
    }
}
