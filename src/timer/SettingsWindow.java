package timer;

import javax.swing.*;
import java.awt.*;

class SettingsWindow extends JFrame {
    //Sound choice
    private String soundPath;
    //Color choice
    private String colorCode;
    JRadioButton defaultColorRB;
    JRadioButton whiteColorRB;
    JRadioButton lightGrayColorRB;
    JRadioButton darkGrayColorRB;
    JRadioButton yellowColorRB;
    JRadioButton pinkColorRB;
    JRadioButton cyanColorRB;
    JButton saveAndCloseButton;
    JRadioButton annoyingAlarmClockRB;
    JRadioButton buzzRB;
    JRadioButton copCarRB;
    JRadioButton houseFireAlarmRB;
    JRadioButton oldFashionedSchoolBellRB;
    JRadioButton roosterRB;
    JRadioButton schoolFireAlarmRB;
    JRadioButton submarineAlarmRB;
    JRadioButton weirdSirenRB;

    SettingsWindow() {
        super("Settings");
        setLayout(new GridLayout(23, 1, 5, 5));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(200,700);
        setResizable(false);

        //Color settings
        JLabel colorHeading = new JLabel("Theme");
        add(colorHeading);

        //Color radio buttons
        defaultColorRB =  new JRadioButton("Default", true);
        add(defaultColorRB);
        whiteColorRB = new JRadioButton("White");
        add(whiteColorRB);
        lightGrayColorRB = new JRadioButton("Light gray");
        add(lightGrayColorRB);
        darkGrayColorRB = new JRadioButton("Gray");
        add(darkGrayColorRB);
        yellowColorRB = new JRadioButton("Yellow");
        add(yellowColorRB);
        pinkColorRB = new JRadioButton("Pink");
        add(pinkColorRB);
        cyanColorRB = new JRadioButton("Cyan");
        add(cyanColorRB);

        //Button group for the color radio buttons
        ButtonGroup colorBtnGroup = new ButtonGroup();
        colorBtnGroup.add(defaultColorRB);
        colorBtnGroup.add(whiteColorRB);
        colorBtnGroup.add(lightGrayColorRB);
        colorBtnGroup.add(darkGrayColorRB);
        colorBtnGroup.add(yellowColorRB);
        colorBtnGroup.add(pinkColorRB);
        colorBtnGroup.add(cyanColorRB);

        //Alarm settings
        JLabel alarmHeading = new JLabel("Alarm");
        add(alarmHeading);

        //Alarm radio buttons
        annoyingAlarmClockRB = new JRadioButton("Annoying Alarm Clock", true);
        add(annoyingAlarmClockRB);
        buzzRB = new JRadioButton("Buzz");
        add(buzzRB);
        copCarRB = new JRadioButton("Cop car");
        add(copCarRB);
        houseFireAlarmRB = new JRadioButton("House fire alarm");
        add(houseFireAlarmRB);
        oldFashionedSchoolBellRB = new JRadioButton("Old fashioned school bell");
        add(oldFashionedSchoolBellRB);
        schoolFireAlarmRB = new JRadioButton("School fire alarm");
        add(schoolFireAlarmRB);
        roosterRB = new JRadioButton("Rooster crow");
        add(roosterRB);
        submarineAlarmRB = new JRadioButton("Submarine alarm");
        add(submarineAlarmRB);
        weirdSirenRB = new JRadioButton("Weird siren");
        add(weirdSirenRB);

        //Button group for the alarm radio buttons
        ButtonGroup alarmBtnGroup = new ButtonGroup();
        alarmBtnGroup.add(annoyingAlarmClockRB);
        alarmBtnGroup.add(buzzRB);
        alarmBtnGroup.add(copCarRB);
        alarmBtnGroup.add(houseFireAlarmRB);
        alarmBtnGroup.add(oldFashionedSchoolBellRB);
        alarmBtnGroup.add(schoolFireAlarmRB);
        alarmBtnGroup.add(roosterRB);
        alarmBtnGroup.add(submarineAlarmRB);
        alarmBtnGroup.add(weirdSirenRB);



        //Save & Cancel buttons
        saveAndCloseButton = new JButton("Save and close");
        add(saveAndCloseButton);
    }

    //Setter and Getter for 'soundPath'
    void setSoundPath(String soundLocation) {
        soundPath = soundLocation;
    }
    String getSoundPath() {
        return soundPath;
    }

    //Setter and Getter for 'colorCode'
    void setColor(String color) {
        colorCode = color;
    }
    String getColorCode() {
        return colorCode;
    }
}
