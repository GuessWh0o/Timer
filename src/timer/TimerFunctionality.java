package timer;

import timer.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;

import static timer.Sound.stop;

public class TimerFunctionality {
    private Timer timer = new Timer();
    int secsRemaining;
    JTextField userInputHrs;
    JTextField userInputMins;
    JTextField userInputSecs;
    double twoThirdsInput;
    double oneThirdInput;
    public JButton startButton;
    public JButton resetButton;
    public int hrsChosen;
    int minsChosen;
    int secsChosen;
    JLabel outputHrs;
    JLabel outputMins;
    JLabel outputSecs;
    public ImageIcon startIcon;
    public ImageIcon pauseIcon;
    public ImageIcon resumeIcon;

    //Start timer
    void start() {
        TimerTask secondsStart = new TimerTask() {
            @Override
            public void run() {
                startButton.setIcon(pauseIcon);
                hrsChosen = (secsRemaining / (60 * 60)) % 24;
                minsChosen = (secsRemaining / 60) % 60;
                secsChosen = secsRemaining % 60;
                outputHrs.setText(Integer.toString(hrsChosen));
                outputHrs.setForeground(Color.decode("#25921A")); //Set the color again for restarting the timer after finishing
                outputMins.setText(Integer.toString(minsChosen));
                outputMins.setForeground(Color.decode("#25921A")); //Set the color again for restarting the timer after finishing
                outputSecs.setText(Integer.toString(secsChosen));
                outputSecs.setForeground(Color.decode("#25921A")); //Set the color again for restarting the timer after finishing
                secsRemaining--;
                //Color change for 2/3 of seconds remaining
                if (secsRemaining < twoThirdsInput) {
                    outputHrs.setForeground(Color.ORANGE);
                    outputMins.setForeground(Color.ORANGE);
                    outputSecs.setForeground(Color.ORANGE);
                }
                //Color change for  1/3 of seconds remaining
                if (secsRemaining < oneThirdInput) {
                    outputHrs.setForeground(Color.RED);
                    outputMins.setForeground(Color.RED);
                    outputSecs.setForeground(Color.RED);
                }

                if (secsRemaining < 0) {
                    Preferences prefs = Preferences.userNodeForPackage(getClass());
                    boolean soundOn = prefs.getBoolean(Constants.SOUND_ON_OFF_KEY, false);
                    outputHrs.setText("=====");
                    outputMins.setText("DONE");
                    outputSecs.setText("=====");
                    startButton.setIcon(startIcon);
                    timer.cancel();
                    if (soundOn) {
                        Sound sound = new Sound(Constants.soundPath);
                        sound.loop();
                    }
                }
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(secondsStart, 0, 1000);
    }

    //Pause timer
    public void pause() {
        startButton.setIcon(resumeIcon);
        timer.cancel();
    }

    //Resume timer. Recreating the task as the previous secondsLeft has already been scheduled
    void resume() {
        TimerTask secondsLeft = new TimerTask() {
            @Override
            public void run() {
                startButton.setIcon(pauseIcon);
                hrsChosen = (secsRemaining / (60 * 60)) % 24;
                minsChosen = (secsRemaining / 60) % 60;
                secsChosen = secsRemaining % 60;
                outputHrs.setText(Integer.toString(hrsChosen));
                outputMins.setText(Integer.toString(minsChosen));
                outputSecs.setText(Integer.toString(secsChosen));
                secsRemaining--;
                if (secsRemaining < twoThirdsInput) {
                    outputHrs.setForeground(Color.ORANGE);
                    outputMins.setForeground(Color.ORANGE);
                    outputSecs.setForeground(Color.ORANGE);
                }
                //Color change for  1/3 of seconds remaining
                if (secsRemaining < oneThirdInput) {
                    outputHrs.setForeground(Color.RED);
                    outputMins.setForeground(Color.RED);
                    outputSecs.setForeground(Color.RED);
                }
                if (secsRemaining < 0) {
                    outputHrs.setText("=====");
                    outputMins.setText("DONE");
                    outputSecs.setText("=====");
                    timer.cancel();
                    startButton.setIcon(startIcon);
                    Sound sound = new Sound(Constants.soundPath);
                    sound.loop();
                }
            }
        };
        timer = new Timer();
        timer.schedule(secondsLeft, 0, 1000);
    }

    //Reset timer
    public void reset() {
        timer.cancel();
        outputHrs.setText("=====");
        outputHrs.setForeground(Color.RED);
        outputMins.setText("STOPPED");
        outputMins.setForeground(Color.RED);
        outputSecs.setText("=====");
        outputSecs.setForeground(Color.RED);
        userInputHrs.setText("00");
        userInputMins.setText("00");
        userInputSecs.setText("00");
        startButton.setIcon(startIcon);
        stop();
    }
}