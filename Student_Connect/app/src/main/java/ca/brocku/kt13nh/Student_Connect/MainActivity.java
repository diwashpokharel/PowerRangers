package ca.brocku.kt13nh.Student_Connect;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ca.brocku.kt13nh.Student_Connect.base_interface_java_v3.Home;
import ca.brocku.kt13nh.Student_Connect.first_login_components.CourseRegisterPage;
import ca.brocku.kt13nh.Student_Connect.login_reg_java_v2.activity_login;
import ca.brocku.kt13nh.Student_Connect.login_reg_java_v2.activity_register;
import ca.brocku.kt13nh.Student_Connect.base_interface_java_v3.NavBar;

/**
 * Created by kevin on 2018-02-12.
 */

public class MainActivity extends AppCompatActivity {

    private Button registerButton;
    private Button existingUserButton;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("User");
    private FirebaseAuth authenticator = FirebaseAuth.getInstance();
    private FirebaseUser currUser = authenticator.getCurrentUser();
    String test;
    /*
    *
    * Start the user on the login/registration page.
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        checkFirstLogin();
        setContentView(R.layout.activity_main_loginreg);
        setListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        checkFirstLogin();
    }
    @Override
    protected void onResume() {
        //checkFirstLogin();
        System.out.println("resumed");
        super.onResume();
    }
    private void checkFirstLogin(){

        if(isLoggedIn()) {
//            table_user.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    System.out.println("This is called");
//                    String UID = currUser.getUid();
//                    String first_login;
//                    first_login = dataSnapshot.child(UID).child("first_login").getValue().toString();
//                    test = first_login;
//                    if(first_login.equals("false")) {
//                        Intent activity_home = new Intent(MainActivity.this, NavBar.class);
//                        startActivity(activity_home);
//                    }
//                    else{
//                        Intent activity_course_register = new Intent(MainActivity.this, CourseRegisterPage.class);
//                        startActivity(activity_course_register);
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
            //if(first_login.equals("false")) {
                Intent activity_home = new Intent(MainActivity.this, NavBar.class);
                startActivity(activity_home);
            //}
            //else{
              //  Intent activity_course_register = new Intent(MainActivity.this, CourseRegisterPage.class);
                //startActivity(activity_course_register);
            //}
        }
    }


    /*
    * Start the user of the main screen if they are currently logged in
    * */

    @Override
    public void onStart(){

        super.onStart();

    }

    /*
    * set on click listeners for the buttons for the user to navigate to
    * each of the layouts
    */
    public void setListeners(){
        this.registerButton = (Button)findViewById(R.id.registerButton);
        this.existingUserButton = (Button)findViewById(R.id.loginButton);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent activity_register = new Intent(MainActivity.this,activity_register.class);
                startActivity(activity_register);
            }
        });
        existingUserButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final int intentRequest=1;
                Intent activity_login = new Intent(MainActivity.this,activity_login.class);
                startActivityForResult(activity_login,intentRequest);
            }
        });
    }

    /*
    *determine if a user is logged in using firebase authentication.
    *If a user is logged in, return boolean value true, otherwise return
    *false boolean.
    */
    public boolean isLoggedIn(){

        if(currUser!=null){
            Toast.makeText(MainActivity.this,currUser.getEmail().toString(),Toast.LENGTH_LONG).show();
            return true;
        }
        else{
            return false;
        }
    }

}
