package softeng.apartmentapp;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

public class Calendar extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int day, int month, int year) {
                openDay(day, month, year);
            }
        });
    }

    public void openDay(int day, int month, int year) {
        Intent intent = new Intent(this, Day.class);

        Bundle extras = new Bundle();

        extras.putInt("day", day);
        extras.putInt("month", month);
        extras.putInt("year", year);
        intent.putExtras(extras);

        startActivity(intent);
    }

}
