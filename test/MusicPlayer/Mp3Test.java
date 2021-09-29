package MusicPlayer;

import MusicPlayer.entity.Mp3;
import MusicPlayer.entity.Music;
import MusicPlayer.entity.MusicPlaylist;
import MusicPlayer.exception.Mp3Exception;
import MusicPlayer.repository.PlaylistDB;
import MusicPlayer.services.MusicPlaylistServices;
import MusicPlayer.services.MusicPlaylistServicesImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Mp3Test {

    Mp3 mp3;
    MusicPlaylistServices musicPlaylistServices;
    PlaylistDB playlistDB;

    @BeforeEach
    void startWh(){
        mp3 = new Mp3();
        MusicPlaylist musicPlaylist = new MusicPlaylist("Afro");
        playlistDB = new PlaylistDB(musicPlaylist);
        musicPlaylistServices =new  MusicPlaylistServicesImpl(playlistDB);
    }

    @AfterEach
    void endWith(){
//        mp3 = null;
//        musicPlaylistServices.clearDB();
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
    void addToPlaylist(){
//        when
        mp3.switchOn(true);
        assertTrue(mp3.isOn());

        Music work = new Music();
        assertNotNull(work);
        try {
            musicPlaylistServices.addToPlaylist(work);
        } catch (Mp3Exception e) {
            e.printStackTrace();
        }
        assertEquals(1, musicPlaylistServices.getNumberOfMusic());
    }

//    @Test
//    void cannotAddToPlaylistWhenOff() throws Mp3Exception {
////        when
//        mp3.switchOn(false);
//        assertFalse(mp3.isOn());
//
//        Music work = new Music();
//        assertNotNull(work);
//        assertThrows(Mp3Exception.class, () -> musicPlaylistServices.addToPlaylist(work));
//        assertEquals(0, musicPlaylistServices.getNumberOfMusic());
//
//    }

    @Test
    void searchForMusic() throws Mp3Exception {
//        when
        mp3.switchOn(true);
        assertTrue(mp3.isOn());

        Music work = new Music();
        work.setName("work");
        musicPlaylistServices.addToPlaylist(work);
        assertNotNull(work);


        Music wild = new Music();
        wild.setName("wild");
        musicPlaylistServices.addToPlaylist(wild);
        assertNotNull(wild);

        assertEquals(2, musicPlaylistServices.getNumberOfMusic());
        assertNotNull(musicPlaylistServices.search("work"));
    }

    @Test
    void deleteFromPlaylist() throws Mp3Exception {
//        when
        mp3.switchOn(true);
        assertTrue(mp3.isOn());

//        MusicPlaylistServices musicPlaylistServices =new  MusicPlaylistServicesImpl();
        Music work = new Music();
        work.setName("work");
        assertNotNull(work);
        musicPlaylistServices.addToPlaylist(work);


        assertEquals(1, musicPlaylistServices.getNumberOfMusic());
        assertNotNull(musicPlaylistServices.search("work"));
        musicPlaylistServices.delete("work");

        assertEquals(0, musicPlaylistServices.getNumberOfMusic());
    }

//    @Test
//    void deleteFromPlaylistWhenMp3IsOff() throws Mp3Exception {
////        when
//        mp3.switchOn(false);
//        assertFalse(mp3.isOn());
//
//        System.out.println(mp3.switchOn(true));
//        Music work = new Music();
//        assertNotNull(work);
//        musicPlaylistServices.addToPlaylist(work);
//
//        System.out.println(musicPlaylistServices.getNumberOfMusic());
//        assertThrows(Mp3Exception.class,()->  musicPlaylistServices.delete("work"));
//    }


}