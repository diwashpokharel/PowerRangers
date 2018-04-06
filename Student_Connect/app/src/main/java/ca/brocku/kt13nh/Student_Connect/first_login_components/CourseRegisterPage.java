package ca.brocku.kt13nh.Student_Connect.first_login_components;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ca.brocku.kt13nh.Student_Connect.R;

/**
 * This is for the registering courses page(activity), only seen one time per user.
 */

public class CourseRegisterPage extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference table_courses = mFirebaseDatabase.getReference().child("Courses");
    private Map<String, String> coursesMapping = new HashMap<String, String>();
    private ArrayAdapter<String> courseListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_register_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Student Connect");
        setSupportActionBar(toolbar);
        setListeners();
        getDatabaseInfo();


    }//onCreate

    //disable back button
    @Override
    public void onBackPressed() {
    }

    //initialize database info into the courses arraylist.
    private void getDatabaseInfo() {
        table_courses.addListenerForSingleValueEvent(new ValueEventListener() {
            //add courses into the courses arraylist
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String courseID = snapshot.getKey().toString();
                    String courseName = snapshot.child("CourseName").getValue().toString();
                    coursesMapping.put(courseID, courseName);
                    courseListAdapter.add(courseID);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //set the button listeners for next and cancel
    private void setListeners() {
        Button next = (Button) findViewById(R.id.nextButton);
        Button previous = (Button) findViewById(R.id.previousButton);

        courseListAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,new ArrayList());

        final AutoCompleteTextView editCourse1, editCourse2, editCourse3, editCourse4, editCourse5, editCourse6;
        editCourse1 = (AutoCompleteTextView) findViewById(R.id.editCourse1);
        editCourse1.setThreshold(1);
        editCourse1.setAdapter(courseListAdapter);
        editCourse2 = (AutoCompleteTextView) findViewById(R.id.editCourse2);
        editCourse2.setThreshold(1);
        editCourse2.setAdapter(courseListAdapter);
        editCourse3 = (AutoCompleteTextView) findViewById(R.id.editCourse3);
        editCourse3.setThreshold(1);
        editCourse3.setAdapter(courseListAdapter);
        editCourse4 = (AutoCompleteTextView) findViewById(R.id.editCourse4);
        editCourse4.setThreshold(1);
        editCourse4.setAdapter(courseListAdapter);
        editCourse5 = (AutoCompleteTextView) findViewById(R.id.editCourse5);
        editCourse5.setThreshold(1);
        editCourse5.setAdapter(courseListAdapter);
        editCourse6 = (AutoCompleteTextView) findViewById(R.id.editCourse6);
        editCourse6.setThreshold(1);
        editCourse6.setAdapter(courseListAdapter);


        //Button listener on Next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to next activity (register interested hobbies page)
                //startActivity(new Intent(CourseRegisterPage.this,HobbiesRegisterPage.class));
                String course1 = editCourse1.getText().toString().toUpperCase();
                String name = coursesMapping.get(course1);
                String course2 = editCourse2.getText().toString().toUpperCase();
                String course3 = editCourse3.getText().toString().toUpperCase();
                String course4 = editCourse4.getText().toString().toUpperCase();
                String course5 = editCourse5.getText().toString().toUpperCase();
                String course6 = editCourse6.getText().toString().toUpperCase();
                //check to see if any of the courses are not valid. If they are, then don't proceed
                if (checkValidCourses(course1, course2, course3, course4, course5, course6)) {
                    Intent courseRegisterPage = new Intent(getBaseContext(), HobbiesRegisterPage.class);
                    if (!course1.equals("")) {
                        courseRegisterPage.putExtra("course1", course1);
                        courseRegisterPage.putExtra("course1Name", coursesMapping.get(course1));
                    }
                    if (!course2.equals("")) {
                        courseRegisterPage.putExtra("course2", course2);
                        courseRegisterPage.putExtra("course2Name", coursesMapping.get(course2));
                    }
                    if (!course3.equals("")) {
                        courseRegisterPage.putExtra("course3", course3);
                        courseRegisterPage.putExtra("course3Name", coursesMapping.get(course3));
                    }
                    if (!course4.equals("")) {
                        courseRegisterPage.putExtra("course4", course4);
                        courseRegisterPage.putExtra("course4Name", coursesMapping.get(course4));
                    }
                    if (!course5.equals("")) {
                        courseRegisterPage.putExtra("course5", course5);
                        courseRegisterPage.putExtra("course5Name", coursesMapping.get(course5));
                    }
                    if (!course6.equals("")) {
                        courseRegisterPage.putExtra("course6", course6);
                        courseRegisterPage.putExtra("course6Name", coursesMapping.get(course6));
                    }
                    getBaseContext().startActivity(courseRegisterPage);
                    finish();
                }

            }
        });

        //Button listener on Previous button
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to previous activity
                FirebaseAuth.getInstance().signOut();
                finish();   //go to previous activity that called this register page
            }
        });
    }

    /*
    * Simple method to check if any of the supplied courses are valid courses
    * */
    private boolean checkValidCourses(String c1, String c2, String c3, String c4, String c5, String c6) {
        Set<String> courses = coursesMapping.keySet();
        if (!c1.trim().isEmpty()) {
            if (!courses.contains(c1)) {
                return false;
            }
        }
        if (!c2.trim().isEmpty()) {
            if (!courses.contains(c2)) {
                return false;
            }
        }
        if (!c3.trim().isEmpty()) {
            if (!courses.contains(c3)) {
                return false;
            }
        }
        if (!c4.trim().isEmpty()) {
            if (!courses.contains(c4)) {
                return false;
            }
        }
        if (!c5.trim().isEmpty()) {
            if (!courses.contains(c5)) {
                return false;
            }
        }
        if (!c6.trim().isEmpty()) {
            if (!courses.contains(c6)) {
                return false;
            }
        }
        return true;
    }


}//CourseRegisterPage
