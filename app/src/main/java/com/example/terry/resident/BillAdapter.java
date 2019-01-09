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

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.myViewHolder> {

    Context context;
    ArrayList<BillDetails> billDetails;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public BillAdapter(Context c, ArrayList<BillDetails> b)
    {
        context=c;
        billDetails = b;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_bill,viewGroup,false),mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int i) {
       String imageType ;

        holder.textViewTitle.setText(billDetails.get(i).getBillDetails());
        holder.textViewDesc.setText(billDetails.get(i).getBillDate());
        holder.textViewPrice.setText(String.format("RM %.2f",billDetails.get(i).getBillAmount()));
        holder.textViewPlate.setText(billDetails.get(i).getCarPlate());
        imageType = billDetails.get(i).getVehicleType();
       if(imageType.equals("Car")||imageType.equals("Kereta")) {
           holder.imageView.setImageResource(R.drawable.car);
       }
        else if(imageType.equals("Motorcycle")||imageType.equals("Motosikal"))
        {
            holder.imageView.setImageResource(R.drawable.motor);
        }
        else if(imageType.equals("Maintenance"))
       {
           holder.imageView.setImageResource(R.drawable.maintenance);
       }
    }

    @Override
    public int getItemCount() {

        return billDetails.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView textViewDesc,textViewTitle,textViewPrice,textViewPlate;
        ImageView imageView;
        public myViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewTitle =(TextView)itemView.findViewById(R.id.textViewTitle);
            textViewDesc =(TextView)itemView.findViewById(R.id.textViewDesc);
            textViewPlate = (TextView)itemView.findViewById(R.id.textViewPlate);
            textViewPrice =(TextView)itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
