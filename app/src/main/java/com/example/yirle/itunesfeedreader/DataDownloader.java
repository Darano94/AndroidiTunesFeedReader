package com.example.yirle.itunesfeedreader;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataDownloader extends AsyncTask<String, Void, String> {
    private static final String TAG = "DataDownloader";
    private XMLParser parser;
    private ListView listView;

    public DataDownloader(ListView listView) {
        this.listView = listView;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        parser = new XMLParser();
        Log.d(TAG, "onPostExecute: " + parser.parseXML(s));
        Log.d(TAG, "onPostExecute: " + parser.getEntries().size());

        FeedAdapter adapter = new FeedAdapter(MainActivity.getContext(), R.layout.listview_item, parser.getEntries());
        listView.setAdapter(adapter);
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuffer result = new StringBuffer();
        result.append(downloadXML(strings[0]));
        return result.toString();
    }

    private String downloadXML(String _url) {
        StringBuilder xmlResult = new StringBuilder();
        try {
            URL url = new URL(_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            char[] buffer = new char[500];
            int charRead;

            while (true) {
                charRead = reader.read(buffer);
                if (charRead < 0)
                    break;
                if (charRead > 0)
                    xmlResult.append(String.copyValueOf(buffer, 0, charRead));
            }
            reader.close();
            return xmlResult.toString();
        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground: invalid url" + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: error IO" + e.getMessage());
        }
        return null;
    }
}
