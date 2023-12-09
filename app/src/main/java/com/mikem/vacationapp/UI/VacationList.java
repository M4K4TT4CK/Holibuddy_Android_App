package com.mikem.vacationapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikem.vacationapp.R;
import com.mikem.vacationapp.database.Repository;
import com.mikem.vacationapp.entities.Excursion;
import com.mikem.vacationapp.entities.Vacation;

import java.util.List;

public class VacationList extends AppCompatActivity {

    private Repository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        FloatingActionButton fab=findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VacationList.this, VacationDetails.class);
                startActivity(intent);
            }
        });
        // recycler view
        RecyclerView recyclerView= findViewById(R.id.vacation_recycler_view);
        mRepository=new Repository(getApplication());
        List<Vacation> allVacations=mRepository.getmAllVacations();
        final VacationAdapter vacationAdapter=new VacationAdapter(this);
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.setVacations(allVacations);
        //System.out.println(getIntent().getStringExtra("test")); <- Remove after final build, no need
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    }

    // add vacations
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==R.id.sample){
            //Toast.makeText(VacationList.this, "put in sample data", Toast.LENGTH_LONG).show();
            mRepository=new Repository(getApplication());
            Vacation vacation =new Vacation(0, "Disney", "All_Star_Sports", "11/14/2023", "11/22/2023");
            mRepository.insert(vacation);
            vacation=new Vacation(0, "Destin", "Hotel", "12/01/2023", "12/10/2023");
            mRepository.insert(vacation);
            Excursion excursion=new Excursion(0, 1, "Car Drive", "12/02/2023");
            mRepository.insert(excursion);
            excursion=new Excursion(0, 1, "Bike Ride", "12/03/2023");
            mRepository.insert(excursion);
            return true;
        }
        if(item.getItemId()==android.R.id.home){
            this.finish();
            return true;
        }
        return false;
    }
}