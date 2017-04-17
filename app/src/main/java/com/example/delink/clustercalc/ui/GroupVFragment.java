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
public class GroupVFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.save_group_v) Button mSaveBtn5;
    @Bind(R.id.grade_bs) EditText mBS;
    @Bind(R.id.grade_fr) EditText mFR;
    @Bind(R.id.grade_ger) EditText mGER;
    @Bind(R.id.grade_ar) EditText mAR;
    @Bind(R.id.grade_mus) EditText mMUS;

    private DatabaseReference databaseReference;

    ArrayList<String> groupE = new ArrayList<>();

    public GroupVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_group_v, container, false);

        ButterKnife.bind(this, view);

        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MYGRADES);

        mSaveBtn5.setOnClickListener(this);

        return view;
    }

    private void saveGroupV() {
        String business = mBS.getText().toString().trim();
        String french = mFR.getText().toString().trim();
        String german = mGER.getText().toString().trim();
        String arabic = mAR.getText().toString().trim();
        String music = mMUS.getText().toString().trim();

        groupE.add(business);
        groupE.add(french);
        groupE.add(german);
        groupE.add(arabic);
        groupE.add(music);

        mBS.setText(null);
        mFR.setText(null);
        mGER.setText(null);
        mAR.setText(null);
        mMUS.setText(null);

        DatabaseReference mRef = databaseReference.child(Constants.FIREBASE_CHILD_MYGRADES_CHILD_4);
        mRef.setValue(groupE);

        Toast.makeText(getActivity(), "Your Group V has been Saved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveBtn5) {
            saveGroupV();
        }
    }
}
