package com.example.gfp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfp.R;
import com.example.gfp.viewmodel.CategoryDisplay;

import java.util.List;


public class CategoriesAdapter
        extends RecyclerView.Adapter<CategoriesAdapter.VH> {

    private final List<CategoryDisplay> data;

    public CategoriesAdapter(List<CategoryDisplay> data) {
        this.data = data;
    }

    public List<CategoryDisplay> getData() {
        return data;
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup p, int viewType) {
        View v = LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_category, p, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        CategoryDisplay cd = data.get(pos);

        h.icon.setText(cd.emoji);
        h.title.setText(cd.categoryName);

        // Inner RecyclerView
        OptionsAdapter optAdapter = new OptionsAdapter(cd.options);
        h.recycler.setLayoutManager(new LinearLayoutManager(h.recycler.getContext()));
        h.recycler.setAdapter(optAdapter);
    }

    @Override public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView icon, title;
        RecyclerView recycler;
        VH(View v) {
            super(v);
            icon     = v.findViewById(R.id.cat_icon);
            title    = v.findViewById(R.id.cat_title);
            recycler = v.findViewById(R.id.options_recycler);
        }
    }
}
