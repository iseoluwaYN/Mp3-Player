package MusicPlayer;

import MusicPlayer.entity.Mp3;
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






}