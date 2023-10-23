package komputator.desktop.ui.utility;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

public class FontUtility {

    public static void setSize(JComponent component, Size size) {
        component.setFont(component.getFont().deriveFont((float) size.getValue()));
    }

    @RequiredArgsConstructor
    @Getter
    public enum Size {
        LARGE(40),
        MEDIUM(25),
        SMALL(16),
        ;
        private final int value;
    }
}
