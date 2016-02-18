package com.dekhtiarenko.nick.translateontap;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.memetix.mst.translate.Translate;

public class ServiceListener extends Service {
    ClipboardManager mClipboardManager=null;


   
    public void onCreate() {
        Toast.makeText(this,"Service created", Toast.LENGTH_SHORT).show();
        mClipboardManager=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        mClipboardManager.addPrimaryClipChangedListener(getClipboardListener());
        Translate.setClientId("translateontap");
        Translate.setClientSecret("1Ywd2fxYWLMIqAVUnjmVZTS25DWWeCrxeFsAEdF9idQ=");
        super.onCreate();

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
       Toast.makeText(this,"Service started", Toast.LENGTH_SHORT).show();
       return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {

        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show();
        super.onDestroy();

    }

    public IBinder onBind(Intent intent) {

        return null;
    }

    public ClipboardManager.OnPrimaryClipChangedListener getClipboardListener(){
       return new ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {
                    TranslatorRequest translatorRequest = new TranslatorRequest(mClipboardManager.getText().toString(), getApplicationContext());
                    TranslateWithMicrosoft translateWithMicrosoft = new TranslateWithMicrosoft();
                    translateWithMicrosoft.execute(translatorRequest);


                }
            };
    }



}
