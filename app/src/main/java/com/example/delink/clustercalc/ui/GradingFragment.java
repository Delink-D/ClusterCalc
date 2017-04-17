package com.example.delink.clustercalc.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.delink.clustercalc.Constants;
import com.example.delink.clustercalc.R;
import com.example.delink.clustercalc.adapters.FirebaseViewHolder;
import com.example.delink.clustercalc.model.Grade;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradingFragment extends Fragment implements View.OnClickListener {

    public final String TAG = GradingFragment.class.getSimpleName();

    private DatabaseReference mDataReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerview) RecyclerView mRecyclerview;
    @Bind(R.id.btn)
    Button btn;

    public GradingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GRADES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grading, container, false);

        ButterKnife.bind(this, view);
        btn.setOnClickListener(this);

        setUpFirebaseAdapter();

        return view;
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Grade,FirebaseViewHolder>(Grade.class, R.layout.grade_item, FirebaseViewHolder.class, mDataReference) {
            @Override
            protected void populateViewHolder(FirebaseViewHolder viewHolder, Grade model, int position) {
                viewHolder.bindGrades(model);
            }
        };

        final FragmentActivity f = getActivity();
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(f));
        mRecyclerview.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == btn){
            Toast.makeText(getActivity(), "TOASTED!",Toast.LENGTH_SHORT).show();
        }
    }
}
