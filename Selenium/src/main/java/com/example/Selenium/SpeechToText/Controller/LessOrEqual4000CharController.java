package com.example.Selenium.SpeechToText.Controller;

import com.example.Selenium.SpeechToText.Model.CSVStoreModel;
import com.example.Selenium.SpeechToText.Model.DataStoreModel;
import com.example.Selenium.SpeechToText.Model.TelegramDataStoreModel;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


public class LessOrEqual4000CharController extends InitializationDriverController {


    public LessOrEqual4000CharController(CountDownLatch countDownLatch, TelegramDataStoreModel telegramDataStoreModel, DataStoreModel dataStoreModel, WebDriver driver, String textFromTextColumnCsvFile, CSVStoreModel textCsvStoreModel, CSVStoreModel voiceCsvStoreModel, CSVStoreModel fileNameCsvStoreModel) {
        super(telegramDataStoreModel, dataStoreModel, driver, textFromTextColumnCsvFile, textCsvStoreModel, voiceCsvStoreModel, fileNameCsvStoreModel);
        this.dataStoreModel.countDownLatch = countDownLatch;
    }

    @Override
    public void changeFileNameWithDriver(DataStoreModel dataStoreModel, Map<String, String> params) {
        File folder = new File("E:\\New folder\\");
        File[] files = folder.listFiles();

        // Sort by time in descending order
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        if (dataStoreModel.getStatusEnumSet().contains(EnumController.ONE_DRIVERS)) {

            // Print out information file sorted by year - month - day - hour - minute - second
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long lastModifiedTimestamp = files[0].lastModified();
            Date lastModifiedDate = new Date(lastModifiedTimestamp);
            String formattedTime = dateFormat.format(lastModifiedDate);
            System.out.println("File: " + files[0].getName() + " - Download time : " + formattedTime);

            File latestFile = files[0];
            String newFileName = params.get("FileName") + ".mp3";
            newFileName = newFileName.replaceAll("[\\\\/:*?\"<>|]", "_"); // Replace invalid characters with underscore "_"
            File newFile = new File(folder, newFileName);
            System.out.println(newFile);
            latestFile.renameTo(newFile);


        } else if (dataStoreModel.getStatusEnumSet().contains(EnumController.TWO_DRIVERS)) {

            // Print out information file sorted by year - month - day - hour - minute - second
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i <= 1; i++) {
                long lastModifiedTimestamp = files[i].lastModified();
                Date lastModifiedDate = new Date(lastModifiedTimestamp);
                String formattedTime = dateFormat.format(lastModifiedDate);
                System.out.println("File: " + files[i].getName() + " - Download time : " + formattedTime);

                File latestFile = files[i];

                String newFileName = params.get("FileName") + ".mp3";

                if (i == 1) {
                    newFileName = params.get("FileName") + " (1).mp3";
                }

                newFileName = newFileName.replaceAll("[\\\\/:*?\"<>|]", "_"); // Replace invalid characters with underscore "_"
                File newFile = new File(folder, newFileName);
                System.out.println(newFile);
                latestFile.renameTo(newFile);
            }
        }
    }

    @Override
    public void changeFileNameWithTab(int count) {

    }
}

