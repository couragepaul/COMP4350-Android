package softeng.apartmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import softeng.restAndObjects.Message;

import static softeng.restAndObjects.RestInterface.getMessages;

public class Messages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<Message> msges = new ArrayList<Message>();
        msges = getMessages("COMP4350_SU"); // testing
        String[] items = new String[msges.size()];
        for(int i=0; i < msges.size(); i++) {
//            items[i] = msges.get(i).sender+ msges.get(i).content.substring(0,20);
            if(msges.get(i).content.length() < 20) {
                items[i] = msges.get(i).content;
            } else {
                items[i] = msges.get(i).content.substring(0,20);
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.content_messages,R.id.Title,items);
        ListView lv = (ListView) findViewById(R.id.msgList);
        lv.setAdapter(adapter);
    }


    public void createNewMessage(View view) {
        Intent intent = new Intent(this, CreateMessage.class);
        startActivity(intent);
    }
}
