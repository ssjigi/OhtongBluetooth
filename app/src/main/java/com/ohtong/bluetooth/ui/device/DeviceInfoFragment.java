package com.ohtong.bluetooth.ui.device;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ohtong.bluetooth.databinding.FragmentDeviceBinding;

public class DeviceInfoFragment extends Fragment {
    private static final String TAG = "OT.DeviceInfoFragment";

    private FragmentDeviceBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(
                getActivity().getApplication());
        DeviceInfoModel deviceInfoModel = new ViewModelProvider(this, factory).get(DeviceInfoModel.class);

        binding = FragmentDeviceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        deviceInfoModel.updateDeviceName().observe(getViewLifecycleOwner(), binding.deviceName::setText);
        deviceInfoModel.updateAndroidversion().observe(getViewLifecycleOwner(), binding.androidVersion::setText);
        deviceInfoModel.updateManufacturer().observe(getViewLifecycleOwner(), binding.manufacturer::setText);
        deviceInfoModel.updateModel().observe(getViewLifecycleOwner(), binding.model::setText);
        deviceInfoModel.updateBuildVersion().observe(getViewLifecycleOwner(), binding.buildVersion::setText);
        deviceInfoModel.updateBoard().observe(getViewLifecycleOwner(), binding.board::setText);
        deviceInfoModel.updateProduct().observe(getViewLifecycleOwner(), binding.product::setText);

        // FIXME: 2022-09-30 
        ActivityResultLauncher<String> permissionLauncher = registerForActivityResult(
                        new ActivityResultContracts.RequestPermission(), isGranted -> {
                            if (isGranted) {
                                Log.d(TAG, "onCreateView: yes");
                                deviceInfoModel.updateScanMode().observe(getViewLifecycleOwner(), binding.scanCode::setText);
                                deviceInfoModel.updateBtName().observe(getViewLifecycleOwner(), binding.btDeviceName::setText);
                                deviceInfoModel.updateBtAddress().observe(getViewLifecycleOwner(), binding.btAddress::setText);
                            } else {
                                Log.d(TAG, "onCreateView: false");
                            }
                        }
        );

        if (ActivityCompat.checkSelfPermission(
                getContext(), Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreateView: launch");
            permissionLauncher.launch(Manifest.permission.BLUETOOTH_SCAN);
        } else {
            deviceInfoModel.updateScanMode().observe(getViewLifecycleOwner(), binding.scanCode::setText);
            deviceInfoModel.updateBtName().observe(getViewLifecycleOwner(), binding.btDeviceName::setText);
            deviceInfoModel.updateBtAddress().observe(getViewLifecycleOwner(), binding.btAddress::setText);
        }
        
        deviceInfoModel.getOffloadedFilteringSupported().observe(
                getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        updateTextAndColor(binding.offloadedFiltering, s);
                    }
                });
        deviceInfoModel.getOffloadedScanBatchingSupported().observe(
                getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        updateTextAndColor(binding.offloadedScan, s);
                    }
                });
        deviceInfoModel.getMultipleAdvertisementSupported().observe(
                getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        updateTextAndColor(binding.multipleAdvertisement, s);
                    }
                });
        deviceInfoModel.getLe2MPhySupported().observe(
                getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        updateTextAndColor(binding.highSpeed, s);
                    }
                });
        deviceInfoModel.getLeCodedPhySupported().observe(
                getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        updateTextAndColor(binding.longRange, s);
                    }
                });
        deviceInfoModel.getLePeriodicAdvertisingSupported().observe(
                getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        updateTextAndColor(binding.periodicAdvertisement, s);
                    }
                });
        deviceInfoModel.getLeExtendedAdvertisingSupported().observe(
                getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        updateTextAndColor(binding.extentedAdvertisement, s);
                    }
                });
        deviceInfoModel.getLeMaximumAdvertisingDataLength().observe(
                getViewLifecycleOwner(), binding.maximumAdvertisement::setText);
        return root;
    }

    private void updateTextAndColor(TextView view, String s) {
        view.setText(s);
        view.setTextColor(
                s.equalsIgnoreCase("YES") ? Color.GREEN : Color.RED);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}