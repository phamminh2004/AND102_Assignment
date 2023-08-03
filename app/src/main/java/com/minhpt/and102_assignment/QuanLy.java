package com.minhpt.and102_assignment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLy extends Fragment {
    SanPhamHelper sanPhamHelper;
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter adapter;
    RecyclerView rv_sp;
    FloatingActionButton btn_add;
    ArrayList<SanPham> listSP = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_sp = view.findViewById(R.id.rv_sp);
        btn_add = view.findViewById(R.id.btn_add);
        sanPhamHelper = new SanPhamHelper(getContext());
        sanPhamDAO = new SanPhamDAO(getContext());
        listSP = sanPhamDAO.getListSanPham();
        adapter = new SanPhamAdapter(getContext(), listSP);
        rv_sp.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_sp.setLayoutManager(layoutManager);
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