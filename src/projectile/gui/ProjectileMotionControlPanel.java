package projectile.gui;

import gui.controlpanel.ButtonsComponent;
import gui.controlpanel.ControlPanel;
import gui.controlpanel.SliderComponent;
import projectile.simulator.ProjectileMotion;
import utils.ActionChangeListener;

import java.awt.*;

public class ProjectileMotionControlPanel extends ControlPanel<ProjectileMotion> {

    private final SliderComponent initialSpeedSliderComponent = new SliderComponent("Initial speed", 30, 50, 10, 10, 5);
    private final SliderComponent initialAngleSliderComponent = new SliderComponent("Initial angle", 45, 80, 10, 10, 5);
    private final SliderComponent frictionSliderComponent = new SliderComponent("Friction", 0, 90, 0, 10, 5);
    private final ButtonsComponent buttonsComponent = new ButtonsComponent();

    public ProjectileMotionControlPanel() {
        buttonsComponent.setLayout(new GridLayout(2, 1));
        addComponents();
    }

    @Override
    public ProjectileMotion prepareSimulator() {
        final int initialSpeed = initialSpeedSliderComponent.getSliderValue() * 10;
        final int initialAngle = initialAngleSliderComponent.getSliderValue();
        final double friction = (100 - frictionSliderComponent.getSliderValue()) / 100d;
        return new ProjectileMotion(initialSpeed, initialAngle, friction);
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
