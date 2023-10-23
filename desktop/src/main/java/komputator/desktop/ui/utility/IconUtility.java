package komputator.desktop.ui.utility;

import javax.swing.*;

public class IconUtility {

    public static void set(JFrame frame, String path) {
        var url = IconUtility.class.getResource(path);
        frame.setIconImage(new ImageIcon(url).getImage());
    }
}
