package gui.controlpanel;

import utils.ActionChangeListener;

import javax.swing.*;

abstract public class ControlPanel<T> extends JPanel {

    abstract public T prepareSimulator();

    abstract public void setListener(final ActionChangeListener eventListener);

}
