package com.ohtong.bluetooth.ui.device;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeviceInfoModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DeviceInfoModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Device Info Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}