package ca.brocku.kt13nh.Student_Connect.tab_components;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ca.brocku.kt13nh.Student_Connect.R;


public class InboxFragment extends Fragment {
    private View view;

    private String title;//String for tab title

    private static RecyclerView recyclerView;

    private TextView Tv;

    private OnItemClickListener mListener;

    private RecyclerView_Adapter chatroomListAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mChatroomDatabaseReference;
    private ChildEventListener mChildEventListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onLongItemClick(View view, int position);
    }

    //public InboxFragment(){}
    public InboxFragment(String title) {
        this.title = title;//Setting tab title
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                        Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dummy_fragment, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mChatroomDatabaseReference = mFirebaseDatabase.getReference().child("Chatrooms");

        setRecyclerView();
        attachDatabaseReadListener();
        return view;

    }
    //Setting recycler view
    private void setRecyclerView() {

        recyclerView = (RecyclerView) view
                .findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity()));//Linear Items

        chatroomListAdapter = new RecyclerView_Adapter(getActivity());
        recyclerView.setAdapter(chatroomListAdapter);// set adapter on recyclerview

    }

    /**
     * Attaches listener to chatrooms table. When a chatroom is added, that chatroom appears in
     * the inbox
     */
    private void attachDatabaseReadListener(){
        if(mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String chatID = dataSnapshot.getKey();
                    String chatName = (String) dataSnapshot.child("ChatName").getValue().toString();
                    Map<String, String> chatroomData = new HashMap<>();
                    chatroomData.put("ChatID", chatID);
                    chatroomData.put("ChatName", chatName);
                    chatroomListAdapter.addChatroom(chatroomData);
                    recyclerView.setAdapter(chatroomListAdapter);
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
            mChatroomDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }






}
