package com.androidaop.demo;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by innost on 15-10-24.
 */
public class SecurityCheckManager {
    private Context mContext;
    private SecurityCheckManager(Context context){
        mContext = context;
    }

    private static SecurityCheckManager gSecurityCheckManager;
    static public SecurityCheckManager createInstance(Context context) {
        if (gSecurityCheckManager == null) {
            gSecurityCheckManager = new SecurityCheckManager(context);
        }
        return gSecurityCheckManager;

    }
    static public SecurityCheckManager getInstanc(){
        return gSecurityCheckManager;

    }

    public boolean checkPermission(String permissionName){
        PackageManager pkm = mContext.getPackageManager();
        return pkm.checkPermission(permissionName,mContext.getPackageName())== PackageManager.PERMISSION_GRANTED;
    }
}
