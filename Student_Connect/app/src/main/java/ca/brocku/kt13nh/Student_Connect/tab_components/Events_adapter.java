package ca.brocku.kt13nh.Student_Connect.tab_components;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import ca.brocku.kt13nh.Student_Connect.R;

public class Events_adapter extends
        RecyclerView.Adapter<EventsCardHolder> {
    private ArrayList<String> arrayList;
    private Context context;


    public Events_adapter(Context context,
                                ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }

    @Override
    public void onBindViewHolder(EventsCardHolder holder,
                                 int position) {


        final EventsCardHolder mainHolder = (EventsCardHolder) holder;
        //Setting text over textview
        mainHolder.title.setText(arrayList.get(position));

    }

    @Override
    public EventsCardHolder onCreateViewHolder(
            ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.events_row, viewGroup, false);
        EventsCardHolder mainHolder = new EventsCardHolder(mainGroup) {
            @Override
            public String toString() {
                return super.toString();
            }
        };

        final View.OnClickListener mOnClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //System.out.println("Hellowoeld");
                //Toast.makeText(context, "hfhjgnef", Toast.LENGTH_SHORT).show();
            }


        };

        final View.OnLongClickListener longClickListener= new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {
                //    showChangeLangDialog();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.menu_add);
                dialog.show();
                return false;
            }
        };

        mainGroup.setOnLongClickListener(longClickListener);
        mainGroup.setOnClickListener(mOnClickListener);


        return mainHolder;

    }


}