package ca.wa11eubrocku.studentconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//for register activity

public class CourseRegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_register_page);

        Button next = (Button) findViewById(R.id.nextButton);
        Button previous = (Button) findViewById(R.id.previousButton);

        EditText editCourse1, editCourse2, editCourse3, editCourse4, editCourse5, editCourse6;
        editCourse1 = (EditText) findViewById(R.id.editCourse1);
        editCourse2 = (EditText) findViewById(R.id.editCourse2);
        editCourse3 = (EditText) findViewById(R.id.editCourse3);
        editCourse4 = (EditText) findViewById(R.id.editCourse4);
        editCourse5 = (EditText) findViewById(R.id.editCourse5);
        editCourse6 = (EditText) findViewById(R.id.editCourse6);

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //go to next activity (register interested recreational page)
                //startActivity(new Intent(MainActivity.this,PopUpAdd.class));
            }
        });

        previous.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //go to previous activity
                finish();   //go to previous activity that called this register page
                //startActivity(new Intent(MainActivity.this,PopUpAdd.class));
            }
        });

    }//onCreate

}//CourseRegisterPage
