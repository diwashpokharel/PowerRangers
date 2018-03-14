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

import ca.brocku.kt13nh.Student_Connect.base_interface_java_v3.Home;
import ca.brocku.kt13nh.Student_Connect.login_reg_java_v2.activity_login;
import ca.brocku.kt13nh.Student_Connect.login_reg_java_v2.activity_register;
import ca.brocku.kt13nh.Student_Connect.base_interface_java_v3.NavBar;

/**
 * Created by kevin on 2018-02-12.
 */

public class MainActivity extends AppCompatActivity {

    Button registerButton;
    Button existingUserButton;

    /*
    *
    * Start the user on the login/registration page.
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loginreg);
        setListeners();
    }


    /*
    * Start the user of the main screen if they are currently logged in
    * */

    @Override
    public void onStart(){
        super.onStart();
        if(isLoggedIn()){
            Intent activity_home = new Intent(MainActivity.this, NavBar.class);
            startActivity(activity_home);
        }
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
                Intent activity_login = new Intent(MainActivity.this,activity_login.class);
                startActivity(activity_login);
            }
        });
    }

    /*
    *determine if a user is logged in using firebase authentication.
    *If a user is logged in, return boolean value true, otherwise return
    *false boolean.
    */
    public boolean isLoggedIn(){
        FirebaseAuth authenticator;
        authenticator = FirebaseAuth.getInstance();
        FirebaseUser currUser = authenticator.getCurrentUser();
        if(currUser!=null){
            Toast.makeText(MainActivity.this,currUser.getEmail().toString(),Toast.LENGTH_LONG).show();

            return true;
        }
        else{
            return false;
        }
    }

}
