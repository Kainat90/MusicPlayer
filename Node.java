class Node {
    Song song;
    Node next;
    Node previous;

    public Node(Song song) {
        this.song = song;
        this.next = null;
        this.previous = null;
    }
}
