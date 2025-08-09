import java.util.Random;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private MyLinkedList<Song> songs;
    private int currentIndex;

    public Playlist() {
        songs = new MyLinkedList<>();
        currentIndex = -1; // Initialize to -1 to indicate no song is currently playing
    }

    public void addSong(Song song) {
        songs.add(song);
        // If the playlist was empty, set currentIndex to 0 to play the first song
        if (songs.size() == 1) {
            currentIndex = 0;
        }
    }

    public void shuffle() {
        if (songs.size() <= 1) {
            return;
        }

        // Convert linked list nodes to a List
        List<MyLinkedList.Node<Song>> nodeList = songs.toNodeList();

        // Shuffle the list
        Collections.shuffle(nodeList, new Random());

        // Clear the existing linked list
        songs.clear();

        // Rebuild the linked list with shuffled nodes
        for (MyLinkedList.Node<Song> node : nodeList) {
            songs.add(node.data);
        }

        // Reset currentIndex to 0 if the playlist is not empty
        if (songs.size() > 0) {
            currentIndex = 0;
        }
    }

    public void deleteSongById(int songId) {
        Song songToRemove = null;
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song.getSongId() == songId) {
                songToRemove = song;
                break;
            }
        }
        if (songToRemove != null) {
            songs.remove(songToRemove);

            // Adjust currentIndex if necessary
            if (songs.size() == 0) {
                currentIndex = -1; // Reset currentIndex if playlist is empty
            } else if (currentIndex >= songs.size()) {
                currentIndex = songs.size() - 1; // Adjust currentIndex to the last valid index
            } else if (currentIndex == songs.size()) {
                currentIndex = 0; // Ensure currentIndex is valid if it points to the removed song
            }
        } else {
            System.out.println("Song with ID " + songId + " not found.");
        }
    }

    public Song getCurrentSong() {
        if (currentIndex < 0 || currentIndex >= songs.size()) {
            return null;
        }
        return songs.get(currentIndex);
    }

    public void nextSong() {
        if (songs.size() > 0) {
            currentIndex = (currentIndex + 1) % songs.size();
        }
    }

    public void previousSong() {
        if (songs.size() > 0) {
            currentIndex = (currentIndex - 1 + songs.size()) % songs.size();
        }
    }

    public void display() {
        Song song = getCurrentSong();
        if (song != null) {
            System.out.println("Currently Playing:");
            System.out.println("Song ID: " + song.getSongId());
            System.out.println("Title: " + song.getTitle());
            System.out.println("Artist: " + song.getArtist());
            System.out.println("Album: " + song.getAlbum());
            System.out.println("Duration: " + song.getDuration() + " seconds");
        } else {
            System.out.println("No songs in playlist.");
        }
    }

    public void displayAllSongs() {
        if (songs.size() == 0) {
            System.out.println("No songs in playlist.");
        } else {
            System.out.println("Playlist:");
            for (int i = 0; i < songs.size(); i++) {
                Song song = songs.get(i);
                System.out.println("Song ID: " + song.getSongId());
                System.out.println("Title: " + song.getTitle());
                System.out.println("Artist: " + song.getArtist());
                System.out.println("Album: " + song.getAlbum());
                System.out.println("Duration: " + song.getDuration() + " seconds");
                System.out.println();
            }
        }
    }
}
