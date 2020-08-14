package com.lieying.lydemo3;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.lieying.lydemo3.bean.SpellRequest;
import com.lieying.lydemo3.config.Config;
import com.lieying.lydemo3.ui.home.HomeFragment;
import com.lieying.lydemo3.ui.hotbuy.HotBuyFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
@Route(path = "/lydemo3/MainActivity")
public class MainActivity extends AppCompatActivity {
    BottomSheetDialog bottomSheetDialog;
    BottomSheetBehavior mBehavior;
    private MaterialDialog mLoadingDialog;

    private MainViewModel model;

    private final String mBucket = Config.BUCKET_NAME;
    private static final String FILE_DIR = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + File.separator + "download/";
    //负责所有界面更新


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        getSupportActionBar().hide();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_nearly, R.id.navigation_zhibo,R.id.navigation_hot_buy,R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


//        Disposable disposable = NetWorkManager.getRequest().getSpellData(getSpellRequest()).compose(ResponseTransformer.handleResult()).compose(SchedulerProvider.getInstance().applySchedulers()).subscribe(data -> {
//            Log.e("response",data.toString());
//        });

    }
    private void showBottomSheetDialog() {
        View view = View.inflate(MainActivity.this,R.layout.dialog_bottomsheet,null);
        bottomSheetDialog = new BottomSheetDialog(MainActivity.this,R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View)view.getParent());
        mBehavior.setPeekHeight(getPeekHeight());
        bottomSheetDialog.show();
        TextView tv_close = view.findViewById(R.id.tv_close);
        LinearLayout ll_video = view.findViewById(R.id.ll_xiaoshipin);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        ll_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseVideo();
            }
        });
    }
    /**
     * 弹窗高度，默认为屏幕高度的四分之三
     * 子类可重写该方法返回peekHeight
     *
     * @return height
     */
    protected int getPeekHeight() {
        Display display = getWindowManager().getDefaultDisplay();
        int peekHeight = display.getHeight()*2/3;
        //设置弹窗高度为屏幕高度的3/4
        return peekHeight;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm = getSupportFragmentManager();
            switch (item.getItemId()){
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_zhibo:
                    showBottomSheetDialog();
                    return true;
                case R.id.navigation_hot_buy:
                    Fragment hotBuyFragment = new HotBuyFragment();
                    fm.beginTransaction().replace(R.id.nav_host_fragment,hotBuyFragment).commit();
                    return true;
                case R.id.navigation_nearly:
                    return true;
                case R.id.navigation_home:
                    Fragment homeFragment = new HomeFragment();
                    fm.beginTransaction().replace(R.id.nav_host_fragment,homeFragment).commit();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void chooseVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 选取图片的返回值
            //
            if (resultCode == RESULT_OK && null!= data) {

                copyLocalFile(data);
        }
         super.onActivityResult(requestCode, resultCode, data);
     }
    private void copyLocalFile(Intent data) {
        String zipFile = "wangwang.mp4";
        String filePath = this.FILE_DIR + zipFile;
        Uri selectedVideo = data.getData();
        Log.e("filedir",this.FILE_DIR);
        try {
            File path = new File(this.FILE_DIR);
            File file = new File(filePath);
            if (!path.exists()) {
                OSSLog.logDebug("MULTIPART_UPLOAD", "Create the path:" + path.getAbsolutePath());
                path.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
                Toast.makeText(this,"进入文件复制",Toast.LENGTH_LONG).show();
                OSSLog.logDebug("MULTIPART_UPLOAD", "create : " + file.getAbsolutePath());
            } else {
                return;
            }

            InputStream input = this.getContentResolver().openInputStream(selectedVideo);
            OSSLog.logDebug("input.available() : " + input.available());
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[500 * 1024];
            int byteCount = 0;
            int totalReadByte = 0;
            while ((byteCount = input.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                totalReadByte += byteCount;
            }
            OSSLog.logDebug("totalReadByte : " + totalReadByte);
            fos.flush();//刷新缓冲区
            input.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     private OSS initOSSClient(){
         OSSCredentialProvider credentialProvider = new OSSAuthCredentialsProvider(Config.STS_SERVER_URL);
         String endPoint = "oss-cn-beijing.aliyuncs.com";
         ClientConfiguration conf = new ClientConfiguration();
         conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
         conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
         conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
         conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
         OSS oss = new OSSClient(getApplicationContext(),endPoint , credentialProvider, conf);
         OSSLog.enableLog();
         return oss;
     }

     private void upLoadFile(String bucketName,String objectKey,String uploadFilePath){
       //  GetObjectRequest get = new GetObjectRequest(bucketName,objectKey,uploadFilePath);

     }

     private SpellRequest getSpellRequest(){
         SpellRequest request = new SpellRequest();
         request.setExpand1("string");
         request.setGroupType(0);
         request.setPageSize(0);
         request.setPageStart(0);
         request.setProductName("string");
         request.setSpellGroupId(0);
         request.setType(0);
         request.setWouldRecomHome(0);
         return request;
     }
//    private void copyLocalFile() {
//        String zipFile = "wangwang.zip";
//        String filePath = this.FILE_DIR + zipFile;
//        try {
//            File path = new File(this.FILE_DIR);
//            File file = new File(filePath);
//            if (!path.exists()) {
//                OSSLog.logDebug("MULTIPART_UPLOAD", "Create the path:" + path.getAbsolutePath());
//                path.mkdir();
//            }
//            if (!file.exists()) {
//                file.createNewFile();
//                OSSLog.logDebug("MULTIPART_UPLOAD", "create : " + file.getAbsolutePath());
//            } else {
//                return;
//            }
//
//
//            InputStream input = getBaseContext().getAssets().open(zipFile);
//
//            OSSLog.logDebug("input.available() : " + input.available());
//
//            FileOutputStream fos = new FileOutputStream(file);
//            byte[] buffer = new byte[500 * 1024];
//            int byteCount = 0;
//            int totalReadByte = 0;
//            while ((byteCount = input.read(buffer)) != -1) {//循环从输入流读取 buffer字节
//                fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
//                totalReadByte += byteCount;
//            }
//            OSSLog.logDebug("totalReadByte : " + totalReadByte);
//            fos.flush();//刷新缓冲区
//            input.close();
//            fos.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    private void initDialog() {
        mLoadingDialog = new MaterialDialog.Builder(MainActivity.this)
                .content("上传中...")
                .progress(true, 0)
                .build();
    }

    private void showLoading() {
        if (mLoadingDialog != null && !mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    private void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }


}
