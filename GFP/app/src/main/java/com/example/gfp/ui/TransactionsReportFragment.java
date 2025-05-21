package com.example.gfp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfp.R;
import com.example.gfp.data.model.Transaction;

import java.util.List;

public class TransactionsReportFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadTransactions();
        return view;
    }

    private void loadTransactions() {
        int userId = ((ReportsActivity)requireActivity()).getUserId();
        List<Transaction> transactions = ((ReportsActivity)requireActivity())
                .getTransactionRepository()
                .getTransactionsByUser(userId);

        TransactionsAdapter adapter = new TransactionsAdapter(transactions);
        recyclerView.setAdapter(adapter);
    }
}