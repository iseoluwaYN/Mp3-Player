package MusicPlayer.entity;


import MusicPlayer.exception.Mp3Exception;

import java.util.ArrayList;

public class Mp3 {

    private boolean isOn;
    private ArrayList<Music> songs = new ArrayList<>();
    private int numberOfSongs;

    public boolean isOn() {
        return isOn;
    }
    public void switchOn(){
        isOn = true;
    }
    public void switchOff(){
        isOn = false;
    }
    public void powerButton(){
        if(isOn) {
            switchOff();
        }
        else switchOn();
    }

    public void downloadMusic(Music music) {
        boolean haveSong=false;
        if(isOn) {
            if (numberOfSongs>0) {
                for(Music song : songs) {
                    if (song != null && song.equals(music)) {
                        haveSong = true;
                        break;
                    }
                }
            }
            if(!haveSong){
                songs.add(music);
                numberOfSongs++;
            }
        }
    }

    public int getNumberOfSongs(){
        return songs.size();
    }

    public void delete(Music music) throws Mp3Exception {
        if(isOn){
            for (Music song : songs) {
                if (song.equals(music)) {
                    songs.remove(song);
                    numberOfSongs--;
                    return;
                }
            }
            throw new Mp3Exception("Sorry, can't find this song :(");
        }
    }
}