package com.choubapp.myproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;


public class TrackerFragment extends Fragment {

    Button add;
    ImageView logout_btn;
    FirebaseAuth mAuth;

    RecyclerView recyclerView3;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    // variable of fire store recycler adapter
    FirestoreRecyclerAdapter<firebasemodel, TrackerViewHolder> trackerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate (R.layout.fragment_tracker, container, false);

        add = root.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),addnote.class);
                startActivity(intent);
            }
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();


       Query query =firebaseFirestore.collection("trackers").
               document(firebaseUser.getUid()).collection("mytrackers").
               orderBy("date",Query.Direction.ASCENDING);

       // pass data to recycler view
       FirestoreRecyclerOptions<firebasemodel> allusertrackers =
               new FirestoreRecyclerOptions.Builder<firebasemodel>().
                       setQuery(query,firebasemodel.class).build();

       trackerAdapter = new FirestoreRecyclerAdapter<firebasemodel,
               TrackerViewHolder>(allusertrackers) {
           @Override
           protected void onBindViewHolder(@NonNull @NotNull TrackerViewHolder holder,
                                           int position, @NonNull @NotNull firebasemodel model) {

               ImageView menupop=holder.itemView.findViewById(R.id.menupop);

               holder.title.setText(model.getMaterial());
               holder.item.setText(model.getItem());
               holder.date.setText(model.getDate());
               holder.notes.setText(model.getNote());

               String docId=trackerAdapter.getSnapshots().getSnapshot(position).getId();


               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       Intent intent=new Intent(v.getContext(), trackerdetails.class);
                       // pass data
                       intent.putExtra("material",model.getMaterial());
                       intent.putExtra("item",model.getItem());
                       intent.putExtra("date",model.getDate());
                       intent.putExtra("notes",model.getNote());
                       intent.putExtra("trackerId",docId);

                       v.getContext().startActivity(intent);

                   }
               });

               menupop.setOnClickListener(new View.OnClickListener() {
                   @RequiresApi(api = Build.VERSION_CODES.M)
                   @Override
                   public void onClick(View v) {

                       PopupMenu popupMenu= new PopupMenu(v.getContext(),v);
                       popupMenu.setGravity(Gravity.END);
                       popupMenu.getMenu().add("Edit").setOnMenuItemClickListener
                               (new MenuItem.OnMenuItemClickListener() {
                           @Override
                           public boolean onMenuItemClick(MenuItem item) {

                               Intent intent=new Intent(v.getContext(), edittrackeractivity.class);
                               // pass data
                               intent.putExtra("material",model.getMaterial());
                               intent.putExtra("item",model.getItem());
                               intent.putExtra("date",model.getDate());
                               intent.putExtra("notes",model.getNote());
                               intent.putExtra("trackerId",docId);
                               v.getContext().startActivity(intent);
                               return false;
                           }
                       });

                       popupMenu.getMenu().add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                           @Override
                           public boolean onMenuItemClick(MenuItem item) {
                               DocumentReference documentReference=firebaseFirestore.
                                       collection("trackers").document(firebaseUser.getUid()).
                                       collection("mytrackers").document(docId);
                               documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                   @Override
                                   public void onSuccess(Void aVoid) {
                                       Toast.makeText(v.getContext(),"Deleted", Toast.LENGTH_SHORT).show();
                                   }
                               }).addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(@NonNull @NotNull Exception e) {
                                       Toast.makeText(v.getContext(),"Failed to delete", Toast.LENGTH_SHORT).show();

                                   }
                               });

                               return false;
                           }
                       });

                       popupMenu.show();

                   }
               });

           }

           @NonNull
           @NotNull
           @Override
           public TrackerViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tracker_layout,parent,false);
               return new TrackerViewHolder(view);
           }
       };

       recyclerView3=root.findViewById(R.id.rv_3);
       staggeredGridLayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
       recyclerView3.setLayoutManager(staggeredGridLayoutManager);
       recyclerView3.setAdapter(trackerAdapter);


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

    public class TrackerViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title, item, date, notes;
        RelativeLayout constraintLayout;

        public TrackerViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            item=itemView.findViewById(R.id.item);
            date=itemView.findViewById(R.id.date);
            notes=itemView.findViewById(R.id.notes);
            constraintLayout = itemView.findViewById(R.id.constraintLayout3);
        }
    }

    // stopping and restarting an activity 

    @Override
    public void onStart() {
        super.onStart();
        trackerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(trackerAdapter!=null){
            trackerAdapter.startListening();
        }
    }
}