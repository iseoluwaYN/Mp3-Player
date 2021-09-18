package MusicPlayer.services;

import MusicPlayer.entity.Music;

public interface MusicPlaylistServices {

    public void addToPlaylist(Music songTitle);
    public void delete(Music songTitle);
    public int getNumberOfMusic();
    public Music search(Music songTitle);

    void clearDB();
}
