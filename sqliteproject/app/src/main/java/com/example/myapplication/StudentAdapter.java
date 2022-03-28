package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    private Context context;
    private int resource;
    private List<Student> studentList;

    public StudentAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.studentList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel viewHodel;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item_list_sv,parent,false);
            viewHodel = new ViewHodel();
            viewHodel.tvId = (TextView) convertView.findViewById(R.id.tvId);
            viewHodel.tvFullName = (TextView) convertView.findViewById(R.id.tvFullName);
            viewHodel.tvClass = (TextView) convertView.findViewById(R.id.tvClass);
            viewHodel.tvAddress = (TextView) convertView.findViewById(R.id.tvPhone);
            viewHodel.tvPhone = (TextView) convertView.findViewById(R.id.tvAddress);
            convertView.setTag(viewHodel);
        }
        else
            viewHodel = (ViewHodel) convertView.getTag();
        Student student = studentList.get(position);
        viewHodel.tvId.setText(String.valueOf(student.getId()));
        viewHodel.tvFullName.setText(student.getFullName());
        viewHodel.tvClass.setText(student.getClazz());
        viewHodel.tvAddress.setText(student.getAddress());
        viewHodel.tvPhone.setText(student.getPhone());

        return convertView;
    }

    class ViewHodel{
        private TextView tvId, tvFullName,tvClass,tvAddress,tvPhone;

    }
}
