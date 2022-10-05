package com.ohtong.bluetooth.core;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class BluetoothManager {
   private static final String TAG = "OH.BluetoothManager";

   private static BluetoothManager sInstance;

   private final Context mContext;

   private final android.bluetooth.BluetoothManager mBtManager;
   private final BluetoothAdapter mBtAdapter;

   private BluetoothManager(Context context) {
      mContext = context;
      mBtManager = (android.bluetooth.BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
      mBtAdapter = mBtManager.getAdapter();
   }

   public static BluetoothManager getInstance(Context context) {
      if (sInstance == null) {
         sInstance = new BluetoothManager(context);
      }

      return sInstance;
   }

   public boolean isOffloadedFilteringSupported() {
      if (checkBtAdapterIsNull())
         return false;

      return mBtAdapter.isOffloadedFilteringSupported();
   }

   public boolean isOffloadedScanBatchingSupported() {
      if (checkBtAdapterIsNull())
         return false;

      return mBtAdapter.isOffloadedScanBatchingSupported();
   }

   public boolean isMultipleAdvertisementSupported() {
      if (checkBtAdapterIsNull())
         return false;

      return mBtAdapter.isMultipleAdvertisementSupported();
   }

   public boolean isLe2MPhySupported() {
      if (checkBtAdapterIsNull())
         return false;

      return mBtAdapter.isLe2MPhySupported();
   }

   public boolean isLeCodedPhySupported() {
      if (checkBtAdapterIsNull())
         return false;

      return mBtAdapter.isLeCodedPhySupported();
   }

   public boolean isLePeriodicAdvertisingSupported() {
      if (checkBtAdapterIsNull())
         return false;

      return mBtAdapter.isLePeriodicAdvertisingSupported();
   }

   public boolean isLeExtendedAdvertisingSupported() {
      if (checkBtAdapterIsNull())
         return false;

      return mBtAdapter.isLeExtendedAdvertisingSupported();
   }

   public int getLeMaximumAdvertisingDataLength() {
      if (checkBtAdapterIsNull())
         return 0;

      return mBtAdapter.getLeMaximumAdvertisingDataLength();
   }

   public String getScanMode() throws SecurityException {
      return convertScanMode(mBtAdapter.getScanMode());
   }

   public String getAddress() throws SecurityException {
      return mBtAdapter.getAddress();
   }

   public String getName() throws SecurityException {
      return mBtAdapter.getName();
   }

   private String convertScanMode(int scanMode) {
      if (scanMode == BluetoothAdapter.SCAN_MODE_CONNECTABLE) {
         return "SCAN_MODE_CONNECTABLE";
      } else if (scanMode == BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
         return "SCAN_MODE_CONNECTABLE_DISCOVERABLE";
      } else {
         return "SCAN_MODE_NONE";
      }
   }

   private boolean checkBtAdapterIsNull() {
      if (mBtAdapter == null) {
         Log.e(TAG, "BluetoothAdapter is NULL!!!");
         return true;
      } else {
         return false;
      }
   }
}
