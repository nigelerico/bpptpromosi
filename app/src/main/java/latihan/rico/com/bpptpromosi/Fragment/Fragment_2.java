package latihan.rico.com.bpptpromosi.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.miguelcatalan.materialsearchview.MaterialSearchView;

import latihan.rico.com.bpptpromosi.Activity.DetailActivity;

import latihan.rico.com.bpptpromosi.Adapter.AdapterList;
import latihan.rico.com.bpptpromosi.Model.ModelListSektor;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.R;


public class Fragment_2 extends Fragment {


     RecyclerView recyclerView;
     ArrayList <ModelListSektor> modelListSektors;
     ModelListSektor mModelListSektor;
     MaterialSearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_fragment_2, container, false);


        setHasOptionsMenu(true);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getContext()).getSupportActionBar().setTitle("List");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        searchView = (MaterialSearchView) view.findViewById(R.id.search_view);

        recyclerView = view.findViewById(R.id.recycler_view_list);
        LinearLayoutManager mGridLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mGridLayoutManager);

        modelListSektors = new ArrayList<>();
        mModelListSektor = new ModelListSektor(1,1,1,"Pantai Gemah", "Ngantang", "0810000", "Jono", "Keren", "yes", "Wisata Pantai");
        modelListSektors.add(mModelListSektor);
        mModelListSektor = new ModelListSektor(1,1,1,"Kampung Susu Dinasty", "Ngawi", "0810000", "Jaka", "Keren", "yes", "Wisata Buatan");
        modelListSektors.add(mModelListSektor);
        mModelListSektor = new ModelListSektor(1,1,1,"Pasanggrahan Agro Wilis", "Bululawang", "0810000", "Kim", "Keren", "yes", "Wisata Buatan");
        modelListSektors.add(mModelListSektor);

        AdapterList myAdapter = new AdapterList(getContext(), modelListSektors);
        recyclerView.setAdapter(myAdapter);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView.setMenuItem(searchItem);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
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
