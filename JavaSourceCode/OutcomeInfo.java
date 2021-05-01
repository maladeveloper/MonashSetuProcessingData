package JavaSourceCode;

import java.util.HashMap;

import org.jsoup.nodes.Document;

public class OutcomeInfo implements InfoInterface {

    Document htmlDoc;
    String keyInfo;
    HashMap<String,String> info = new HashMap<String, String>();

    public OutcomeInfo(Document htmlDoc, String keyInfo){

        this.htmlDoc = htmlDoc; this.keyInfo = keyInfo;

        this.extractInfo();
    }


    private void extractInfo() {

        

    }


    public HashMap<String, String> getInfo() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
