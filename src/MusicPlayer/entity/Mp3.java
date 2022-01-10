package MusicPlayer.entity;


public class Mp3 {

    private boolean isOn;

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

}