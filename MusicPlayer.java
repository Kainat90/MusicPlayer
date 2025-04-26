import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;

public class MusicPlayer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicLibrary library = new MusicLibrary();
        Playlist playlist = new Playlist();

        // Adding sample songs with corrected file paths
        library.addSong(new Song("Maand", "Bayaan | Hassan Raheem| Rovalio", "Divide", 240, "C:\\Users\\Kainat\\Music\\Maand.wav"));
        library.addSong(new Song("Tou Kya Hua", "Bayaan", "After Hours", 200, "C:\\Users\\Kainat\\Music\\TouKyaHua-Bayaan.wav"));
        library.addSong(new Song("Someone Like You", "Adele", "21", 285, "C:\\Users\\Kainat\\Music\\Adele - Someone Like You (Official Music Video).wav"));

        boolean running = true;

        while (running) {
            System.out.println("\nMusic Player Menu:");
            System.out.println("1. View Music Library");
            System.out.println("2. Search Song by Title");
            System.out.println("3. Add Song to Playlist");
            System.out.println("4. View Playlist");
            System.out.println("5. Sort Playlist by Title");
            System.out.println("6. Play a Song");
            System.out.println("7. Play Playlist in Loop");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.println("\nMusic Library:");
                        library.displaySongs();
                        Thread.sleep(5000);
                        break;

                    case 2:
                        System.out.print("Enter the title of the song: ");
                        String title = scanner.nextLine();
                        Song found = library.searchSongByTitle(title);
                        if (found != null) {
                            System.out.println("Song Found: " + found);
                        } else {
                            System.out.println("Song not found.");
                        }
                        Thread.sleep(5000);
                        break;

                    case 3:
                        System.out.println("Enter the index of the song to add to the playlist: ");
                        library.displaySongs();
                        int index = scanner.nextInt() - 1;
                        Song songToAdd = library.getSong(index);
                        if (songToAdd != null) {
                            playlist.addSong(songToAdd);
                            System.out.println("Song added to playlist.");
                        }
                        Thread.sleep(5000);
                        break;

                    case 4:
                        System.out.println("\nPlaylist:");
                        playlist.displayPlaylist();
                        Thread.sleep(5000);
                        break;

                    case 5:
                        playlist.sortPlaylistByTitle();
                        System.out.println("Playlist sorted by title.");
                        Thread.sleep(5000);
                        break;

                    case 6:
                        System.out.print("Enter the index of the song to play: ");
                        int playIndex = scanner.nextInt() - 1;
                        Song playing = playlist.playSong(playIndex);
                        if (playing != null) {
                            System.out.println("Playing: " + playing);
                            playAudio(playing.getFilePath());
                        }
                        Thread.sleep(5000); // 2-second delay after playing the song
                        break;

                    case 7:
                        System.out.println("Playing playlist in loop...");
                        playlist.playLoop();
                        Thread.sleep(5000); // 2-second delay after starting the playlist loop
                        break;

                    case 8:
                        running = false;
                        System.out.println("Exiting Music Player. Goodbye!");
                        Thread.sleep(5000);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }

    public static void playAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.out.println("File not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            System.out.println("Press Enter to stop the audio.");
            new Scanner(System.in).nextLine(); // Wait for user input to stop

            clip.stop();
            clip.close();
        } catch (Exception e) {
            System.out.println("Error playing the audio: " + e.getMessage());
        }
    }
}
