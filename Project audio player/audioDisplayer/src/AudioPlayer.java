public class AudioPlayer {
    private Playlist playlist;

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public void play() {
        if (playlist != null) {
            playlist.display();
        } else {
            System.out.println("No playlist set.");
        }
    }

    public void pause() {
        System.out.println("Playback paused.");
    }

    public void next() {
        if (playlist != null) {
            playlist.nextSong();
            playlist.display();
        } else {
            System.out.println("No playlist set.");
        }
    }

    public void previous() {
        if (playlist != null) {
            playlist.previousSong();
            playlist.display();
        } else {
            System.out.println("No playlist set.");
        }
    }
}
