package ca.brocku.kt13nh.Student_Connect.floating_action_button_components;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import ca.brocku.kt13nh.Student_Connect.R;
import ca.brocku.kt13nh.Student_Connect.chatroom_components.Message;

/**
 * This class used for when a user wants to post a new question.
 * Requires question title(editTitleField), question description (editDescriptionField), and whether
 * they want to post the question anonymously or not (anonymousCheckBox).
 * Upload image button handler still pending, Ronny has part for this***************
 */
public class NewQuestion extends Activity{

    private static final int RC_PHOTO_PICKER =  2;

    private Spinner spinnerCourses;
    private EditText editTitleField, editDescriptionField;
    private CheckBox anonymousCheckbox;

    private Uri imageUri;
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference table_questions = mFirebaseDatabase.getReference().child("Questions");
    private DatabaseReference table_user = mFirebaseDatabase.getReference().child("User");
    private FirebaseAuth authenticator= FirebaseAuth.getInstance();
    private FirebaseUser currUser = authenticator.getCurrentUser();
    private StorageReference imageRef;

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
        initDropDownCourses();

    }//onCreate

    /**
     * Method below adds listener on the buttons and retrieves the information entered by the user.
     */
    public void addListenerOnButton(){

        //for each part of form (Spinner, buttons, edittext fields, checkbox)
        spinnerCourses = (Spinner) findViewById(R.id.spinnerCourses);

        Button postButton = (Button) findViewById(R.id.postButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        editTitleField = (EditText) findViewById(R.id.editQATitle);
        editDescriptionField = (EditText) findViewById(R.id.editQADescription);

        anonymousCheckbox = (CheckBox) findViewById(R.id.checkBoxQAAnonymous);

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

                //information stored after user clicks POST

                //convert values from form to string
                final String spinnerCourseSelected = spinnerCourses.getSelectedItem().toString();
                final String questionTitle = editTitleField.getText().toString();
                final String questionDescription = editDescriptionField.getText().toString();

                //if checkbox checked, user wants to post anonymously (true if checked, false otherwise)
                final boolean anonymous = anonymousCheckbox.isChecked();
                final Map<String, String> questionData = new HashMap<>();
                final String questionID = UUID.randomUUID().toString();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        String UID = currUser.getUid();
                        String firstName = dataSnapshot.child(UID).child("first_name").getValue().toString();
                        String lastName = dataSnapshot.child(UID).child("last_name").getValue().toString();
                        String fullName = firstName + " " + lastName;
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = df.format(date);
                        String email = currUser.getEmail();

                        questionData.put("title", questionTitle);
                        questionData.put("date", formattedDate);
                        questionData.put("description", questionDescription);
                        questionData.put("course", spinnerCourseSelected);
                        questionData.put("user", fullName);
                        questionData.put("email",email);
                        if(anonymous==true)
                            questionData.put("anonymous","true");
                        else
                            questionData.put("anonymous","false");

                        if(imageRef != null){
                            //Upload file to Firebase storage
                            imageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                    questionData.put("imageUrl", downloadUrl.toString());
                                }
                            });

                        }

                        table_questions.child(questionID).setValue(questionData);
                        table_user.child(UID).child("qa").child(questionID).setValue(questionTitle);
                        Toast.makeText(NewQuestion.this, "Question created!", Toast.LENGTH_SHORT).show();


                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });

    }//addListenerOnButton

    public void uploadImage(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"),
                RC_PHOTO_PICKER);
    }

    /**
     * Adds listener to enrolled section in User's table that gets the courses the current user is
     * enrolled in and displays them in the dropdown
     */
    private void initDropDownCourses(){

        final String UID = currUser.getUid();
        final ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.textcenter, R.id.name_text);

        table_user.child(UID).child("enrolled").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[] values = new String[(int)dataSnapshot.getChildrenCount()];
                int pos=0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String name = snapshot.getKey().toString();
                    values[pos]=name;
                    pos++;
                }
                adapter.addAll(values);
                spinnerCourses.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_PHOTO_PICKER){
            if(resultCode == RESULT_OK){
                imageUri = data.getData();
                //Get a reference to store file at Images/<FILENAME>
                imageRef = FirebaseStorage.getInstance().getReference()
                        .child("Images").child(imageUri.getLastPathSegment());

                String uriString = imageUri.toString();

                File myFile = new File(uriString);
                String displayName = null;

                //Find the name of the file selected by the user
                if (uriString.startsWith("content://")) {
                    Cursor cursor = null;
                    try {
                        cursor = this.getContentResolver().query(imageUri, null,
                                null, null,
                                null);
                        if (cursor != null && cursor.moveToFirst()) {
                            displayName = cursor.getString(cursor.getColumnIndex
                                    (OpenableColumns.DISPLAY_NAME));
                        }
                    } finally {
                        cursor.close();
                    }
                } else if (uriString.startsWith("file://")) {
                    displayName = myFile.getName();
                }

                TextView imageNameView = findViewById(R.id.imageNameTextView);
                imageNameView.setText(displayName);
            }
        }
    }

}//PopUpAdd