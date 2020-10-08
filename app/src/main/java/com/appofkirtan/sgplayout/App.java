package com.appofkirtan.sgplayout;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

        //default value
        Map<String,Object>defaultValue = new HashMap<>();
        defaultValue.put(UpdateHelperClass.KEY_UPDATE_ENABLE,false);
        defaultValue.put(UpdateHelperClass.KEY_UPDATE_VERSION,"1.0");
        defaultValue.put(UpdateHelperClass.KEY_UPDATE_URL,"https://webofkirtan.github.io/firstpage/firstpage.html");

        remoteConfig.setDefaults(defaultValue);
        remoteConfig.fetch(2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    remoteConfig.activateFetched();
                }
            }
        }); // fetch data from firebase every 5 seconds , in real world you need make it to 1min, 5min;

    }
}
