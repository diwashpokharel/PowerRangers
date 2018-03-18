package com.example.diwash.inboxscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

public class Form extends AppCompatActivity {
    //multiple users in the same line
    private MultiAutoCompleteTextView actv;
    //choice of users. sample array.
    //private String[] users = new String[] {"aa11aa","aa22aa","aa11bb"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.9));

        actv = (MultiAutoCompleteTextView) findViewById(R.id.autoComplete);
        String[] users = new String[] {"aa11aa","aa22aa","aa11bb"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,users);
        actv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        actv.setThreshold(1);
        actv.setAdapter(adapter);
        //seperated by comma

    }
    public void addListeners() {
        Button cancelButton = (Button) findViewById (R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}