package MusicPlayer.repository;

import MusicPlayer.entity.Music;
import MusicPlayer.entity.MusicPlaylist;
import MusicPlayer.exception.Mp3Exception;

import java.util.List;
import java.util.Optional;


public class PlaylistDB{

    private final MusicPlaylist musicPlaylist;

    public PlaylistDB(MusicPlaylist musicPlaylist) {
            this.musicPlaylist = musicPlaylist;
    }

    public List<Music> getPlaylist(){return  musicPlaylist.getMusicList();}

    public void download(Music song){
        getPlaylist().add( song);
    }

    public int size() {
        return getPlaylist().size();
    }

    public Music searchFor(String songTitle) throws Mp3Exception {
        Optional<Music> music = musicPlaylist
                                    .getMusicList()
                                    .stream()
                                    .filter(m -> m.getName().equals(songTitle))
                                    .findFirst();

        return music.orElseThrow(() -> new Mp3Exception("Music not found"));

    }

    public void deleteMusic(String songTitle) {
        getPlaylist().removeIf(music -> music.getName().equals(songTitle));
    }

    public void clearDb(){
        getPlaylist().clear();
    }
}
