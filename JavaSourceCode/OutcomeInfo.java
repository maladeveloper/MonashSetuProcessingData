package JavaSourceCode;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OutcomeInfo implements InfoInterface {

    Element htmlDoc;
    String keyInfo;
    HashMap<String,String> info = new HashMap<String, String>();

    public OutcomeInfo(Element htmlDoc, String keyInfo){

        this.htmlDoc = htmlDoc; this.keyInfo = keyInfo;

        this.extractInfo();
    }


    private void extractInfo() {

        Elements tableData = this.htmlDoc.getElementsByClass("FrequencyBlock_table").select("tbody").first().select("tr");
        
        for(Element row: tableData){

            String key = row.select("th").first().text().replaceAll("\\s+", "");

            try{
                this.info.put(key, row.select("td").get(1).text());
            }catch(IndexOutOfBoundsException e){ }
        }       
    }

    public HashMap<String, String> getInfo() {return this.info;}

    public String getKey(){return this.keyInfo;}
    
}
