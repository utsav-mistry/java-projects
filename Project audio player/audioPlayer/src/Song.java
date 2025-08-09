public class Song {
    private int songId;
    private String title;
    private String artist;
    private String album;
    private int duration; // Assuming duration is in seconds
    private byte[] songData;

    // Constructor
    public Song(int songId, String title, String artist, String album, int duration, byte[] songData) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.songData = songData;
    }

    // Getters
    public int getId() {
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

    public byte[] getSongData() {
        return songData;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", duration=" + duration +
                '}';
    }
}
