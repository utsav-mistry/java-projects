import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicDatabaseSync {

    /**
     * 
     * 
     * @param MUSIC_FOLDER_PATH "has to be replaced with the original file path of
     *                          the music folder"
     * 
     * 
     */
    private static final String MUSIC_FOLDER_PATH = "/mu/downloads/music"; // Adjust as needed

    public static void syncDatabaseWithFolder() {
        File folder = new File(MUSIC_FOLDER_PATH);
        if (!folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        Connection connection = null;
        PreparedStatement insertStatement = null;
        PreparedStatement deleteStatement = null;
        ResultSet resultSet = null;

        try {
            // Use the static connection from DatabaseConnector
            connection = DatabaseConnector.con;

            // Prepare SQL statements
            String checkSongQuery = "SELECT song_id FROM Songs WHERE title = ?";
            String deleteSongQuery = "DELETE FROM Songs WHERE title = ?";
            String insertSongQuery = "INSERT INTO Songs (title, artist, album, duration, song_data) VALUES (?, ?, ?, ?, ?)";

            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".mp3")) {
                        String title = file.getName().substring(0, file.getName().lastIndexOf('.')); // Assumes title is
                                                                                                     // the file name
                                                                                                     // without
                                                                                                     // extension

                        // Check if the song already exists in the database
                        insertStatement = connection.prepareStatement(insertSongQuery);
                        insertStatement.setString(1, title);
                        resultSet = insertStatement.executeQuery();

                        if (!resultSet.next()) {
                            // If song does not exist in database, insert it
                            try (FileInputStream fis = new FileInputStream(file)) {
                                PreparedStatement statement = connection.prepareStatement(insertSongQuery);
                                statement.setString(1, title);
                                statement.setString(2, "Unknown Artist"); // Placeholder for artist
                                statement.setString(3, "Unknown Album"); // Placeholder for album
                                statement.setTime(4, new java.sql.Time(0)); // Placeholder for duration
                                statement.setBinaryStream(5, fis, fis.available());
                                statement.executeUpdate();
                                System.out.println("Inserted new song: " + title);
                            }
                        }
                    }
                }
            }

            // Delete songs from database that are no longer in the folder
            List<String> existingTitles = getExistingTitlesFromFolder(folder);

            deleteStatement = connection.prepareStatement(deleteSongQuery);
            PreparedStatement checkExistingStatement = connection.prepareStatement(checkSongQuery);

            // First, get all titles from the database
            ResultSet dbResultSet = checkExistingStatement.executeQuery();
            List<String> dbTitles = new ArrayList<>();
            while (dbResultSet.next()) {
                dbTitles.add(dbResultSet.getString("title"));
            }

            // Delete songs that are not in the folder
            for (String title : dbTitles) {
                if (!existingTitles.contains(title)) {
                    deleteStatement.setString(1, title);
                    deleteStatement.executeUpdate();
                    System.out.println("Deleted song from database: " + title);
                }
            }

        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (insertStatement != null)
                    insertStatement.close();
                if (deleteStatement != null)
                    deleteStatement.close();
                // Connection is handled by the caller (main class) to keep it open
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    private static List<String> getExistingTitlesFromFolder(File folder) {
        List<String> titles = new ArrayList<>();
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".mp3")) {
                    String title = file.getName().substring(0, file.getName().lastIndexOf('.'));
                    titles.add(title);
                }
            }
        }
        return titles;
    }
}