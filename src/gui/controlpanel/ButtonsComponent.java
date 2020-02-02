package gui.controlpanel;

import gui.GUIEvents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonsComponent extends JPanel {

    private final static String START_BUTTON = "START";
    private static final String STOP_BUTTON = "STOP";
    private final static String CLEAR_BUTTON = "CZYŚĆ";
    protected final JButton startStopButton = new JButton(START_BUTTON);

    public ButtonsComponent(final ActionListener actionListener) {
        configureButtons(actionListener);
    }

    protected void configureButtons(final ActionListener actionListener) {
        configureButton(actionListener, startStopButton, GUIEvents.START_SIMULATION);
        configureButton(actionListener, new JButton(CLEAR_BUTTON), GUIEvents.CLEAR_SIMULATIONS);
    }

    public void configureButton(ActionListener actionListener, JButton button, String startSimulation) {
        button.addActionListener(actionListener);
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
