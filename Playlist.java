class Playlist {
    private Node head;
    private Node tail;

    public Playlist() {
        this.head = null;
        this.tail = null;
    }

    public void addSong(Song song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
            tail = newNode;
            head.next = head; // Circular reference
            head.previous = head;
        } else {
            newNode.previous = tail;
            newNode.next = head;
            tail.next = newNode;
            head.previous = newNode;
            tail = newNode;
        }
    }

    public void removeSong(int index) {
        if (index < 0 || head == null) {
            System.out.println("Invalid index.");
            return;
        }

        Node current = head;
        int count = 0;
        do {
            if (count == index) {
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    if (current == head) {
                        head = current.next;
                    } else if (current == tail) {
                        tail = current.previous;
                    }
                }
                return;
            }
            current = current.next;
            count++;
        } while (current != head);

        System.out.println("Invalid index.");
    }

    public void displayPlaylist() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        Node current = head;
        int index = 1;
        do {
            System.out.println(index + ". " + current.song);
            current = current.next;
            index++;
        } while (current != head);
    }

    public void sortPlaylistByTitle() {
        if (head == null || head.next == head) return;

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            do {
                if (current.next != head && current.song.title.compareToIgnoreCase(current.next.song.title) > 0) {
                    Song temp = current.song;
                    current.song = current.next.song;
                    current.next.song = temp;
                    swapped = true;
                }
                current = current.next;
            } while (current.next != head);
        } while (swapped);
    }

    public Song playSong(int index) {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return null;
        }

        Node current = head;
        int count = 0;
        do {
            if (count == index) {
                return current.song;
            }
            current = current.next;
            count++;
        } while (current != head);

        System.out.println("Invalid index.");
        return null;
    }

    public void playLoop() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        Node current = head;
        System.out.println("Playing in loop. Press Ctrl+C to stop.");
        do {
            System.out.println("Playing: " + current.song);
            try {
                MusicPlayer.playAudio(current.song.getFilePath());
            } catch (Exception e) {
                System.out.println("Error playing the song: " + e.getMessage());
            }
            current = current.next;
        } while (true);
    }
}
