package com.mikem.vacationapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikem.vacationapp.R;
import com.mikem.vacationapp.database.Repository;
import com.mikem.vacationapp.entities.Excursion;
import com.mikem.vacationapp.entities.Vacation;

import java.util.ArrayList;
import java.util.List;

public class VacationList extends AppCompatActivity {

    private Repository mRepository;
    private VacationAdapter vacationAdapter;
    private SearchView searchView;
    private List<Vacation> vacationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> startActivity(new Intent(VacationList.this, VacationDetails.class)));

        mRepository = new Repository(getApplication());
        vacationList = mRepository.getmAllVacations();

        vacationAdapter = new VacationAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.vacation_recycler_view);
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.setVacations(vacationList);

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String text) {
        List<Vacation> filteredList = new ArrayList<>();
        for (Vacation vacation : vacationList) {
            if (vacation.getVacationTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(vacation);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No vacation found!", Toast.LENGTH_LONG).show();
        } else {
            vacationAdapter.setVacations(filteredList);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    }

    // add vacations
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.sample){
            //Toast.makeText(VacationList.this, "put in sample data", Toast.LENGTH_LONG).show();
            mRepository=new Repository(getApplication());
            Vacation vacation =new Vacation(0, "Disney", "All_Star_Sports", "11/14/2023", "11/22/2023", "2023-11-31 12:00:00");
            mRepository.insert(vacation);
            vacation=new Vacation(0, "Destin", "Hotel", "12/01/2023", "12/10/2023", "2023-11-31 12:00:00");
            mRepository.insert(vacation);
            Excursion excursion=new Excursion(0, 1, "Car Drive", "12/02/2023", "2023-11-31 12:00:00");
            mRepository.insert(excursion);
            excursion=new Excursion(0, 1, "Bike Ride", "12/03/2023", "2023-11-31 12:00:00");
            mRepository.insert(excursion);
            return true;
        }
        // go to home screen
        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                try {
                    intent = new Intent(VacationList.class.newInstance(), MainActivity.class);
                } catch (IllegalAccessException | InstantiationException e) {
                    throw new RuntimeException(e);
                }
                startActivity(intent);
            }
        });
        // item select for refresh vacations
        if (id == R.id.refresh2) {
            refreshVacationList();
            return true;
        }
        return super.onOptionsItemSelected(item); // return false;
    }

    private void refreshVacationList() {
        // Refresh the list of vacations
        vacationList = mRepository.getmAllVacations();
        vacationAdapter.setVacations(vacationList);
        // Reset the search view
        if (searchView != null) {
            searchView.setQuery("", false);
            searchView.clearFocus();
        }
    }
}