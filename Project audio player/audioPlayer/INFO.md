# Audio Player Java Project

## Overview

This project is a Java-based audio player application that allows users to manage a playlist of songs stored in a MySQL database. The application supports adding, deleting, and shuffling songs, as well as playing them. The project includes a custom implementation of a generic dynamic array list, `MyArrayList<T>`, to handle playlist operations.

## Project Structure

- **`Main.java`**: The main entry point of the application with the user interface and menu logic.
- **`Playlist.java`**: Manages playlist operations including adding, deleting, shuffling, and displaying songs.
- **`AudioPlayer.java`**: Handles audio playback using Java's `javax.sound.sampled` package.
- **`PlaylistManager.java`**: Provides static methods for interacting with the database to manage songs.
- **`DatabaseConnector.java`**: Manages the database connection.
- **`MyArrayList.java`**: A custom implementation of a generic dynamic array list.
- **`MusicDatabaseSync.java`**: Synchronizes the database with the music folder.
- **`Song.java`**: Represents a song with attributes and methods for accessing and modifying them.

## Detailed Method Usage

### `Main.java`

**`main(String[] args)`**
- Initializes the database connection via `DatabaseConnector`.
- Displays a menu and processes user input for playlist operations.
- Utilizes methods from `Playlist` and `AudioPlayer` based on user choices.

**Methods Used:**
- **`viewPlaylist()`**: Shows all songs in the playlist using `playlist.display()`.
- **`addSong(Scanner scanner)`**: Adds a song to the playlist by ID.
- **`deleteSong(Scanner scanner)`**: Deletes a song by ID from the playlist and database.
- **`shufflePlaylist()`**: Shuffles the playlist.
- **`refreshPlaylist()`**: Synchronizes the playlist with the database.

### `Playlist.java`

**`addSong(int songId)`**
- Retrieves and adds a song to the playlist if not already present.

**`deleteSong(int songId)`**
- Retrieves, removes a song from the playlist, and deletes it from the database.

**`shuffle()`**
- Randomizes the order of songs in the playlist.

**`nextSong()`**
- Moves to the next song in the playlist.

**`previousSong()`**
- Moves to the previous song in the playlist.

**`display()`**
- Lists all songs in the playlist.

### `AudioPlayer.java`

**`play(int songId)`**
- Displays "Playing: " followed by the song title.

**`stop()`**
- Displays "Playback stopped."

**`next()`**
- Moves to the next song in the playlist and displays its title.

**`previous()`**
- Moves to the previous song in the playlist and displays its title.

**`setPlaylist(Playlist playlist)`**
- Sets the playlist for navigation.

### `PlaylistManager.java`

**`refreshPlaylist(Playlist playlist)`**
- Clears the playlist and reloads it from the database.

**`createSong(Song song)`**
- Inserts a new song into the database.

**`readSong(int songId)`**
- Retrieves a song from the database.

**`updateSong(Song song)`**
- Updates a song's details in the database.

**`deleteSong(int songId)`**
- Removes a song from the database.

### `DatabaseConnector.java`

**`getConnection()`**
- Establishes a connection to the MySQL database.

### `MyArrayList<T>.java`

**`MyArrayList()`**
- Initializes an empty list with a default capacity.

**`add(T element)`**
- Adds an element to the list, expanding capacity if necessary.

**`remove(T element)`**
- Removes the first occurrence of the specified element from the list.

**`get(int index)`**
- Retrieves an element at the specified index.

**`size()`**
- Returns the number of elements in the list.

**`isEmpty()`**
- Checks if the list is empty.

**`clear()`**
- Removes all elements from the list.

**`ensureCapacity()`**
- Doubles the list's capacity if needed.

### `MusicDatabaseSync.java`

**`syncDatabaseWithFolder(String folderPath)`**
- Synchronizes the database with the music folder.

### `Song.java`

**Attributes:**
- `songId`: ID of the song.
- `title`: Title of the song.
- `artist`: Artist of the song.
- `album`: Album of the song.
- `duration`: Duration of the song.
- `songData`: Binary data of the song.

**Methods:**
- **`getSongId()`**: Returns the song ID.
- **`getTitle()`**: Returns the song title.
- **`getArtist()`**: Returns the song artist.
- **`getAlbum()`**: Returns the song album.
- **`getDuration()`**: Returns the song duration.
- **`getSongData()`**: Returns the song binary data.
- **`setSongId()`**: Sets the song ID.
- **`setTitle()`**: Sets the song title.
- **`setArtist()`**: Sets the song artist.
- **`setAlbum()`**: Sets the song album.
- **`setDuration()`**: Sets the song duration.
- **`setSongData()`**: Sets the song binary data.

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
