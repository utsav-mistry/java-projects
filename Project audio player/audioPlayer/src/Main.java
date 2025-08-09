import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.setPlaylist(playlist);
        MusicDatabaseSync.syncDatabaseWithFolder();
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. View Playlist");
            System.out.println("2. Add Song");
            System.out.println("3. Delete Song");
            System.out.println("4. Shuffle Playlist");
            System.out.println("5. Refresh Playlist");
            System.out.println("6. Play");
            System.out.println("7. Pause");
            System.out.println("8. Next Song");
            System.out.println("9. Previous Song");
            System.out.println("10. Exit");
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
                    PlaylistManager.refreshPlaylist(playlist);
                    break;
                case 6:
                    audioPlayer.play();
                    break;
                case 7:
                    audioPlayer.pause();
                    break;
                case 8:
                    audioPlayer.next();
                    break;
                case 9:
                    audioPlayer.previous();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addSong(Scanner scanner, Playlist playlist) {
        System.out.print("Enter song ID to add: ");
        int songId = scanner.nextInt();
        playlist.addSong(songId);
    }

    private static void deleteSong(Scanner scanner, Playlist playlist) {
        System.out.print("Enter song ID to delete: ");
        int songId = scanner.nextInt();
        playlist.deleteSong(songId);
    }
}
