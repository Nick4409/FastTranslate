package com.dekhtiarenko.nick.translateontap;

import android.os.AsyncTask;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by Nick on 16.02.2016.
 */
public class TranslateWithMicrosoft extends AsyncTask<TranslatorRequest, Integer, TranslatorRequest> {
    @Override
    protected TranslatorRequest doInBackground(TranslatorRequest... params) {
        TranslatorRequest translatorRequest= new TranslatorRequest();
        translatorRequest.setContext(params[0].getContext());
        try {
            translatorRequest.setWord(Translate.execute(params[0].getWord(), Language.AUTO_DETECT, Language.UKRAINIAN));
        } catch (Exception e) {
            params[0].setWord(e.getMessage());
        }
        return translatorRequest;
    }


//    private String toTranslate=null;
//    @Override

    @Override
    protected void onPostExecute(TranslatorRequest translatorRequest) {
        Toast.makeText(translatorRequest.getContext(), translatorRequest.getWord(), Toast.LENGTH_LONG).show();
        super.onPostExecute(translatorRequest);
    }
//    protected String doInBackground(String... params) {
//        String translation= null;
//        try {
//            translation = Translate.execute(params[0].toString(), Language.AUTO_DETECT, Language.UKRAINIAN);
//        } catch (Exception e) {
//            translation = e.getMessage();
//        }
//
//       return translation;
//    }
//
//
//    @Override
//    protected void onPostExecute(String translation) {
//        Toast.makeText(param, translation, Toast.LENGTH_LONG).show();
//        super.onPostExecute(context);
//
//    }
}
