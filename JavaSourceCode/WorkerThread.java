package JavaSourceCode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

import JavaSourceCode.Report;

public class WorkerThread extends Thread{

    private int id;
    private ArrayList<File> files;
    private String resultsDir = "ProcessedReports/";
    private String allResultsFile = "all_processed_reports.json";
    private String[] reportKeyPath = {"headerInfo", "criticalData"};
    private String jsonFileExt = ".json";

    public WorkerThread(int id, ArrayList<File> files){
        
        this.id = id; this.files = files;
    }

    public void run(){

        try{

            ArrayList<Report> processedReports = new ArrayList<Report>();

            for(File curFile : this.files){

                Report newReport = new Report(curFile);

                processedReports.add(newReport);

                
            }
                    //Write out each report to processed reports folder
            for (Report report: processedReports){

                ObjectMapper reportMapper = new ObjectMapper(); 

                String reportName = resultsDir + report.getFileInfo().get(reportKeyPath[0]).get(reportKeyPath[1]) + jsonFileExt;

                reportMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportName), report);
            }

            System.out.print("Worker thread finished: " + String.valueOf(this.id) +"\n");
        }catch(Exception e){}

    }
}