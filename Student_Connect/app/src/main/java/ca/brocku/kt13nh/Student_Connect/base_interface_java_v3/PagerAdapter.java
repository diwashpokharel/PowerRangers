package ca.brocku.kt13nh.Student_Connect.base_interface_java_v3;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import ca.brocku.kt13nh.Student_Connect.tab_components.InboxFragment;
import ca.brocku.kt13nh.Student_Connect.tab_components.QaFragment;
import ca.brocku.kt13nh.Student_Connect.tab_components.EventsFragment;


/*
* This is a simple pager adapter class specifically for tab navigation and position
* */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    //get position of the selected fragment
    @Override
    public Fragment getItem(int position) {
        //position of tabs
        switch (position) {
            case 0:
                InboxFragment tab1 = new InboxFragment("Inbox");
                return tab1;
            case 1:
                QaFragment tab2 = new QaFragment("QA");
                return tab2;
            default:
                EventsFragment tab3 = new EventsFragment("Events");
                return tab3;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}