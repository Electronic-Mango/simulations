package utils;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public interface ActionChangeListener extends ActionListener, ChangeListener {
    // Interface combining both ActionListener and ChangeListener.
    // This way both ActionListener and ChangeListener can be passed as single argument
    // and assigned as both without the need for unsafe casting.
}
