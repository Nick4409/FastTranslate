package com.dekhtiarenko.nick.translateontap;

import android.content.Context;

/**
 * Created by Nick on 17.02.2016.
 */
public class TranslatorRequest {
    private String word;
    private Context context;

    TranslatorRequest(String word, Context context){
        this.word=word;
        this.context=context;
    }

    TranslatorRequest(){

    }
    public String getWord(){
        return word;
    }

    public Context getContext(){
        return context;
    }

    public void setWord(String word){
        this.word=word;
    }

    public void setContext(Context context){
        this.context=context;
    }
}
