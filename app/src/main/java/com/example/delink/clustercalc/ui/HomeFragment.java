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
import android.widget.TextView;
import android.widget.Toast;

import com.example.delink.clustercalc.Constants;
import com.example.delink.clustercalc.R;
import com.example.delink.clustercalc.adapters.FirebaseViewHolderCourses;
import com.example.delink.clustercalc.adapters.FirebaseViewHolderGrades;
import com.example.delink.clustercalc.model.Courses;
import com.example.delink.clustercalc.model.Grades;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static final String TAG = HomeFragment.class.getSimpleName();

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference1;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter1;

    private ArrayList<String> grades = new ArrayList<>();

    @Bind(R.id.courseHomeRecyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.home_math_grade) TextView mMath;
    @Bind(R.id.home_english_grade) TextView mEnglish;
    @Bind(R.id.home_kiswahili_grade) TextView mKIswahili;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_COURSES);
        databaseReference1 = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MYGRADES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        setGetGrades();

        setUpFirebaseAdapter();

        return view;
    }

    private void setGetGrades() {

        databaseReference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                GenericTypeIndicator<List<String>> genericTypeIndicator =new GenericTypeIndicator<List<String>>(){};
                List<String> taskDesList=dataSnapshot.getValue(genericTypeIndicator);

                for(int i=0;i<taskDesList.size();i++)
                    if (!taskDesList.get(i).equals("")) grades.add(taskDesList.get(i));

                mMath.setText(grades.get(0));
                mEnglish.setText(grades.get(1));
                mKIswahili.setText(grades.get(2));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
