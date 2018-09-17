package com.example.yirle.itunesfeedreader;

public class FeedEntry {
    private String title;
    private String summary;
    private String im_price;
    private String rights;

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIm_price() {
        return im_price;
    }

    public void setIm_price(String im_price) {
        this.im_price = im_price;
    }
}
