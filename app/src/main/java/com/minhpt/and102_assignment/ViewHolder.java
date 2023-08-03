package com.minhpt.and102_assignment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tv_tensp, tv_giaban, tv_soluong, tv_edit, tv_delete;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_tensp = itemView.findViewById(R.id.tv_tensp);
        tv_giaban = itemView.findViewById(R.id.tv_giaban);
        tv_soluong = itemView.findViewById(R.id.tv_soluong);
        tv_edit = itemView.findViewById(R.id.tv_edit);
        tv_delete = itemView.findViewById(R.id.tv_delete);
    }
}
