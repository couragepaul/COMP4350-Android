package softeng.apartmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class ViewBulletin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bulletin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String sender = extras.getString("sender");
        String subject = extras.getString("subject");
        int timestamp = extras.getInt("timestamp");
        Date datetime = new Date(timestamp);
        String content = extras.getString("content");

        TextView senderText = (TextView) findViewById(R.id.sender1);
        senderText.setText(sender);
        TextView timestampText = (TextView) findViewById(R.id.timestamp1);
        timestampText.setText(datetime.toString());
        TextView contentText = (TextView) findViewById(R.id.content1);
        contentText.setText(content);
        TextView subjectText = (TextView) findViewById(R.id.subject1);
        subjectText.setText(subject);
    }

}
