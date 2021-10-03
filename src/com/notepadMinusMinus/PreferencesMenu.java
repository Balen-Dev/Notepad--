package com.notepadMinusMinus;

import javax.swing.*;
import java.awt.*;

public class PreferencesMenu {
    GUI gui;

    public PreferencesMenu(GUI gui) {
        this.gui = gui;
    }

    public void wordWrap() {
        if (gui.wordWrapOn == false) {
            gui.wordWrapOn = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.iWrap.setText("Word Wrap: on");
        } else if (gui.wordWrapOn == true) {
            gui.wordWrapOn = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.iWrap.setText("Word Wrap: off");
        }
    }

    public void changeColor() {
        gui.colorChooser = new JColorChooser();
        Color color = gui.colorChooser.showDialog(gui.window,"Choose a color",new Color(0x1f1f1f));
        gui.textArea.setForeground(color);
    }
}
