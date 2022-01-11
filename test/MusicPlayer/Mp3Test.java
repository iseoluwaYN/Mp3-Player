package MusicPlayer;

import MusicPlayer.entity.Mp3;
import MusicPlayer.entity.Music;
import MusicPlayer.enums.SongState;
import MusicPlayer.exception.Mp3Exception;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}