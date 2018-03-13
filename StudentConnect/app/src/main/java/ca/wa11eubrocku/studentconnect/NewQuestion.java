package ca.wa11eubrocku.studentconnect;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewQuestion extends Activity{

    private Spinner spinnerCourses;
    private Button postButton, cancelButton;
    private EditText editTitleField, editDescriptionField;
    private String questionTitle, questionDescription, spinnerCourseSelected;
    private CheckBox anonymousCheckbox;
    private boolean anonymous;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_question_window);

        //Used for adjusting popup window based on user's cellphone screen size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));//if we want window screen to be 80% of our phone's screen, multiply by 0.8

        //for drop down menu(in the pop up window)
        addListenerOnButton();

    }//onCreate

    //Used for button on pop up window
    public void addListenerOnButton(){

        //for each part of form (Spinner, buttons, edittext fields, checkbox)
        spinnerCourses = (Spinner) findViewById(R.id.spinnerCourses);
        spinnerCourses.setPrompt("Select a course");    //check if this message shows*************************
        postButton = (Button) findViewById(R.id.postButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        editTitleField = (EditText) findViewById(R.id.editQATitle);
        editDescriptionField = (EditText) findViewById(R.id.editQADescription);
        anonymousCheckbox = (CheckBox) findViewById(R.id.checkBoxQAAnonymous);

        //convert values from form to string
        spinnerCourseSelected = spinnerCourses.getSelectedItem().toString();
        questionTitle = editTitleField.getText().toString();
        questionDescription = editDescriptionField.getText().toString();
        //if checkbox checked, user wants to post anonymously (true if checked, false otherwise)
        anonymous = anonymousCheckbox.isChecked();

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewQuestion.this, "Post button pressed :" +
                                "\nCourse selected: " + String.valueOf(spinnerCourses.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }//addListenerOnButton

}//PopUpAdd
