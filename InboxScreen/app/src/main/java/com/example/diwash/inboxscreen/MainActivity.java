package com.example.diwash.inboxscreen;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    final Context context = this;
    private MultiAutoCompleteTextView actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //components from main activity with just one button
        btn = (Button) findViewById(R.id.button);

        //Button Listener
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View prompt = li.inflate(R.layout.activity_form,null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setView(prompt);

                AlertDialog ad = dialog.create();
                dialog.setTitle("Invite Users");
                dialog.show();
            }
        });
    }
}
