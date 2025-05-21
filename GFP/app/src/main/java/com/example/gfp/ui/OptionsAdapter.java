package com.example.gfp.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfp.R;
import com.example.gfp.viewmodel.OptionDisplay;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

public class OptionsAdapter
        extends RecyclerView.Adapter<OptionsAdapter.VH> {

    private final List<OptionDisplay> list;

    public OptionsAdapter(List<OptionDisplay> data) {
        this.list = data;
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_option, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        OptionDisplay od = list.get(position);

        holder.name.setText(od.optionName);
        holder.amount.setText(String.valueOf(od.budget));
        holder.fixedSwitch.setChecked(od.isFixed);

        holder.amount.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s,int st,int c,int a){}
            @Override public void onTextChanged(CharSequence s,int st,int b,int c){}
            @Override public void afterTextChanged(Editable s) {
                try {
                    od.budget = Double.parseDouble(s.toString());
                } catch (NumberFormatException ex) {
                    od.budget = 0.0;
                }
            }
        });

        holder.fixedSwitch.setOnCheckedChangeListener((btn, isChecked) -> {
            od.isFixed = isChecked;
        });
    }

    @Override public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView name;
        EditText amount;
        SwitchMaterial fixedSwitch;

        VH(View v) {
            super(v);
            name        = v.findViewById(R.id.opt_name);
            amount      = v.findViewById(R.id.opt_amount);
            fixedSwitch = v.findViewById(R.id.opt_fixed_switch);
        }
    }
}
