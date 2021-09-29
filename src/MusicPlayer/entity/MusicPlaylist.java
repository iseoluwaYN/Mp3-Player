package MusicPlayer.entity;

import java.util.ArrayList;
import java.util.List;

public class MusicPlaylist  {

    private final List<Music> musicList;
    private String name;

    public MusicPlaylist(String name) {
        this.name = name;
        musicList = new ArrayList<>();
    }
    public List<Music> getMusicList() {
        return musicList;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
