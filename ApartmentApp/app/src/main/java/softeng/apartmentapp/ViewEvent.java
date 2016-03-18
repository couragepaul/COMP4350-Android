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

public class ViewEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String sender = extras.getString("sender");
        int starttime = extras.getInt("starttime");
        Date startString = new Date(starttime);
        int endtime = extras.getInt("endtime");
        Date endString = new Date(endtime);
        String title = extras.getString("title");

        String content = extras.getString("content");

        TextView senderText = (TextView) findViewById(R.id.sender1);
        senderText.setText(sender);
        TextView contentText = (TextView) findViewById(R.id.content1);
        contentText.setText(content);
        TextView subjectText = (TextView) findViewById(R.id.title1);
        subjectText.setText(title);
        TextView starttimeText = (TextView) findViewById(R.id.starttime1);
        starttimeText.setText(startString.toString());
        TextView endTimeText = (TextView) findViewById(R.id.endtime1);
        endTimeText.setText(endString.toString());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

}
