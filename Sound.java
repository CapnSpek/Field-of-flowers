import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
    Clip clip;
    URL soundURL[] = new URL[10];

    public Sound()
    {
        soundURL[0] = getClass().getResource("/Sound/Minecraft PickUp.wav");
        soundURL[1] = getClass().getResource("/Sound/Steezy Prime - Letting Go.wav");
        soundURL[2] = getClass().getResource("/Sound/Tobago Ocean Waves.wav");
        //soundURL[3] = getClass().getResource("/Sound/Birds.wav");
        //soundURL[4] = getClass().getResource("/Sound/fanfare.wav");
    }

    public void setFile(int i)
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e)
        {
        }
    }

    public void play()
    {
        clip.start();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop()
    {
        clip.stop();
    }
}