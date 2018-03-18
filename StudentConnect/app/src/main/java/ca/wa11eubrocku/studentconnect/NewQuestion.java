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

/**
 * This class used for when a user wants to post a new question.
 * Requires question title(editTitleField), question description (editDescriptionField), and whether
 * they want to post the question anonymously or not (anonymousCheckBox).
 * Upload image button handler still pending, Ronny has part for this***************
 */
public class NewQuestion extends Activity{

    private Spinner spinnerCourses;
    private EditText editTitleField, editDescriptionField;
    private CheckBox anonymousCheckbox;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_question_window);

        //Used for adjusting popup window based on user's cellphone screen size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //if we want window screen to be 80% of our phone's screen, multiply by 0.8
        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        //for drop down menu(in the pop up window)
        addListenerOnButton();

    }//onCreate

    /**
     * Method below adds listener on the buttons and retrieves the information entered by the user.
     */
    public void addListenerOnButton(){

        //for each part of form (Spinner, buttons, edittext fields, checkbox)
        spinnerCourses = (Spinner) findViewById(R.id.spinnerCourses);

        Button postButton = (Button) findViewById(R.id.postButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button uploadPic = (Button) findViewById(R.id.uploadPicButton);

        editTitleField = (EditText) findViewById(R.id.editQATitle);
        editDescriptionField = (EditText) findViewById(R.id.editQADescription);

        anonymousCheckbox = (CheckBox) findViewById(R.id.checkBoxQAAnonymous);

        //add listener on upload picture button (Ronny has missing code since he already did this on his part)
        uploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get and save image from phone *****************
            }
        });
        //add listener on cancel button
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //add listener on post button
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewQuestion.this, "Post button pressed :" +
                                "\nCourse selected: " + String.valueOf(spinnerCourses.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                //information stored after user clicks POST

                //convert values from form to string
                String spinnerCourseSelected = spinnerCourses.getSelectedItem().toString();
                String questionTitle = editTitleField.getText().toString();
                String questionDescription = editDescriptionField.getText().toString();

                //if checkbox checked, user wants to post anonymously (true if checked, false otherwise)
                boolean anonymous = anonymousCheckbox.isChecked();

            }
        });

    }//addListenerOnButton

}//PopUpAdd
