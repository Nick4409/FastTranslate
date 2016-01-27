package com.dekhtiarenko.nick.translateontap;


import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;




public class MainActivity extends AppCompatActivity {
    TextView mProgramStateTextView;
    Switch mProgramStateSwitch;
    ClipboardManager mClipboardManager;
    String mTranslation="Нічого не відбулося....";

    OnPrimaryClipChangedListener mClipboardListener =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgramStateTextView = (TextView)findViewById(R.id.programStateTextView);
        mProgramStateSwitch = (Switch)findViewById(R.id.startProgramSwitch);
        mClipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        Translate.setClientId("translateontap");
        Translate.setClientSecret("1Ywd2fxYWLMIqAVUnjmVZTS25DWWeCrxeFsAEdF9idQ=");
        OpenDictionaryAPI opa =new OpenDictionaryAPI(getApplicationContext());

        if(mProgramStateSwitch.isChecked()) {
            mProgramStateTextView.setText(R.string.app_checked_switch);
            mClipboardManager.addPrimaryClipChangedListener(mClipboardListener);
        }
        else {
            mProgramStateTextView.setText(R.string.app_unchecked_switch);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getTranslation(String toTranslate){
        try {
            return   Translate.execute(toTranslate, Language.AUTO_DETECT, Language.UKRAINIAN);
        } catch (Exception e) {
            return e.toString();
        }
    }

    public void onClickStartProgramSwitch(View view) {
        if (mProgramStateSwitch.isChecked()) {
            mProgramStateTextView.setText(R.string.app_checked_switch);
            mClipboardListener=getClipboardListener();
            mClipboardManager.addPrimaryClipChangedListener(mClipboardListener);
        }
        else {
            mProgramStateTextView.setText(R.string.app_unchecked_switch);
            mClipboardManager.removePrimaryClipChangedListener(mClipboardListener);
            mClipboardListener=null;
        }
    }

    public OnPrimaryClipChangedListener getClipboardListener(){
        return new OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                new Helper().execute();
            }
        };
    }


    private class Helper extends AsyncTask<Void, Void, Void> {
        String toTranslate = mClipboardManager.getText().toString();
        String translatedText = "";
        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                translatedText = getTranslation(toTranslate);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                translatedText = e.toString();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            Toast.makeText(getApplicationContext(), translatedText, Toast.LENGTH_LONG).show();
            super.onPostExecute(result);
        }

    }



}


