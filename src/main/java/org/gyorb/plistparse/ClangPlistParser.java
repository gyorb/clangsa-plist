package org.gyorb.plistparse;


import com.dd.plist.PropertyListParser;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSArray;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;
import com.dd.plist.NSNumber;

import java.io.File;

class ClangPlistParser{

    String description;
    String type;
    String category;
    Integer line;
    Integer col;
    String file;

    private static String plistFile;

    private static NSDictionary rootDict;

    public ClangPlistParser(String filePath){
        plistFile = filePath;
        File f = new File(plistFile);
        try{
            rootDict = (NSDictionary)PropertyListParser.parse(f);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void print(String msg){
        System.out.println(msg +"\n");
    }

    public void printAscii(){
        print(rootDict.toASCIIPropertyList());
    }

    public void parsePlist(){
        try {
            //String version = rootDict.objectForKey("clang_version").toString();
            //print("Clang version: "+version);

            //print("Printing file list:");
            NSObject[] files = ((NSArray)rootDict.objectForKey("files")).getArray();
            for(NSObject fp:files){
                print(fp.toString());
            }

            print("Diagnostic data:");
            NSObject[] diagnostics = ((NSArray)rootDict.objectForKey("diagnostics")).getArray();
            for(NSObject diag:diagnostics){
                NSDictionary d = (NSDictionary)diag;
                NSString desc = (NSString)d.get("description");
                description = desc.getContent();

                NSString cat = (NSString)d.get("category");
                category = cat.getContent();

                //NSString msg = (NSString)d.get("nonvalid_key");
                //if (msg != null) {
                //    print(msg.getContent());
                //}

                NSDictionary location = (NSDictionary)d.get("location");
                line = ((NSNumber)location.get("line")).intValue();
                col = ((NSNumber)location.get("col")).intValue();
                NSNumber fileIndex = (NSNumber)location.get("file");
                NSObject filePath = files[fileIndex.intValue()];
                
                this.file = ((NSString)filePath).getContent();

            }
            } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


}