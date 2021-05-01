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
    private ArrayList<InfoInterface> infos = new ArrayList<InfoInterface>(); 

    public Report(File curFile) throws IOException{

        this.htmlDoc = Jsoup.parse(curFile, null);
        
        this.addInfo();        
    }

    private void addInfo(){

        //Add header info 
        this.infos.add(new HeaderInfo(this.htmlDoc));

        //Add the response info 
        this.infos.add(new ResponseInfo(this.htmlDoc) );
    }










    public HashMap<String, String> getFileInfo(){

        return this.fileInfo;
    }
}