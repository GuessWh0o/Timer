package timer;

import timer.exceptions.NegativeException;
import timer.exceptions.OverTwentyFourException;

import javax.swing.*;
import java.awt.*;


class MainWindow extends JFrame {
    private TimerFunctionality timerFunc = new TimerFunctionality();
    private boolean soundOn = true;

    MainWindow() {
        super("Timer");
        setLayout(new GridLayout(4, 3, 5, 5));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(650, 290);
        setResizable(false);

        //Set the color of background
        getContentPane().setBackground(Color.decode(Constants.colorCode));


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

        timerFunc.startIcon = new ImageIcon(getClass().getResource("/images/start_button_small.png"));
        timerFunc.startIcon.setDescription("Start");
        timerFunc.startButton = new JButton(timerFunc.startIcon);
        add(timerFunc.startButton);

        timerFunc.pauseIcon = new ImageIcon(getClass().getResource("/images/pause-button.png"));
        timerFunc.pauseIcon.setDescription("Pause");

        timerFunc.resumeIcon = new ImageIcon(getClass().getResource("/images/start_button_small.png"));
        timerFunc.resumeIcon.setDescription("Resume");

        ImageIcon resetIcon = new ImageIcon(getClass().getResource("/images/reset-button.png"));
        resetIcon.setDescription("Reset");
        timerFunc.resetButton = new JButton(resetIcon);
        add(timerFunc.resetButton);

        //Add SoundOffOnButton
        ImageIcon soundOnIcon = new ImageIcon(getClass().getResource("/images/sound_on.png"));
        soundOnIcon.setDescription("On/Off Sound");
        timerFunc.soundOffOnButton = new JButton(soundOnIcon);
        add(timerFunc.soundOffOnButton);

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


        timerFunc.soundOffOnButton.addActionListener(e -> {
            boolean onOf = timerFunc.soundOffOnButton.getIcon().equals(soundOnIcon);
            timerFunc.soundOnOff(onOf);
        });

    }
}