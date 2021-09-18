package MusicPlayer.services;

import MusicPlayer.entity.Music;
import MusicPlayer.repository.PlaylistDB;

public class MusicPlaylistServicesImpl  implements MusicPlaylistServices{

    private PlaylistDB playlistDB;

    public MusicPlaylistServicesImpl() {
        playlistDB = PlaylistDB.getInstance();
    }

    @Override
    public void addToPlaylist(Music songTitle) {
            playlistDB.download(songTitle);
    }

    @Override
    public void delete(Music songTitle) {
        playlistDB.deleteMusic(songTitle);
    }

    @Override
    public int getNumberOfMusic() {
        return playlistDB.size();
    }

    @Override
    public Music search(Music songTitle) {
        return playlistDB.searchFor(songTitle);
    }

    public void clearDB(){
        playlistDB.clearDb();
    }
}
