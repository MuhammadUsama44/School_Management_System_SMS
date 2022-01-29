package com.example.schoolmanagementsystemsms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListCourseAdapter extends BaseAdapter {
    List<School_Modal> School_global_data = new ArrayList<>();
    Context CONTEXT;
    LayoutInflater inflater;

    public ListCourseAdapter(Context c, List<School_Modal> data) {
        CONTEXT = c;
        School_global_data = data;
        inflater = (LayoutInflater) CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {

        return School_global_data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = inflater.inflate(R.layout.courserow, null);

        School_Modal SM = School_global_data.get(position);

        int i = position;
        i++;

        String id = String.valueOf(i);

        TextView ID = v.findViewById(R.id.text_1);
        ID.setText(id);

        TextView NAME = v.findViewById(R.id.coursename);
        NAME.setText(SM.getNAME());


        TextView iddd = v.findViewById(R.id.IDDtext);
        iddd.setText(SM.getID());

        return v;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
