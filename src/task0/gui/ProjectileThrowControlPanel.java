package task0.gui;

import gui.controlpanel.ButtonsComponent;
import gui.controlpanel.ControlPanel;
import gui.controlpanel.ControlPanelSliderComponent;
import gui.drawboard.SimulationComponent;
import task0.simulator.ProjectileThrow;

import java.awt.*;
import java.awt.event.ActionListener;

public class ProjectileThrowControlPanel extends ControlPanel<ProjectileThrow> {

    private final ControlPanelSliderComponent initialSpeedSliderComponent = new ControlPanelSliderComponent("PRĘDKOŚĆ POCZĄTKOWA", 30, 50, 10, 10, 5);
    private final ControlPanelSliderComponent initialAngleSliderComponent = new ControlPanelSliderComponent("KĄT POCZĄTKOWY", 45, 80, 10, 10, 5);
    private final ControlPanelSliderComponent frictionSliderComponent = new ControlPanelSliderComponent("TARCIE", 0, 90, 0, 10, 5);
    private final ButtonsComponent buttonsComponent;

    public ProjectileThrowControlPanel(ActionListener actionListener) {
        buttonsComponent = new ButtonsComponent(actionListener);
        buttonsComponent.setLayout(new GridLayout(2, 1));
        addComponents();
    }

    @Override
    public SimulationComponent<ProjectileThrow> prepareSimulationComponent() {
        final int initialSpeed = initialSpeedSliderComponent.getSliderValue() * 10;
        final int initialAngle = initialAngleSliderComponent.getSliderValue();
        final double friction = (100 - frictionSliderComponent.getSliderValue()) / 100d;
        final ProjectileThrow projectileThrow = new ProjectileThrow(initialSpeed, initialAngle, friction);
        return new SimulationComponent<>(projectileThrow);
    }

    @Override
    protected void addComponents() {
        add(initialSpeedSliderComponent);
        add(initialAngleSliderComponent);
        add(frictionSliderComponent);
        add(buttonsComponent);
    }
}
