package ca.wa11eubrocku.studentconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * This class is for the Hobbies register page, only shown once to each user.
 */
public class HobbiesRegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies_register_page);

        addListeners();

    }//onCreate

    private void addListeners(){

        Button next = (Button) findViewById(R.id.nextButton);
        Button previous = (Button)findViewById(R.id.previousButton);

        //creative arts category checkboxes
        final CheckBox checkPlays = (CheckBox)findViewById(R.id.artsCheckPlays);
        final CheckBox checkSculp = (CheckBox)findViewById(R.id.artsCheckSculpting);
        final CheckBox checkPhoto = (CheckBox)findViewById(R.id.artsCheckPhotography);
        final CheckBox checkPaint = (CheckBox)findViewById(R.id.artsCheckPainting);

        //educational category checkboxes
        final CheckBox checkProgramming = (CheckBox)findViewById(R.id.eduCheckProgramming);
        final CheckBox checkMuseum = (CheckBox)findViewById(R.id.eduCheckMuseum);
        final CheckBox checkBookClub = (CheckBox)findViewById(R.id.eduCheckBookClub);

        //Games and sports category checkboxes
        final CheckBox checkSoccer = (CheckBox)findViewById(R.id.gamesCheckSoccer);
        final CheckBox checkHockey = (CheckBox)findViewById(R.id.gamesCheckHockey);
        final CheckBox checkBasketball = (CheckBox)findViewById(R.id.gamesCheckBasketball);
        final CheckBox checkVolleyball = (CheckBox)findViewById(R.id.gamesCheckVolleyball);

        //outdoor recreation category checkboxes
        final CheckBox checkField = (CheckBox)findViewById(R.id.outdoorCheckField);
        final CheckBox checkHike = (CheckBox)findViewById(R.id.outdoorCheckHikes);
        final CheckBox checkBird = (CheckBox)findViewById(R.id.outdoorCheckBird);

        //other category checkboxes
        final CheckBox checkEat = (CheckBox)findViewById(R.id.otherCheckEating);
        final CheckBox checkEscape = (CheckBox)findViewById(R.id.otherCheckEscape);
        final CheckBox checkTravel = (CheckBox)findViewById(R.id.otherCheckTraveling);


        //Button listener on Previous button
        previous.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //go to previous activity
                finish();   //go to previous activity that called this register page
            }
        });

        //Button listener on Next button
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //variables are true if checkbox checked
                boolean plays = checkPlays.isChecked();
                boolean sculp = checkSculp.isChecked();
                boolean photo = checkPhoto.isChecked();
                boolean paint = checkPaint.isChecked();

                boolean programming = checkProgramming.isChecked();
                boolean museum = checkMuseum.isChecked();
                boolean bookClub = checkBookClub.isChecked();

                boolean soccer = checkSoccer.isChecked();
                boolean basketball = checkBasketball.isChecked();
                boolean volleyball = checkVolleyball.isChecked();
                boolean hockey = checkHockey.isChecked();

                boolean fieldTrip = checkField.isChecked();
                boolean hike = checkHike.isChecked();
                boolean birdWatching = checkBird.isChecked();

                boolean eating = checkEat.isChecked();
                boolean escapeRoom = checkEscape.isChecked();
                boolean travel = checkTravel.isChecked();

                //go to what activity/page from here kevin?*****************************************************************************************************************************
            }
        });


    }


}//HobbiesRegisterPage
