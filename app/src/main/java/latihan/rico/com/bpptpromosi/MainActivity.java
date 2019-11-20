package latihan.rico.com.bpptpromosi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import latihan.rico.com.bpptpromosi.Activity.MoreActivity;
import latihan.rico.com.bpptpromosi.Activity.MoreActivityList;
import latihan.rico.com.bpptpromosi.Fragment.Fragment_1;
import latihan.rico.com.bpptpromosi.Fragment.Fragment_2;
import latihan.rico.com.bpptpromosi.Fragment.Fragment_3;
import latihan.rico.com.bpptpromosi.Fragment.Fragment_4;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener, MoreActivity.BottomSheetListener, MoreActivityList.BottomSheetListener {

    BottomNavigationView bottomNavigationView;
    TextView tv;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  //     toolbar = getSupportActionBar();

//       toolbar.hide();

        loadFragment(new Fragment_1());// inisialisasi BottomNavigaionView
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
                fragment = new Fragment_1();
                break;
            case R.id.list_menu:
             //   toolbar.hide();
                fragment = new Fragment_2();
                break;
            case R.id.search_menu:
              //  toolbar.show();
                fragment = new Fragment_3();
                break;
            case R.id.help_menu:
               // toolbar.show();
                fragment = new Fragment_4();
                break;
        }   return loadFragment(fragment);
    }

    @Override
    public void onButtonClicked(String text) {

    }

    @Override
    public void onCoba(String text) {

    }


//    @Override
//    public void onListSelected(int mPosition) {
//
//    }
}