package com.example.NguyenGiaTruong_08D4800065;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class NguyenGiaTruongXeAdapter extends ArrayAdapter<NguyenGiaTruong_Xe> {

    private Context context;
    private int resource;
    private List<NguyenGiaTruong_Xe> nguyenGiaTruongXeList;

    public NguyenGiaTruongXeAdapter(Context context, int resource, List<NguyenGiaTruong_Xe> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.nguyenGiaTruongXeList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel viewHodel;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item_list_sv,parent,false);

            viewHodel = new ViewHodel();
            viewHodel.tvBienKiemSoat = (TextView) convertView.findViewById(R.id.tvBienKiemSoat);
            viewHodel.tvTenChuXe = (TextView) convertView.findViewById(R.id.tvTenChuXe);
            viewHodel.tvHangXe = (TextView) convertView.findViewById(R.id.tvHangXe);
            viewHodel.tvTrongTai = (TextView) convertView.findViewById(R.id.tvTrongTai);
            viewHodel.tvHinhThucKinhDoanh = (TextView) convertView.findViewById(R.id.tvHinhThucKinhDoanh);
            convertView.setTag(viewHodel);
        }
        else
            viewHodel = (ViewHodel) convertView.getTag();
        NguyenGiaTruong_Xe nguyenGiaTruongXe = nguyenGiaTruongXeList.get(position);
        viewHodel.tvBienKiemSoat.setText(String.valueOf(nguyenGiaTruongXe.getBienKiemSoat()));
        viewHodel.tvTenChuXe.setText(nguyenGiaTruongXe.getTenChuXe());
        viewHodel.tvHangXe.setText(nguyenGiaTruongXe.getHangXe());
        viewHodel.tvTrongTai.setText(String.valueOf(nguyenGiaTruongXe.getTrongTai()));
        viewHodel.tvHinhThucKinhDoanh.setText(nguyenGiaTruongXe.getHinhThucKinhDoanh());

        return convertView;
    }

    class ViewHodel{
        private TextView tvBienKiemSoat, tvTenChuXe, tvHangXe, tvTrongTai, tvHinhThucKinhDoanh;

    }
}
