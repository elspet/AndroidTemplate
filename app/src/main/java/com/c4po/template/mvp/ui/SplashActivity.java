package com.c4po.template.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.c4po.template.R;
import com.c4po.template.base.BaseActivity;
import com.c4po.template.global.application.MyApplication;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 欢迎页
 *
 * @author Lisa
 * @date 2018/8/21
 */
public class SplashActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    private static final int RC_CAMERA_AND_STORAGE = 111;

    /**
     * 在此处添加应用需要的权限
     */
    String[] reqPerms = {android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_splash);
        locationAndStorageTask();
    }


    private void enterApp() {
        // 设置数据库
        MyApplication application = (MyApplication) getApplication();
        application.setDatabase();
        Intent toFaceRecognition = new Intent(this, AdvertisementActivity.class);
        startActivity(toFaceRecognition);
        this.finish();
    }

    /**
     * 权限请求
     */
    @AfterPermissionGranted(RC_CAMERA_AND_STORAGE)
    public void locationAndStorageTask() {

        if (EasyPermissions.hasPermissions(this, reqPerms)) {
            // Have permissions, do the thing!
            Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show();
            enterApp();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, "应用需要存储空间、照相权限", RC_CAMERA_AND_STORAGE, reqPerms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (perms.size() == reqPerms.length) {
            enterApp();
        } else {
            locationAndStorageTask();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle(getString(R.string.app_request_permissions_dialog_title))
                    .setRationale(getString(R.string.app_request_permissions_dialog_content))
                    .setNegativeButton("取消")
                    .build().show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_CAMERA_AND_STORAGE) {
            locationAndStorageTask();
        }
    }
}
