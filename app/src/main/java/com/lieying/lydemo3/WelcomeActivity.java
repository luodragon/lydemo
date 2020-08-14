package com.lieying.lydemo3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
@Route(path = "/lydemo3/WelcomeActivity")
public class WelcomeActivity extends AppCompatActivity {
    private Intent intent;
    String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.ACCESS_WIFI_STATE};
    List<String> mPermissionList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ARouter.getInstance().inject(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        checkPermission();


    }

    //授权服务
    private void checkPermission() {
        for (int i = 0; i < permission.length; i++) {
            if (ContextCompat.checkSelfPermission(WelcomeActivity.this, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permission[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            Toast.makeText(WelcomeActivity.this, "已经授权", Toast.LENGTH_LONG).show();
            ARouter.getInstance().build("/lydemo3/MainActivity").navigation();
            this.finish();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(WelcomeActivity.this, permissions, 1);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {//用户拒绝授权的权限
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(WelcomeActivity.this, permissions[i]);
                    if (showRequestPermission) {
                        Toast.makeText(WelcomeActivity.this, "权限未申请", Toast.LENGTH_LONG).show();
                    }

                } else {//用户同意的权限
                   // startActivity(intent);
                    ARouter.getInstance().build("/lydemo3/MainActivity").navigation();
                    this.finish();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
