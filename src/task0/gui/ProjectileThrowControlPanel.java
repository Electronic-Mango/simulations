package task0.gui;

import gui.controlpanel.ButtonsComponent;
import gui.controlpanel.ControlPanel;
import gui.controlpanel.SliderComponent;
import task0.simulator.ProjectileThrow;
import utils.ActionChangeListener;

import java.awt.*;

public class ProjectileThrowControlPanel extends ControlPanel<ProjectileThrow> {

    private final SliderComponent initialSpeedSliderComponent = new SliderComponent("PRĘDKOŚĆ POCZĄTKOWA", 30, 50, 10, 10, 5);
    private final SliderComponent initialAngleSliderComponent = new SliderComponent("KĄT POCZĄTKOWY", 45, 80, 10, 10, 5);
    private final SliderComponent frictionSliderComponent = new SliderComponent("TARCIE", 0, 90, 0, 10, 5);
    private final ButtonsComponent buttonsComponent = new ButtonsComponent();

    public ProjectileThrowControlPanel() {
        buttonsComponent.setLayout(new GridLayout(2, 1));
        addComponents();
    }

    @Override
    public ProjectileThrow prepareSimulator() {
        final int initialSpeed = initialSpeedSliderComponent.getSliderValue() * 10;
        final int initialAngle = initialAngleSliderComponent.getSliderValue();
        final double friction = (100 - frictionSliderComponent.getSliderValue()) / 100d;
        return new ProjectileThrow(initialSpeed, initialAngle, friction);
    }

    @Override
    public void setListener(final ActionChangeListener actionListener) {
        buttonsComponent.setActionListener(actionListener);
    }

    private void addComponents() {
        add(initialSpeedSliderComponent);
        add(initialAngleSliderComponent);
        add(frictionSliderComponent);
        add(buttonsComponent);
    }

}
