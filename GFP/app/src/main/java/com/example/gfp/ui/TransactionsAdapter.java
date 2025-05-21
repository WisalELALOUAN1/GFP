package com.example.gfp.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfp.R;
import com.example.gfp.data.model.Transaction;

import java.util.List;
import java.util.Locale;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder> {

    private List<Transaction> transactions;

    public TransactionsAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);

        String amountText = String.format(Locale.getDefault(), "%.2f DH", transaction.getAmount());
        holder.amount.setText(amountText);
        holder.amount.setTextColor(transaction.getType().equals("debit")
                ? Color.parseColor("#FF0000")
                : Color.parseColor("#4CAF50"));

        holder.description.setText(transaction.getDescription());
        holder.type.setText(transaction.getType());
        holder.date.setText(transaction.getTime());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView description, amount, type, date;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.transactionDescription);
            amount = itemView.findViewById(R.id.transactionAmount);
            type = itemView.findViewById(R.id.transactionType);
            date = itemView.findViewById(R.id.transactionDate);
        }
    }
}