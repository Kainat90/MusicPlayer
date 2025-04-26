import java.util.Arrays;

class MusicLibrary {
    private Song[] songs;
    private int size;

    public MusicLibrary() {
        songs = new Song[10];
        size = 0;
    }

    public void addSong(Song song) {
        if (size == songs.length) {
            songs = Arrays.copyOf(songs, songs.length * 2);
        }
        songs[size++] = song;
    }

    public void displaySongs() {
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + songs[i]);
        }
    }

    public Song getSong(int index) {
        if (index >= 0 && index < size) {
            return songs[index];
        }
        System.out.println("Invalid index.");
        return null;
    }

    public Song searchSongByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (songs[i].title.equalsIgnoreCase(title)) {
                return songs[i];
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}