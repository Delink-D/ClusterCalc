package com.example.delink.clustercalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.delink.clustercalc.R;
import com.example.delink.clustercalc.model.Subjects;

import java.util.ArrayList;

public class FirebaseViewHolderSubs extends RecyclerView.ViewHolder {

    private View mView;
    private Context mContext;

    public FirebaseViewHolderSubs(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindSubs(Subjects subjects){
        ArrayAdapter<String> adapter;
        ArrayList<String> mArraylist = new ArrayList<>();
        ListView mList = (ListView) mView.findViewById(R.id.group_list);

        adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1,mArraylist);

        mArraylist.add(subjects.getSub1());
        mArraylist.add(subjects.getSub2());
        mArraylist.add(subjects.getSub3());

        if (subjects.getSub4() != null && subjects.getSub5() != null){
            mArraylist.add(subjects.getSub4());
            mArraylist.add(subjects.getSub5());
        }

        mList.setAdapter(adapter);
    }
}