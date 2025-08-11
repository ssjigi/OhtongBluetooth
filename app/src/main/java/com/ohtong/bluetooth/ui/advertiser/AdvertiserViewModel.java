package com.ohtong.bluetooth.ui.advertiser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvertiserViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AdvertiserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}