package MusicPlayer.entity;

public class MusicPlaylist  {
    public Album albumName;
    public Music music;

    public MusicPlaylist(Music music) {

    }

    public Album getAlbumName() {
        return albumName;
    }

    public void setAlbumName(Album albumName) {
        this.albumName = albumName;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
