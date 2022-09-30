package com.ohtong.bluetooth.ui.device;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.ohtong.bluetooth.core.BluetoothManager;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeviceInfoModel extends AndroidViewModel {
    private static final String TAG = "OT.DeviceInfoModel";
    private final MutableLiveData<String> mText;
    private BluetoothManager mBtAdapter;

    // Hardware data
    private final MutableLiveData<String> mDeviceName;
    private final MutableLiveData<String> mAndroidversion;
    private final MutableLiveData<String> mManufacturer;
    private final MutableLiveData<String> mModel;
    private final MutableLiveData<String> mBuildVersion;
    private final MutableLiveData<String> mBoard;
    private final MutableLiveData<String> mProduct;

    // Bluetooth common data
    private final MutableLiveData<String> mBtName;
    private final MutableLiveData<String> mBtAddress;
    private final MutableLiveData<String> mScanMode;

    // Bluetooth Low Energy data
    private final MutableLiveData<String> mOffloadedFiltering;
    private final MutableLiveData<String> mOffloadedScan;
    private final MutableLiveData<String> mMultiAdvertisement;
    private final MutableLiveData<String> mLe2MPhy;
    private final MutableLiveData<String> mLeCoded;
    private final MutableLiveData<String> mLePeriodic;
    private final MutableLiveData<String> mLeExtended;
    private final MutableLiveData<String> mLeMaxAdvertising;

    public DeviceInfoModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is Device Info Fragment");
        mBtAdapter = BluetoothManager.getInstance(application);

        mDeviceName = new MutableLiveData<>();
        mAndroidversion = new MutableLiveData<>();
        mManufacturer = new MutableLiveData<>();
        mModel = new MutableLiveData<>();
        mBuildVersion = new MutableLiveData<>();
        mBoard = new MutableLiveData<>();
        mProduct = new MutableLiveData<>();

        mBtName = new MutableLiveData<>();
        mBtAddress = new MutableLiveData<>();
        mScanMode = new MutableLiveData<>();

        mOffloadedFiltering = new MutableLiveData<>();
        mOffloadedScan = new MutableLiveData<>();
        mMultiAdvertisement = new MutableLiveData<>();
        mLe2MPhy = new MutableLiveData<>();
        mLeCoded = new MutableLiveData<>();
        mLePeriodic = new MutableLiveData<>();
        mLeExtended = new MutableLiveData<>();
        mLeMaxAdvertising = new MutableLiveData<>();
    }

    public LiveData<String> updateDeviceName() {
        mDeviceName.setValue(Build.DEVICE);
        return mDeviceName;
    }

    public LiveData<String> updateAndroidversion() {
        mAndroidversion.setValue(Build.VERSION.RELEASE);
        return mAndroidversion;
    }

    public LiveData<String> updateManufacturer() {
        mManufacturer.setValue(Build.MANUFACTURER);
        return mManufacturer;
    }

    public LiveData<String> updateModel() {
        mModel.setValue(Build.MODEL);
        return mModel;
    }

    public LiveData<String> updateBuildVersion() {
        mBuildVersion.setValue(Build.VERSION.INCREMENTAL);
        return mBuildVersion;
    }

    public LiveData<String> updateBoard() {
        mBoard.setValue(Build.BOARD);
        return mBoard;
    }

    public LiveData<String> updateProduct() {
        mProduct.setValue(Build.PRODUCT);
        return mProduct;
    }

    public LiveData<String> updateScanMode() {
        mScanMode.setValue(mBtAdapter.getScanMode());
        return mScanMode;
    }

    public LiveData<String> updateBtName() {
        mBtName.setValue(mBtAdapter.getName());
        return mBtName;
    }

    public LiveData<String> updateBtAddress() {
        mBtAddress.setValue(mBtAdapter.getAddress());
        return mBtAddress;
    }

    public MutableLiveData<String> getOffloadedFilteringSupported() {
        String supported = mBtAdapter.isOffloadedFilteringSupported() ? "YES" : "NO";
        mOffloadedFiltering.setValue(supported);
        return mOffloadedFiltering;
    }

    public MutableLiveData<String> getOffloadedScanBatchingSupported() {
        String supported = mBtAdapter.isOffloadedScanBatchingSupported() ? "YES" : "NO";
        mOffloadedScan.setValue(supported);
        return mOffloadedScan;
    }

    public MutableLiveData<String> getMultipleAdvertisementSupported() {
        String supported = mBtAdapter.isMultipleAdvertisementSupported() ? "YES" : "NO";
        mMultiAdvertisement.setValue(supported);
        return mMultiAdvertisement;
    }

    public MutableLiveData<String> getLe2MPhySupported() {
        String supported = mBtAdapter.isLe2MPhySupported() ? "YES" : "NO";
        mLe2MPhy.setValue(supported);
        return mLe2MPhy;
    }

    public MutableLiveData<String> getLeCodedPhySupported() {
        String supported = mBtAdapter.isLeCodedPhySupported() ? "YES" : "NO";
        mLeCoded.setValue(supported);
        return mLeCoded;
    }

    public MutableLiveData<String> getLePeriodicAdvertisingSupported() {
        String supported = mBtAdapter.isLePeriodicAdvertisingSupported() ? "YES" : "NO";
        mLePeriodic.setValue(supported);
        return mLePeriodic;
    }

    public MutableLiveData<String> getLeExtendedAdvertisingSupported() {
        String supported = mBtAdapter.isLeExtendedAdvertisingSupported() ? "YES" : "NO";
        mLeExtended.setValue(supported);
        return mLeExtended;
    }

    public MutableLiveData<String> getLeMaximumAdvertisingDataLength() {
        String dataLength = String.valueOf(mBtAdapter.getLeMaximumAdvertisingDataLength());
        mLeMaxAdvertising.setValue(dataLength);
        return mLeMaxAdvertising;
    }

    public void test() {
        WindowManager windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
    }
}