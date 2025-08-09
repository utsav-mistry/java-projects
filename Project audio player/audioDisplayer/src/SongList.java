public class SongList {
    private MyLinkedList<Song> songList;

    public SongList() {
        songList = new MyLinkedList<>();
    }

    // Adds a song to the list
    public void addSong(Song song) {
        songList.add(song);
    }

    // Removes a song from the list
    public void removeSong(int songId) {
        MyLinkedList.Node<Song> current = songList.getHead();
        while (current != null) {
            if (current.data.getSongId() == songId) {
                songList.remove(current.data);
                return;
            }
            current = current.next;
        }
    }

    // Retrieves a song by ID
    public Song getSong(int songId) {
        MyLinkedList.Node<Song> current = songList.getHead();
        while (current != null) {
            if (current.data.getSongId() == songId) {
                return current.data;
            }
            current = current.next;
        }
        return null; // Song not found
    }

    // Displays all songs in the list
    public void displaySongs() {
        MyLinkedList.Node<Song> current = songList.getHead();
        while (current != null) {
            Song song = current.data;
            System.out.println("ID: " + song.getSongId() +
                               ", Title: " + song.getTitle() +
                               ", Artist: " + song.getArtist() +
                               ", Album: " + song.getAlbum() +
                               ", Duration: " + song.getDuration());
            current = current.next;
        }
    }

    // Returns the size of the song list
    public int size() {
        return songList.size();
    }
}
