package com.example.myapplication;

import static com.example.myapplication.R.drawable.nam;
import static com.example.myapplication.R.drawable.nu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
            viewHodel.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            viewHodel.tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
            viewHodel.imgGender = (ImageView) convertView.findViewById(R.id.imgGender);
            convertView.setTag(viewHodel);
        }
        else
            viewHodel = (ViewHodel) convertView.getTag();
        Student student = studentList.get(position);
        viewHodel.tvId.setText("Mã SV: "+String.valueOf(student.getId()));
        viewHodel.tvFullName.setText("Họ tên: "+student.getFullName());
        viewHodel.tvPhone.setText("Điện thoại: "+student.getPhone());
        viewHodel.tvEmail.setText("Email: "+student.getEmail());
        if(student.getGender().equals("Nam"))
            viewHodel.imgGender.setImageResource(nam);
        else
            viewHodel.imgGender.setImageResource(nu);
        return convertView;
    }

    class ViewHodel{
        private TextView tvId, tvFullName,tvPhone,tvEmail;
        private ImageView imgGender;

    }
}
