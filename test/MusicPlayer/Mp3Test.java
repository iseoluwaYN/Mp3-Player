package MusicPlayer;

import MusicPlayer.entity.Mp3;
import MusicPlayer.entity.Music;
import MusicPlayer.enums.SongState;
import MusicPlayer.exception.Mp3Exception;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Mp3Test {

    Mp3 mp3;

    @BeforeEach
    void startWh(){
        mp3 = new Mp3();
    }

    @AfterEach
    void endWith(){
        mp3 = null;
    }

    @Test
    void mp3Exists(){
        assertNotNull(mp3);
    }

    @Test
    void mp3IsOffByDefaultTest(){
        assertFalse(mp3.isOn());
    }

    @Test
    void mp3CanSwitchOnTest(){
        assertNotNull(mp3);
        assertFalse(mp3.isOn());
        mp3.powerButton();
        assertTrue(mp3.isOn());
    }

    @Test
    void mp3CanSwitchOffTest(){

        assertNotNull(mp3);
        assertFalse(mp3.isOn());

        mp3.powerButton();
        assertTrue(mp3.isOn());

        mp3.powerButton();
        assertFalse(mp3.isOn());
    }

    @Test
    void mp3CanDownloadMusic(){
        assertNotNull(mp3);
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        Music betterBetter = new Music();

        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());
        mp3.downloadMusic(betterBetter);
        assertEquals(2, mp3.getNumberOfSongs());
    }

    @Test
    void mp3WillNotDownloadMusicThatExists() {
        assertNotNull(mp3);
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());
        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());
    }

    @Test
    void mp3CannotDownloadMusicIfSwitchedOff(){
        assertNotNull(mp3);
        assertFalse(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(0, mp3.getNumberOfSongs());
    }

    @Test
    void mp3CanDeleteMusic() throws Mp3Exception {
        assertNotNull(mp3);
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());

        mp3.delete(peru);
        assertEquals(0, mp3.getNumberOfSongs());
    }

    @Test
    void mp3CannotDeleteMusicThatDoesNotExist() throws Mp3Exception {
        assertNotNull(mp3);
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        Music betterBetter = new Music();

        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());

        assertThrows(Mp3Exception.class, ()-> mp3.delete(betterBetter));
    }

    @Test
    void mp3CanDeleteMusicIfSwitchedOff() throws Mp3Exception {
        assertNotNull(mp3);
        assertFalse(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(0, mp3.getNumberOfSongs());

        mp3.delete(peru);
        assertEquals(0, mp3.getNumberOfSongs());
    }

    @Test
    void mp3CanPLayMusic(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());

        mp3.play(peru);
        assertEquals(SongState.PLAY, mp3.getSongState());
        assertEquals(peru, mp3.getMusicPlaying());
    }

    @Test
    void mp3CannotPlayMusicIfIsOff(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());

        mp3.powerButton();
        assertFalse(mp3.isOn());

        mp3.play(peru);
        assertFalse(mp3.isPlaying());
        assertNotEquals(peru, mp3.getMusicPlaying());
    }

    @Test
    void mp3CannotPlayMusicThatDoesNotExist(){
        assertNotNull(mp3);
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        assertEquals(0, mp3.getNumberOfSongs());

        mp3.play(peru);
        assertFalse(mp3.isPlaying());
        assertNotEquals(peru, mp3.getMusicPlaying());
    }


    @Test
    void mp3CanPauseMusicPLaying(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());

        mp3.play(peru);
        assertEquals(SongState.PLAY, mp3.getSongState());
        assertEquals(peru, mp3.getMusicPlaying());

        mp3.playOrPause();
        assertEquals(SongState.PAUSE, mp3.getSongState());
        assertFalse(mp3.isPlaying());
    }
    @Test
    void mp3CannotPauseMusicWhenOff(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());

        mp3.play(peru);
        assertEquals(SongState.PLAY, mp3.getSongState());
        assertEquals(peru, mp3.getMusicPlaying());

        mp3.powerButton();
        mp3.playOrPause();
        assertNotEquals(SongState.PAUSE, mp3.getSongState());
    }

    @Test
    void mp3CanStopMusicPlaying(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());

        mp3.play(peru);
        assertEquals(SongState.PLAY, mp3.getSongState());
        assertEquals(peru, mp3.getMusicPlaying());

        mp3.stop();
        assertEquals(SongState.STOP, mp3.getSongState());
        assertNotEquals(peru, mp3.getMusicPlaying());
    }

     @Test
    void mp3CannotStopPlayingMusicWhenOff(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());

        mp3.play(peru);
        assertTrue(mp3.isPlaying());
        assertEquals(SongState.PLAY, mp3.getSongState());
        assertEquals(peru, mp3.getMusicPlaying());

         mp3.playOrPause();
         assertEquals(SongState.PAUSE, mp3.getSongState());
         assertEquals(peru, mp3.getMusicPlaying() );

        mp3.powerButton();
        assertFalse(mp3.isOn());
        mp3.stop();
        assertNotEquals(SongState.STOP, mp3.getSongState());
