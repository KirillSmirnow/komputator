package komputator.desktop.ui;

import komputator.desktop.ui.utility.FontUtility;
import komputator.desktop.ui.utility.FontUtility.Size;
import komputator.desktop.ui.utility.IconUtility;

import javax.swing.*;

public class ErrorFrame extends JFrame {

    public ErrorFrame(String message) {
        configureFrame();
        add(createMessageArea(message));
    }

    private void configureFrame() {
        setTitle("Error!");
        IconUtility.set(this, "/image/error.png");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setResizable(false);
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
