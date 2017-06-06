package org.gyorb.plistparse;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.DefaultParser;

import com.dd.plist.PropertyListParser;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSArray;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;
import com.dd.plist.NSNumber;

public class App 
{
    static ArrayList<Issue> parseReport(String plistFile){
        //TODO: add to debug log
        // System.out.println("Parsing report:" + plistFile);
        ArrayList<Issue> issues = new ArrayList<Issue>();

        try {
            File f = new File(plistFile);
            NSDictionary rootDict = (NSDictionary)PropertyListParser.parse(f);

            NSObject[] files = ((NSArray)rootDict.objectForKey("files")).getArray();

            NSObject[] diagnostics = ((NSArray)rootDict.objectForKey("diagnostics")).getArray();

            for(NSObject diag:diagnostics){
                NSDictionary d = (NSDictionary)diag;
                NSString desc = (NSString)d.get("description");
                String de = desc.getContent();

                NSDictionary location = (NSDictionary)d.get("location");
                Integer line = ((NSNumber)location.get("line")).intValue();
                Integer col = ((NSNumber)location.get("col")).intValue();
                NSNumber fileIndex = (NSNumber)location.get("file");
                NSObject filePath = files[fileIndex.intValue()];
                
                String file = ((NSString)filePath).getContent();

                NSString cname = (NSString)d.get("check_name");

                Issue issue = new Issue(de, line, col, file, cname.getContent());
                issues.add(issue);
            }
            } catch(Exception ex) {
            ex.printStackTrace();
        }
        return issues;
    }

    public static void main( String[] args )
    {
        Options options = new Options();
        Option verbose = new Option( "verbose", "be extra verbose" );
        options.addOption( verbose );

        Option report = Option.builder("r")
            .longOpt( "report" )
            .desc( "use given file for log"  )
            .hasArg()
            .argName( "SIZE" )
            .build();
        options.addOption( report );

        // create the parser
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine cmd = parser.parse( options, args );

            String saReport = cmd.getOptionValue("report");

            if( saReport == null) {
                System.out.println("Report file is missing.");
            }
            else {
                // print date for country specified by countryCode
                ArrayList<Issue> issues = parseReport(saReport);
                for (Issue i : issues) {
                   System.out.println(i.format()); 
                }
            }

        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }
    }
}