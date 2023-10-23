package komputator.desktop.ui.utility;

import javax.swing.*;
import java.awt.*;

import static java.util.Arrays.stream;

public class Layouts {

    public static JComponent horizontal(JComponent... components) {
        var panel = new JPanel(new GridLayout(1, 0));
        stream(components).forEach(panel::add);
        return panel;
    }
}
