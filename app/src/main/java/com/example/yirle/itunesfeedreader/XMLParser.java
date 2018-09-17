package com.example.yirle.itunesfeedreader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

public class XMLParser {
    private static final String TAG = "XMLParser";
    private ArrayList<FeedEntry> entries = new ArrayList<>();

    public boolean parseXML(String xmlData) {
        boolean status = true;
        boolean inEntry = false;
        int eventType;
        String tagName = "";
        String tagValue = "";
        FeedEntry currentRecord = null;
        try {
            XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
            fac.setNamespaceAware(true);
            XmlPullParser xpp = fac.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("entry".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentRecord = new FeedEntry();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        tagValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry) {
                            if ("title".equalsIgnoreCase(tagName)) {
                                currentRecord.setTitle(tagValue);
                            } else if ("summary".equalsIgnoreCase(tagName)) {
                                currentRecord.setSummary(tagValue);
                            } else if ("im:price".equalsIgnoreCase(tagName)) {
                                currentRecord.setIm_price(tagValue);
                            } else if ("rights".equalsIgnoreCase(tagName)) {
                                currentRecord.setRights(tagValue);
                            } else if ("entry".equalsIgnoreCase(tagName)) {
                                entries.add(currentRecord);
                                inEntry = false;
                            }
                        }
                        break;
                }
                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            status = false;
            Log.e(TAG, "parseXML: Factory couldnt be instantiated: " + e.getMessage());
        } catch (IOException e) {
            status = false;
            Log.e(TAG, "parseXML: " + e.getMessage());
        }
        return status;
    }

    public ArrayList<FeedEntry> getEntries() {
        return entries;
    }
}
