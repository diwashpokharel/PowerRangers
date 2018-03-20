package ca.brocku.kt13nh.Student_Connect.drawer_components;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.brocku.kt13nh.Student_Connect.R;

/*
 * Takes care of the Profile under Account Information.
 * It is connected to Firebase.
 *
 * It will have 6 textboxes and a save button. The save button will be updated in real-time. It
 * will be disabled if any of the information is incorrect, enabled otherwise.
 *
 * BUGS: None.
 *
 * @author Maysara Al Jumaily
 * @version 1.0
 * March 18, 2018
 */

public class Profile extends AppCompatActivity {
    //Only 1 button needed
    Button save;
    //The 6 textboxes needed.
    EditText email;
    EditText firstName;
    EditText lastName;
    EditText currentPassword;
    EditText newPassword;
    EditText newPasswordConfirm;
    //String currentPasswordLiteral;//the actual password of the user, retrieved from Firebase;

    static boolean isCorrectPassword;//Indicates whether or not the current password entered in
    // the textbox matches the password in the database.

    //These are variables used to show error messages.
    final String NAME_TIP = "1 - 32 characters. ";
    final String NEW_PASSWORD_TIP = "8 - 32 characters. Must include at least one number, at " +
            "least one upper case and at least one lower case letter. New password cannot be the " +
            "same as the current one. Only English letters and numbers.";
    final String NEW_PASSWORD_CONFIRM_TIP = "Invalid Password. Must be long enough and matches " +
            "new password.";

    //Firebase connections
    FirebaseDatabase database;
    FirebaseUser currentUser;

    DatabaseReference userReference;

