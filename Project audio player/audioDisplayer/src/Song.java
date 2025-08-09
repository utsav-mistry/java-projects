public class Song {
    private int songId;
    private String title;
    private String artist;
    private String album;
    private int duration; // Duration in seconds

    public Song(int songId, String title, String artist, String album, int duration) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    // Getters
    public int getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }

    // Setters
    public void setSongId(int songId) {
        this.songId = songId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
