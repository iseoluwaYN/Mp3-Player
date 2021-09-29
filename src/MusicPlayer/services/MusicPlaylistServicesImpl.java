package MusicPlayer.services;

import MusicPlayer.entity.Mp3;
import MusicPlayer.entity.Music;
import MusicPlayer.exception.Mp3Exception;
import MusicPlayer.repository.PlaylistDB;

public class MusicPlaylistServicesImpl  implements MusicPlaylistServices{

    private final PlaylistDB playlistDB;
    private final Mp3 mp3;

    public MusicPlaylistServicesImpl(PlaylistDB playlistDB) {
        this.playlistDB = playlistDB;
        mp3 = new Mp3();
    }

    public PlaylistDB getPlaylistDB() {
        return playlistDB;
    }

    @Override
    public void addToPlaylist(Music songTitle) throws Mp3Exception {
        if(mp3.isOn()) {
            throw new Mp3Exception("Mp3 is off");
        }
        playlistDB.download(songTitle);

    }

    @Override
    public void delete(String songTitle) throws Mp3Exception {
        if(mp3.isOn()) {
            throw new Mp3Exception("Mp3 is off");
        }
        playlistDB.deleteMusic(songTitle);
    }

    @Override
    public int getNumberOfMusic() {
        return playlistDB.size();
    }

    @Override
    public Music search(String songTitle) throws Mp3Exception {
        return playlistDB.searchFor(songTitle);
    }

    public void clearDB(){
        playlistDB.clearDb();
    }
}
