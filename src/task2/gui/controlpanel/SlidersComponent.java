package task2.gui.controlpanel;

import gui.controlpanel.SliderComponent;
import task2.simulator.RuleSet;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SlidersComponent extends JPanel {

    private final SliderComponent animationSpeed = new AnimationSpeedSlider();
    private final SliderComponent deathByLonelinessSlider = new SliderComponent("Śmierć z samotności", 2, 8, 0, 1, 1);
    private final SliderComponent deathByCrowdingSlider = new SliderComponent("Śmierć z przeludnienia", 3, 8, 0, 1, 1);
    private final SliderComponent parentsRequiredSlider = new SliderComponent("Wymagani rodzice", 3, 8, 0, 1, 1);

    public SlidersComponent(final ChangeListener changeListener) {
        setLayout(new GridLayout(2, 2));
        configureListeners(changeListener);
        addComponents();
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

    private void configureListeners(final ChangeListener changeListener) {
        animationSpeed.setChangeListener(changeListener);
        deathByLonelinessSlider.setChangeListener(changeListener);
        deathByCrowdingSlider.setChangeListener(changeListener);
        parentsRequiredSlider.setChangeListener(changeListener);
    }

    private void addComponents() {
        add(animationSpeed);
        add(deathByLonelinessSlider);
        add(deathByCrowdingSlider);
        add(parentsRequiredSlider);
    }

}
