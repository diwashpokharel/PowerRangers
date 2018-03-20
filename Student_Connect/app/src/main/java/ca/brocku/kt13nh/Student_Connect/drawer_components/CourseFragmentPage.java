package ca.brocku.kt13nh.Student_Connect.drawer_components;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import java.util.ArrayList;

import ca.brocku.kt13nh.Student_Connect.R;

/**
 * This class used for course page in nav drawer, user can manage their courses on this page.
 *
 * If the user wants to add a course then they type in the course code and click add.
 *
 * If the user wants to delete a course, then they select one or more courses to remove then click
 * delete button at the bottom, and if they do this then a confirmation dialog will pop up to make
 * sure they want to delete the course, if yes then proceed with deletion.
 */

public class CourseFragmentPage extends ListFragment {

    //courses entered by user stored here, this should change to appropriate type when we want to use the course code
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private EditText edit;
    private SparseBooleanArray checkedItemPositions;
    private int itemCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.courses_fragment_page, container, false);

        ImageButton btnAdd = (ImageButton) v.findViewById(R.id.btnAddCourse);  //add course button
        Button btnDel = (Button) v.findViewById(R.id.btnDel); //delete course button

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,list);   //used to set courses to ListView

        //On click listener for Add course button
        View.OnClickListener listenerAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        //OnClick listener for Delete course/courses button
        View.OnClickListener listenerDel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedItemPositions = getListView().getCheckedItemPositions();
                itemCount = getListView().getCount();

                //alert dialog pops up to confirm if user wants to delete selected course
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Delete Course");
                alert.setMessage("Are you sure you want to delete this course?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //remove course from their list of courses in database
                        //continue with delete from list view
                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                adapter.remove(list.get(i));
                            }
                        }
                        checkedItemPositions.clear();
                        adapter.notifyDataSetChanged();
                    }
                });
                //if user hits no, return to courses fragment page
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close dialog
                        dialog.cancel();
                    }
                });
                alert.show();

            }
        };

        btnAdd.setOnClickListener(listenerAdd); //set event listener for add course button
        btnDel.setOnClickListener(listenerDel); //set even listener for delete course button

        setListAdapter(adapter);

        return v;

    }//onCreateView

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Courses");

        //for when the user wants to add a new course, they type in the course code
        edit = (EditText) getActivity().findViewById(R.id.courseEditItem);

    }//on ViewCreated

}//CourseFragmentPage