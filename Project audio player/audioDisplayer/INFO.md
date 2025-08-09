# Audio Player Java Project

## Overview

This project is a Java-based audio player application designed to manage a playlist of songs. The application supports various operations such as viewing the playlist, adding and deleting songs, shuffling the playlist, and navigating between songs. The songs are managed via a MySQL database. The project includes a custom implementation of a dynamic array list (`MyArrayList<T>`) for managing the playlist.

## Project Structure

- **`Main.java`**: The main entry point of the application. It provides a console-based menu for interacting with the playlist and audio player.
- **`Playlist.java`**: Manages playlist operations including adding, deleting, shuffling, and displaying songs. It also interacts with the database to retrieve and remove songs.
- **`AudioPlayer.java`**: Handles the playback functionality. For the Type 2 project, it displays messages indicating the song currently being "played" instead of actual audio playback.
- **`PlaylistManager.java`**: Provides static methods for interacting with the database. It includes functionality to refresh the playlist from the database and manage song entries.
- **`DatabaseConnector.java`**: Manages the connection to the MySQL database.
- **`MyArrayList.java`**: A custom implementation of a generic dynamic array list. It includes methods for adding, removing, retrieving elements, and managing capacity.
- **`Song.java`**: Represents a song entity with attributes such as ID, title, artist, album, duration, and song data.

## Detailed Method Usage

### `Main.java`

- **`main(String[] args)`**: Initializes the playlist and audio player. Displays a menu for user interaction and handles user input.
- **`addSong(Scanner scanner, Playlist playlist)`**: Adds a song to the playlist based on user input.
- **`deleteSong(Scanner scanner, Playlist playlist)`**: Deletes a song from the playlist based on user input.

### `Playlist.java`

- **`addSong(int songId)`**: Retrieves a song from the database by its ID and adds it to the playlist if it is not already present.
- **`deleteSong(int songId)`**: Removes a song from the playlist and the database by its ID.
- **`shuffle()`**: Randomizes the order of songs in the playlist.
- **`nextSong()`**: Moves to the next song in the playlist and displays its title.
- **`previousSong()`**: Moves to the previous song in the playlist and displays its title.
- **`display()`**: Lists all songs in the playlist.
- **`clear()`**: Clears all songs from the playlist.
- **`getSongs()`**: Returns a list of all songs in the playlist.

### `AudioPlayer.java`

- **`play(int songId)`**: (Not implemented for Type 2) Intended to play a song by its ID.
- **`stop()`**: (Not implemented for Type 2) Intended to stop the currently playing song.
- **`next()`**: Moves to the next song in the playlist and displays its title.
- **`previous()`**: Moves to the previous song in the playlist and displays its title.
- **`setPlaylist(Playlist playlist)`**: Sets the playlist for playback.

### `PlaylistManager.java`

- **`refreshPlaylist(Playlist playlist)`**: Clears and reloads the playlist from the database.
- **`createSong(Song song)`**: Inserts a new song into the database.
- **`readSong(int songId)`**: Retrieves a song from the database by its ID.
- **`updateSong(Song song)`**: Updates a song's details in the database.
- **`deleteSong(int songId)`**: Removes a song from the database by its ID.

### `DatabaseConnector.java`

- **`getConnection()`**: Establishes a connection to the MySQL database.

### `MyArrayList<T>.java`

- **`MyArrayList()`**: Initializes an empty list with a default capacity.
- **`add(T element)`**: Adds an element to the list, expanding capacity if necessary.
- **`remove(T element)`**: Removes the first occurrence of the specified element from the list.
- **`get(int index)`**: Retrieves an element at the specified index.
- **`size()`**: Returns the number of elements in the list.
- **`isEmpty()`**: Checks if the list is empty.
- **`clear()`**: Removes all elements from the list.
- **`ensureCapacity()`**: Doubles the list's capacity if needed.

### `Song.java`

- **Attributes**: Represents a song with ID, title, artist, album, duration, and song data.

## Database Setup

1. **Create Database and Table:**
   ```sql
   CREATE DATABASE AudioPlayerDB;
   USE AudioPlayerDB;

   CREATE TABLE Songs (
       song_id INT PRIMARY KEY,
       title VARCHAR(255),
       artist VARCHAR(255),
       album VARCHAR(255),
       duration INT,
       song_data BLOB
   );
