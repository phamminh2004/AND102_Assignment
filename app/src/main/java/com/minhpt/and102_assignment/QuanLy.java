package com.minhpt.and102_assignment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.service.controls.actions.FloatAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLy extends Fragment {
    SanPhamHelper sanPhamHelper;
    QuanLyAdapter adapter;
    ListView lv_sp;
    FloatingActionButton btn_add;
    public ArrayList<SanPham> listSP = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_sp = view.findViewById(R.id.lv_sp);
        btn_add = view.findViewById(R.id.btn_add);
        sanPhamHelper = new SanPhamHelper(getContext());
        listSP = sanPhamHelper.getListSanPham();
        adapter = new QuanLyAdapter(getContext(), listSP);
        lv_sp.setAdapter(adapter);
        btn_add.setOnClickListener(v -> {
            SanPham sanPham = new SanPham();
            adapter.Add_SP(sanPham);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_ly, container, false);
    }
}