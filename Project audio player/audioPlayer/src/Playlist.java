import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private MyArrayList<Song> songs = new MyArrayList<>();
    private int currentIndex = -1;

    // Add a song to the playlist and database
    public void addSong(int songId) {
        Song song = getSongFromDatabase(songId);
        if (song != null && !contains(song)) {
            songs.add(song);
            System.out.println("Song added: " + song.getTitle());
        } else {
            System.out.println("Song already exists in the playlist or not found.");
        }
    }

    private boolean contains(Song song) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).equals(song)) {
                return true;
            }
        }
        return false;
    }

    // Remove a song from the playlist and database
    public void deleteSong(int songId) {
        Song song = getSongFromDatabase(songId);
        if (song != null && songs.remove(song)) {
            deleteSongFromDatabase(songId);
            System.out.println("Song removed: " + song.getTitle());
        } else {
            System.out.println("Song not found in the playlist.");
        }
    }

    // Shuffle the playlist
    public void shuffle() {
        // Convert MyArrayList to a regular List to use Collections.shuffle
        List<Song> songList = new java.util.ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            songList.add(songs.get(i));
        }
        Collections.shuffle(songList);
        // Update MyArrayList with shuffled songs
        songs.clear();
        for (Song song : songList) {
            songs.add(song);
        }
        System.out.println("Playlist shuffled.");
    }

    // Move to the next song
    public void nextSong() {
        if (songs.isEmpty()) {
            System.out.println("The playlist is empty.");
            return;
        }
        currentIndex = (currentIndex + 1) % songs.size();
        Song currentSong = songs.get(currentIndex);
        System.out.println("Now playing: " + currentSong.getTitle());
    }

    // Move to the previous song
    public void previousSong() {
        if (songs.isEmpty()) {
            System.out.println("The playlist is empty.");
            return;
        }
        currentIndex = (currentIndex - 1 + songs.size()) % songs.size();
        Song currentSong = songs.get(currentIndex);
        System.out.println("Now playing: " + currentSong.getTitle());
    }

    // Display the playlist
    public void display() {
        if (songs.isEmpty()) {
            System.out.println("The playlist is empty.");
            return;
        }
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).getTitle());
        }
    }

    // Return a copy of the list of songs
    public List<Song> getSongs() {
        // Convert MyArrayList to a regular List to return
        List<Song> songList = new java.util.ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            songList.add(songs.get(i));
        }
        return songList;
    }

    // Clear the playlist
    public void clear() {
        songs.clear();
        System.out.println("Playlist cleared.");
    }

    // Retrieve a song from the database
    private Song getSongFromDatabase(int songId) {
        String query = "SELECT * FROM Songs WHERE song_id = ?";
        try (Connection connection = DatabaseConnector.con;
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, songId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Song(
                            songId,
                            resultSet.getString("title"),
                            resultSet.getString("artist"),
                            resultSet.getString("album"),
                            resultSet.getInt("duration"),
                            resultSet.getBytes("song_data"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving song from database: " + e.getMessage());
        }
        return null;
    }

    // Remove a song from the database
    private void deleteSongFromDatabase(int songId) {
        String query = "DELETE FROM Songs WHERE song_id = ?";
        try (Connection connection = DatabaseConnector.con;
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, songId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting song from database: " + e.getMessage());
        }
    }
}
