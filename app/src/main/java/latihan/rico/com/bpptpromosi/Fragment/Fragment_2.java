package latihan.rico.com.bpptpromosi.Fragment;


import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;


import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Activity.DetailActivity;
import latihan.rico.com.bpptpromosi.Adapter.AdapterList;
import latihan.rico.com.bpptpromosi.Adapter.AdapterProspect;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.R;


public class Fragment_2 extends Fragment {


    RecyclerView recyclerView;
    ArrayList <ModelProspect> modelProspectList;
    ModelProspect mModelProspect;
    ImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_fragment_2, container, false);


        imageView= view.findViewById(R.id.ic_search);
        recyclerView = view.findViewById(R.id.recycler_view_list);
        LinearLayoutManager mGridLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mGridLayoutManager);

        modelProspectList = new ArrayList<>();
        mModelProspect = new ModelProspect(1,"budi","sawah","pertanian",R.drawable.ic_launcher_background);
        modelProspectList.add(mModelProspect);
        mModelProspect = new ModelProspect(1,"laksmi","sawah","pertanian",R.drawable.ic_launcher_background);
        modelProspectList.add(mModelProspect);
        mModelProspect = new ModelProspect(1,"cindy","sawah","pertanian",R.drawable.ic_launcher_background);
        modelProspectList.add(mModelProspect);

        AdapterList myAdapter = new AdapterList(getContext(), modelProspectList);
        recyclerView.setAdapter(myAdapter);
        setHasOptionsMenu(true);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getContext(), SearchActivity.class);
                startActivity(mIntent);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Cari");
        super.onCreateOptionsMenu(menu,inflater);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //method
                return false;
            }

        });

    }


}
