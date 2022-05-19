package com.example.pfe_0220.Planning.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SchoolYearDaysAdapter extends RecyclerView.Adapter<SchoolYearDaysAdapter.ViewHolder> {

    ArrayList<Calendar> days;

   public void UpdateSchoolYearAdapter(ArrayList<Calendar> c_days){
       if(days != null)
       days.clear();
    days = c_days;
       notifyDataSetChanged();

   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_day, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SetUpViewHolder(days.get(position));
    }

    @Override
    public int getItemCount() {
        if(days == null) return 0;
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView day_num, month_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day_num = itemView.findViewById(R.id.day_num);
            month_name = itemView.findViewById(R.id.month_name);
        }

        public void SetUpViewHolder(Calendar day) {
            if(day.get(Calendar.DAY_OF_MONTH) < 10)   day_num.setText("0"+day.get(Calendar.DAY_OF_MONTH)+"");
            else
            day_num.setText(day.get(Calendar.DAY_OF_MONTH)+"");
            month_name.setText(new SimpleDateFormat("MMM").format(day.getTime()));
        }

    }
}
