package com.example.delink.clustercalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.delink.clustercalc.R;
import com.example.delink.clustercalc.model.Grade;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    View mView;
    Context mContext;

    public FirebaseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindGrades(Grade grade){
        TextView mLabel = (TextView) mView.findViewById(R.id.grade_label);
        TextView mPoints = (TextView) mView.findViewById(R.id.grade_points);

        mLabel.setText(grade.getLabel());
        mPoints.setText(Integer.toString(grade.getPoints()));
    }
}
