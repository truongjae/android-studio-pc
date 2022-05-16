package com.example.NguyenGiaTruong_08D4800065;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.NguyenGiaTruong_08D480065.R;

import java.util.List;

public class MayTinhAdapterNguyenGiaTruong extends ArrayAdapter<MayTinhNguyenGiaTruong> {

    private Context context;
    private int resource;
    private List<MayTinhNguyenGiaTruong> mayTinhNguyenGiaTruongList;

    public MayTinhAdapterNguyenGiaTruong(Context context, int resource, List<MayTinhNguyenGiaTruong> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.mayTinhNguyenGiaTruongList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel viewHodel;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item_list_mt,parent,false);
            viewHodel = new ViewHodel();
            viewHodel.tvId = (TextView) convertView.findViewById(R.id.tvId);
            viewHodel.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHodel.tvLoaiMayTinh = (TextView) convertView.findViewById(R.id.tvLoaiMayTinh);
            viewHodel.tvHangSX = (TextView) convertView.findViewById(R.id.tvHangSX);
            viewHodel.tvNamSX = (TextView) convertView.findViewById(R.id.tvNamSX);
            viewHodel.tvDonGia = (TextView) convertView.findViewById(R.id.tvDonGia);
            viewHodel.tvSoLuong = (TextView) convertView.findViewById(R.id.tvSoLuong);
            convertView.setTag(viewHodel);
        }
        else
            viewHodel = (ViewHodel) convertView.getTag();
        MayTinhNguyenGiaTruong mayTinhNguyenGiaTruong = mayTinhNguyenGiaTruongList.get(position);
        viewHodel.tvId.setText(String.valueOf(mayTinhNguyenGiaTruong.getId()));
        viewHodel.tvName.setText("Tên: "+ mayTinhNguyenGiaTruong.getName());
        viewHodel.tvLoaiMayTinh.setText("Loại: "+ mayTinhNguyenGiaTruong.getLoaiMayTinh());
        viewHodel.tvHangSX.setText("Hãng SX: "+ mayTinhNguyenGiaTruong.getHangSX());
        viewHodel.tvNamSX.setText("Năm SX: "+ String.valueOf(mayTinhNguyenGiaTruong.getNamSX()));
        viewHodel.tvDonGia.setText("Đơn Giá: "+ String.valueOf(mayTinhNguyenGiaTruong.getDonGia()));
        viewHodel.tvSoLuong.setText("Số Lượng: "+ String.valueOf(mayTinhNguyenGiaTruong.getSoLuong()));
        return convertView;
    }

    class ViewHodel{
        private TextView tvId, tvName, tvLoaiMayTinh,tvHangSX, tvNamSX, tvDonGia, tvSoLuong;
    }
}
