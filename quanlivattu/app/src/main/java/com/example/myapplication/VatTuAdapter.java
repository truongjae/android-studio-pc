package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class VatTuAdapter extends ArrayAdapter<VatTu> {

    private Context context;
    private int resource;
    private List<VatTu> vatTuList;

    public VatTuAdapter(Context context, int resource, List<VatTu> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.vatTuList = objects;
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
            viewHodel.tvHangSanXuat = (TextView) convertView.findViewById(R.id.tvHangSanXuat);
            convertView.setTag(viewHodel);
        }
        else
            viewHodel = (ViewHodel) convertView.getTag();
        VatTu vatTu = vatTuList.get(position);
        viewHodel.tvId.setText(String.valueOf(vatTu.getId()));
        viewHodel.tvName.setText("Tên: "+ vatTu.getName());
        viewHodel.tvDonViTinh.setText("Đơn vị tính: "+ vatTu.getDonViTinh());
        viewHodel.tvDonGia.setText("Đơn giá: "+ vatTu.getDonGia());
        viewHodel.tvHangSanXuat.setText("Hãng SX: "+ vatTu.getHangSanXuat());

        return convertView;
    }

    class ViewHodel{
        private TextView tvId, tvName,tvDonViTinh,tvDonGia, tvHangSanXuat;
    }
}
