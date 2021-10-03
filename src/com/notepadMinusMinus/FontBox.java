package com.notepadMinusMinus;

import java.awt.*;

public class FontBox {
    GUI gui;

    public FontBox(GUI gui) {
        this.gui = gui;
    }

    public void changeFont() {
        gui.textArea.setFont(new Font((String) gui.fontBox.getSelectedItem(), Font.PLAIN, gui.textArea.getFont().getSize()));
    }
}
