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

import java.util.ArrayList;

import softeng.restAndObjects.Bulletin;
import softeng.restAndObjects.RestInterface;

import static softeng.restAndObjects.RestInterface.getBulletins;

public class Bulletins extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<Bulletin> bulletins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_bulletin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bulletins = getBulletins();
        String[] items = new String[bulletins.size()];
        for(int i=0; i < bulletins.size(); i++) {
            if(bulletins.get(i).subject.length() < 20) {
                items[i] = bulletins.get(i).subject;
            } else {
                items[i] = bulletins.get(i).subject.substring(0,20);
            }
        }

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, items);
        final ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        //onclick listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList =(String) (lv.getItemAtPosition(position));
                //go to view the message Page
                viewBulletin(view,bulletins.get(position));
            }

        });

    }

    public void addBulletin(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText subject = new EditText(this);
        final EditText content = new EditText(this);

        subject.setInputType(InputType.TYPE_CLASS_TEXT);
        content.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setMessage("Please Enter A Subject and Description");

        layout.addView(subject);
        layout.addView(content);
        builder.setView(layout);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //send the new bulletin
                String sub = subject.getText().toString();
                String con = content.getText().toString();

                if (sub == null || sub.isEmpty()) {
                    sub = "No Subject";
                }
                if (con == null || con.isEmpty()) {
                    con = "No Description";
                }

                softeng.restAndObjects.Bulletin bulletin = new softeng.restAndObjects.Bulletin("Test", sub, con, 1);

                try {
                    RestInterface.sendBulletin(bulletin);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                finish();
                startActivity(getIntent());
            }
        });

        builder.show();
    }

    public void viewBulletin(View view, Bulletin bulletin) {
        Intent intent = new Intent(this, ViewBulletin.class);
        Bundle extras = new Bundle();
        extras.putString("sender", bulletin.sender);
        extras.putString("subject", bulletin.subject);
        extras.putInt("timestamp", bulletin.timestamp);
        extras.putString("content", bulletin.content);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
