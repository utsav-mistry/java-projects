import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize Playlist and AudioPlayer
        Playlist playlist = new Playlist();
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.setPlaylist(playlist);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. View Playlist");
            System.out.println("2. Add Song");
            System.out.println("3. Delete Song");
            System.out.println("4. Refresh Playlist");
            System.out.println("5. Play");
            System.out.println("6. Pause");
            System.out.println("7. Next Song");
            System.out.println("8. Previous Song");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    playlist.display();
                    break;
                case 2:
                    addSong(scanner, playlist);
                    break;
                case 3:
                    deleteSong(scanner, playlist);
                    break;
                case 4:
                    playlist.shuffle();
                    break;
                case 5:
                    audioPlayer.play();
                    break;
                case 6:
                    audioPlayer.pause();
                    break;
                case 7:
                    audioPlayer.next();
                    break;
                case 8:
                    audioPlayer.previous();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addSong(Scanner scanner, Playlist playlist) {
        System.out.print("Enter song ID: ");
        int songId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter song title: ");
        String title = scanner.nextLine();

        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();

        System.out.print("Enter album: ");
        String album = scanner.nextLine();

        System.out.print("Enter duration (in seconds): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Create Song object
        Song song = new Song(songId, title, artist, album, duration);

        // Add song to the playlist
        playlist.addSong(song);

        System.out.println("Song added successfully!");
    }

    private static void deleteSong(Scanner scanner, Playlist playlist) {
        System.out.print("Enter song ID to delete: ");
        int songId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Delete song from the playlist
        playlist.deleteSongById(songId);

        System.out.println("Song deleted successfully!");
    }
}
