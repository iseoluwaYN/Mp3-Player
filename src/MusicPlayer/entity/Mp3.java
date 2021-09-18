package MusicPlayer.entity;


public class Mp3 {
    public boolean isOn;
    public MusicPlaylist musicPlaylist;
    public ControlPanel controlPanel;

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