//        assertNotEquals(peru, mp3.getMusicPlaying());
    }

    @Test
    void mp3CanPauseAndPLayMusic(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        mp3.downloadMusic(peru);
        assertEquals(1, mp3.getNumberOfSongs());

        mp3.play(peru);
        assertTrue(mp3.isPlaying());
        assertEquals(SongState.PLAY, mp3.getSongState());
        assertEquals(peru, mp3.getMusicPlaying());

        mp3.playOrPause();
        assertEquals(SongState.PAUSE, mp3.getSongState());
        assertEquals(peru, mp3.getMusicPlaying() );

        mp3.playOrPause();
        assertEquals(SongState.PLAY, mp3.getSongState());
        assertEquals(peru, mp3.getMusicPlaying() );
    }

    @Test
    void mp3HasDefaultVolume(){
        mp3.powerButton();
        assertTrue(mp3.isOn());
        assertEquals(10,mp3.getVolume());
    }

    @Test
    void mp3CannotGetVolumeIfIsOff(){
        assertFalse(mp3.isOn());
        assertEquals(0,mp3.getVolume());
    }

    @Test
    void volumeCanBeIncreased(){
        mp3.powerButton();
        assertTrue(mp3.isOn());
        assertEquals(10,mp3.getVolume());

        mp3.increaseVolume();
        mp3.increaseVolume();
        mp3.increaseVolume();
        mp3.increaseVolume();
        mp3.increaseVolume();

        assertEquals(15, mp3.getVolume());
    }

    @Test
    void volumeCannotBeIncreasedIfIsOff(){
        assertFalse(mp3.isOn());
        assertEquals(0,mp3.getVolume());

        mp3.increaseVolume();
        mp3.increaseVolume();
        mp3.increaseVolume();
        mp3.increaseVolume();
        mp3.increaseVolume();

        assertEquals(0, mp3.getVolume());
    }
    @Test
    void volumeCanBeDecreased(){
        mp3.powerButton();
        assertTrue(mp3.isOn());
        assertEquals(10,mp3.getVolume());

        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();

        assertEquals(5, mp3.getVolume());
    }

    @Test
    void volumeCannotBeDecreaseIfIsOff(){
        assertFalse(mp3.isOn());
        assertEquals(0,mp3.getVolume());

        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();

        assertEquals(0, mp3.getVolume());
    }

    @Test
    void minVolumeIsZero(){
        mp3.powerButton();
        assertTrue(mp3.isOn());
        assertEquals(10,mp3.getVolume());

        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();
        mp3.decreaseVolume();

        assertEquals(0, mp3.getVolume());
    }
    @Test
    void mp3CanMuteAndUnmute(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        mp3.muteOrUnmute();
        assertTrue(mp3.isMute());

        assertEquals(0, mp3.getVolume());
        mp3.muteOrUnmute();
        assertFalse(mp3.isMute());
    }

    @Test
    void mp3returnsToPreviousVolumeAfterUnmute(){
        mp3.powerButton();
        assertTrue(mp3.isOn());
        Music peru = new Music();
        mp3.downloadMusic(peru);
        mp3.play(peru);
        assertEquals(10, mp3.getVolume());


        mp3.muteOrUnmute();
        assertTrue(mp3.isMute());
        assertEquals(0, mp3.getVolume());
        mp3.powerButton();
        mp3.muteOrUnmute();
        assertEquals(0, mp3.getVolume());
    }

    @Test
    void m3pCanCreatePlaylist(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        ArrayList<Music> myFistPlaylist = new ArrayList<>();
        mp3.createPlaylist(myFistPlaylist);
        assertEquals(1, mp3.getNumberOfPlaylists());
    }

    @Test
    void m3pCannotCreatePlaylistWhenOff(){
        assertFalse(mp3.isOn());

        ArrayList<Music> myFistPlaylist = new ArrayList<>();
        mp3.createPlaylist(myFistPlaylist);
        assertEquals(0, mp3.getNumberOfPlaylists());
    }

    @Test
    void m3pCanAdMusicToPlaylist() {
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        Music betterBetter = new Music();
        mp3.downloadMusic(peru);
        mp3.downloadMusic(betterBetter);
        assertEquals(2, mp3.getNumberOfSongs());

        ArrayList<Music> myFistPlaylist = new ArrayList<>();
        mp3.createPlaylist(myFistPlaylist);
        assertEquals(1, mp3.getNumberOfPlaylists());

        mp3.addToPlaylist(peru, myFistPlaylist);
        assertEquals(1, mp3.getNumberOfSongsInPlaylist(myFistPlaylist));
    }

    @Test
    void m3pCannotAddToPlaylistWhenOff() {
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        Music betterBetter = new Music();
        mp3.downloadMusic(peru);
        mp3.downloadMusic(betterBetter);
        assertEquals(2, mp3.getNumberOfSongs());

        ArrayList<Music> myFistPlaylist = new ArrayList<>();
        mp3.createPlaylist(myFistPlaylist);
        assertEquals(1, mp3.getNumberOfPlaylists());

        mp3.powerButton();
        mp3.addToPlaylist(peru, myFistPlaylist);
        assertEquals(0, mp3.getNumberOfSongsInPlaylist(myFistPlaylist));
    }

    @Test
    void mp3CanRemoveFromPlaylist(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        Music betterBetter = new Music();
        mp3.downloadMusic(peru);
        mp3.downloadMusic(betterBetter);
        assertEquals(2, mp3.getNumberOfSongs());

        ArrayList<Music> myFistPlaylist = new ArrayList<>();
        mp3.createPlaylist(myFistPlaylist);
        assertEquals(1, mp3.getNumberOfPlaylists());

        mp3.addToPlaylist(peru, myFistPlaylist);
        mp3.addToPlaylist(betterBetter, myFistPlaylist);
        assertEquals(2, mp3.getNumberOfSongsInPlaylist(myFistPlaylist));

        mp3.removeFromPlaylist(peru, myFistPlaylist);
        assertEquals(1, mp3.getNumberOfSongsInPlaylist(myFistPlaylist));
    }
    @Test
    void mp3CannotRemoveFromPlaylistWhenOff(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        Music betterBetter = new Music();
        mp3.downloadMusic(peru);
        mp3.downloadMusic(betterBetter);
        assertEquals(2, mp3.getNumberOfSongs());

        ArrayList<Music> myFistPlaylist = new ArrayList<>();
        mp3.createPlaylist(myFistPlaylist);
        assertEquals(1, mp3.getNumberOfPlaylists());

        mp3.addToPlaylist(peru, myFistPlaylist);
        mp3.addToPlaylist(betterBetter, myFistPlaylist);
        assertEquals(2, mp3.getNumberOfSongsInPlaylist(myFistPlaylist));

        mp3.powerButton();
        mp3.removeFromPlaylist(peru, myFistPlaylist);
        assertEquals(2, mp3.getNumberOfSongsInPlaylist(myFistPlaylist));
    }

    @Test
    void m3pCanPlayNextSong(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        Music betterBetter = new Music();
        mp3.downloadMusic(peru);
        mp3.downloadMusic(betterBetter);
        assertEquals(2, mp3.getNumberOfSongs());

        mp3.play(peru);
        assertEquals(peru, mp3.getMusicPlaying());
        mp3.playNextSong();
        assertEquals(betterBetter, mp3.getMusicPlaying());
    }
    @Test
    void m3pCannotPlayNextSongWhenOff(){
        mp3.powerButton();
        assertTrue(mp3.isOn());

        Music peru = new Music();
        Music betterBetter = new Music();
        mp3.downloadMusic(peru);
        mp3.downloadMusic(betterBetter);
        assertEquals(2, mp3.getNumberOfSongs());

        mp3.play(peru);
        assertEquals(peru, mp3.getMusicPlaying());

        mp3.powerButton();
        mp3.playNextSong();
        assertEquals(null, mp3.getMusicPlaying());
    }


}