package com.appofkirtan.sgplayout;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import org.w3c.dom.Text;

public class UpdateHelperClass {

    public static String KEY_UPDATE_ENABLE = "isUpdate";
    public static String KEY_UPDATE_VERSION = "version";
    public static String KEY_UPDATE_URL = "update_url";
   public static String currtenversion;
    public static String appVersion;
   public static  int temp = 1;


    public interface OnUpdateCheckListener{
        void onUpdateCheckListener(String urlApp);
    }

    public static Builder with(Context context){
        return new Builder(context);
    }
    private OnUpdateCheckListener onUpdateCheckListener;
    private Context context;

    public UpdateHelperClass(OnUpdateCheckListener onUpdateCheckListener, Context context) {
        this.onUpdateCheckListener = onUpdateCheckListener;
        this.context = context;
    }
    public void check(){

        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        if (remoteConfig.getBoolean(KEY_UPDATE_ENABLE)){
            currtenversion = remoteConfig.getString(KEY_UPDATE_VERSION);
            appVersion = getAppVersion(context);
            String UpdateURL = remoteConfig.getString(KEY_UPDATE_URL);

            if (!TextUtils.equals(currtenversion,appVersion)  && onUpdateCheckListener!=null ){
                onUpdateCheckListener.onUpdateCheckListener(UpdateURL);
            }
            else {
                temp=0;
            }
        }
    }
//    !TextUtils.equals(currtenversion,appVersion)  && onUpdateCheckListener!=null

    private String getAppVersion(Context context) {
        String result ="";
        try{
            result = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName;
            result = result.replaceAll("[a-zA-Z] |-","");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static  class Builder{

        private Context context;
        private OnUpdateCheckListener onUpdateCheckListener;

        public  Builder(Context context){
            this.context = context;

        }
        public  Builder onUpdateCheck(OnUpdateCheckListener onUpdateCheckListener){
            this.onUpdateCheckListener = onUpdateCheckListener;
            return this;
        }
        public UpdateHelperClass build(){
            return  new UpdateHelperClass(onUpdateCheckListener,context);
        }
        public UpdateHelperClass check(){
            UpdateHelperClass updateHelperClass = build();
            updateHelperClass.check();
            return updateHelperClass;
        }
    }
}
