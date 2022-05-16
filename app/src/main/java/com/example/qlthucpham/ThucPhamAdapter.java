package com.example.qlthucpham;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ThucPhamAdapter extends ArrayAdapter<ThucPham> {
    private Context context;
    private int resource;
    private List<ThucPham> thucPhamList;
    public ThucPhamAdapter(Context context, int resource, List<ThucPham> objects) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.thucPhamList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel viewHodel;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item_list_thuc_pham,parent,false);
            viewHodel = new ViewHodel();
            viewHodel.tvId = (TextView) convertView.findViewById(R.id.tvId);
            viewHodel.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHodel.tvDonViTinh = (TextView) convertView.findViewById(R.id.tvDonViTinh);
            viewHodel.tvDonGia = (TextView) convertView.findViewById(R.id.tvDonGia);
            convertView.setTag(viewHodel);
        }
        else
            viewHodel = (ViewHodel) convertView.getTag();
        ThucPham thucPham = thucPhamList.get(position);
        viewHodel.tvId.setText("Mã TP: "+String.valueOf(thucPham.getId()));
        viewHodel.tvName.setText("Tên SP: "+thucPham.getName());
        viewHodel.tvDonViTinh.setText("ĐVT: "+thucPham.getDonViTinh());
        viewHodel.tvDonGia.setText("Đơn Giá: "+String.valueOf(thucPham.getDonGia()));
        return convertView;
    }

    class ViewHodel{
        private TextView tvId, tvName,tvDonViTinh, tvDonGia;
    }
}
