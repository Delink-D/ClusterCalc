package com.example.delink.clustercalc.ui;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.delink.clustercalc.R;
import com.example.delink.clustercalc.model.Courses;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CoursesDetailsActivity extends AppCompatActivity {
    private ArrayList<Courses> courses = new ArrayList<>();

    @Bind(R.id.details_course_name) TextView mCourseName;
    @Bind(R.id.details_course_cluster) TextView mCourseCluster;
//    @Bind(R.id.details_course_subjetcs) TextView mSubjetcs;
    int coursePosition;
    int courseCluster;
    String courseName;
//    Map<String,Object> subjects = new HashMap<>();
//    List<Object> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_details);

        ButterKnife.bind(this);

        coursePosition = getIntent().getIntExtra("position",0);
        courses = Parcels.unwrap(getIntent().getParcelableExtra("courses"));

        courseName = courses.get(coursePosition).getName();
        courseCluster = courses.get(coursePosition).getCluster();

//        subjects = courses.get(coursePosition).getSubjects();
//        mSubjetcs.setText(">> " + subjects.get(1));

//        Toast.makeText(this, ">> " + courses.get(coursePosition).getName() + ">> " + courseCluster, Toast.LENGTH_SHORT).show();
        mCourseName.setText(courseName);
        mCourseCluster.setText(getString(R.string.cluster_points, courseCluster));
    }
}
