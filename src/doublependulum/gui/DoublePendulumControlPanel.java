package doublependulum.gui;

import doublependulum.simulator.DoublePendulum;
import doublependulum.simulator.Pendulum;
import gui.controlpanel.ButtonsComponent;
import gui.controlpanel.ControlPanel;
import gui.controlpanel.SliderComponent;
import utils.ActionChangeListener;

import java.awt.*;

import static utils.Numbers.TWO_PI;

public class DoublePendulumControlPanel extends ControlPanel<DoublePendulum> {

    private final SliderComponent angle1Slider = new SliderComponent("Angle ϴ₁", 90, 360, 0, 90, 45);
    private final SliderComponent angle2Slider = new SliderComponent("Angle ϴ₂", 90, 360, 0, 90, 45);
    private final SliderComponent length1Slider = new SliderComponent("Length 1", 80, 140, 20, 20, 10);
    private final SliderComponent length2Slider = new SliderComponent("Length 2", 80, 140, 20, 20, 10);
    private final SliderComponent mass1Slider = new SliderComponent("Weight 1", 5, 10, 1, 1, 1);
    private final SliderComponent mass2Slider = new SliderComponent("Weight 2", 5, 10, 1, 1, 1);
    private final SliderComponent frictionSlider = new SliderComponent("Friction", 0, 10, 0, 1, 1);
    private final ButtonsComponent buttonsComponent = new ButtonsComponent();

    public DoublePendulumControlPanel() {
        setLayout(new GridLayout(2, 4));
        buttonsComponent.setLayout(new GridLayout(1, 2));
        addComponents();
    }

    @Override
    public DoublePendulum prepareSimulator() {
        final Pendulum pendulum1 = new Pendulum(mass1Slider.getSliderValue(), length1Slider.getSliderValue(),
                Math.toRadians(angle1Slider.getSliderValue()), frictionSlider.getSliderValue());
        final Pendulum pendulum2 = new Pendulum(mass2Slider.getSliderValue(), length2Slider.getSliderValue(),
                Math.toRadians(angle2Slider.getSliderValue()), frictionSlider.getSliderValue());
        return new DoublePendulum(pendulum1, pendulum2);
    }

    @Override
    public void setListener(final ActionChangeListener eventListener) {
        angle1Slider.setChangeListener(eventListener);
        angle2Slider.setChangeListener(eventListener);
        length1Slider.setChangeListener(eventListener);
        length2Slider.setChangeListener(eventListener);
        mass1Slider.setChangeListener(eventListener);
        mass2Slider.setChangeListener(eventListener);
        frictionSlider.setChangeListener(eventListener);
        buttonsComponent.setActionListener(eventListener);
    }

    public void updateAngleSliders(final DoublePendulum doublePendulum) {
        int angle1 = (int) Math.toDegrees(doublePendulum.getPendulum1().getAngle());
        int angle2 = (int) Math.toDegrees(doublePendulum.getPendulum2().getAngle());
        if (angle1 < 0) angle1 += Math.toDegrees(TWO_PI);
        if (angle2 < 0) angle2 += Math.toDegrees(TWO_PI);
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

    private void addComponents() {
        add(angle1Slider);
        add(length1Slider);
        add(mass1Slider);
        add(buttonsComponent);
        add(angle2Slider);
        add(length2Slider);
        add(mass2Slider);
        add(frictionSlider);
    }

}
