package com.example.yirle.itunesfeedreader;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView allApps;
    private static Context context;
    private String feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml";
    private int feedLimit = 10;

    private static final String STATE_URL = "UrlState";
    private static final String State_Limit = "LimitState";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_URL, feedUrl);
        outState.putInt(State_Limit, feedLimit);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        allApps = findViewById(R.id.listView);
        if(savedInstanceState != null){
            feedLimit = savedInstanceState.getInt(State_Limit);
            feedUrl = savedInstanceState.getString(STATE_URL);
        }
        downloadFeed(String.format(feedUrl, feedLimit));

    }

    private void downloadFeed(String url) {
        DataDownloader da = new DataDownloader(allApps);
        da.execute(url);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_layout, menu);
        if (feedLimit == 10) {
            menu.findItem(R.id.mnuTop10).setChecked(true);
        } else {
            menu.findItem(R.id.mnuTop25).setChecked(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.mnuFree:
                feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=%d/xml";
                break;
            case R.id.mnuPaid:
                feedUrl = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/toppaidapplications/limit=%d/xml";
                break;
            case R.id.mnuTop10:
            case R.id.mnuTop25:
                if (!item.isChecked()) {
                    item.setChecked(true);
                    feedLimit = 35 - feedLimit;
                } else {
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        downloadFeed(String.format(feedUrl, feedLimit));
        return true;
    }

    public static Context getContext() {
        return context;
    }
}
