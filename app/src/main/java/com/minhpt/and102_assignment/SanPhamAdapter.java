package com.minhpt.and102_assignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private ArrayList<SanPham> list;
    private SanPhamDAO sanPhamDAO;
    String regex_int = "\\d+";


    public SanPhamAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
        sanPhamDAO = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_tensp.setText(list.get(position).tensp);
        holder.tv_giaban.setText(list.get(position).giaban + " VNĐ");
        holder.tv_soluong.setText("SL: " + list.get(position).soluong);
        holder.tv_delete.setOnClickListener(v -> {
            new AlertDialog.Builder(context).setTitle("Cánh báo").setIcon(R.drawable.ic_warning).setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int masp = list.get(holder.getAdapterPosition()).masp;
                            boolean check = sanPhamDAO.deleteSanPham(masp);
                            if (check) {
                                list.clear();
                                list = sanPhamDAO.getListSanPham();
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
        holder.tv_edit.setOnClickListener(v -> {
            SanPham sanPham = list.get(position);
            Edit_SP(sanPham);
        });
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
            } else if (!giaban.matches(regex_int) || !soluong.matches(regex_int)) {
                Toast.makeText(context, "Giá bán và số lượng phải là số và >=0", Toast.LENGTH_SHORT).show();
            } else {
                SanPham sanPham1 = new SanPham(sanPham.masp, tensp, Integer.parseInt(giaban), Integer.parseInt(soluong));
                boolean check = sanPhamDAO.updateSanPham(sanPham1);
                if (check) {
                    list.clear();
                    list = sanPhamDAO.getListSanPham();
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
            } else if (!giaban.matches(regex_int) || !soluong.matches(regex_int)) {
                Toast.makeText(context, "Giá bán và số lượng phải là số và >=0", Toast.LENGTH_SHORT).show();
            } else {
                SanPham sanPham1 = new SanPham(sanPham.masp, tensp, Integer.parseInt(giaban), Integer.parseInt(soluong));
                boolean check = sanPhamDAO.addSanPham(sanPham1);
                if (check) {
                    list.clear();
                    list = sanPhamDAO.getListSanPham();
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

    @Override
    public int getItemCount() {
        return list.size();
    }
}
