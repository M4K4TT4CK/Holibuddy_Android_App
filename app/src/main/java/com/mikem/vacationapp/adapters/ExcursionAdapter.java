package com.mikem.vacationapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikem.vacationapp.R;
import com.mikem.vacationapp.UI.ExcursionDetails;
import com.mikem.vacationapp.entities.Excursion;

import java.util.List;


// Task B - code that implements inheritance
// RecyclerView is the parent class and ExcursionAdapter is the child class
public class ExcursionAdapter extends RecyclerView.Adapter<ExcursionAdapter.ExcursionViewHolder> {

    private final LayoutInflater mInflater;

    private final Context context;

    private List<Excursion> mExcursion;

    public ExcursionAdapter (Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    // inheritance from RecycleView
    class ExcursionViewHolder extends RecyclerView.ViewHolder{
        private final TextView excursionListItem;

        private ExcursionViewHolder(View itemView) {
            super(itemView);
            excursionListItem = itemView.findViewById(R.id.excursion_list_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    final Excursion current = mExcursion.get(position);

                    Intent intent = new Intent(context, ExcursionDetails.class);

                    intent.putExtra("vacationId", current.getVacationID());
                    intent.putExtra("excursionId", current.getExcursionID());
                    intent.putExtra("excursionTitle", current.getExcursionTitle());
                    intent.putExtra("excursionStartDate", current.getExcursionDate());
                    context.startActivity(intent);
                }

            });
        }
    }

    @NonNull
    @Override
    public ExcursionAdapter.ExcursionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.activity_excursion_list_item, parent,false);
        return new ExcursionAdapter.ExcursionViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull ExcursionAdapter.ExcursionViewHolder holder,int position) {

        if(mExcursion != null) {
            Excursion current = mExcursion.get(position);
            String title =  current.getExcursionTitle();
            holder.excursionListItem.setText(title);
        }else{
            holder.excursionListItem.setText("No Title");
        }
    }

    public void setVacationTitle(List<Excursion> title) {
        mExcursion = title;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if(mExcursion != null)
            return mExcursion.size();
        else return 0;
    }

    public void setExcursions (List<Excursion> excursions){
        mExcursion = excursions;
        notifyDataSetChanged();
    }
}