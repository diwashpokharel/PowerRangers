package ca.brocku.kt13nh.Student_Connect.tab_components;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ca.brocku.kt13nh.Student_Connect.R;

public abstract class EventsCardHolder extends RecyclerView.ViewHolder {


    public TextView title;


    public EventsCardHolder(View view) {
        super(view);


        this.title = (TextView) view.findViewById(R.id.eventsTitle);

    }


}