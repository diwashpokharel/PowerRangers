package ca.wa11eubrocku.studentconnect;

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

/**
 * This class used for hobbies page in nav drawer, user can manage their hobbies on this page.
 *
 * If the user wants to add a hobby then they type in the box to search it and click add.
 *
 * If the user wants to delete a hobby, then they select one or more hobbies to remove then click
 * delete button at the bottom, and if they do this then a confirmation dialog will pop up to make
 * sure they want to delete the hobby, if yes then proceed with deletion.
 *
 * Would it be a good idea to add autofill feature to edittext box when user types beginning of interested hobby?*********************
 */

public class HobbiesFragmentPage extends ListFragment {

    private ArrayList<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private EditText edit;
    private SparseBooleanArray checkedItemPositions;
    private int itemCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hobbies_fragment_page, container, false);

        ImageButton btnAdd = (ImageButton) v.findViewById(R.id.btnAddHobby);  //add hobby button
        Button btnDel = (Button) v.findViewById(R.id.btnDel); //delete hobby button

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,list);   //used to set hobbies to ListView

        //On click listener for Add hobby button
        View.OnClickListener listenerAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        //OnClick listener for Delete hobby/hobbies button
        View.OnClickListener listenerDel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedItemPositions = getListView().getCheckedItemPositions();
                itemCount = getListView().getCount();

                //alert dialog pops up to confirm if user wants to delete selected hobby
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Delete Hobby");
                alert.setMessage("Are you sure you want to delete this hobby?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //remove hobby from their list of hobbies in database
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
                //if user hits no, return to hobbies fragment page
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

        btnAdd.setOnClickListener(listenerAdd); //set event listener for add hobby button
        btnDel.setOnClickListener(listenerDel); //set even listener for delete hobby button

        setListAdapter(adapter);

        return v;

    }//onCreateView

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Hobbies");

        edit = (EditText) getActivity().findViewById(R.id.hobbyEditItem);

    }//on ViewCreated

}//CourseFragmentPage
