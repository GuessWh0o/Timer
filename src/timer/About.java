package timer;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    About() {
        super("About");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 110);
        setResizable(false);

        JLabel aboutInfo = new JLabel("<html><p>Timer</p><p>Developed by Daniel Stanciu, daniel_stanciu91@yahoo.com</p><p>Version 1.1 (May 20th, 2017)</p></html>");
        add(aboutInfo);
    }
}
