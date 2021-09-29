package MusicPlayer.entity;


import java.util.ArrayList;
import java.util.List;

public class Mp3 {
    private static  Mp3 mp3;
    public boolean isOn;
    public MusicPlaylist musicPlaylist;
    public ControlPanel controlPanel;
    public final List<MusicPlaylist> musicPlaylists ;

    public Mp3(){
        this.musicPlaylists = new ArrayList<>();
    }

    public List<MusicPlaylist> getPlayLists() {
        return musicPlaylists;
    }

    public static Mp3 getInstance() {
        if(mp3 == null){
            mp3 = new Mp3();
        }
        return mp3;
    }

    public boolean isOn() {
        return isOn;
    }

    public boolean switchOn(boolean isOn){
        this.isOn = isOn;
        return isOn;
    }

    public MusicPlaylist getMusicPlaylist() {
        return musicPlaylist;
    }

    public void setMusicPlaylist(MusicPlaylist musicPlaylist) {
        this.musicPlaylist = musicPlaylist;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel control){
        this.controlPanel = control;
    }
}