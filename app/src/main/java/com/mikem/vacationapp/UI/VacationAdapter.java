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

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.VacationViewHolder> {

    private List<Vacation> mVacations;
    private final Context context;
    private final LayoutInflater mInflater;

    public VacationAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public class VacationViewHolder extends RecyclerView.ViewHolder {
        public final TextView vacationItemView;

        public VacationViewHolder(@NonNull View itemView) {
            super(itemView);
            vacationItemView = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final Vacation current = mVacations.get(position);
                Intent intent = new Intent(context, VacationDetails.class);
                intent.putExtra("vacationId", current.getVacationID());
                intent.putExtra("vacationTitle", current.getVacationTitle());
                intent.putExtra("vacationLodging", current.getVacationLodging());
                intent.putExtra("vacationStartDate", current.getVacationStartDate());
                intent.putExtra("vacationEndDate", current.getVacationEndDate());
                context.startActivity(intent);
            });
        }
    }

    @NonNull
    @Override
    public VacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.activity_vacation_list_item, parent, false);
        return new VacationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VacationViewHolder holder, int position) {
        if (mVacations != null) {
            Vacation current = mVacations.get(position);
            holder.vacationItemView.setText(current.getVacationTitle());
        } else {
            holder.vacationItemView.setText("No Vacation Title");
        }
    }

    @Override
    public int getItemCount() {
        return mVacations != null ? mVacations.size() : 0;
    }

    public void setVacations(List<Vacation> vacations) {
        mVacations = vacations;
        notifyDataSetChanged();
    }
}
