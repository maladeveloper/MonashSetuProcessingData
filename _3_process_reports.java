import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import JavaSourceCode.WorkerThread;

public class _3_process_reports {

    static private int numberOfThreads = 10;
    static private String reportsDir = "DownloadedReports/";


    public static void main(String[] args) throws IOException {

        
        File[] reportFileNames  = new File(reportsDir).listFiles();
        ArrayList<ArrayList<File>> splitFiles = new ArrayList<ArrayList<File>>();
        ArrayList<File> tempFiles = new ArrayList<File>();
        int count = 0;

        for (int i=0; i < reportFileNames.length; i++){

            count ++; 

            if(count == (reportFileNames.length / numberOfThreads)){

                count = 0;splitFiles.add(tempFiles);

                tempFiles = new ArrayList<File>();
            }
            
            tempFiles.add(reportFileNames[i]);
        }
        splitFiles.add(tempFiles);

        System.out.print("Processing " + String.valueOf(reportFileNames.length) +" reports via " + String.valueOf(numberOfThreads)+ " threads...\n");

        //Call the threads
        for (int i=0; i<numberOfThreads; i++){

            WorkerThread workerThread = new WorkerThread(i+1,splitFiles.get(i));

            System.out.print("Starting new worker thread: " + String.valueOf(i+1)+ "\n");

            workerThread.start();
        }
        

    }

}