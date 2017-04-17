package com.example.delink.clustercalc.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.delink.clustercalc.Constants;
import com.example.delink.clustercalc.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupIIIFragment extends Fragment implements View.OnClickListener{

    @Bind(R.id.save_group_iii) Button mSaveBtn3;
    @Bind(R.id.grade_hist) EditText mHIST;
    @Bind(R.id.grade_geo) EditText mGEO;
    @Bind(R.id.grade_religious) EditText mReligion;

    private DatabaseReference databaseReference;

    ArrayList<String> groupC = new ArrayList<>();

    public GroupIIIFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_iii, container, false);

        ButterKnife.bind(this,view);

        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MYGRADES);

        mSaveBtn3.setOnClickListener(this);

        return view;
    }

    private void saveGroupIII() {
        String history = mHIST.getText().toString().trim();
        String geography = mGEO.getText().toString().trim();
        String religion = mReligion.getText().toString().trim();

        boolean validHistory = isValidHist(history);
        boolean validGeography = isValidGeo(geography);
        boolean validReligion = isValidReligion(religion);

        if (!validHistory || !validGeography || !validReligion) return;

        groupC.add(history);
        groupC.add(geography);
        groupC.add(religion);

        mHIST.setText(null);
        mGEO.setText(null);
        mReligion.setText(null);

        DatabaseReference mRef = databaseReference.child(Constants.FIREBASE_CHILD_MYGRADES_CHILD_2);
        mRef.setValue(groupC);

        Toast.makeText(getActivity(), "Your Group III has been Saved!", Toast.LENGTH_LONG).show();
    }

    private boolean isValidHist(String s) {
        if (s.length() > 2){
            mHIST.setError("Grade should be a letter or Followed by (+) / (-)");
            return false;
        }
        return true;
    }
    private boolean isValidGeo(String s) {
        if (s.length() > 2){
            mGEO.setError("Grade should be a letter or Followed by (+) / (-)");
            return false;
        }
        return true;
    }
    private boolean isValidReligion(String s) {
        if (s.length() > 2){
            mReligion.setError("Grade should be a letter or Followed by (+) / (-)");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveBtn3) {
            saveGroupIII();
        }
    }
}
