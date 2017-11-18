package timer;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import static timer.Sound.stop;


class MainWindow extends JFrame implements MenuListener {
    private TimerFunctionality timerFunc = new TimerFunctionality();
    private SettingsWindow settingsFrame = new SettingsWindow();
    private JMenu settings;
    private JMenu about;
    private JMenu exit;
    private Preferences userPreferences = Preferences.userNodeForPackage(getClass());
    private final String colorPrefKey = "COLOR_CODE";
    private final String soundPrefKey = "SOUND_PATH";


    MainWindow() {
        super("Timer");
        setLayout(new GridLayout(4,3,5,5));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(650,290);
        setResizable(false);

        //Set theme
        String colorCode = userPreferences.get(colorPrefKey, "#ededed"); //Get the saved color code and assign it to a new variable
        switch (colorCode) {
            case "#ededed":
                settingsFrame.defaultColorRB.setSelected(true); //Keep the button selected for next time
                getContentPane().setBackground(Color.decode("#ededed")); //Set the color for next time
                break;
            case "#FFFFFF":
                settingsFrame.whiteColorRB.setSelected(true); //Keep the button selected for next time
                getContentPane().setBackground(Color.decode("#FFFFFF")); //Set the color for next time
                break;
            case "#D2D8DF":
                settingsFrame.lightGrayColorRB.setSelected(true); //Keep the button selected for next time
                getContentPane().setBackground(Color.decode("#D2D8DF")); //Set the color for next time
                break;
            case "#A2A4A6":
                settingsFrame.darkGrayColorRB.setSelected(true); //Keep the button selected for next time
                getContentPane().setBackground(Color.decode("#A2A4A6")); //Set the color for next time
                break;
            case "#FBFF00":
                settingsFrame.yellowColorRB.setSelected(true); //Keep the button selected for next time
                getContentPane().setBackground(Color.decode("#D2D8DF")); //Set the color for next time
                break;
            case "#F58EB3":
                settingsFrame.pinkColorRB.setSelected(true); //Keep the button selected for next time
                getContentPane().setBackground(Color.decode("#F58EB3")); //Set the color for next time
                break;
            case "#32D0F7":
                settingsFrame.cyanColorRB.setSelected(true); //Keep the button selected for next time
                getContentPane().setBackground(Color.decode("#32D0F7")); //Set the color for next time
                break;
        }

        //Set sound
        String soundPath = userPreferences.get(soundPrefKey, "/sound/annoying-alarm-clock.wav");
        switch(soundPath) {
            case "/sound/annoying-alarm-clock.wav":
                settingsFrame.annoyingAlarmClockRB.setSelected(true);
                settingsFrame.setSoundPath("/sound/annoying-alarm-clock.wav");
                break;
            case "/sound/buzz.wav":
                settingsFrame.buzzRB.setSelected(true);
                settingsFrame.setSoundPath("/sound/buzz.wav");
                break;
            case "/sound/cop-car.wav":
                settingsFrame.copCarRB.setSelected(true);
                settingsFrame.setSoundPath("/sound/cop-car.wav");
                break;
            case "/sound/house-fire-alarm.wav":
                settingsFrame.houseFireAlarmRB.setSelected(true);
                settingsFrame.setSoundPath("/sound/house-fire-alarm.wav");
                break;
            case "/sound/old-fashioned-school-bell.wav":
                settingsFrame.oldFashionedSchoolBellRB.setSelected(true);
                settingsFrame.setSoundPath("/sound/old-fashioned-school-bell.wav");
                break;
            case "/sound/rooster.wav":
                settingsFrame.roosterRB.setSelected(true);
                settingsFrame.setSoundPath("/sound/rooster.wav");
                break;
            case "/sound/school-fire-alarm.wav":
                settingsFrame.schoolFireAlarmRB.setSelected(true);
                settingsFrame.setSoundPath("/sound/school-fire-alarm.wav");
                break;
            case "/sound/submarine-alarm.wav":
                settingsFrame.submarineAlarmRB.setSelected(true);
                settingsFrame.setSoundPath("/sound/submarine-alarm.wav");
                break;
            case "/sound/weird-siren.wav":
                settingsFrame.weirdSirenRB.setSelected(true);
                settingsFrame.setSoundPath("/sound/weird-siren.wav");
                break;
        }

        JLabel hrsLabel = new JLabel("Hours", SwingConstants.CENTER);
        add(hrsLabel);

        JLabel minsLabel = new JLabel("Minutes", SwingConstants.CENTER);
        add(minsLabel);

        JLabel secsLabel = new JLabel("Seconds", SwingConstants.CENTER);
        add(secsLabel);

        timerFunc.userInputHrs = new JTextField("00", SwingConstants.CENTER);
        timerFunc.userInputHrs.setHorizontalAlignment(SwingConstants.CENTER);
        timerFunc.userInputHrs.setFont(new Font("Arial", Font.PLAIN, 30));
        add(timerFunc.userInputHrs);

        timerFunc.userInputMins = new JTextField("00", SwingConstants.CENTER);
        timerFunc.userInputMins.setHorizontalAlignment(SwingConstants.CENTER);
        timerFunc.userInputMins.setFont(new Font("Arial", Font.PLAIN, 30));
        add(timerFunc.userInputMins);

        timerFunc.userInputSecs = new JTextField("00", SwingConstants.CENTER);
        timerFunc.userInputSecs.setHorizontalAlignment(SwingConstants.CENTER);
        timerFunc.userInputSecs.setFont(new Font("Arial", Font.PLAIN, 30));
        add(timerFunc.userInputSecs);

        timerFunc.outputHrs = new JLabel("...", SwingConstants.CENTER);
        timerFunc.outputHrs.setFont(new Font("Arial", Font.BOLD, 40));
        timerFunc.outputHrs.setForeground(Color.decode("#25921A"));
        add(timerFunc.outputHrs);

        timerFunc.outputMins = new JLabel("...", SwingConstants.CENTER);
        timerFunc.outputMins.setFont(new Font("Arial", Font.BOLD, 40));
        timerFunc.outputMins.setForeground(Color.decode("#25921A"));
        add(timerFunc.outputMins);

        timerFunc.outputSecs = new JLabel("...", SwingConstants.CENTER);
        timerFunc.outputSecs.setFont(new Font("Arial", Font.BOLD, 40));
        timerFunc.outputSecs.setForeground(Color.decode("#25921A"));
        add(timerFunc.outputSecs);

        timerFunc.startIcon = new ImageIcon(getClass().getResource("/images/start-button.png"));
        timerFunc.startIcon.setDescription("Start");
        timerFunc.startButton = new JButton(timerFunc.startIcon);
        add(timerFunc.startButton);

        timerFunc.pauseIcon = new ImageIcon(getClass().getResource("/images/pause-button.png"));
        timerFunc.pauseIcon.setDescription("Pause");

        timerFunc.resumeIcon = new ImageIcon(getClass().getResource("/images/start-button.png"));
        timerFunc.resumeIcon.setDescription("Resume");

        ImageIcon resetIcon = new ImageIcon(getClass().getResource("/images/reset-button.png"));
        resetIcon.setDescription("Reset");
        timerFunc.resetButton = new JButton(resetIcon);
        add(timerFunc.resetButton);

        //Adding action listeners to the color radio buttons
        settingsFrame.defaultColorRB.addActionListener(ee -> {
            settingsFrame.setColor("#ededed");
            getContentPane().setBackground(Color.decode(settingsFrame.getColorCode()));
            userPreferences.put(colorPrefKey, settingsFrame.getColorCode()); //Save the color code
        });

        settingsFrame.whiteColorRB.addActionListener(ee -> {
            settingsFrame.setColor("#FFFFFF");
            getContentPane().setBackground(Color.decode(settingsFrame.getColorCode()));
            userPreferences.put(colorPrefKey, settingsFrame.getColorCode()); //Save the color code
        });

        settingsFrame.lightGrayColorRB.addActionListener(ee -> {
            settingsFrame.setColor("#D2D8DF");
            getContentPane().setBackground(Color.decode(settingsFrame.getColorCode()));
            userPreferences.put(colorPrefKey, settingsFrame.getColorCode()); //Save the color code
        });

        settingsFrame.darkGrayColorRB.addActionListener(ee -> {
            settingsFrame.setColor("#A2A4A6");
            getContentPane().setBackground(Color.decode(settingsFrame.getColorCode()));
            userPreferences.put(colorPrefKey, settingsFrame.getColorCode()); //Save the color code
        });

        settingsFrame.yellowColorRB.addActionListener(ee -> {
            settingsFrame.setColor("#FBFF00");
            getContentPane().setBackground(Color.decode(settingsFrame.getColorCode()));
            userPreferences.put("COLOR_CODE", settingsFrame.getColorCode()); //Save the color code
        });

        settingsFrame.pinkColorRB.addActionListener(ee -> {
            settingsFrame.setColor("#F58EB3");
            getContentPane().setBackground(Color.decode(settingsFrame.getColorCode()));
            userPreferences.put(colorPrefKey, settingsFrame.getColorCode()); //Save the color code
        });

        settingsFrame.cyanColorRB.addActionListener(ee -> {
            settingsFrame.setColor("#32D0F7");
            getContentPane().setBackground(Color.decode(settingsFrame.getColorCode()));
            userPreferences.put(colorPrefKey, settingsFrame.getColorCode()); //Save the color code
        });

        //Adding action listeners to the sound radio buttons in settings
        if(settingsFrame.annoyingAlarmClockRB.isSelected()) {
            stop();
            settingsFrame.setSoundPath("/sound/annoying-alarm-clock.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
        }

        settingsFrame.annoyingAlarmClockRB.addActionListener(e -> {
            stop();
            settingsFrame.setSoundPath("/sound/annoying-alarm-clock.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
            sound.play();
            userPreferences.put(soundPrefKey, settingsFrame.getSoundPath()); //Save the sound file path
        });

        if(settingsFrame.buzzRB.isSelected()) {
            stop();
            settingsFrame.setSoundPath("/sound/buzz.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
        }

        settingsFrame.buzzRB.addActionListener(ee -> {
            stop();
            settingsFrame.setSoundPath("/sound/buzz.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
            sound.play();
            userPreferences.put(soundPrefKey, settingsFrame.getSoundPath()); //Save the sound file path
        });

        if(settingsFrame.copCarRB.isSelected()) {
            stop();
            settingsFrame.setSoundPath("/sound/cop-car.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
        }

        settingsFrame.copCarRB.addActionListener(ee -> {
            stop();
            settingsFrame.setSoundPath("/sound/cop-car.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
            sound.play();
            userPreferences.put(soundPrefKey, settingsFrame.getSoundPath()); //Save the sound file path
        });

        if(settingsFrame.houseFireAlarmRB.isSelected()) {
            stop();
            settingsFrame.setSoundPath("/sound/house-fire-alarm.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
        }

        settingsFrame.houseFireAlarmRB.addActionListener(ee -> {
            stop();
            settingsFrame.setSoundPath("/sound/house-fire-alarm.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
            sound.play();
            userPreferences.put(soundPrefKey, settingsFrame.getSoundPath()); //Save the sound file path
        });

        if(settingsFrame.oldFashionedSchoolBellRB.isSelected()) {
            stop();
            settingsFrame.setSoundPath("/sound/old-fashioned-school-bell.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
        }

        settingsFrame.oldFashionedSchoolBellRB.addActionListener(ee -> {
            stop();
            settingsFrame.setSoundPath("/sound/old-fashioned-school-bell.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
            sound.play();
            userPreferences.put(soundPrefKey, settingsFrame.getSoundPath()); //Save the sound file path
        });

        if(settingsFrame.roosterRB.isSelected()) {
            stop();
            settingsFrame.setSoundPath("/sound/rooster.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
        }

        settingsFrame.roosterRB.addActionListener(ee -> {
            stop();
            settingsFrame.setSoundPath("/sound/rooster.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
            sound.play();
            userPreferences.put(soundPrefKey, settingsFrame.getSoundPath()); //Save the sound file path
        });

        if(settingsFrame.schoolFireAlarmRB.isSelected()) {
            stop();
            settingsFrame.setSoundPath("/sound/school-fire-alarm.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
        }

        settingsFrame.schoolFireAlarmRB.addActionListener(ee -> {
            stop();
            settingsFrame.setSoundPath("/sound/school-fire-alarm.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
            sound.play();
            userPreferences.put(soundPrefKey, settingsFrame.getSoundPath()); //Save the sound file path
        });

        if(settingsFrame.submarineAlarmRB.isSelected()) {
            stop();
            settingsFrame.setSoundPath("/sound/submarine-alarm.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
        }

        settingsFrame.submarineAlarmRB.addActionListener(ee -> {
            stop();
            settingsFrame.setSoundPath("/sound/submarine-alarm.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
            sound.play();
            userPreferences.put(soundPrefKey, settingsFrame.getSoundPath()); //Save the sound file path
        });

        if(settingsFrame.weirdSirenRB.isSelected()) {
            stop();
            settingsFrame.setSoundPath("/sound/weird-siren.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
        }

        settingsFrame.weirdSirenRB.addActionListener(ee -> {
            stop();
            settingsFrame.setSoundPath("/sound/weird-siren.wav");
            Sound sound = new Sound(settingsFrame.getSoundPath());
            sound.play();
            userPreferences.put(soundPrefKey, settingsFrame.getSoundPath()); //Save the sound file path
        });

        //Functionality for the START / PAUSE / RESET button
        timerFunc.startButton.addActionListener(e -> {
            switch (timerFunc.startButton.getIcon().toString()) {
                case "Pause":
                    timerFunc.pause();
                    System.out.println(timerFunc.startButton.getIcon().toString());
                    break;
                case "Resume":
                    timerFunc.resume();
                    System.out.println(timerFunc.startButton.getIcon().toString());
                    break;
                default:
                    System.out.println(timerFunc.startButton.getIcon().toString());
                    try {
                        //Calculate seconds from user input
                        timerFunc.hrsChosen = Integer.parseInt(timerFunc.userInputHrs.getText());
                        timerFunc.minsChosen = Integer.parseInt(timerFunc.userInputMins.getText());
                        timerFunc.secsChosen = Integer.parseInt(timerFunc.userInputSecs.getText());
                        timerFunc.secsRemaining = timerFunc.hrsChosen * 3600 + timerFunc.minsChosen * 60 + timerFunc.secsChosen;
                        if (timerFunc.hrsChosen < 0 || timerFunc.minsChosen < 0 || timerFunc.secsChosen < 0)
                            throw new NegativeException();
                        if (timerFunc.hrsChosen > 24)
                            throw new OverTwentyFourException();
                        //Getter for two thirds of userInput for color change
                        timerFunc.twoThirdsInput = 66.66 * timerFunc.secsRemaining / 100;
                        //Getter for one third of userInput for color change
                        timerFunc.oneThirdInput = 33.33 * timerFunc.secsRemaining / 100;
                        timerFunc.start();
                    } catch (NegativeException ee) {
                        JOptionPane.showMessageDialog(
                                MainWindow.this,
                                "INPUT ERROR: Please insert numbers higher than 0",
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE
                        );
                        timerFunc.userInputHrs.setText("00");
                        timerFunc.userInputMins.setText("00");
                        timerFunc.userInputSecs.setText("00");
                    } catch (OverTwentyFourException ee) {
                        JOptionPane.showMessageDialog(
                                MainWindow.this,
                                "INPUT ERROR: The 'Hours' number needs to be lower than 24",
                                "Invalid Input - Hours",
                                JOptionPane.ERROR_MESSAGE
                        );
                        timerFunc.userInputHrs.setText("00");
                    } catch (NumberFormatException ee) {
                        timerFunc.userInputHrs.setText("00");
                        timerFunc.userInputMins.setText("00");
                        timerFunc.userInputSecs.setText("00");
                        JOptionPane.showMessageDialog(
                                MainWindow.this, "INPUT ERROR: Please use digits",
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
            }
        });

        //Functionality for the RESET button
        timerFunc.resetButton.addActionListener(e -> {
            timerFunc.reset();
        });

        //Menu
        JMenuBar menuBar = new JMenuBar();
        add(menuBar);

        settings = new JMenu("Settings");
        settings.addMenuListener(this);
        menuBar.add(settings);

        about = new JMenu("About");
        about.addMenuListener(this);
        menuBar.add(about);

        exit = new JMenu("Exit");
        exit.addMenuListener(this);
        menuBar.add(exit);
    }

    @Override
    public void menuSelected(MenuEvent e) {
        //Open settings window
        if(e.getSource().equals(settings)) {
            about.setEnabled(false);
            exit.setEnabled(false);
            settingsFrame.setAlwaysOnTop(true);
            settingsFrame.setLocationRelativeTo(null);
            settingsFrame.setVisible(true);

            //WindowListener for closing the settings window
            settingsFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    stop();
                    settings.setEnabled(true);
                    about.setEnabled(true);
                    exit.setEnabled(true);
                }
            });
        }

        //Open 'About' window
        if(e.getSource().equals(about)) {
            settings.setEnabled(false);
            exit.setEnabled(false);
            About aboutFrame = new About();
            aboutFrame.setAlwaysOnTop(true);
            aboutFrame.setLocationRelativeTo(null);
            aboutFrame.setVisible(true);

            //WindowListener for closing the about window
            aboutFrame.addWindowListener(new WindowAdapter() {
               @Override
                public void windowClosing(WindowEvent we) {
                   settings.setEnabled(true);
                   about.setEnabled(true);
                   exit.setEnabled(true);
               }
            });
        }

        //Exit program
        if(e.getSource().equals(exit)) {
            System.exit(0);
        }

        //Action listener for the saveAndCloseButton
        settingsFrame.saveAndCloseButton.addActionListener(ee -> {
            settingsFrame.dispose();
            settings.setEnabled(true);
            about.setEnabled(true);
            exit.setEnabled(true);
            stop();
        });
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        //Disable buttons
        settings.setEnabled(false);
        about.setEnabled(false);
        exit.setEnabled(false);
    }

    @Override
    public void menuCanceled(MenuEvent e) {
    }
}