package com.example.delink.clustercalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.delink.clustercalc.R;
import com.example.delink.clustercalc.model.Grades;

public class FirebaseViewHolderGrades extends RecyclerView.ViewHolder{

    private View mView;
    private Context mContext;

    public FirebaseViewHolderGrades(View itemView) {
        super(itemView);
        this.mView = itemView;
        this.mContext = itemView.getContext();
    }

    public void bindGrades(Grades grades){
        TextView mMath = (TextView) mView.findViewById(R.id.home_math_grade);
        TextView mEnglish = (TextView) mView.findViewById(R.id.home_english_grade);
        TextView mKIswahili = (TextView) mView.findViewById(R.id.home_kiswahili_grade);

        mMath.setText("< " + grades.getGroup().get(0));
        mEnglish.setText("> " + grades.getGroup().get(1));
        mKIswahili.setText(grades.getGroup().get(3));

        Toast.makeText(mContext, ">>>" + grades.getGroup().get(0), Toast.LENGTH_SHORT).show();
    }
}
