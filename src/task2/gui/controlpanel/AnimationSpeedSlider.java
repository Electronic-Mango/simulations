package task2.gui.controlpanel;

import gui.controlpanel.ControlPanelSliderComponent;

import javax.swing.event.ChangeListener;

public class AnimationSpeedSlider extends ControlPanelSliderComponent {

    public AnimationSpeedSlider(final ChangeListener changeListener) {
        super("Szybkość animacji", 50, 100, 0, 20, 5, changeListener);
        setPaintLabels(false);
    }

    @Override
    public int getSliderValue() {
        final int unscaledValue = super.getSliderValue();
        return (int) -(Math.sqrt(unscaledValue) * 100) + 1000;
    }
}
