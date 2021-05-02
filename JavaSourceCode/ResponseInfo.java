package JavaSourceCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ResponseInfo implements InfoInterface {

    Document htmlDoc;
    String keyInfo = "responseInfo";
    HashMap<String,String> info = new HashMap<String, String>();
    ArrayList<String> orderedKeys = new ArrayList<String>(Arrays.asList("rater","respCount", "inviteCount", "respRate"));



    public ResponseInfo(Document htmlDoc){

        this.htmlDoc = htmlDoc;

        this.extractInfo();
    }
    
    
    
    private void extractInfo(){

        Elements rows = this.htmlDoc.getElementsByClass("CrossCategoryBlockRow TableContainer").select("tr");

        this.info.put( orderedKeys.get(0),rows.first().select("th").get(1).text());

        orderedKeys.remove(0); rows.remove(0);


        for (String keyInfo: orderedKeys){

            this.info.put(keyInfo, rows.first().select("td").text());

            rows.remove(0);
        }
    }


    public HashMap<String, String> getInfo() {return this.info;}

    public String getKey(){return this.keyInfo;}
    
}
