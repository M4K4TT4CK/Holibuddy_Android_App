package com.mikem.vacationapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikem.vacationapp.R;
import com.mikem.vacationapp.adapters.ExcursionAdapter;
import com.mikem.vacationapp.database.Repository;
import com.mikem.vacationapp.entities.Excursion;
import com.mikem.vacationapp.entities.Vacation;
import com.mikem.vacationapp.util.DateValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


// display detailed view of the vacation

    public class VacationDetails extends AppCompatActivity {
        private DateValidator dateValidator = new DateValidator();
        private Repository repository;
        int vacationId;
        String title;
        String lodging;
        String start_date;
        String end_date;
        String create_date;

        Vacation currentVac;
        int numExc;
        int excursionId;

        EditText vacationIdText;
        EditText vacationTitleText;
        EditText vacationLodgingText;
        EditText vacationStartText;
        EditText vacationEndText;


        final Calendar myCalendarStart = Calendar.getInstance();
        final Calendar myCalendarEnd = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener startDate;
        DatePickerDialog.OnDateSetListener endDate;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_vacation_details);

            Objects.requireNonNull(getSupportActionBar()).setTitle("Details");

            repository = new Repository(getApplication());
            vacationTitleText = findViewById(R.id.vacation_title);
            vacationLodgingText = findViewById(R.id.vacation_lodging);
            vacationStartText = findViewById(R.id.startDate);
            vacationEndText = findViewById(R.id.endDate);


            vacationId = getIntent().getIntExtra("vacationId", -1);
            title = getIntent().getStringExtra("vacationTitle");
            lodging = getIntent().getStringExtra("vacationLodging");
            start_date = getIntent().getStringExtra("vacationStartDate");
            end_date = getIntent().getStringExtra("vacationEndDate");

            if (title != null) {
                vacationId = getIntent().getIntExtra("vacationId", -1);
                String vacStringStart = getIntent().getStringExtra("vacStartDate");
                String vacStringEnd = getIntent().getStringExtra("vacEndDate");
                vacationTitleText.setText(title);
                vacationLodgingText.setText(lodging);
                vacationStartText.setText(start_date);
                vacationEndText.setText(end_date);
            }

            vacationStartText = findViewById(R.id.startDate);

            // Enter, edit, and delete vacation information.
            startDate = (view, year, monthOfYear, dayOfMonth) -> {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                updateLabelStart();
            };

            vacationStartText.setOnClickListener(v -> new DatePickerDialog(VacationDetails.this, startDate, myCalendarStart
                    .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                    myCalendarStart.get(Calendar.DAY_OF_MONTH)).show());

            vacationEndText = findViewById(R.id.endDate);
            endDate = (view, year, monthOfYear, dayOfMonth) -> {
                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                updateLabelEnd();
            };

            vacationEndText.setOnClickListener(v -> {
                // TODO Auto-generated method stub
                new DatePickerDialog(VacationDetails.this, endDate, myCalendarEnd
                        .get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            });


            FloatingActionButton fab = findViewById(R.id.enter_excursion_details);

            //animate fab for load in rotate
            ObjectAnimator fabAni =  ObjectAnimator.ofFloat(fab, "rotation", 0, 360);
            fabAni.setDuration(2500);
            fabAni.setRepeatMode(ObjectAnimator.REVERSE);
            fabAni.setRepeatCount(ObjectAnimator.INFINITE);

            //anime fab for appear in
            ObjectAnimator fabAppear =ObjectAnimator.ofFloat(fab, "alpha", 0.0f, 1);
            fabAppear.setDuration(1000);

            //animatorSet
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(fabAni);
            animatorSet.play(fabAppear).with(fabAni);
            animatorSet.start();

            fab.setOnClickListener(view -> {
                Intent intent = new Intent(VacationDetails.this, ExcursionDetails.class);
                intent.putExtra("vacationId", vacationId);
                startActivity(intent);
            });


            startDate = (view, year, monthOfYear, dayOfMonth) -> {

                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            };

            RecyclerView recyclerView = findViewById(R.id.excursion_recycler_view);
            repository = new Repository(getApplication());
            final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
            recyclerView.setAdapter(excursionAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            List<Excursion> filteredExcursions = new ArrayList<>();
            for (Excursion e : repository.getmAllExcursions()) {
                if (e.getVacationID() == vacationId) filteredExcursions.add(e);
            }
            excursionAdapter.setExcursions(filteredExcursions);
        }

        private void updateLabelStart() {
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            vacationStartText.setText(sdf.format(myCalendarStart.getTime()));
        }


        private void updateLabelEnd() {
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            vacationEndText.setText(sdf.format(myCalendarEnd.getTime()));
        }

        // Include validation that the input dates are formatted correctly.
//        private boolean dateCheck() {
//            // Task B - industry standards on security -- try/catch
//            Date startDateVac = new Date();
//            try {
//                startDateVac = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(start_date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            Date endDateVac = new Date();
//            try {
//                endDateVac = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(end_date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            assert startDateVac != null;
//            return startDateVac.before(endDateVac);
//        }

        // Include validation that the vacation end date is after the start date

        public boolean dateValidationStart(String start_date){

            if (!start_date.trim().equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.US);
                sdf.setLenient(false);
                try {
                    Date validDate = sdf.parse(start_date);

                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(VacationDetails.this, "Enter a valid date!", Toast.LENGTH_LONG).show();
                    return false;
                }

            }
            return true;
        }

        public boolean dateValidationEnd(String end_date){

            if (!end_date.trim().equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.US);
                sdf.setLenient(false);
                try {
                    Date validDate = sdf.parse(end_date);


                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(VacationDetails.this, "Enter a valid date.", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu_vacation_details, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();

            if (id == R.id.save) {

                start_date = vacationStartText.getText().toString();
                end_date = vacationEndText.getText().toString();

                if (dateValidator.dateCheck(start_date, end_date) && dateValidator.dateValidationStart(start_date) && dateValidator.dateValidationEnd(end_date)) {

                    String title = vacationTitleText.getText().toString();
                    String lodging = vacationLodgingText.getText().toString();
                    if (vacationId == -1) {
                        Vacation newVacation = new Vacation(0, title, lodging, start_date, end_date, create_date);
                        repository.insert(newVacation);

                    } else {
                        String start = vacationStartText.getText().toString();
                        String end = vacationEndText.getText().toString();
                        Vacation newVacation = new Vacation(vacationId, title, lodging, start, end, create_date);
                        repository.update(newVacation);
                    }
                    Toast.makeText(VacationDetails.this, "Vacation added successfully!", Toast.LENGTH_LONG).show();
                    this.finish();

                    return true;

                }

                else{
                    Toast.makeText(VacationDetails.this, "End date must be after the start date!", Toast.LENGTH_LONG).show();
                    return false;
                }
            }

            if (id == R.id.delete) {
                for (Vacation vac : repository.getmAllVacations()) {
                    if (vac.getVacationID() == Integer.parseInt(String.valueOf(vacationId)))
                        currentVac = vac;
                }

                numExc = 0;
                for (Excursion exc : repository.getmAllExcursions()) {
                    if (exc.getVacationID() == Integer.parseInt(String.valueOf(vacationId)))
                        ++numExc;
                }
                // validation for so vacation cant be deleted iif excursion associated with it
                if (numExc == 0) {
                    repository.delete(currentVac);
                    Toast.makeText(VacationDetails.this, currentVac.getVacationTitle() + " was deleted", Toast.LENGTH_LONG).show();
                    this.finish();
                } else {
                    Toast.makeText(VacationDetails.this, "Can't delete a vacation with associated adventures!", Toast.LENGTH_LONG).show();
                }
                return true;
            }

            //  Include sharing features so the user can share all the vacation details via a sharing feature (either e-mail, clipboard or SMS) that automatically populates with the vacation details.

            if (id == R.id.share) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, vacationTitleText.getText().toString() + " " + vacationLodgingText.getText().toString() + " " +
                        vacationStartText.getText().toString() + " " + vacationEndText.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "That looks like a great trip!!");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            }

            //  Include an alert that the user can set which will trigger on the start and end date, displaying the vacation title and whether it is starting or ending

            if (id == R.id.start_alert)
            {
                String dateFromScreen = vacationStartText.getText().toString();
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try{
                    assert myDate != null;
                    long trigger = myDate.getTime();
                    Intent intent = new Intent(VacationDetails.this, MyReceiver.class);
                    intent.putExtra("key", title + " is starting");
                    PendingIntent sender = PendingIntent.getBroadcast(VacationDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);}

                catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(VacationDetails.this, "Alert added for Vacation start date!", Toast.LENGTH_LONG).show();
                return true;
            }

            // Fix Aspect B3E - remove vacationStartText and replace with vacationEndText
            if (id == R.id.end_alert) {
                String dateFromScreen = vacationEndText.getText().toString();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try{
                    assert myDate != null;
                    long trigger = myDate.getTime();
                    Intent intent = new Intent(VacationDetails.this, MyReceiver.class);
                    intent.putExtra("key", title + " is ending");
                    PendingIntent sender = PendingIntent.getBroadcast(VacationDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);}
                catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(VacationDetails.this, "Alert added for Vacation end date!", Toast.LENGTH_LONG).show();
                return true;

            }

            if (id == R.id.refresh) {
                RecyclerView recyclerView = findViewById(R.id.excursion_recycler_view);
                repository = new Repository(getApplication());
                final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
                recyclerView.setAdapter(excursionAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                List<Excursion> filteredExcursions = new ArrayList<>();
                for (Excursion e : repository.getmAllExcursions()) {
                    if (e.getVacationID() == vacationId) filteredExcursions.add(e);
                }
                excursionAdapter.setExcursions(filteredExcursions);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        protected void onResume() {
            super.onResume();
            RecyclerView recyclerView = findViewById(R.id.excursion_recycler_view);
            final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
            recyclerView.setAdapter(excursionAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            List<Excursion> filteredExcursions = new ArrayList<>();
            for (Excursion e : repository.getmAllExcursions()) {
                if(e.getVacationID() == vacationId) filteredExcursions.add(e);
            }
            excursionAdapter.setExcursions(filteredExcursions);
        }
    }