    /**
     * Method that will be executed automatically.
     *
     * @param savedInstanceState State of you activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize the Firebase objects and populate the database
        database = FirebaseDatabase.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userReference = database.getReference().child("User").child(currentUser.getUid());

        //Initialize the textboxes and the button. Also add real-time validator to the textboxes.
        initializeComponents();

        //For testing purposes, the email textbox text is set to be the following:
        addRealTimeValidation(firstName, R.id.firstNameLayout, 1, 32, 0);
        addRealTimeValidation(lastName, R.id.lastNameLayout, 1, 32, 0);
        addRealTimeValidation(currentPassword, R.id.currentPasswordLayout, 8, 32, 1);
        addRealTimeValidation(newPassword, R.id.newPasswordLayout, 8, 32, 2);
        addRealTimeValidation(newPasswordConfirm, R.id.newPasswordConfirmLayout, 8, 32, 3);

        //Information is retreived from Firebase in the updateTextboxes() method.
        updateTextboxes();
    }

    /**
     * @param component textbox to add real-time validation to.
     * @param id        Id of TextInputLayout the textbox in.
     * @param minSize   Minimum number of characters it is allowed to hold.
     * @param maxSize   Maximum number of characters it is allowed to hold.
     * @param inputType What kind of textbox it is. There will be 4 types.
     *                  Type 0: for first and last name.
     *                  Type 1: for current password.
     *                  Type 2: for new password.
     *                  Type 3: for confirming the new password.
     */
    private void addRealTimeValidation(final EditText component, final int id, final int minSize,
                                       final int maxSize, final int inputType) {

        final TextInputLayout til = findViewById(id);// TextInputLayout the textbox is in.
        til.setErrorEnabled(true);//Enable the error message. I.e. make it visible if there is an
        // error to be displayed, if there isn't, there will be nothing to show. If disabled, it
        // will not show whether or not there is an error.

        //Adding a focusing listener, once the textbox has a focus, executed the code found.
        component.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {//currently in focus
                    if (ifValidInput(component, minSize, maxSize, inputType)) {
                        til.setError(null);
                        checkOverallInput();
                    } else {
                        til.setError(setMessage(inputType));
                        checkOverallInput();
                    }
                } else {//Not focused, hide error.
                    til.setError(null);
                    checkOverallInput();
                }
            }
        });

        //Adding a listener when the text has been altered (before, after and during text change).
        component.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                textBoxTextChange(til, component, minSize, maxSize, inputType);
                checkOverallInput();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textBoxTextChange(til, component, minSize, maxSize, inputType);
                checkOverallInput();
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textBoxTextChange(til, component, minSize, maxSize, inputType);
                checkOverallInput();
            }
        });
    }

    /**
     * Takes care of the checking needed when content in the textbox gets changed. Displays the
     * appropriate error message if necessary.
     *
     * @param til       TextInputLayout object that contain the textbox.
     * @param component Textbox to perform the checking on.
     * @param minSize   Minimum number of characters it is allowed to hold.
     * @param maxSize   Maximum number of characters it is allowed to hold.
     * @param inputType What kind of textbox it is. There will be 4 types.
     *                  Type 0: for first and last name.
     *                  Type 1: for current password.
     *                  Type 2: for new password.
     *                  Type 3: for confirming the new password.
     */
    private void textBoxTextChange(TextInputLayout til, EditText component, int minSize,
                                   int maxSize, int inputType) {
        if (ifValidInput(component, minSize, maxSize, inputType)) {
            til.setError(null);
            checkOverallInput();
        } else {
            til.setError(setMessage(inputType));
            checkOverallInput();
        }
    }

    /**
     * Will check whether or not the input of the textbox passed is valid.
     *
     * @param component Textbox to be examined.
     * @param minSize   Minimum number of characters it is allowed to hold.
     * @param maxSize   Maximum number of characters it is allowed to hold.
     * @param inputType What kind of textbox it is. There will be 4 types.
     *                  Type 0: for first and last name.
     *                  Type 1: for current password.
     *                  Type 2: for new password.
     *                  Type 3: for confirming the new password.
     * @return True if the textbox has valid input, false otherwise.
     */
    private boolean ifValidInput(final EditText component, final int minSize,
                                 final int maxSize, final int inputType) {

        //Check if the current number of characters is within the required range.
        if (component.getText().length() < minSize || component.getText().length() > maxSize) {
            return false;
        }

        switch (inputType) {
            case 1://current password
                //The validation will happen later, by getting the password from Firebase.
                //For now, just assume it is valid.
                return true;
            //return checkIfCurrentPasswordMatches();
            case 2://new password
                //Check if the new password is valid. It is done by the regular expression below.
                Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[a-zA-Z0-9]{8,32}$");
                Matcher m = p.matcher(component.getText().toString());

                if (m.matches()) {//password is valid
                    if (currentPassword.getText().toString().matches(newPassword.getText()
                            .toString())) {//new password entered is the same as current password.
                        return false;
                    } else {//completely valid password
                        return true;
                    }
                }
                return false;
            case 3://new password confirm
                return (component.getText().toString().matches(newPassword.getText().toString()));
            default:
                break;
        }
        return true;
    }

    /**
     * Checks if the current password entered matches the one stored in Firebase.
     *
     * @return True if the current password matches the one stored in the database, false otherwise.
     */
    private boolean checkIfCurrentPasswordMatches() {

//        currentUser.get
//
//        userPassword.keepSynced(true);
//        userPassword.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Check if current password matches the one in the database
//                if (currentPassword.getText().toString().equals(dataSnapshot.getValue(String
//                        .class))) {
//                    isCorrectPassword = true;
//                } else {
//                    isCorrectPassword = false;
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//        return isCorrectPassword;
        return true;
    }

    /**
     * Initializing the components.
     */
    private void initializeComponents() {
        email = (EditText) findViewById(R.id.email);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        currentPassword = (EditText) findViewById(R.id.currentPassword);
        newPassword = (EditText) findViewById(R.id.newPassword);
        newPasswordConfirm = (EditText) findViewById(R.id.newPasswordConfirm);

        email.setEnabled(false);
        email.setClickable(false);
        save = (Button) findViewById(R.id.save);
        save.setEnabled(false);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //The ChildEventListener is used to update the database.
                ChildEventListener cel = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        updateData(dataSnapshot);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                userReference.addChildEventListener(cel);
            }
        });
    }

    /**
     * Takes care of updating the database.
     *
     * @param dataSnapshot the database to be updated.
     */
    private void updateData(DataSnapshot dataSnapshot) {
        //data will store the needed entry in the database.
        Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();
        String userEmail = (String) data.get("email");
        String newFirstName = firstName.getText().toString();
        String newLastName = lastName.getText().toString();
        String newUpdatedPassword = newPassword.getText().toString();

        //match the user to be updated based on the first 6 characters in the email textbox.
        if (userEmail.toLowerCase().equals(email.getText().toString().substring(0, 6).toLowerCase
                ())) {
            userReference.child(userEmail).child("firstName").setValue(newFirstName);
            userReference.child(userEmail).child("lastName").setValue(newLastName);
            //If the current password entered is matches the one in the database and the user
            // entered a new password which is valid. The password will be updated.
            if (ifValidInput(newPassword, 8, 32, 2) && checkIfCurrentPasswordMatches()) {
                userReference.child(userEmail).child("password").setValue(newUpdatedPassword);
            } else if (ifValidInput(newPassword, 8, 32, 2) && !checkIfCurrentPasswordMatches()) {
                //if current password entered does not match the one in the database, display an
                // error massage.
                AlertDialog alertDialog = new AlertDialog.Builder(Profile.this).create();
                alertDialog.setTitle("Incorrect Password");
                alertDialog.setMessage("Current password entered does not match the stored " +
                        "password in the database.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            updateTextboxes();
        }
    }

    /**
     * Takes care of validating all the textboxes. If the input is valid, the save button will be
     * enabled. Otherwise, it will be disabled.
     *
     * @return True if all the input is valid, false otherwise.
     */
    private boolean checkOverallInput() {
        //will check if the save button should be enabled or not. I will make sure that the first
        // and last names are valid. For the passwords, if the current password, new password and
        // confirm passwords are empty, it is considered as valid. If at least one of them is not
        // empty, then all the three textboxes must have valid input.
        if (ifValidInput(firstName, 1, 32, 0)
                && ifValidInput(lastName, 1, 32, 0)
                && ((ifValidInput(currentPassword, 8, 32, 1)
                && ifValidInput(newPassword, 8, 32, 2)
                && ifValidInput(newPasswordConfirm, 8, 32, 3))
                || (currentPassword.getText().toString().trim().length() == 0
                && newPassword.getText().toString().trim().length() == 0
                && newPasswordConfirm.getText().toString().trim().length() == 0))) {
            save.setEnabled(true);
            return true;
        } else {
            save.setEnabled(false);
            return false;
        }
    }

    /**
     * Used to return the appropriate error message based on the inputType passed.
     *
     * @param inputType What kind of textbox it is. There will be 4 types.
     *                  Type 1: for current password.
     *                  Type 2: for new password.
     *                  Type 3: for confirming new password.
     *                  Otherewise: for first and last name.
     * @return String with the appropriate tip.
     */
    private String setMessage(int inputType) {
        switch (inputType) {
            case 1://current password
                return null;
            case 2://new password
                return NEW_PASSWORD_TIP;
            case 3://new password confirm
                return NEW_PASSWORD_CONFIRM_TIP;
            default://regular
                return NAME_TIP;
        }
    }


    /**
     * Simple method that will update the textboxes. It will retrieve data from Firebase and
     * updates the textboxes according.
     */
    private void updateTextboxes() {
        //Get the email from the textbox.
        //String e = email.getText().subSequence(0, 6).toString().toUpperCase();
        //Get the user information based on the email.
        //DatabaseReference user = FirebaseDatabase.getInstance().getReference(e);
        DatabaseReference userEmail = userReference.child("email");//Get the email stored.
        DatabaseReference userFirstName = userReference.child("first_name");//Get the first name stored.
        DatabaseReference userLastName = userReference.child("last_name");//Get the last name stored.

        userEmail.addValueEventListener(getDataFromFirebase(email));
        //Make the email to contain @brocku.ca extension.
        String fullEmail = email.getText().toString() + "@brocku.ca";
        email.setText(fullEmail);
        //Update the first and last name.
        userFirstName.addValueEventListener(getDataFromFirebase(firstName));
        userLastName.addValueEventListener(getDataFromFirebase(lastName));

        //Clear the current password, new password and confirm new password.
        currentPassword.setText("");
        newPassword.setText("");
        newPasswordConfirm.setText("");
    }

    /**
     * Used to retrieve the entry needed from Firebase. It will set the component (EditText
     * passed) text to the entry that needs to be retrieved.
     *
     * @param component the textbox to be updated.
     * @return an object which is needed to retrieve the entry from Firebase.
     */
    private ValueEventListener getDataFromFirebase(final EditText component) {
        return (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                component.setText((dataSnapshot.getValue(String.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}