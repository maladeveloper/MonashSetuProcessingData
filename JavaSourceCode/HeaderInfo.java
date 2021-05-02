package JavaSourceCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HeaderInfo implements InfoInterface {

    Document htmlDoc;
    String keyInfo = "headerInfo";
    HashMap<String,String> info = new HashMap<String, String>();
    String criticalData = "criticalData";
    String[] criticalDataKeys = {"unitCode", "location"};
    ArrayList<String> dataOrder = new ArrayList<String>(Arrays.asList("title", "subtitle","faculty", criticalData, "name"));

    public HeaderInfo(Document htmlDoc){

        this.htmlDoc = htmlDoc;

        this.extractInfo();
    }

    private void extractInfo(){
        Elements rows = this.htmlDoc.getElementsByClass("ReportBlockDetails").select("tr");

        for(String infoKey: this.dataOrder){
            String value = rows.first().select("strong").first().text();          
            this.info.put(infoKey, value);
            rows.remove(0);
        }

        this.splitCriticalData();
    }

    private void splitCriticalData(){

        String[] splitData = this.info.get(this.criticalData).split("_");

        //Add unitcode and location
        for (int i=0; i<this.criticalDataKeys.length; i++){this.info.put(this.criticalDataKeys[i], splitData[i]);}

    }

    public HashMap<String, String> getInfo() {return this.info;}

    public String getKey(){return this.keyInfo;}
    
}
