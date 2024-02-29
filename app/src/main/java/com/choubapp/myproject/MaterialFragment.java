package com.choubapp.myproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MaterialFragment extends Fragment implements UpdateRecyclerView {

    private RecyclerView recyclerView, recycleView2;
    private StaticRvAdapter staticRvAdapter;

    ArrayList<DynamicRVModel> items = new ArrayList();
    DynamicRVAdapter dynamicRVAdapter;
    ImageView logout_btn;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_material, container, false);

        final ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.paper,"PAPER"));
        item.add(new StaticRvModel(R.drawable.plastic,"PLASTIC"));
        item.add(new StaticRvModel(R.drawable.glass,"GLASS"));
        item.add(new StaticRvModel(R.drawable.bulbs,"BULBS"));
        item.add(new StaticRvModel(R.drawable.metal,"METAL"));
        item.add(new StaticRvModel(R.drawable.ewaste,"E-WASTE"));

        recyclerView = root.findViewById(R.id.rv_1); // get reference of recycle view
        staticRvAdapter = new StaticRvAdapter(item, getActivity(), this );
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.
                HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter); // set adapter to recycle view

        items = new ArrayList<>();

        recycleView2 = root.findViewById(R.id.rv_2);
        dynamicRVAdapter = new DynamicRVAdapter(items);
        recycleView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.
                VERTICAL, false));
        recycleView2.setAdapter(dynamicRVAdapter); // set adapter to recycle view

        logout_btn = root.findViewById(R.id.logout_btn);
        mAuth = FirebaseAuth.getInstance();

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mAuth.getCurrentUser()!= null)
                mAuth.signOut();
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
                getActivity().finish();


            }
        });


        return root;
    }

    @Override
    public void callback(int position, ArrayList<DynamicRVModel> items) {
        dynamicRVAdapter = new DynamicRVAdapter(items);
        dynamicRVAdapter.notifyDataSetChanged();
        recycleView2.setAdapter(dynamicRVAdapter);

    }


}