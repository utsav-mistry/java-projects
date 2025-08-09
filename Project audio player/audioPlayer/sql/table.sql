CREATE DATABASE AudioPlayerDB;

USE AudioPlayerDB;

CREATE TABLE Songs (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    artist VARCHAR(255),
    album VARCHAR(255),
    duration TIME,
    song_data LONGBLOB
);