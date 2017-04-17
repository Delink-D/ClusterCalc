package com.example.delink.clustercalc.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.delink.clustercalc.Constants;
import com.example.delink.clustercalc.R;
import com.example.delink.clustercalc.adapters.FirebaseViewHolderSubs;
import com.example.delink.clustercalc.model.Subjects;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectsFragment extends Fragment {

    public final String TAG = GradingFragment.class.getSimpleName();

    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    @Bind(R.id.recyclerviewcourses) RecyclerView mRecyclerview;

    public SubjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SUBJECTS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);

        ButterKnife.bind(this, view);

        setUpFirebaseAdapter();

        return view;
    }

    private void setUpFirebaseAdapter() {
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Subjects,FirebaseViewHolderSubs>(Subjects.class, R.layout.group_card, FirebaseViewHolderSubs.class, databaseReference) {
            @Override
            protected void populateViewHolder(FirebaseViewHolderSubs viewHolder, Subjects model, int position) {
                viewHolder.bindSubs(model);
            }
        };

        final FragmentActivity f = getActivity();
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new GridLayoutManager(f,2));
        mRecyclerview.setAdapter(firebaseRecyclerAdapter);
    }

}
