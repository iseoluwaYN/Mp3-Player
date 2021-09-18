package MusicPlayer;

import MusicPlayer.entity.Mp3;
import MusicPlayer.entity.Music;
import MusicPlayer.entity.MusicPlaylist;
import MusicPlayer.services.MusicPlaylistServices;
import MusicPlayer.services.MusicPlaylistServicesImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Mp3Test {

    Mp3 mp3;
    MusicPlaylistServices musicPlaylistServices;
    @BeforeEach
    void startWh(){
        mp3 = new Mp3();
        musicPlaylistServices =new  MusicPlaylistServicesImpl();
    }

    @AfterEach
    void endWith(){
        mp3 = null;
        musicPlaylistServices.clearDB();
    }

    @Test
    void mp3Exists(){
        assertNotNull(mp3);
    }

    @Test
    void canBeTurnedOn(){
        mp3.switchOn(false);
        assertFalse(mp3.isOn());
        mp3.switchOn(true);
        assertTrue(mp3.isOn());
    }

    @Test
    void isOffByDefault(){
        assertFalse(mp3.isOn());
     }


    @Test
    void mp3HasPlaylist(){
//        when
        mp3.switchOn(true);
        assertTrue(mp3.isOn());

        assertNotNull(musicPlaylistServices);
    }


    @Test
    void addToPlaylist() {
//        when
        mp3.switchOn(true);
        assertTrue(mp3.isOn());

        Music work = new Music();
        assertNotNull(work);
        musicPlaylistServices.addToPlaylist(work);
        assertEquals(1, musicPlaylistServices.getNumberOfMusic());
    }

    @Test
    void searchForMusic(){
//        when
        mp3.switchOn(true);
        assertTrue(mp3.isOn());

        Music work = new Music();
        assertNotNull(work);
        musicPlaylistServices.addToPlaylist(work);

        Music wild = new Music();
        assertNotNull(wild);
        musicPlaylistServices.addToPlaylist(wild);

        assertEquals(2, musicPlaylistServices.getNumberOfMusic());
        assertNotNull(musicPlaylistServices.search(work));

    }

    @Test
    void deleteFromPlaylist() {
//        when
        mp3.switchOn(true);
        assertTrue(mp3.isOn());

//        MusicPlaylistServices musicPlaylistServices =new  MusicPlaylistServicesImpl();
        Music work = new Music();
        assertNotNull(work);
        musicPlaylistServices.addToPlaylist(work);

        assertEquals(1, musicPlaylistServices.getNumberOfMusic());
        assertNotNull(musicPlaylistServices.search(work));
        musicPlaylistServices.delete(work);
        assertEquals(0, musicPlaylistServices.getNumberOfMusic());
    }
}