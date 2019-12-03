package latihan.rico.com.bpptpromosi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import latihan.rico.com.bpptpromosi.Fragment.ListFragment;
import latihan.rico.com.bpptpromosi.Fragment.SearchFragment;
import latihan.rico.com.bpptpromosi.Fragment.RealisasiFragment;
import latihan.rico.com.bpptpromosi.FragmentHelp.HelpFragment;
import latihan.rico.com.bpptpromosi.Fragment.HomeFragment;
import latihan.rico.com.bpptpromosi.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener, MoreActivity.BottomSheetListener{

    BottomNavigationView bottomNavigationView;
    TextView tv;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  //     toolbar = getSupportActionBar();

//       toolbar.hide();

        loadFragment(new HomeFragment());// inisialisasi BottomNavigaionView
        bottomNavigationView = findViewById(R.id.bottom_navigation);// beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        toolbar.setTitle("Home");
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }   return false;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;   switch (menuItem.getItemId()){
            case R.id.home_menu:
              //  toolbar.hide();
                fragment = new HomeFragment();
                break;
            case R.id.list_menu:
             //   toolbar.hide();
                fragment = new ListFragment();
                break;
            case R.id.search_menu:
              //  toolbar.show();
                fragment = new SearchFragment();
                break;
            case R.id.realisasi_menu:
                //  toolbar.show();
                fragment = new RealisasiFragment();
                break;
            case R.id.help_menu:
               // toolbar.show();
                fragment = new HelpFragment();
                break;
        }   return loadFragment(fragment);
    }

    @Override
    public void onButtonClicked(String text) {

    }

}