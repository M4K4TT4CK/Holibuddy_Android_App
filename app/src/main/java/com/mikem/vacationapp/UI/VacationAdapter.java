package com.mikem.vacationapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikem.vacationapp.R;
import com.mikem.vacationapp.entities.Vacation;

import java.util.List;

// Task B - code that implements inheritance
// RecyclerView in the parent class and VacationAdapter is the child class
public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.VacationViewHolder> {

    public List<Vacation> mVacations;
    private final Context context;

    private final LayoutInflater mInflator;

    public VacationAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
        this.context = context;
    }

    public class VacationViewHolder extends RecyclerView.ViewHolder {

        public final TextView vacationItemView;
        public VacationViewHolder(@NonNull View itemview){
            super(itemview);
            this.vacationItemView = itemview.findViewById(R.id.textView2);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Vacation current=mVacations.get(position);
                    // notes
                    Intent intent =new Intent(context, VacationDetails.class);
                    intent.putExtra("vacationId", current.getVacationID());
                    intent.putExtra("vacationTitle", current.getVacationTitle());
                    intent.putExtra("vacationLodging", current.getVacationLodging());
                    intent.putExtra("vacationStartDate", current.getVacationStartDate());
                    intent.putExtra("vacationEndDate", current.getVacationEndDate());
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public VacationAdapter.VacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =  mInflator.inflate(R.layout.activity_vacation_list_item, parent,false);
        return new VacationViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull VacationAdapter.VacationViewHolder holder, int position) {
        if(mVacations!=null){
            Vacation current=mVacations.get(position);
            String title=current.getVacationTitle();
            holder.vacationItemView.setText(title);
        }
        else{
            holder.vacationItemView.setText("No Vacation Title");
        }
    }

    @Override
    public int getItemCount() {
        if(mVacations!=null){
            return mVacations.size();
        }
        else return 0;
    }

    public void setVacations(List<Vacation> vacations){
        mVacations=vacations;
        notifyDataSetChanged();
    }

}
