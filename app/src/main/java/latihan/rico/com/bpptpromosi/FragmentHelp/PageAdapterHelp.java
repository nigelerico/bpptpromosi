package latihan.rico.com.bpptpromosi.FragmentHelp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapterHelp extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapterHelp(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                VisiMisiFragment tab1 = new VisiMisiFragment();
                return tab1;
            case 1:
                MottoFragment tab2 = new MottoFragment();
                return tab2;
            case 2:
                HubungiFragment tab3 = new HubungiFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}





