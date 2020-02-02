package task1.gui;

import gui.controlpanel.ButtonsComponent;
import gui.controlpanel.ControlPanel;
import gui.controlpanel.ControlPanelSliderComponent;
import gui.drawboard.SimulationComponent;
import task1.gui.drawboard.DoublePendulumComponent;
import task1.simulator.DoublePendulum;
import task1.simulator.Pendulum;

import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class DoublePendulumControlPanel extends ControlPanel<DoublePendulum> {

    private final ControlPanelSliderComponent angle1Slider;
    private final ControlPanelSliderComponent angle2Slider;
    private final ControlPanelSliderComponent length1Slider;
    private final ControlPanelSliderComponent length2Slider;
    private final ControlPanelSliderComponent mass1Slider;
    private final ControlPanelSliderComponent mass2Slider;
    private final ControlPanelSliderComponent frictionSlider;
    private final ButtonsComponent buttonsComponent;

    public DoublePendulumControlPanel(final ActionListener actionListener, final ChangeListener changeListener) {
        setLayout(new GridLayout(2, 4));
        angle1Slider = new ControlPanelSliderComponent("Kąt ϴ₁", 90, 360, 0, 90, 45, changeListener);
        angle2Slider = new ControlPanelSliderComponent("Kąt ϴ₂", 90, 360, 0, 90, 45, changeListener);
        length1Slider = new ControlPanelSliderComponent("Długość 1", 80, 140, 20, 20, 10, changeListener);
        length2Slider = new ControlPanelSliderComponent("Długość 2", 80, 140, 20, 20, 10, changeListener);
        mass1Slider = new ControlPanelSliderComponent("Waga 1", 50, 100, 10, 10, 5, changeListener);
        mass2Slider = new ControlPanelSliderComponent("Waga 2", 50, 100, 10, 10, 5, changeListener);
        frictionSlider = new ControlPanelSliderComponent("Tarcie", 0, 10, 0, 1, 1, changeListener);
        buttonsComponent = new ButtonsComponent(actionListener);
        buttonsComponent.setLayout(new GridLayout(1, 2));
        addComponents();
    }

    public DoublePendulum getNewPendulum() {
        Pendulum pendulum1 = new Pendulum(mass1Slider.getSliderValue(), length1Slider.getSliderValue(),
                Math.toRadians(angle1Slider.getSliderValue()), frictionSlider.getSliderValue());
        Pendulum pendulum2 = new Pendulum(mass2Slider.getSliderValue(), length2Slider.getSliderValue(),
                Math.toRadians(angle2Slider.getSliderValue()), frictionSlider.getSliderValue());
        return new DoublePendulum(pendulum1, pendulum2);
    }

    @Override
    public SimulationComponent<DoublePendulum> prepareSimulationComponent() {
        return new DoublePendulumComponent(getNewPendulum());
    }

    @Override
    protected void addComponents() {
        add(angle1Slider);
        add(length1Slider);
        add(mass1Slider);
        add(buttonsComponent);
        add(angle2Slider);
        add(length2Slider);
        add(mass2Slider);
        add(frictionSlider);
    }

    public void updateAngleSliders(DoublePendulum doublePendulum) {
        int angle1 = (int) Math.toDegrees(doublePendulum.getPendulum1().getAngle());
        int angle2 = (int) Math.toDegrees(doublePendulum.getPendulum2().getAngle());
        if (angle1 < 0) angle1 += 360;
        if (angle2 < 0) angle2 += 360;
        angle1Slider.setSliderValue(angle1);
        angle2Slider.setSliderValue(angle2);
    }

    public void startSimulation() {
        buttonsComponent.switchToStopButton();
        angle1Slider.sliderSetEnabled(false);
        angle2Slider.sliderSetEnabled(false);
    }

    public void stopSimulation() {
        buttonsComponent.switchToStartButton();
        angle1Slider.sliderSetEnabled(true);
        angle2Slider.sliderSetEnabled(true);
    }
}
