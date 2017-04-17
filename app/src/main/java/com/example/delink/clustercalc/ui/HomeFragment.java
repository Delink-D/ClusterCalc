package com.example.delink.clustercalc.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.delink.clustercalc.Constants;
import com.example.delink.clustercalc.R;
import com.example.delink.clustercalc.adapters.FirebaseViewHolderCourses;
import com.example.delink.clustercalc.model.Courses;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    @Bind(R.id.courseHomeRecyclerView) RecyclerView mRecyclerView;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_COURSES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();

        return view;
    }

    private void setUpFirebaseAdapter() {
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Courses,FirebaseViewHolderCourses>(Courses.class,R.layout.course_item,FirebaseViewHolderCourses.class,databaseReference) {
            @Override
            protected void populateViewHolder(FirebaseViewHolderCourses viewHolder, Courses model, int position) {
                viewHolder.bindCourses(model);
            }
        };

        final FragmentActivity f = getActivity();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(f));
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}
