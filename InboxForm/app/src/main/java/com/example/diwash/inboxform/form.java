package com.example.diwash.inboxform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

public class form extends AppCompatActivity {
    //multiple users in the same line
    private MultiAutoCompleteTextView actv;
    private String[] users = new String[] {"aa11aa","aa22aa","aa11bb"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        actv = (MultiAutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        String[] users = new String[] {"aa11aa","aa22aa","aa11bb"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,users);
        actv.setAdapter(adapter);
        actv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
