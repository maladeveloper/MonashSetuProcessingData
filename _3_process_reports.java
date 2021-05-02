import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

import JavaSourceCode.Report;

public class _3_process_reports {

    static private String reportsDir = "DownloadedReports/";
    static private String resultsDir = "ProcessedReports/";
    static private String allResultsFile = "all_processed_reports.json";
    static private String[] reportKeyPath = {"headerInfo", "criticalData"};
    static private String jsonFileExt = ".json";

    public static void main(String[] args) throws IOException {

        
        File[] reportFileNames  = new File(reportsDir).listFiles();

        ArrayList<Report> processedReports = new ArrayList<Report>();

        for(File curFile : reportFileNames){

            Report newReport = new Report(curFile);

            processedReports.add(newReport);

        }

        //Write out each report to processed reports folder
        for (Report report: processedReports){
            ObjectMapper reportMapper = new ObjectMapper(); 

            String reportName = resultsDir + report.getFileInfo().get(reportKeyPath[0]).get(reportKeyPath[1]) + jsonFileExt;

            reportMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportName), report);
        }

    }

}