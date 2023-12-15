package com.mikem.vacationapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikem.vacationapp.R;
import com.mikem.vacationapp.adapters.VacationAdapter;
import com.mikem.vacationapp.database.Repository;
import com.mikem.vacationapp.entities.Excursion;
import com.mikem.vacationapp.entities.Vacation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VacationList extends AppCompatActivity {

    private static final int CREATE_FILE = 1;

    private Repository mRepository;
    private VacationAdapter vacationAdapter;
    private SearchView searchView;
    private List<Vacation> vacationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Current Vacations");

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sample) {
            // Insert sample data logic
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
        } else if (id == R.id.refresh2) {
            refreshVacationList();
            return true;
        } else if (id == R.id.export_to_csv) {
            createFile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createFile() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/csv");
        intent.putExtra(Intent.EXTRA_TITLE, "vacation_report.csv");
        startActivityForResult(intent, CREATE_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_FILE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                writeCsvToFile(uri);
            }
        }
    }

private void writeCsvToFile(Uri uri) {
    try (ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(uri, "w");
         FileOutputStream fileOutputStream = new FileOutputStream(pfd.getFileDescriptor())) {

        StringBuilder data = new StringBuilder();
        data.append("ID,Title,Lodging,Start Date,End Date\r\n"); // Use \r\n for new lines
        for (Vacation vacation : vacationList) {
            data.append(vacation.getVacationID()).append(",");
            data.append("\"").append(vacation.getVacationTitle()).append("\","); // Enclose in quotes if data might contain commas
            data.append("\"").append(vacation.getVacationLodging()).append("\",");
            data.append(vacation.getVacationStartDate()).append(",");
            data.append(vacation.getVacationEndDate()).append("\r\n");
        }
        fileOutputStream.write(data.toString().getBytes("UTF-8")); // Specify UTF-8 encoding
        Toast.makeText(this, "File saved successfully.", Toast.LENGTH_LONG).show();
    } catch (IOException e) {
        e.printStackTrace();
        Toast.makeText(this, "Failed to save file.", Toast.LENGTH_LONG).show();
    }
}


    private void refreshVacationList() {
        vacationList = mRepository.getmAllVacations();
        vacationAdapter.setVacations(vacationList);
        if (searchView != null) {
            searchView.setQuery("", false);
            searchView.clearFocus();
        }
    }
}
