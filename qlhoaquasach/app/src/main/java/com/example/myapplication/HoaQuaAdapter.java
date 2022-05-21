package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class HoaQuaAdapter extends ArrayAdapter<HoaQuaSach> {

    private Context context;
    private int resource;
    private List<HoaQuaSach> hoaQuaSachList;

    public HoaQuaAdapter(Context context, int resource, List<HoaQuaSach> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.hoaQuaSachList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel viewHodel;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item_list_hoa_qua_sach,parent,false);
            viewHodel = new ViewHodel();
            viewHodel.tvId = (TextView) convertView.findViewById(R.id.tvId);
            viewHodel.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHodel.tvLoai = (TextView) convertView.findViewById(R.id.tvLoai);
            viewHodel.tvDonViTinh = (TextView) convertView.findViewById(R.id.tvDonViTinh);
            viewHodel.tvDonGia = (TextView) convertView.findViewById(R.id.tvDonGia);
            viewHodel.tvNoiSX = (TextView) convertView.findViewById(R.id.tvNoiSX);
            convertView.setTag(viewHodel);
        }
        else
            viewHodel = (ViewHodel) convertView.getTag();
        HoaQuaSach hoaQuaSach = hoaQuaSachList.get(position);
        viewHodel.tvId.setText("Mã: "+String.valueOf(hoaQuaSach.getId()));
        viewHodel.tvName.setText("Tên: "+ hoaQuaSach.getName());
        viewHodel.tvLoai.setText("Loại: "+ hoaQuaSach.getLoai());
        viewHodel.tvDonViTinh.setText("Đơn Vị Tính: "+ hoaQuaSach.getDonViTinh());
        viewHodel.tvDonGia.setText("Giá: "+ String.valueOf(hoaQuaSach.getDonGia()));
        viewHodel.tvNoiSX.setText("Nơi SX: "+ hoaQuaSach.getNoiSX());
        return convertView;
    }

    class ViewHodel{
        private TextView tvId, tvName, tvLoai, tvDonViTinh, tvDonGia,tvNoiSX;
    }
}
