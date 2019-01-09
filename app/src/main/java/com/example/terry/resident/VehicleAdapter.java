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

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.myViewHolder> {

    Context context;
    ArrayList<CarDetails> carDetails;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public VehicleAdapter(Context c, ArrayList<CarDetails> b)
    {
        context=c;
        carDetails = b;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_vehicle,viewGroup,false),mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int i) {
        holder.textViewStatus.setText(carDetails.get(i).getApprovalStatus());
        holder.textViewPlate.setText(carDetails.get(i).getVehiclePlate());
        holder.textViewPeriod.setText(carDetails.get(i).getVehicleSticker());
        String status = carDetails.get(i).getApprovalStatus();
        if(status.equals("Approved"))
        {
            holder.btnDelete.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {

        return carDetails.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView textViewPeriod,textViewPlate,textViewStatus;
        ImageView btnDelete;
        public myViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewPeriod =(TextView)itemView.findViewById(R.id.textViewPeriod);
            textViewPlate =(TextView)itemView.findViewById(R.id.textViewPlate);
            textViewStatus = (TextView)itemView.findViewById(R.id.textViewStatus);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }
}
