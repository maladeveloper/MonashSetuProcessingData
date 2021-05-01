package JavaSourceCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HeaderInfo implements InfoInterface {

    Document htmlDoc;
    HashMap<String,String> info = new HashMap<String, String>();

    public HeaderInfo(Document htmlDoc){

        this.htmlDoc = htmlDoc;

        this.extractInfo();
    }

    private void extractInfo(){
        Elements rows = this.htmlDoc.getElementsByClass("ReportBlockDetails").select("tr");
        ArrayList<String> dataOrder = new ArrayList<String>(Arrays.asList("title", "subtitle","faculty", "criticalData", "name"));

        for(String infoKey: dataOrder){
            String value = rows.first().select("strong").first().text();          
            this.info.put(infoKey, value);
            rows.remove(0);
        }

        //System.out.println(this.info);
    }











    public HashMap<String, String> getInfo() {
        

        return null;
    }
    
    
}
