package com.example.pfe_0220.Planning.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Planning.Models.DayNode;
import com.example.pfe_0220.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SchoolYearDaysAdapter extends RecyclerView.Adapter<SchoolYearDaysAdapter.ViewHolder> {

   public ArrayList<DayNode> days;
    private SchoolClassesAdapter.ItemClickListener clickListener;
    public void UpdateSchoolYearAdapter(ArrayList<DayNode> c_days) {
        if (days != null)
            days.clear();
        days = c_days;
        notifyDataSetChanged();

    }




    public void setClickListener(SchoolClassesAdapter.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    public interface ItemClickListener {
        public void onClick(View view, int position);
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
        if (days == null) return 0;
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView day_num, month_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day_num = itemView.findViewById(R.id.day_num);
            month_name = itemView.findViewById(R.id.month_name);
            itemView.setOnClickListener(this);
        }

        public void SetUpViewHolder(DayNode day) {
            if (day.day.get(Calendar.DAY_OF_MONTH) < 10)
                day_num.setText("0" + day.day.get(Calendar.DAY_OF_MONTH) + "");
            else
                day_num.setText(day.day.get(Calendar.DAY_OF_MONTH) + "");
            if(day.selected) day_num.setBackgroundColor(Color.RED);
            else day_num.setBackgroundColor(Color.TRANSPARENT);
            month_name.setText(new SimpleDateFormat("MMM").format(day.day.getTime()));
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }
}
