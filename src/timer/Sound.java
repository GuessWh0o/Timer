package timer;
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    private static AudioClip clip = new AudioClip() {
        @Override
        public void play() {

        }

        @Override
        public void loop() {

        }

        @Override
        public void stop() {

        }
    };

    Sound(String filename) {
        try {
            clip = Applet.newAudioClip(Sound.class.getResource(filename));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    void loop() {
        try {
            new Thread() {
                public void run() {
                    clip.loop();
                }
            }.start();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    static void stop() {
        try {
            new Thread() {
                public void run() {
                    clip.stop();
                }
            }.start();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
