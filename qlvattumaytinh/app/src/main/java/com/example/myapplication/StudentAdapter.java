package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

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
            viewHodel.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHodel.tvDonViTinh = (TextView) convertView.findViewById(R.id.tvDonViTinh);
            viewHodel.tvDonGia = (TextView) convertView.findViewById(R.id.tvDonGia);
            convertView.setTag(viewHodel);
        }
        else
            viewHodel = (ViewHodel) convertView.getTag();
        Student student = studentList.get(position);
        viewHodel.tvId.setText("Mã TP: "+String.valueOf(student.getId()));
        viewHodel.tvName.setText("Tên: "+student.getName());
        viewHodel.tvDonViTinh.setText("DVT: "+student.getDonViTinh());
        viewHodel.tvDonGia.setText("Giá: "+student.getDonGia());
        return convertView;
    }

    class ViewHodel{
        private TextView tvId, tvName, tvDonViTinh, tvDonGia;
    }
}
