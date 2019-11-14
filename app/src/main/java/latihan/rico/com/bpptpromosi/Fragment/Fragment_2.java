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
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.R;


public class Fragment_2 extends Fragment {


    RecyclerView recyclerView;
    ArrayList <ModelProspect> modelProspectList;
    ModelProspect mModelProspect;
    MaterialSearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_fragment_2, container, false);



        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Material Search");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        searchView = (MaterialSearchView) view.findViewById(R.id.search_view);

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


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return;
    }


}
