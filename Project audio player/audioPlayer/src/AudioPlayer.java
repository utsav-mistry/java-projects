import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AudioPlayer {
    private Clip clip;
    private int currentSongIndex = -1;
    private Playlist playlist = new Playlist();
    private boolean isPlaying = false;

    // Play a song by its ID
    public void play(int songId) {
        String query = "SELECT song_data FROM Songs WHERE song_id = ?";
        try (Connection connection = DatabaseConnector.con;
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, songId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    byte[] songData = resultSet.getBytes("song_data");
                    playClip(new ByteArrayInputStream(songData));
                    currentSongIndex = findSongIndexById(songId);
                } else {
                    System.out.println("Song not found.");
                }
            }
        } catch (SQLException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing song: " + e.getMessage());
        }
    }

    // Stop the currently playing song
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            isPlaying = false;
            System.out.println("Song stopped.");
        }
    }

    // Pause the currently playing song
    public void pause() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            isPlaying = false;
            System.out.println("Song paused.");
        }
    }

    // Resume the paused song
    public void play() {
        if (clip != null && !clip.isRunning() && isPlaying) {
            clip.start();
            System.out.println("Song resumed.");
        }
    }

    // Play the next song in the playlist
    public void next() {
        if (playlist.getSongs().isEmpty())
            return;

        currentSongIndex = (currentSongIndex + 1) % playlist.getSongs().size();
        play(playlist.getSongs().get(currentSongIndex).getId());
    }

    // Play the previous song in the playlist
    public void previous() {
        if (playlist.getSongs().isEmpty())
            return;

        currentSongIndex = (currentSongIndex - 1 + playlist.getSongs().size()) % playlist.getSongs().size();
        play(playlist.getSongs().get(currentSongIndex).getId());
    }

    // Set the playlist for this audio player
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
        if (!playlist.getSongs().isEmpty()) {
            currentSongIndex = 0;
        }
    }

    // Helper method to play audio from a stream
    private void playClip(ByteArrayInputStream songStream)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (clip != null && clip.isOpen()) {
            clip.stop();
            clip.close();
        }
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(songStream)) {
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            isPlaying = true;
            System.out.println("Playing song...");
        }
    }

    // Helper method to find the index of a song by its ID
    private int findSongIndexById(int songId) {
        for (int i = 0; i < playlist.getSongs().size(); i++) {
            if (playlist.getSongs().get(i).getId() == songId) {
                return i;
            }
        }
        return -1;
    }
}
