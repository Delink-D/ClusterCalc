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
public class GroupIVFragment extends Fragment implements View.OnClickListener{

    @Bind(R.id.save_group_iv) Button mSaveBtn4;
    @Bind(R.id.grade_agri) EditText mAGRI;
    @Bind(R.id.grade_art) EditText mART;
    @Bind(R.id.grade_hs) EditText mHS;
    @Bind(R.id.grade_comp) EditText mCOMP;
    @Bind(R.id.grade_av) EditText mAV;

    private DatabaseReference databaseReference;

    ArrayList<String> groupD = new ArrayList<>();

    public GroupIVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_iv, container, false);

        ButterKnife.bind(this,view);

        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MYGRADES);

        mSaveBtn4.setOnClickListener(this);

        return view;
    }

    private void saveGroupIV() {
        String agriculture = mAGRI.getText().toString().trim();
        String art = mART.getText().toString().trim();
        String homescience = mHS.getText().toString().trim();
        String computer = mCOMP.getText().toString().trim();
        String aviation = mAV.getText().toString().trim();

//        boolean validHistory = isValidHist(agriculture);
//        boolean validGeography = isValidGeo(art);
//        boolean validReligion = isValidReligion(religion);
//
//        if (!validHistory || !validGeography || !validReligion) return;

        groupD.add(agriculture);
        groupD.add(art);
        groupD.add(homescience);
        groupD.add(computer);
        groupD.add(aviation);

        mAGRI.setText(null);
        mART.setText(null);
        mHS.setText(null);
        mCOMP.setText(null);
        mAV.setText(null);

        DatabaseReference mRef = databaseReference.child(Constants.FIREBASE_CHILD_MYGRADES_CHILD_3);
        mRef.setValue(groupD);

        Toast.makeText(getActivity(), "Your Group III has been Saved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveBtn4) {
            saveGroupIV();
        }
    }
}
