package ca.wa11eubrocku.studentconnect;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * This class is used for the pop up window when user wants to create a new Event
 * Able to use FragmentActiviy instead of Fragment for Nav Drawer?
 * TimePicker (for date section) does not work properly, nothing happens when date picked**********
 *
 */

public class NewEvent extends FragmentActivity{

    private Spinner spinnerEvents;
    private Button createButton, cancelButton;
    private EditText editTitleField, editDescriptionField, editLocationField;
    private String eventTitle, eventDescription, spinnerEventTypeSelected, location;
    private CheckBox anonymousCheckbox;
    private boolean anonymous;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_event_window);

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
        spinnerEvents = (Spinner) findViewById(R.id.spinnerEvents);
        createButton = (Button) findViewById(R.id.createButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        editTitleField = (EditText) findViewById(R.id.editEventTitle);
        editDescriptionField = (EditText) findViewById(R.id.editEventDescription);
        editLocationField = (EditText) findViewById(R.id.editLocation);
        anonymousCheckbox = (CheckBox) findViewById(R.id.checkBoxEvent);

        //convert values from form to string
        spinnerEventTypeSelected = spinnerEvents.getSelectedItem().toString();
        eventTitle = editTitleField.getText().toString();
        eventDescription = editDescriptionField.getText().toString();
        location = editLocationField.getText().toString();

        //If checkbox checked, user wants to post anonymously (true if checked, false otherwise)
        anonymous = anonymousCheckbox.isChecked();

        //Add listener on cancel button
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Add listener on post button, prints out course selected and that create button was pressed
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewEvent.this, "Create button pressed :" +
                                "\nEvent selected: " + String.valueOf(spinnerEvents.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }//addListenerOnButton

    /**
     * Method below used for time picker component of new event.
     * @param v
     */
    public void showTimePickerDialog(View v){

        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");   //this might now work

    }//showTimePickerDialog

    /**
     * Method below used for date picker component of new event.
     * @param v
     */
    public void showDatePickerDialog(View v) {

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }//showDatePickerDialog

}//PopUpAdd
