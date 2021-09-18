package MusicPlayer.repository;

import MusicPlayer.entity.Music;
import MusicPlayer.entity.MusicPlaylist;

import java.util.ArrayList;
import java.util.List;


public class PlaylistDB {

    private List<Music> playlist = new ArrayList<>();
    private static  PlaylistDB playlistDB;

    private PlaylistDB() {

    }

    public static PlaylistDB getInstance() {
        if(playlistDB == null){
            playlistDB = new PlaylistDB();
        }
        return playlistDB;
    }

    public void download(Music song){
        playlist.add(song);
    }

    public int size() {
        return playlist.size();
    }

    public Music searchFor(Music songTitle) {
        for(Music song: playlist){
            if(song == songTitle)
                return song;
        }
        return null;
    }

    public void deleteMusic(Music songTitle) {
        for(Music song: playlist){
            if(song == songTitle){
                playlist.remove(song);
            }
            break;
        }
    }

    public void clearDb(){
        playlist.clear();
    }
}
