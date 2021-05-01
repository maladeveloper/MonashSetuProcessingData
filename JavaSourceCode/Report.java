package JavaSourceCode;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Report{

    private Document htmlDoc;

    private HashMap<String, String> fileInfo = new HashMap<String, String>();

    private PrintWriter out;

    public Report(File curFile) throws IOException{

        this.htmlDoc = Jsoup.parse(curFile, null);
        this.extractHeaderInfo();
        
    }

    private void extractHeaderInfo(){

        Element divHeader = this.htmlDoc.getElementsByClass("ReportBlockDetails").first();
        Elements rows = divHeader.select("tr");
        ArrayList<String> dataOrder = new ArrayList<String>(Arrays.asList("title", "subtitle","faculty", "criticalData", "name"));

        for(String infoKey: dataOrder){
            String value = rows.first().select("strong").first().text();          
            this.fileInfo.put(infoKey, value);
            rows.remove(0);
        }
        
        System.out.println(this.fileInfo);        
    }

    public HashMap<String, String> getFileInfo(){

        return this.fileInfo;
    }
}