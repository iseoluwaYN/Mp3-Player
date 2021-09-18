package MusicPlayer.entity;

import java.util.ArrayList;
import java.util.List;

public class Album {
    public String name;
    List<Music> music = new ArrayList<Music>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
