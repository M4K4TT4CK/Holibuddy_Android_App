package com.mikem.vacationapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.mikem.vacationapp.R;
import com.mikem.vacationapp.entities.Excursion;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.mikem.vacationapp.database.Repository;
import com.mikem.vacationapp.entities.Vacation;


public class ExcursionDetails extends AppCompatActivity {

    Repository mRepository;

    int excursionId;
    int vacationId;
    String title;
    String date;

    String vacationStartDate;
    String vacationEndDate;


    final Calendar myCalendar = Calendar.getInstance();

    EditText excursionTitleText;
    EditText excursionDateText;

    DatePickerDialog.OnDateSetListener excursionDateCalendar;

    Excursion currentExcursion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_details);

        mRepository = new Repository(getApplication());
        excursionTitleText = findViewById(R.id.excursion_title);
        excursionDateText = findViewById(R.id.excursion_date);

        vacationId = getIntent().getIntExtra("vacationId", -1);
        excursionId = getIntent().getIntExtra("excursionId", -1);
        title = getIntent().getStringExtra("excursionTitle");
        date = getIntent().getStringExtra("excursionStartDate");

        // Display a list of excursions associated with each vacation
        List<Vacation> myVacations = mRepository.getmAllVacations();
        for (Vacation v : myVacations){
            if(v.getVacationID() == vacationId){
                vacationStartDate = v.getVacationStartDate();
                vacationEndDate = v.getVacationEndDate();
                break;
            }
        }


        if (title != null) {
            excursionTitleText.setText(title);
            excursionDateText.setText(date);
        }

        excursionDateText = findViewById(R.id.excursion_date);
        excursionDateCalendar = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            updateLabelDate();
        };
        excursionDateText.setOnClickListener(v -> new DatePickerDialog(ExcursionDetails.this, excursionDateCalendar, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_excursion_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
            return true;
        }

        if (id == R.id.excursion_save) {

            date = excursionDateText.getText().toString();

            //  Include validation that the excursion date is during the associated vacation
            if (dateCheckExcursion() && dateValidation(date)) {

                if (excursionId == -1) {
                    String title = excursionTitleText.getText().toString();
                    Excursion newExcursion = new Excursion(0, vacationId, title, date);
                    mRepository.insert(newExcursion);
                    Toast.makeText(ExcursionDetails.this, "Excursion Added!", Toast.LENGTH_LONG).show();
                    this.finish();

                } else if (excursionId >= 0) {
                    String title = excursionTitleText.getText().toString();
                    String date = excursionDateText.getText().toString();
                    Excursion newExcursion = new Excursion(excursionId, vacationId, title, date);
                    mRepository.update(newExcursion);
                    Toast.makeText(ExcursionDetails.this, "Excursion Updated!", Toast.LENGTH_LONG).show();
                    this.finish();
                }


            } else {
                Toast.makeText(ExcursionDetails.this, "Enter a date within the vacation date range!", Toast.LENGTH_LONG).show();
            }
            return true;
        }

        // Add, update, and delete as many excursions as needed
        // Enter, edit, and delete excursion information.
        if (id == R.id.excursion_delete) {

            for (Excursion exc : mRepository.getmAllExcursions()) {
                if (exc.getExcursionID() == excursionId) currentExcursion = exc;
            }

            mRepository.delete(currentExcursion);
            Toast.makeText(ExcursionDetails.this, currentExcursion.getExcursionTitle() + " was deleted", Toast.LENGTH_LONG).show();
            this.finish();
        }

        // Include an alert that the user can set that will trigger on the excursion date, stating the excursion title

        if(id == R.id.excursion_alert){
            String title = excursionTitleText.getText().toString();
            String dateFromScreen = excursionDateText.getText().toString();
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;

            try {
                myDate = sdf.parse(dateFromScreen);
                Toast.makeText(ExcursionDetails.this, "Alert Set!", Toast.LENGTH_LONG).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try{
                assert myDate != null;
                long trigger = myDate.getTime();
                Intent intent = new Intent(ExcursionDetails.this, MyReceiver.class);
                intent.putExtra("key", title);
                PendingIntent sender = PendingIntent.getBroadcast(ExcursionDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);}
            catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ////  Include validation that the input dates are formatted correctly
    public boolean dateCheckExcursion(){
        // Task B - industry standards on security -- try/catch
        Date excursionStartDate = new Date();
        try{
            excursionStartDate = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        Date startDateVac = new Date();
        try {
            startDateVac = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(vacationStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date endDateVac = new Date();

        try {
            endDateVac = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(vacationEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert startDateVac != null;
        return !startDateVac.after(excursionStartDate) && !Objects.requireNonNull(endDateVac).before(excursionStartDate);
    }

    public boolean dateValidation(String date){

        if (!date.trim().equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.US);
            sdf.setLenient(false);

            try {
                Date validDate = sdf.parse(date);

            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(ExcursionDetails.this, "Date must be during Vacation dates!", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }


    private void updateLabelDate() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);

        excursionDateText.setText(sdf.format(myCalendar.getTime()));
    }
}