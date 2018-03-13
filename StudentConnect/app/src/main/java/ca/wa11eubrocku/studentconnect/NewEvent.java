package ca.wa11eubrocku.studentconnect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;


/**
 * This class is used for the pop up window when user wants to create a new Event
 * Able to use FragmentActiviy instead of Fragment for Nav Drawer?
 *
 */

public class NewEvent extends AppCompatActivity{

    private Spinner spinnerEvents;
    private DatePicker date;
    private TimePicker time;
    private EditText editTitleField, editDescriptionField, editLocationField;
    private CheckBox anonymousCheckBox;

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
        addListenersOnButtons();

    }//onCreate

    //Used for button on pop up window
    public void addListenersOnButtons(){

        //for each part of form (Spinner, buttons, edittext fields, checkbox)
        spinnerEvents = (Spinner) findViewById(R.id.spinnerEvents);

        Button createButton = (Button) findViewById(R.id.createButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        date = (DatePicker) findViewById(R.id.datePicker);
        time = (TimePicker) findViewById(R.id.timePicker);

        editTitleField = (EditText) findViewById(R.id.editEventTitle);
        editDescriptionField = (EditText) findViewById(R.id.editEventDescription);
        editLocationField = (EditText) findViewById(R.id.editLocation);

        anonymousCheckBox = (CheckBox) findViewById(R.id.checkBoxEvent);

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

            //everything below is only read and saved AFTER user clicks "Create"
                int day = date.getDayOfMonth(); //get selected day of the month
                int month = date.getMonth(); //get selected month
                int year = date.getYear(); //get selected year

                int hour = time.getHour();
                int minute = time.getMinute();

            //convert values from form to string
                String spinnerEventTypeSelected = spinnerEvents.getSelectedItem().toString();
                String eventTitle = editTitleField.getText().toString();
                String eventDescription = editDescriptionField.getText().toString();
                String location = editLocationField.getText().toString();

            //If checkbox checked, user wants to post anonymously (true if checked, false otherwise)
                boolean anonymous = anonymousCheckBox.isChecked();

            }
        });

    }//addListenerOnButton

}//PopUpAdd
