package gameoflife.gui.controlpanel;

import gui.controlpanel.SliderComponent;

public class AnimationSpeedSlider extends SliderComponent {

    public AnimationSpeedSlider() {
        super("Szybkość animacji", 50, 100, 0, 20, 5);
        setPaintLabels(false);
    }

    @Override
    public int getSliderValue() {
        final int unscaledValue = super.getSliderValue();
        return (int) -(Math.sqrt(unscaledValue) * 100) + 1000;
    }

}
