import java.sql.*;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/AudioPlayerDB";
    private static final String JDBC_USER = "root"; // Update with your MySQL username
    private static final String JDBC_PASSWORD = ""; // Update with your MySQL password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static MyLinkedList<Song> getAllSongs() {
        MyLinkedList<Song> songs = new MyLinkedList<>();
        String query = "SELECT * FROM Songs";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int songId = rs.getInt("song_id");
                String title = rs.getString("title");
                int artistId = rs.getInt("artist_id");
                int albumId = rs.getInt("album_id");
                int duration = rs.getInt("duration");

                // Fetch artist and album details
                String artist = getArtistName(artistId, conn);
                String album = getAlbumTitle(albumId, conn);

                Song song = new Song(songId, title, artist, album, duration);
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    private static String getArtistName(int artistId, Connection conn) throws SQLException {
        String query = "SELECT name FROM Artists WHERE artist_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, artistId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        }
        return "Unknown Artist";
    }

    private static String getAlbumTitle(int albumId, Connection conn) throws SQLException {
        String query = "SELECT title FROM Albums WHERE album_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, albumId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("title");
                }
            }
        }
        return "Unknown Album";
    }
}
