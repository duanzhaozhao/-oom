package com.example.loading;

/**
 * Created by skysoft on 2016/7/6.
 */
public class Mp3Info {
    long id;
    private String path;
    private long albumID;

    public long getId() {
        return id;
    }

    public long getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    long duration;
    String title,artist;

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    public long getAlbumId() {
        return albumID;
    }
}
