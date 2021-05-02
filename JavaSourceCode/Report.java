package JavaSourceCode;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.*;
import org.apache.commons.lang3.text.WordUtils;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Report{

    private Document htmlDoc; 
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

        //Add the learning outcome info
        Elements outcomeDivs = this.htmlDoc.getElementsByClass("FrequencyBlock_HalfMain");
        
        for(Element outcome : outcomeDivs){

            String keyInfo = outcome.getElementsByClass("FrequencyQuestionTitle").select("span").text();

            keyInfo = getCleanKey(keyInfo);
            
            this.infos.add(new OutcomeInfo(outcome, keyInfo));
        }
    }

    private String getCleanKey(String keyInfo){

        String cleanKey = keyInfo.replaceAll("[^a-zA-Z ]", "").toLowerCase();

        cleanKey = WordUtils.capitalizeFully(cleanKey, ' ').replaceAll(" ", "");

        cleanKey = Character.toLowerCase(cleanKey.charAt(0)) + cleanKey.substring(1);

        return cleanKey;
    }



    public HashMap<String, HashMap<String,String>> getFileInfo(){

        HashMap<String, HashMap<String,String>> reportInfo = new HashMap<String, HashMap<String,String>>();

        for (InfoInterface info: this.infos){ reportInfo.put(info.getKey(), info.getInfo());}

        return reportInfo;
    } 
}