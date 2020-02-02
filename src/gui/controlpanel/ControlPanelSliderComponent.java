package gui.controlpanel;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public class ControlPanelSliderComponent extends JPanel {

    private final JLabel label = new JLabel();
    private final JSlider slider = new JSlider();

    public ControlPanelSliderComponent(final String labelText, final int defaultValue,
                                       final int maximumValue, final int minimumValue,
                                       final int majorTickSpacing, final int minorTickSpacing,
                                       final ChangeListener changeListener) {
        this(labelText, defaultValue, maximumValue, minimumValue, majorTickSpacing, minorTickSpacing);
        slider.addChangeListener(changeListener);
    }

    public ControlPanelSliderComponent(final String labelText, final int defaultValue,
                                       final int maximumValue, final int minimumValue,
                                       final int majorTickSpacing, final int minorTickSpacing) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEtchedBorder());
        configureLabelComponent(labelText);
        configureSliderComponent(defaultValue, maximumValue, minimumValue, majorTickSpacing, minorTickSpacing);
    }

    private void configureLabelComponent(final String labelText) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setText(labelText);
        final JPanel panel = new JPanel();
        panel.add(label);
        add(panel);
    }

    private void configureSliderComponent(final int defaultValue, final int maximumValue,
                                          final int minimumValue, final int majorTickSpacing,
                                          final int minorTickSpacing) {
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setValue(defaultValue);
        slider.setMaximum(maximumValue);
        slider.setMinimum(minimumValue);
        slider.setMajorTickSpacing(majorTickSpacing);
        slider.setMinorTickSpacing(minorTickSpacing);
        final JPanel panel = new JPanel();
        panel.add(slider);
        add(panel);
    }

    public int getSliderValue() {
        return slider.getValue();
    }

    public void setSliderValue(final int value) {
        slider.setValue(value);
    }

    public void sliderSetEnabled(final boolean enabled) {
        slider.setEnabled(enabled);
    }

    public void setPaintLabels(final boolean paintLabels) {
        slider.setPaintLabels(paintLabels);
    }

    public void setChangeListener(final ChangeListener changeListener) {
        slider.addChangeListener(changeListener);
    }

}


