package gameoflife.gui.controlpanel;

import gameoflife.simulator.RuleSet;
import gui.GUIEvents;
import gui.controlpanel.ButtonsComponent;
import utils.ActionChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOfLifeControlPanel extends JPanel {

    private static final String SIZE_BUTTON = "SIZE";
    private static final String RANDOM_BUTTON = "RANDOM";
    private final ButtonsComponent buttons = new ButtonsComponent();
    private final SlidersComponent sliders;

    public GameOfLifeControlPanel(final ActionChangeListener listener) {
        sliders = new SlidersComponent(listener);
        configureButtonsComponent(listener);
        addComponents();
    }

    private void configureButtonsComponent(final ActionListener actionListener) {
        buttons.setLayout(new GridLayout(4, 1));
        buttons.configureButton(new JButton(SIZE_BUTTON), GUIEvents.RESIZE_SIMULATION);
        buttons.configureButton(new JButton(RANDOM_BUTTON), GUIEvents.RANDOM_STATE);
        buttons.setActionListener(actionListener);
    }

    private void addComponents() {
        add(sliders);
        add(buttons);
    }

    public int getAnimationSpeed() {
        return sliders.getAnimationSpeed();
    }

    public RuleSet getNewRuleSet() {
        return sliders.getNewRuleSet();
    }

    public void switchToStartButton() {
        buttons.switchToStartButton();
    }

    public void switchToStopButton() {
        buttons.switchToStopButton();
    }

}
