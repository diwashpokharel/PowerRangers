package ca.brocku.kt13nh.Student_Connect.tab_components;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ca.brocku.kt13nh.Student_Connect.R;


public class EventsFragment extends android.support.v4.app.Fragment {


    private View view;

    private String title;//String for tab title

    private static RecyclerView recyclerView;

    public EventsFragment() {
    }

    public EventsFragment(String title) {
        this.title = title;//Setting tab title
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                    Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dummy_fragment, container, false);

        setRecyclerView();
        return view;

    }
    //Setting recycler view
    private void setRecyclerView() {


        String [] events= {"Event Title","Event Title","Event Title"};
        recyclerView = (RecyclerView) view
                .findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity()));//Linear Items


        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < events.length; i++) {
            arrayList.add(events[i]);//Adding items to recycler view
        }
        Events_adapter adapter = new Events_adapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview

    }

}
