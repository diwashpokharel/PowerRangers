package ca.wa11eubrocku.studentconnect;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import java.util.ArrayList;

//for drawer courses page -- needs to be changed to fragment for drawer (paste to CourseFragmentPage)
// used http://wptrafficanalyzer.in/blog/deleting-selected-items-from-listview-in-android/

public class CoursesPage extends ListActivity {

    ArrayList<String> list = new ArrayList<String>();   //courses entered by user stored here, this should change to appropriate type when we want to use the course code

    ArrayAdapter<String>  adapter;   //used to set courses to ListView

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_page);

        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAddCourse);   //add course button
        Button btnDel = (Button) findViewById(R.id.btnDel); //delete course button

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,list);

        //On click listener for Add course button
        View.OnClickListener listenerAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.courseEditItem);
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        //On click listener for delete button
        View.OnClickListener listenerDel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
                int itemCount = getListView().getCount();

                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){
                        adapter.remove(list.get(i));
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();
            }
        };

        btnAdd.setOnClickListener(listenerAdd); //set event listener for add course button
        btnDel.setOnClickListener(listenerDel); //set even listener for delete course button

        setListAdapter(adapter);

    }//onCreate

}//CoursesPage