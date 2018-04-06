package ca.brocku.kt13nh.Student_Connect.qa_components;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import ca.brocku.kt13nh.Student_Connect.R;

public class QaDisplaySettings extends AppCompatActivity {
    private boolean postAsAnonymous;
    private CheckBox anonymousCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa_display_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getIntent().getStringExtra("Q&A Settings"));
        anonymousCheckBox = (CheckBox) findViewById(R.id.anonymousCheck);
        anonymousCheckBox.setChecked(getIntent().getBooleanExtra("anonymous",
                false));
    }

    /**
     * Listener for when Post as Anonymous is checked/unchecked
     * @param view
     */
    public void anonymousCheckClicked(View view){
        if(anonymousCheckBox.isChecked())
            postAsAnonymous = true;
        else
            postAsAnonymous = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                //Destroys current activity and sends whether the user selected "Post as Anonymous"
                //as the result
                Intent intent = new Intent();
                intent.putExtra("anonymous", postAsAnonymous);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
