package softeng.apartmentapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.*;

import softeng.restAndObjects.Event;
import softeng.restAndObjects.RestInterface;

import static softeng.restAndObjects.RestInterface.getEvents;


public class Day extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int day = extras.getInt("day");
        int month = extras.getInt("month");
        int year = extras.getInt("year");

        events = getEvents();
        String[] items = new String[events.size()];
        for(int i=0; i < events.size(); i++) {
            if(events.get(i).title.length() < 20) {
                items[i] = events.get(i).title;
            } else {
                items[i] = events.get(i).title.substring(0,20);
            }
        }

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, items);
        final ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        //onclick listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (String) (lv.getItemAtPosition(position));
                //go to view the message Page
                viewEvent(view, events.get(position));
            }

        });
    }

    public void addEvent(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText title = new EditText(this);
        final EditText content = new EditText(this);

        title.setInputType(InputType.TYPE_CLASS_TEXT);
        content.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setMessage("Please Enter A Title and Description");

        layout.addView(title);
        layout.addView(content);
        builder.setView(layout);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tit = title.getText().toString();
                String con = content.getText().toString();

                if (tit == null || tit.isEmpty()) {
                    tit = "No Subject";
                }
                if (con == null || con.isEmpty()) {
                    con = "No Description";
                }

                softeng.restAndObjects.Event event = new softeng.restAndObjects.Event("Test", con, tit, 1, 1, 1);

                try {
                    RestInterface.sendEvent(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                finish();
                startActivity(getIntent());
            }
        });

        builder.show();
    }

    public void viewEvent(View view, Event event) {
        Intent intent = new Intent(this, ViewEvent.class);
        Bundle extras = new Bundle();

        extras.putString("sender", event.sender);
        extras.putString("title", event.title);
        extras.putInt("timestamp", event.timestamp);
        extras.putString("content", event.content);
        extras.putInt("starttime", event.starttime);
        extras.putInt("endtime", event.endtime);

        intent.putExtras(extras);
        startActivity(intent);
    }
}
