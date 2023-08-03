package com.minhpt.and102_assignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuanLyAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<SanPham> list;
    SanPhamHelper sanPhamHelper;

    public QuanLyAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
        sanPhamHelper = new SanPhamHelper(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_sp, parent, false);
        TextView tv_tensp = convertView.findViewById(R.id.tv_tensp);
        TextView tv_giaban = convertView.findViewById(R.id.tv_giaban);
        TextView tv_soluong = convertView.findViewById(R.id.tv_soluong);
        TextView tv_edit = convertView.findViewById(R.id.tv_edit);
        TextView tv_delete = convertView.findViewById(R.id.tv_delete);
        tv_tensp.setText(list.get(position).tensp);
        tv_giaban.setText(list.get(position).giaban + " VNĐ");
        tv_soluong.setText("SL: " + list.get(position).soluong);
        tv_delete.setOnClickListener(v -> {
            new AlertDialog.Builder(context).setTitle("Cánh báo").setIcon(R.drawable.ic_warning).setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int masp = list.get(position).masp;
                            boolean check = sanPhamHelper.deleteSanPham(masp);
                            if (check) {
                                list.clear();
                                list = sanPhamHelper.getListSanPham();
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        });
        tv_edit.setOnClickListener(v -> {
            SanPham sanPham = list.get(position);
            Edit_SP(sanPham);
        });
        return convertView;
    }

    public void Edit_SP(SanPham sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.upadte_dialog, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        EditText edt_tensp = view.findViewById(R.id.edt_tensp);
        EditText edt_giaban = view.findViewById(R.id.edt_giaban);
        EditText edt_soluong = view.findViewById(R.id.edt_soluong);
        Button btn_update = view.findViewById(R.id.btn_update);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        edt_tensp.setText(sanPham.tensp);
        edt_giaban.setText(sanPham.giaban + "");
        edt_soluong.setText(sanPham.soluong + "");
        btn_update.setOnClickListener(v -> {
            String tensp = edt_tensp.getText().toString();
            String giaban = edt_giaban.getText().toString();
            String soluong = edt_soluong.getText().toString();
            if (tensp.isEmpty() || giaban.isEmpty() || soluong.isEmpty()) {
                Toast.makeText(context, "Vui lòng điền thông tin!",
                        Toast.LENGTH_SHORT).show();
            } else {
                SanPham sanPham1 = new SanPham(sanPham.masp, tensp, Integer.parseInt(giaban), Integer.parseInt(soluong));
                boolean check = sanPhamHelper.updateSanPham(sanPham1);
                if (check) {
                    list.clear();
                    list = sanPhamHelper.getListSanPham();
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Chỉnh sửa thành công!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Chỉnh sửa thất bại!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public void Add_SP(SanPham sanPham) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.add_dialog, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        EditText edt_tensp = view.findViewById(R.id.edt_tensp);
        EditText edt_giaban = view.findViewById(R.id.edt_giaban);
        EditText edt_soluong = view.findViewById(R.id.edt_soluong);
        Button btn_add = view.findViewById(R.id.btn_add);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);

        btn_add.setOnClickListener(v -> {
            String tensp = edt_tensp.getText().toString();
            String giaban = edt_giaban.getText().toString();
            String soluong = edt_soluong.getText().toString();
            if (tensp.isEmpty() || giaban.isEmpty() || soluong.isEmpty()) {
                Toast.makeText(context, "Vui lòng điền thông tin!",
                        Toast.LENGTH_SHORT).show();
            } else {
                SanPham sanPham1 = new SanPham(sanPham.masp, tensp, Integer.parseInt(giaban), Integer.parseInt(soluong));
                boolean check = sanPhamHelper.addSanPham(sanPham1);
                if (check) {
                    list.clear();
                    list = sanPhamHelper.getListSanPham();
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Thêm thành công!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thêm sửa thất bại!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}
