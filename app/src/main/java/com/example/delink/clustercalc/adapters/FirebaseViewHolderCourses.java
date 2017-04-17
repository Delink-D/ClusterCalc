package com.example.delink.clustercalc.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.delink.clustercalc.Constants;
import com.example.delink.clustercalc.R;
import com.example.delink.clustercalc.model.Courses;
import com.example.delink.clustercalc.ui.CoursesDetailsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseViewHolderCourses extends RecyclerView.ViewHolder implements View.OnClickListener {

    private View mView;
    private Context mContext;

    public FirebaseViewHolderCourses(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindCourses(Courses courses){
        TextView mCourseName = (TextView) mView.findViewById(R.id.course_name);
        TextView mClusterPoint = (TextView) mView.findViewById(R.id.cluster_point);

        mCourseName.setText(sliceString(courses.getName(), 33));
        mClusterPoint.setText("Cluster: " + courses.getCluster());
    }

    public String sliceString(String string, int maxLength){
        if (string.length() <= maxLength){
            return string;
        }else

            return string.substring(0, maxLength-3) + "...";
    }
    @Override
    public void onClick(View v) {
        final ArrayList<Courses> courses = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_COURSES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    courses.add(snapshot.getValue(Courses.class));
//                    Toast.makeText(mContext, "Print : " + snapshot.getValue(), Toast.LENGTH_SHORT).show();
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, CoursesDetailsActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("courses", Parcels.wrap(courses));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
