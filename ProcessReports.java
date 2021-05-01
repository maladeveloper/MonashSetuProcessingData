import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import JavaSourceCode.Report;

public class ProcessReports {

    static private String reportsDir = "DownloadedReports/";

    public static void main(String[] args) {

        
        File[] reportFileNames  = new File(reportsDir).listFiles();

        ArrayList<Report> processedReports = new ArrayList<Report>();

        for(File curFile : reportFileNames){

            Report newReport = new Report(curFile.toString());

            processedReports.add(newReport);



        }

        System.out.print(processedReports);

    }


}