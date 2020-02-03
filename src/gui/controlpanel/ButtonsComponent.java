package gui.controlpanel;

import gui.GUIEvents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ButtonsComponent extends JPanel {

    private final static String START_BUTTON = "START";
    private static final String STOP_BUTTON = "STOP";
    private final static String CLEAR_BUTTON = "CZYŚĆ";
    protected final JButton startStopButton = new JButton(START_BUTTON);

    public ButtonsComponent() {
        configureButton(startStopButton, GUIEvents.START_SIMULATION);
        configureButton(new JButton(CLEAR_BUTTON), GUIEvents.CLEAR_SIMULATIONS);
    }

    public void setActionListener(final ActionListener actionListener) {
        Arrays.stream(getComponents()).filter(JButton.class::isInstance).map(JButton.class::cast).forEach(button -> button.addActionListener(actionListener));
    }

    public void configureButton(final JButton button, final String startSimulation) {
        button.setActionCommand(startSimulation);
        button.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        add(button);
    }

    public void switchToStartButton() {
        startStopButton.setText(START_BUTTON);
        startStopButton.setActionCommand(GUIEvents.START_SIMULATION);
    }

    public void switchToStopButton() {
        startStopButton.setText(STOP_BUTTON);
        startStopButton.setActionCommand(GUIEvents.STOP_SIMULATION);
    }

}
