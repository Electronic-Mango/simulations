package task2.gui.controlpanel;

import gui.GUIEvents;
import gui.controlpanel.ButtonsComponent;
import task2.simulator.RuleSet;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOfLifeControlPanel extends JPanel {

    private static final String SIZE_BUTTON = "ROZMIAR";
    private static final String RANDOM_BUTTON = "LOSUJ";
    private final SlidersComponent sliders;
    private final ButtonsComponent buttons;

    public GameOfLifeControlPanel(final ActionListener actionListener, final ChangeListener changeListener) {
        sliders = new SlidersComponent(changeListener);
        buttons = new ButtonsComponent(actionListener);
        buttons.setLayout(new GridLayout(4, 1));
        buttons.configureButton(actionListener, new JButton(SIZE_BUTTON), GUIEvents.RESIZE_SIMULATION);
        buttons.configureButton(actionListener, new JButton(RANDOM_BUTTON), GUIEvents.RANDOM_STATE);
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
