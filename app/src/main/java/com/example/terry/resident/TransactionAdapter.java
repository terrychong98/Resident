package com.example.terry.resident;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.myViewHolder> {

    Context context;
    ArrayList<TransactionDetails> transactionDetails;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public TransactionAdapter(Context c, ArrayList<TransactionDetails> b)
    {
        context=c;
        transactionDetails = b;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_transaction,viewGroup,false),mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int i) {

        String a;
        String symbol = "+";
        holder.textViewDesc.setText(transactionDetails.get(i).getAction());
        a =transactionDetails.get(i).getAction();
        if(a.equals("Pay Bill"))
        {
            symbol = "-";
        }
        else if(a.equals("Reload"))
        {
            symbol = "+";
        }
        holder.textViewDate.setText(transactionDetails.get(i).getDate());
        holder.textViewAmount.setText(String.format(""+symbol+" RM %.2f", transactionDetails.get(i).getAmount()));
    }

    @Override
    public int getItemCount() {

        return transactionDetails.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView textViewDesc,textViewAmount,textViewDate;
        ImageView imageView;
        public myViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewDesc =(TextView)itemView.findViewById(R.id.textViewDesc);
            textViewAmount = itemView.findViewById(R.id.textViewAmount);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }
    }
}
