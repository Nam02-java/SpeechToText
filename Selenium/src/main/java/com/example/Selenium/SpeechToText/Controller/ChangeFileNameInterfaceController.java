package com.example.Selenium.SpeechToText.Controller;

import com.example.Selenium.SpeechToText.Model.DataStoreModel;

import java.util.Map;

public interface ChangeFileNameInterfaceController {

    public abstract void changeFileNameWithDriver(DataStoreModel dataStoreModelAllData, Map<String, String> params);

    public abstract void changeFileNameWithTab(int count);

}
