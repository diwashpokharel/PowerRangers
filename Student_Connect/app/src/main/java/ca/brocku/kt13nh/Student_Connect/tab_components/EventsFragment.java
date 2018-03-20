package ca.brocku.kt13nh.Student_Connect.tab_components;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ca.brocku.kt13nh.Student_Connect.R;


public class EventsFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference table_events = mFirebaseDatabase.getReference().child("Events");
    private DatabaseReference table_user = mFirebaseDatabase.getReference().child("User");

    private ChildEventListener mChildEventListener;
    private ChildEventListener eventJoinListener;
    private FirebaseAuth authenticator= FirebaseAuth.getInstance();
    private FirebaseUser currUser = authenticator.getCurrentUser();
    private Events_adapter events_adapter;
    private View view;

    private String title;//String for tab title

    private static RecyclerView recyclerView;

    public EventsFragment() {
    }

    @SuppressLint("ValidFragment")
    public EventsFragment(String title) {
        this.title = title;//Setting tab title
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                    Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dummy_fragment, container, false);

        setRecyclerView();
        attachDatabaseReadListener();
        return view;

    }
    //Setting recycler view
    private void setRecyclerView() {


        //String [] events= {"Event Title","Event Title","Event Title"};
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//Linear Items


        //ArrayList<String> arrayList = new ArrayList<>();
        //for (int i = 0; i < events.length; i++) {
        //    arrayList.add(events[i]);//Adding items to recycler view
        //}


        events_adapter = new Events_adapter(getActivity());

        recyclerView.setAdapter(events_adapter);// set adapter on recyclerview

    }

    /**
     * Attaches listener to events table, add to list of events for user
     */
    private void attachDatabaseReadListener(){
        if(mChildEventListener == null) {

            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    load(dataSnapshot);
                    System.out.println(dataSnapshot.getValue().toString());
                    //update();
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
            table_events.addChildEventListener(mChildEventListener);
        }
    }


    private void load(DataSnapshot dataSnapshot){
        //update();
        String eventID = dataSnapshot.getKey();
        String title = (String) dataSnapshot.child("title").getValue().toString();
        String date = (String) dataSnapshot.child("date").getValue().toString();
        String description = (String) dataSnapshot.child("description").getValue().toString();
        String location = (String) dataSnapshot.child("location").getValue().toString();
        String time = (String) dataSnapshot.child("time").getValue().toString();
        String email = (String) dataSnapshot.child("email").getValue().toString();
        String UID = currUser.getUid();

        System.out.println(dataSnapshot.child("joined").getValue().toString());

        Map<String, String> eventData = new HashMap<>();

        eventData.put("eventID", eventID);
        eventData.put("title", title);
        eventData.put("date", date);
        eventData.put("description", description);
        eventData.put("location", location);
        eventData.put("time", time);
        eventData.put("email",email);
        //System.out.println("UID: "+UID);
        //System.out.println("Has child: "+dataSnapshot.child("joined").hasChild(UID));
        //if this is the creator then auto matically join
        if(dataSnapshot.child("joined").hasChild(UID)){
            eventData.put("joined","true");
        }
        else {
            eventData.put("joined","false");
        }

        events_adapter.addEvent(eventData);
        recyclerView.setAdapter(events_adapter);

    }

}
