import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistManager {

    // Refresh the playlist from the database
    public static void refreshPlaylist(Playlist playlist) {
        playlist.clear(); // Clear the current playlist

        String query = "SELECT song_id FROM Songs"; // Adjust query as needed

        try (Connection connection = DatabaseConnector.con;
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int songId = resultSet.getInt("song_id");
                playlist.addSong(songId);
            }

            System.out.println("Playlist refreshed.");

        } catch (SQLException e) {
            System.out.println("Error refreshing playlist: " + e.getMessage());
        }
    }

    // Create a new song in the database
    public static void createSong(Song song) {
        String query = "INSERT INTO Songs (song_id, title, artist, album, duration, song_data) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.con;
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, song.getId());
            statement.setString(2, song.getTitle());
            statement.setString(3, song.getArtist());
            statement.setString(4, song.getAlbum());
            statement.setInt(5, song.getDuration());
            statement.setBytes(6, song.getSongData());

            statement.executeUpdate();
            System.out.println("Song created: " + song.getTitle());

        } catch (SQLException e) {
            System.out.println("Error creating song: " + e.getMessage());
        }
    }

    // Read a song from the database
    public static Song readSong(int songId) {
        Song song = null;
        String query = "SELECT * FROM Songs WHERE song_id = ?";

        try (Connection connection = DatabaseConnector.con;
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, songId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String artist = resultSet.getString("artist");
                    String album = resultSet.getString("album");
                    int duration = resultSet.getInt("duration");
                    byte[] songData = resultSet.getBytes("song_data");

                    song = new Song(songId, title, artist, album, duration, songData);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error reading song: " + e.getMessage());
        }

        return song;
    }

    // Update a song in the database
    public static void updateSong(Song song) {
        String query = "UPDATE Songs SET title = ?, artist = ?, album = ?, duration = ?, song_data = ? WHERE song_id = ?";

        try (Connection connection = DatabaseConnector.con;
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, song.getTitle());
            statement.setString(2, song.getArtist());
            statement.setString(3, song.getAlbum());
            statement.setInt(4, song.getDuration());
            statement.setBytes(5, song.getSongData());
            statement.setInt(6, song.getId());

            statement.executeUpdate();
            System.out.println("Song updated: " + song.getTitle());

        } catch (SQLException e) {
            System.out.println("Error updating song: " + e.getMessage());
        }
    }

    // Delete a song from the database
    public static void deleteSong(int songId) {
        String query = "DELETE FROM Songs WHERE song_id = ?";

        try (Connection connection = DatabaseConnector.con;
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, songId);
            statement.executeUpdate();
            System.out.println("Song deleted with ID: " + songId);

        } catch (SQLException e) {
            System.out.println("Error deleting song: " + e.getMessage());
        }
    }
}
