import java.util.*;
import javax.sound.sampled.*;
import java.io.*;

// Song Class to Represent Individual Songs
class Song {
    String title;
    String artist;
    String album;
    int duration; // Duration in seconds
    String filePath; // Path to the audio file

    public Song(String title, String artist, String album, int duration, String filePath) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Album: " + album + ", Duration: " + duration + "s";
    }

    public String getFilePath() {
        return filePath;
    }
}

