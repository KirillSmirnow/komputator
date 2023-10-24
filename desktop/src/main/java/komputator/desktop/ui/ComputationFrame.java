package komputator.desktop.ui;

import komputator.desktop.ui.utility.FontUtility;
import komputator.desktop.ui.utility.FontUtility.Size;
import komputator.desktop.ui.utility.IconUtility;
import komputator.desktop.ui.utility.Layouts;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

import static java.util.Arrays.stream;

@Slf4j
public class ComputationFrame extends JFrame {

    private Mode mode = Mode.BACKGROUND;
    private Supplier<Long> n = () -> 500_000_000L;

    @Getter
    private IntConsumer progress = percentage -> log.info("Progress: {}%", percentage);

    @Getter
    private Consumer<String> result = text -> log.info("Result: {}", text);

    @Getter
    private Consumer<Boolean> buttonEnabled = enabled -> log.info("Button enabled: {}", enabled);

    @Setter
    private BiConsumer<Mode, Long> computeAction = (mode, n) -> log.info("Compute action: mode={}, n={}", mode, n);

    public ComputationFrame() {
        add(
                createTitleLabel(),
                createModeSelector(),
                createParameterInput(),
                createProgressBar(),
                createResultLabel(),
                createComputeButton()
        );
        configureFrame();
    }

    private void configureFrame() {
        setTitle("Komputator");
        IconUtility.set(this, "/image/komputator.png");
        setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void add(JComponent... components) {
        stream(components).forEach(this::add);
    }

    private JComponent createTitleLabel() {
        var titleLabel = new JLabel("Hi! I'm Komputator", SwingConstants.CENTER);
        FontUtility.setSize(titleLabel, Size.LARGE);
        return titleLabel;
    }

    private JComponent createModeSelector() {
        var label = new JLabel("Mode:", SwingConstants.CENTER);
        FontUtility.setSize(label, Size.MEDIUM);
        var foregroundMode = new JRadioButton("Foreground", mode == Mode.FOREGROUND);
        FontUtility.setSize(foregroundMode, Size.SMALL);
        foregroundMode.addActionListener(e -> mode = Mode.FOREGROUND);
        var backgroundMode = new JRadioButton("Background", mode == Mode.BACKGROUND);
        FontUtility.setSize(backgroundMode, Size.SMALL);
        backgroundMode.addActionListener(e -> mode = Mode.BACKGROUND);
        var group = new ButtonGroup();
        group.add(foregroundMode);
        group.add(backgroundMode);
        return Layouts.horizontal(label, foregroundMode, backgroundMode);
    }

    private JComponent createParameterInput() {
        var label = new JLabel("n =", SwingConstants.CENTER);
        FontUtility.setSize(label, Size.MEDIUM);
        var field = new JTextField(Long.toString(n.get()));
        FontUtility.setSize(field, Size.SMALL);
        n = () -> Long.valueOf(field.getText());
        return Layouts.horizontal(label, field);
    }

    private JComponent createProgressBar() {
        var progressBar = new JProgressBar();
        FontUtility.setSize(progressBar, Size.SMALL);
        progressBar.setForeground(Color.magenta);
        progressBar.setStringPainted(true);
        progress = progressBar::setValue;
        return progressBar;
    }

    private JComponent createResultLabel() {
        var label = new JLabel();
        FontUtility.setSize(label, Size.SMALL);
        result = label::setText;
        return label;
    }

    private JComponent createComputeButton() {
        var button = new JButton("Compute!");
        FontUtility.setSize(button, Size.MEDIUM);
        button.setBackground(Color.orange);
        button.addActionListener(e -> computeAction.accept(mode, n.get()));
        buttonEnabled = button::setEnabled;
        return button;
    }
}
