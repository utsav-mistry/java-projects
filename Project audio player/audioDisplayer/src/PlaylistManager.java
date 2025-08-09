import java.util.List;

public class PlaylistManager {
    private Playlist playlist;

    public PlaylistManager(Playlist playlist) {
        this.playlist = playlist;
    }

    public void loadSongsFromDatabase() {
        MyLinkedList<Song> songs = DatabaseManager.getAllSongs();
        // Convert the linked list to a list of nodes
        List<MyLinkedList.Node<Song>> nodeList = songs.toNodeList();
        // Add all songs from the linked list to the playlist
        for (MyLinkedList.Node<Song> node : nodeList) {
            playlist.addSong(node.data);
        }
    }

    public void refreshPlaylist() {
        // Reinitialize the playlist
        playlist = new Playlist();
        loadSongsFromDatabase();
    }

    public void displayCurrentSong() {
        playlist.display();
    }

    public void nextSong() {
        playlist.nextSong();
        playlist.display();
    }

    public void previousSong() {
        playlist.previousSong();
        playlist.display();
    }
}
