package ca.wa11eubrocku.studentconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//This class just used to test the pages. THIS IS TO BE DELETED*******************

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newQuestion = (Button) findViewById(R.id.addQuestionButton);
        Button registerCourses = (Button) findViewById(R.id.courseRegisterPageButton);
        Button manageCourses = (Button) findViewById(R.id.manageCoursesButton);
        Button newEvent = (Button) findViewById(R.id.addEventButton);
        Button registerHobbies = (Button) findViewById(R.id.hobbiesRegisterButton);
        newEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,NewEvent.class));
            }
        });

        newQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,NewQuestion.class));
            }
        });

        registerHobbies.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,HobbiesRegisterPage.class));
            }
        });

        registerCourses.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,CourseRegisterPage.class));
            }
        });

        //used to show what fragment will look like in drawer
        manageCourses.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,CoursesPage.class));
            }

        });




    }
}
