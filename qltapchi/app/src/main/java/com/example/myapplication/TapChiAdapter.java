package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class TapChiAdapter extends ArrayAdapter<TapChi> {

    private Context context;
    private int resource;
    private List<TapChi> tapChiList;

    public TapChiAdapter(Context context, int resource, List<TapChi> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.tapChiList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel viewHodel;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item_list_tap_chi,parent,false);
            viewHodel = new ViewHodel();
            viewHodel.tvId = (TextView) convertView.findViewById(R.id.tvId);
            viewHodel.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHodel.tvLoai = (TextView) convertView.findViewById(R.id.tvLoai);
            viewHodel.tvSoXB = (TextView) convertView.findViewById(R.id.tvSoXB);
            viewHodel.tvNhaXB = (TextView) convertView.findViewById(R.id.tvNhaXB);
            viewHodel.tvDonGia = (TextView) convertView.findViewById(R.id.tvDonGia);
            convertView.setTag(viewHodel);
        }
        else
            viewHodel = (ViewHodel) convertView.getTag();
        TapChi tapChi = tapChiList.get(position);
        viewHodel.tvId.setText("Mã TP: "+String.valueOf(tapChi.getId()));
        viewHodel.tvName.setText("Tên: "+ tapChi.getName());
        viewHodel.tvLoai.setText("Loại: "+ tapChi.getLoai());
        viewHodel.tvSoXB.setText("Số XB: "+ String.valueOf(tapChi.getSoXB()));
        viewHodel.tvNhaXB.setText("Nhà XB: "+ tapChi.getNhaXB());
        viewHodel.tvDonGia.setText("Giá: "+ String.valueOf(tapChi.getDonGia()));
        return convertView;
    }

    class ViewHodel{
        private TextView tvId, tvName, tvLoai, tvSoXB, tvNhaXB, tvDonGia;
    }
}
