package latihan.rico.com.bpptpromosi.Fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_fragment_1, container, false);

        recyclerView1 = view.findViewById(R.id.recycler_view);
        recyclerView2 = view.findViewById(R.id.recycler_view2);
        toolbar_bppt = view.findViewById(R.id.toolbar);
        toolbar_bppt.setTitle("Bppt Promosi");



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

}
