package latihan.rico.com.bpptpromosi.Fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import latihan.rico.com.bpptpromosi.Adapter.AdapterProspect;
import latihan.rico.com.bpptpromosi.Model.ModelProspect;
import latihan.rico.com.bpptpromosi.R;

public class Fragment_1 extends Fragment {


    Toolbar toolbar_bppt;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    ArrayList <ModelProspect> modelProspectList;
    ModelProspect mModelProspect;
    CollapsingToolbarLayout collapsingToolbarLayout;
    NestedScrollView scroll;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_fragment_1, container, false);

        //setHasOptionsMenu(true);

        recyclerView1 = view.findViewById(R.id.recycler_view);
        recyclerView2 = view.findViewById(R.id.recycler_view2);
        toolbar_bppt = view.findViewById(R.id.toolbar_bppt);
       // toolbar_bppt.setTitle("Bppt Promosi");
     //   ((AppCompatActivity)getContext()).setSupportActionBar(toolbar_bppt);

        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Home");

        scroll = view.findViewById(R.id.nested_scroll);

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(getContext(), R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);

        setMarginNaik();


        LinearLayoutManager mGridLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(mGridLayoutManager);

        LinearLayoutManager mGridLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(mGridLayoutManager2);





        modelProspectList = new ArrayList<>();
        mModelProspect = new ModelProspect(1,"tes1","sawah","pertanian",R.drawable.ic_launcher_background);
        modelProspectList.add(mModelProspect);
        mModelProspect = new ModelProspect(1,"tes1","sawah","pertanian",R.drawable.ic_launcher_background);
        modelProspectList.add(mModelProspect);
        mModelProspect = new ModelProspect(1,"tes1","sawah","pertanian",R.drawable.ic_launcher_background);
        modelProspectList.add(mModelProspect);


        AdapterProspect myAdapter = new AdapterProspect(getContext(), modelProspectList);
        recyclerView1.setAdapter(myAdapter);

        AdapterProspect myAdapter2 = new AdapterProspect(getContext(), modelProspectList);
        recyclerView2.setAdapter(myAdapter2);

        return view;

    }


    private void setMarginNaik(){
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) scroll.getLayoutParams();
        layoutParams.setMargins(0,0,0,170);
        scroll.setLayoutParams(layoutParams);

    }

}
