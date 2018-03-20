package ca.brocku.kt13nh.Student_Connect.base_interface_java_v3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import ca.brocku.kt13nh.Student_Connect.tab_components.EventsFragment;
import ca.brocku.kt13nh.Student_Connect.tab_components.InboxFragment;
import ca.brocku.kt13nh.Student_Connect.tab_components.QaFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InboxFragment("Inbox");
            case 1:
                return new QaFragment("QA");
            case 2:
                return new EventsFragment("Events");
            default:
                return new InboxFragment("Inbox");
        }
    }

    public int getCount() {
        return this.mNumOfTabs;
    }
}
