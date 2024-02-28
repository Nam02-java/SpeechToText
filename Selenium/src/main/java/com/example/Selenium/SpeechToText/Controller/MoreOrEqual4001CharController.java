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

public class MoreOrEqual4001CharController extends InitializationDriverController {
    public MoreOrEqual4001CharController(CountDownLatch countDownLatch, TelegramDataStoreModel telegramDataStoreModel, DataStoreModel dataStoreModel, WebDriver driver, String textFromTextColumnCsvFile, CSVStoreModel textCsvStoreModel, CSVStoreModel voiceCsvStoreModel, CSVStoreModel fileNameCsvStoreModel) {
        super(telegramDataStoreModel, dataStoreModel, driver, textFromTextColumnCsvFile, textCsvStoreModel, voiceCsvStoreModel, fileNameCsvStoreModel);
        this.dataStoreModel.countDownLatch = countDownLatch;
    }


    @Override
    public void changeFileNameWithDriver(DataStoreModel dataStoreModelAllData, Map<String, String> params) {

    }

    @Override
    public void changeFileNameWithTab(int count) {
        File folder = new File("E:\\New folder\\");
        File[] files = folder.listFiles();

        // Sort by time in descending order
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        File latestFile = files[0];

        // Print out information file sorted by year - month - day - hour - minute - second
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lastModifiedTimestamp = files[0].lastModified();
        Date lastModifiedDate = new Date(lastModifiedTimestamp);
        String formattedTime = dateFormat.format(lastModifiedDate);
        System.out.println("File: " + files[0].getName() + " - Download time : " + formattedTime);

        if (!textCsvStoreModel.isFlag()) {
            if (count == 0) {
                String newFileName = dataStoreModel.getParams().get("FileName") + ".mp3";
                newFileName = newFileName.replaceAll("[\\\\/:*?\"<>|]", "_"); // Replace invalid characters with underscore "_"
                File newFile = new File(folder, newFileName);
                latestFile.renameTo(newFile);
            } else if (count != 0) {
                String newFileName = dataStoreModel.getParams().get("FileName") + " " + "(" + count + ")" + ".mp3";
                newFileName = newFileName.replaceAll("[\\\\/:*?\"<>|]", "_"); // Replace invalid characters with underscore "_"

                File newFile = new File(folder, newFileName);
                latestFile.renameTo(newFile);
            }
        }
    }
}
