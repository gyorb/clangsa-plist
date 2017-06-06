package org.gyorb.plistparse;

import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang3.text.StrSubstitutor;

class Issue{
    Map<String, String> values = new HashMap<String, String>();

    public Issue(String description, Integer line, Integer col, String filePath, String checker){
        this.values.put("desc", description);
        this.values.put("line", line.toString());
        this.values.put("col", col.toString());
        this.values.put("file", filePath);
        this.values.put("checker", checker);
    }

    public String format(String format){
        StrSubstitutor sub = new StrSubstitutor(this.values, "%(", ")");
        String result = sub.replace(format);
        return result;
    }

    public String format(){
        return this.format("%(file):%(line):%(col): %(checker): %(desc)");
    }

    public String gccformat(){
        // https://gcc.gnu.org/onlinedocs/gnat_ugn/Output-and-Error-Message-Control.html
        return this.format("%(file):%(line):%(col) %(desc)");
    }

    public String clangformat(){
        // https://clang.llvm.org/diagnostics.html
        return this.format("%(file):%(line):%(col): %(checker): %(desc)");
    }
}
