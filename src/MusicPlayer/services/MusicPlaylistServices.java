package MusicPlayer.services;

import MusicPlayer.entity.Music;
import MusicPlayer.exception.Mp3Exception;
import MusicPlayer.repository.PlaylistDB;

public interface MusicPlaylistServices {

    public void addToPlaylist(Music songTitle) throws Mp3Exception;
    public void delete(String songTitle) throws Mp3Exception;
    public int getNumberOfMusic();
    public PlaylistDB getPlaylistDB();
    Music search(String songTitle) throws Mp3Exception;

    void clearDB();
}
