package softeng.apartmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import softeng.restAndObjects.Message;

import static softeng.restAndObjects.RestInterface.sendMessage;

public class CreateMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_create_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sendMessageButton(View v) {
        //get information we need to send
        //String sender,String recipient,String content,int urgency
        String sender = "Test_User";
        String recipient = ((EditText)findViewById(R.id.username)).getText().toString();
        String content = ((EditText)findViewById(R.id.newMessage)).getText().toString();
        String urgent = ((EditText)findViewById(R.id.urgency)).getText().toString();
        int urgency = Integer.parseInt(urgent);
        if(urgency > 10) {
            urgency = 10;
        }
        Message theNewMessage = new Message(sender,recipient,content,urgency,1,false);
        sendMessage(theNewMessage);

        //go back to messages page
        finish();
    }
}
