package komputator.desktop.ui;

import komputator.desktop.ui.utility.FontUtility;
import komputator.desktop.ui.utility.FontUtility.Size;

import javax.swing.*;

public class ErrorFrame extends JFrame {

    public ErrorFrame(String message) {
        configureFrame();
        add(createMessageArea(message));
    }

    private void configureFrame() {
        setTitle("Error!");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JComponent createMessageArea(String message) {
        var area = new JTextArea(message);
        FontUtility.setSize(area, Size.SMALL);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        return area;
    }
}
