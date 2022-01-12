package MusicPlayer.entity;


import MusicPlayer.enums.SongState;
import MusicPlayer.exception.Mp3Exception;

import java.util.ArrayList;

public class Mp3 {

    private boolean isOn;
    private ArrayList<Music> songs = new ArrayList<>();
    private int numberOfSongs;
    private Music musicPlaying;
    private SongState songState;
    private boolean isPlaying;
    private int volume;
    private boolean isMute;
    private ArrayList<ArrayList<Music>> playlists = new ArrayList<>();


    public boolean isOn() {
        return isOn;
    }

    public void switchOn() {
        isOn = true;
        volume= 10;
    }

    public void switchOff() {
        isOn = false;
        musicPlaying = null;
    }

    public void powerButton() {
        if (isOn) {
            switchOff();
        } else switchOn();
    }

    public void downloadMusic(Music music) {
        boolean haveSong = false;
        if (isOn) {
            if (numberOfSongs > 0) {
                for (Music song : songs) {
                    if (song != null && song.equals(music)) {
                        haveSong = true;
                        break;
                    }
                }
            }
            if (!haveSong) {
                songs.add(music);
                numberOfSongs++;
            }
        }
    }

    public int getNumberOfSongs() {
        return songs.size();
    }

    public void delete(Music music) throws Mp3Exception {
        if (isOn) {
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

    public void play(Music songToPlay) {
        if (isOn) {
            for (Music song : songs) {
                if (song != null && song.equals(songToPlay)) {
                    musicPlaying = song;
                    isPlaying = true;
                    songState = SongState.PLAY;
                    return;
                }
            }
        }
    }

    public SongState getSongState() {
        return songState;
    }

    public Music getMusicPlaying() {
        return musicPlaying;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void playOrPause() {
        if (isOn) {
            if (songState.equals(SongState.PLAY)) {
                isPlaying = false;
                songState = SongState.PAUSE;
            } else if (songState.equals(SongState.PAUSE)) {
                isPlaying = true;
                songState = SongState.PLAY;
            }
        }
    }

    public void stop() {
        if (isOn) {
            if (songState == SongState.PLAY || songState == SongState.PAUSE) {
                isPlaying = false;
                songState = SongState.STOP;
                musicPlaying = null;
            }
        }
    }

    public int getVolume() {
        return volume;
    }

    public void increaseVolume() {
        if (isOn){
            if(isMute) {
                volume++;
                isMute=false;
            } else if (volume<100)
                volume++;
        }
    }

    public void decreaseVolume() {
        if (isOn){
            if(isMute) {
                volume--;
                isMute=false;
            } else if (volume > 0)
                volume--;
        }
    }

    public void muteOrUnmute() {
        if (isOn) {
            if (!isMute) {
                volume = 0;
                isMute = true;
            } else {
                isMute = false;
            }
        }
    }

    public boolean isMute() {
        return isMute;
    }

    public void createPlaylist(ArrayList<Music> newPlaylist) {
        if (isOn)
            playlists.add(newPlaylist);
    }

    public int getNumberOfPlaylists() {
        return playlists.size();
    }

    public void addToPlaylist(Music songToAdd, ArrayList<Music> playlistOfChoice) {
        if(isOn) {
            for (ArrayList playlist : playlists) {
                if (playlist.equals(playlistOfChoice)) {
                    playlistOfChoice.add(songToAdd);
                }
            }
        }
    }

    public int getNumberOfSongsInPlaylist(ArrayList playlistName) {
        int playlistNoOfSongs = 0;
        for (ArrayList<Music> playlist : playlists) {
            if (playlist.equals(playlistName))
                playlistNoOfSongs = playlist.size();
        }
        return playlistNoOfSongs;
    }

    public void removeFromPlaylist(Music songToRemove, ArrayList<Music> playlistOfChoice) {
        if(isOn) {
            for (ArrayList playlist : playlists) {
                if (playlist.equals(playlistOfChoice)) {
                    playlistOfChoice.remove(songToRemove);
                }
            }
        }
    }
}