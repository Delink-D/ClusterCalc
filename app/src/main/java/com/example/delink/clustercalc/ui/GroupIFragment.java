package com.example.delink.clustercalc.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
public class GroupIFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.save_group_i) Button mSaveBtn1;
    @Bind(R.id.grade_mat) EditText mMAT;
    @Bind(R.id.grade_eng) EditText mENG;
    @Bind(R.id.grade_kis) EditText mKIS;

    private DatabaseReference databaseReference;

    ArrayList<String> groupA = new ArrayList<>();

    public GroupIFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_i, container, false);

        ButterKnife.bind(this, view);

        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MYGRADES);

        mSaveBtn1.setOnClickListener(this);

        return view;
    }

    private void saveGroupI() {
        String math = mMAT.getText().toString().trim();
        String english = mENG.getText().toString().trim();
        String kiswahili = mKIS.getText().toString().trim();

        boolean validMath = isValidMath(math);
        boolean validEnglish = isValidEng(english);
        boolean validKiswahili = isValidKis(kiswahili);

        if (!validMath || !validEnglish || !validKiswahili) return;

//        Toast.makeText(getActivity(), validMath + " >> " + validEnglish + " >> " + validKiswahili, Toast.LENGTH_SHORT).show();

        groupA.add(math);
        groupA.add(english);
        groupA.add(kiswahili);

        mMAT.setText(null);
        mENG.setText(null);
        mKIS.setText(null);

//        Toast.makeText(getActivity(), groupA.toString(), Toast.LENGTH_SHORT).show();

        DatabaseReference dref = databaseReference.child(Constants.FIREBASE_CHILD_MYGRADES_CHILD_0);
        dref.setValue(groupA);

        Toast.makeText(getActivity(), "Your Group I has been Saved!", Toast.LENGTH_LONG).show();
    }

    private boolean isValidMath(String s) {
        if (s.length() > 2){
            mMAT.setError("Grade should be a letter or Followed by (+) / (-)");
            return false;
        }
//        else if ((!s.equals("A")) || (!s.equals("B")) || (!s.equals("C")) || (!s.equals("D")) || (!s.equals("E"))){
//            mMAT.setError("Grade should be A - E or Followed by (+) / (-)");
//            return false;
//        }
        return true;
    }
    private boolean isValidEng(String s) {
        if (s.length() > 2){
            mENG.setError("Grade should be a letter or Followed by (+) / (-)");
            return false;
        }
//        else if ((!s.equals("A")) || (!s.equals("B")) || (!s.equals("C")) || (!s.equals("D")) || (!s.equals("E"))){
//            mENG.setError("Grade should be A - E or Followed by (+) / (-)");
//            return false;
//        }
        return true;
    }
    private boolean isValidKis(String s) {
        if (s.length() > 2){
            mKIS.setError("Grade should be a letter or Followed by (+) / (-)");
            return false;
        }
//        else if ((!s.equals("A")) || (!s.equals("B")) || (!s.equals("C")) || (!s.equals("D")) || (!s.equals("E"))){
//            mKIS.setError("Grade should be A - E or Followed by (+) / (-)");
//            return false;
//        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveBtn1){
            saveGroupI();
        }
    }
}
