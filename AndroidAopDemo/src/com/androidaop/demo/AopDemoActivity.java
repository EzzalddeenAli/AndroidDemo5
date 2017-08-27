package com.androidaop.demo;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.androidaop.demo.SecurityCheckAnnotation;

public class AopDemoActivity extends Activity {
    private static final String TAG = "AopDemoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        Log.e(TAG,"onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        checkPhoneState();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
    @SecurityCheckAnnotation(declaredPermission="android.permission.READ_PHONE_STATE")
    public void checkPhoneState(){
        if(checkPermission("android.permission.READ_PHONE_STATE") == false){
            Log.e(TAG,"have no permission to read phone state");
            return;
        }
        Log.e(TAG,"Read Phone State succeed");
        return;
    }

    private boolean checkPermission(String permissionName){
        try{
            PackageManager pm = getPackageManager();
            int nret = pm.checkPermission(permissionName,getPackageName());
            return nret == PackageManager.PERMISSION_GRANTED;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
