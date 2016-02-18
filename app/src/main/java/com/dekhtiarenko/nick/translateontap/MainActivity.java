package com.dekhtiarenko.nick.translateontap;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView mProgramStateTextView;
    Switch mProgramStateSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgramStateTextView = (TextView)findViewById(R.id.programStateTextView);
        mProgramStateSwitch = (Switch)findViewById(R.id.startProgramSwitch);




        if(mProgramStateSwitch.isChecked()) {
            mProgramStateTextView.setText(R.string.app_checked_switch);
            Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
            intent.putExtra("ID", true);
            sendBroadcast(intent);
        }
        else {
            mProgramStateTextView.setText(R.string.app_unchecked_switch);
            Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
            intent.putExtra("ID", false);
            sendBroadcast(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClickStartProgramSwitch(View view) {
        if (mProgramStateSwitch.isChecked()) {
            mProgramStateTextView.setText(R.string.app_checked_switch);
            Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
            intent.putExtra("ID", true);
            sendBroadcast(intent);
        }
        else {
            mProgramStateTextView.setText(R.string.app_unchecked_switch);
            Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
            intent.putExtra("ID", false);
            sendBroadcast(intent);
        }
    }



//    private class Helper extends AsyncTask<Void, Void, Void> {
//        String toTranslate = mClipboardManager.getText().toString();
//        String translatedText = "";
//        @Override
//        protected Void doInBackground(Void... params) {
//            // TODO Auto-generated method stub
//            try {
//                translatedText = Translate.execute(toTranslate, Language.AUTO_DETECT, Language.UKRAINIAN);
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                translatedText = e.toString();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            // TODO Auto-generated method stub
//            mToast.makeText(getApplicationContext(), translatedText, Toast.LENGTH_LONG).show();
//            super.onPostExecute(result);
//        }
//
//    }



}


