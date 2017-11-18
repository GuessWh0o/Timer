package timer;

import javax.swing.*;
import java.awt.*;

class Gui extends JFrame {
    int secsRemaining;
    JTextField userInputHrs;
    JTextField userInputMins;
    JTextField userInputSecs;
    double twoThirdsInput;
    double oneThirdInput;
    JButton startButton;
    JButton resetButton;
    int hrsChosen;
    int minsChosen;
    int secsChosen;
    JLabel outputHrs;
    JLabel outputMins;
    JLabel outputSecs;
    ImageIcon startIcon;
    ImageIcon pauseIcon;
    ImageIcon resumeIcon;
    private TimerFunctionality timerFunc = new TimerFunctionality();

    Gui() {
        super("Timer");
        setLayout(new GridLayout(4,3,5,5));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(650,290);
        setResizable(false);

        JLabel hrsLabel = new JLabel("Hours", SwingConstants.CENTER);
        add(hrsLabel);

        JLabel minsLabel = new JLabel("Minutes", SwingConstants.CENTER);
        add(minsLabel);

        JLabel secsLabel = new JLabel("Seconds", SwingConstants.CENTER);
        add(secsLabel);

        userInputHrs = new JTextField("00", SwingConstants.CENTER);
        userInputHrs.setHorizontalAlignment(SwingConstants.CENTER);
        userInputHrs.setFont(new Font("Arial", Font.PLAIN, 30));
        add(userInputHrs);

        userInputMins = new JTextField("00", SwingConstants.CENTER);
        userInputMins.setHorizontalAlignment(SwingConstants.CENTER);
        userInputMins.setFont(new Font("Arial", Font.PLAIN, 30));
        add(userInputMins);

        userInputSecs = new JTextField("00", SwingConstants.CENTER);
        userInputSecs.setHorizontalAlignment(SwingConstants.CENTER);
        userInputSecs.setFont(new Font("Arial", Font.PLAIN, 30));
        add(userInputSecs);

        outputHrs = new JLabel("...", SwingConstants.CENTER);
        outputHrs.setFont(new Font("Arial", Font.BOLD, 40));
        outputHrs.setForeground(Color.decode("#25921A"));
        add(outputHrs);

        outputMins = new JLabel("...", SwingConstants.CENTER);
        outputMins.setFont(new Font("Arial", Font.BOLD, 40));
        outputMins.setForeground(Color.decode("#25921A"));
        add(outputMins);

        outputSecs = new JLabel("...", SwingConstants.CENTER);
        outputSecs.setFont(new Font("Arial", Font.BOLD, 40));
        outputSecs.setForeground(Color.decode("#25921A"));
        add(outputSecs);

        startIcon = new ImageIcon(getClass().getResource("/images/start-button.png"));
        startIcon.setDescription("Start");
        startButton = new JButton(startIcon);
        add(startButton);

        pauseIcon = new ImageIcon(getClass().getResource("/images/pause-button.png"));
        pauseIcon.setDescription("Pause");

        resumeIcon = new ImageIcon(getClass().getResource("/images/start-button.png"));
        resumeIcon.setDescription("Resume");

        ImageIcon resetIcon = new ImageIcon(getClass().getResource("/images/reset-button.png"));
        resetIcon.setDescription("Reset");
        resetButton = new JButton(resetIcon);
        add(resetButton);
        //Functionality for the START / PAUSE / RESET button
        startButton.addActionListener(e -> {
            switch (startButton.getIcon().toString()) {
                case "Pause":
                    timerFunc.start();
                    System.out.println(startButton.getIcon().toString());
                    break;
                case "Resume":
                    timerFunc.resume();
                    System.out.println(startButton.getIcon().toString());
                    break;
                default:
                    System.out.println(startButton.getIcon().toString());
                    try {
                        //Calculate seconds from user input
                        hrsChosen = Integer.parseInt(userInputHrs.getText());
                        minsChosen = Integer.parseInt(userInputMins.getText());
                        secsChosen = Integer.parseInt(userInputSecs.getText());
                        secsRemaining = hrsChosen * 3600 + minsChosen * 60 + secsChosen;
                        if (hrsChosen < 0 || minsChosen < 0 || secsChosen < 0)
                            throw new NegativeException();
                        if (hrsChosen > 24)
                            throw new OverTwentyFourException();
                        //Getter for two thirds of userInput for color change
                        twoThirdsInput = 66.66 * secsRemaining / 100;
                        //Getter for one third of userInput for color change
                        oneThirdInput = 33.33 * secsRemaining / 100;
                        timerFunc.start();
                    } catch (NegativeException ee) {
                        JOptionPane.showMessageDialog(
                                Gui.this,
                                "INPUT ERROR: Please insert numbers higher than 0",
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE
                        );
                        userInputHrs.setText("00");
                        userInputMins.setText("00");
                        userInputSecs.setText("00");
                    } catch (OverTwentyFourException ee) {
                        JOptionPane.showMessageDialog(
                                Gui.this,
                                "INPUT ERROR: The 'Hours' number needs to be lower than 24",
                                "Invalid Input - Hours",
                                JOptionPane.ERROR_MESSAGE
                        );
                        userInputHrs.setText("00");
                    } catch (NumberFormatException ee) {
                        userInputHrs.setText("00");
                        userInputMins.setText("00");
                        userInputSecs.setText("00");
                        JOptionPane.showMessageDialog(
                                Gui.this, "INPUT ERROR: Please use digits",
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
            }
        });

        //Functionality for the RESET button
        resetButton.addActionListener(e -> {
            timerFunc.reset();
        });

    }
}