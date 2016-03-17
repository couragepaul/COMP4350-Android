package softeng.apartmentapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Bulletin extends AppCompatActivity {
    List<String> data;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fillList();
    }

    protected void fillList() {
        ListView listView = (ListView)findViewById(R.id.listView);

        data = new ArrayList<>();
        data.add("Title");
        data.add("Dorum Lipsum Something Something...");

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, data);

        listView.setAdapter(adapter);
    }

    public void addBulletin(View view) {
        data.add("HI!");
        data.add("Stuff goes here I guess");
        adapter.notifyDataSetChanged();
    }
}
