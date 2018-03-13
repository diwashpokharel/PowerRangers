package ca.wa11eubrocku.studentconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is for the Hobbies register page, only shown once to each user. Uses the class
 * ExpandableListAdapter.java as well.
 */
public class HobbiesRegisterPage extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;    //should be adjusted for hobbies we use
    HashMap<String, List<String>> listDataChild;    //should be adjusted for hobbies

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies_register_page);

        // get the ListView
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // ListView Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // ListView Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // ListView Group collapsed listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // ListView on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

    }//onCreate

    /*
     * Preparing the list data
     */
    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data (we listed these in design phase)
        listDataHeader.add("Creative Arts");
        listDataHeader.add("Educational");
        listDataHeader.add("Games and Sports");
        listDataHeader.add("Outdoor Recreation");
        listDataHeader.add("Other");

        // Adding child data (examples)
        List<String> creativeArts = new ArrayList<String>();
        creativeArts.add("Painting");
        creativeArts.add("Photography");
        creativeArts.add("Sculpting");
        creativeArts.add("Plays");

        List<String> educational = new ArrayList<String>();
        educational.add("Book Clubs");
        educational.add("Programming");
        educational.add("Museums");


        List<String> gamesAndSports = new ArrayList<String>();
        gamesAndSports.add("Soccer");
        gamesAndSports.add("Hockey");
        gamesAndSports.add("Basketball");
        gamesAndSports.add("Volleyball");

        List<String> outdoorRecreation = new ArrayList<String>();
        outdoorRecreation.add("Bird Watching");
        outdoorRecreation.add("Field Trips");
        outdoorRecreation.add("Eating at new places");

        List<String> other = new ArrayList<String>();
        other.add("Food");
        other.add("Traveling");
        other.add("Escape Rooms");

        listDataChild.put(listDataHeader.get(0), creativeArts); // Header, Child data
        listDataChild.put(listDataHeader.get(1), educational);
        listDataChild.put(listDataHeader.get(2), gamesAndSports);
        listDataChild.put(listDataHeader.get(3), outdoorRecreation);
        listDataChild.put(listDataHeader.get(4), other);

    }//prepareListData

}//HobbiesRegisterPage
