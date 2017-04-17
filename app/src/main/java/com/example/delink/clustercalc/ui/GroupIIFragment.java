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
public class GroupIIFragment extends Fragment implements View.OnClickListener{

    @Bind(R.id.save_group_ii) Button mSaveBtn2;
    @Bind(R.id.grade_phy) EditText mPHY;
    @Bind(R.id.grade_chem) EditText mCHEM;
    @Bind(R.id.grade_bio) EditText mBIO;

    private DatabaseReference databaseReference;

    ArrayList<String> groupB = new ArrayList<>();

    public GroupIIFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_ii, container, false);

        ButterKnife.bind(this,view);

        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MYGRADES);

        mSaveBtn2.setOnClickListener(this);
        return view;
    }

    private void saveGroupII() {
        String physics = mPHY.getText().toString().trim();
        String chemistry = mCHEM.getText().toString().trim();
        String biology = mBIO.getText().toString().trim();

        boolean validPhysics = isValidPhy(physics);
        boolean validChemistry = isValidChem(chemistry);
        boolean validBiology = isValidBio(biology);

        if (!validPhysics || !validChemistry || !validBiology) return;

        groupB.add(physics);
        groupB.add(chemistry);
        groupB.add(biology);

        mPHY.setText(null);
        mCHEM.setText(null);
        mBIO.setText(null);

        DatabaseReference mRef = databaseReference.child(Constants.FIREBASE_CHILD_MYGRADES_CHILD_1);
        mRef.setValue(groupB);

        Toast.makeText(getActivity(), "Your Group I has been Saved!", Toast.LENGTH_LONG).show();
    }

    private boolean isValidPhy(String s) {
        if (s.length() > 2){
            mPHY.setError("Grade should be a letter or Followed by (+) / (-)");
            return false;
        }
        return true;
    }
    private boolean isValidChem(String s) {
        if (s.length() > 2){
            mCHEM.setError("Grade should be a letter or Followed by (+) / (-)");
            return false;
        }
        return true;
    }
    private boolean isValidBio(String s) {
        if (s.length() > 2){
            mBIO.setError("Grade should be a letter or Followed by (+) / (-)");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveBtn2) {
            saveGroupII();
        }
    }
}
