package task2.gui.controlpanel;

import gui.controlpanel.ControlPanelSliderComponent;
import task2.simulator.RuleSet;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SlidersComponent extends JPanel {
    private final ControlPanelSliderComponent animationSpeed;
    private final ControlPanelSliderComponent deathByLonelinessSlider;
    private final ControlPanelSliderComponent deathByCrowdingSlider;
    private final ControlPanelSliderComponent parentsRequiredSlider;

    public SlidersComponent(final ChangeListener changeListener) {
        setLayout(new GridLayout(2, 2));
        animationSpeed = new AnimationSpeedSlider(changeListener);
        deathByLonelinessSlider = new ControlPanelSliderComponent("Śmierć z samotności", 2, 8, 0, 1, 1, changeListener);
        deathByCrowdingSlider = new ControlPanelSliderComponent("Śmierć z przeludnienia", 3, 8, 0, 1, 1, changeListener);
        parentsRequiredSlider = new ControlPanelSliderComponent("Wymagani rodzice", 3, 8, 0, 1, 1, changeListener);
        add(animationSpeed);
        add(deathByLonelinessSlider);
        add(deathByCrowdingSlider);
        add(parentsRequiredSlider);
    }

    public int getAnimationSpeed() {
        return animationSpeed.getSliderValue();
    }

    public RuleSet getNewRuleSet() {
        final int deathByLoneliness = deathByLonelinessSlider.getSliderValue();
        final int deathByCrowding = deathByCrowdingSlider.getSliderValue();
        final int parentsRequired = parentsRequiredSlider.getSliderValue();
        return new RuleSet(deathByLoneliness, deathByCrowding, parentsRequired);
    }
}
