package com.notepadMinusMinus;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class EditMenu {
    GUI gui;

    public EditMenu(GUI gui) {
        this.gui = gui;
    }

    public void undo() {
        gui.um.undo();
    }

    public void redo() {
        gui.um.redo();
    }

    class MyHighlighter extends DefaultHighlighter.DefaultHighlightPainter {
        public MyHighlighter(Color color) {
            super(color);
        }
    }

    DefaultHighlighter.DefaultHighlightPainter highlighter = new MyHighlighter(Color.GRAY);

    public void removeAllHighlights() {
        Highlighter removeHighlights = gui.textArea.getHighlighter();
        Highlighter.Highlight[] remove = removeHighlights.getHighlights();

        for (int i=0; i<remove.length; ++i) {
            if (remove[i].getPainter() instanceof MyHighlighter) {
                removeHighlights.removeHighlight(remove[i]);
            }
        }
    }

    public void highlightWord(String target) {
        try {
            Highlighter highlight = gui.textArea.getHighlighter();
            Document doc = gui.textArea.getDocument();
            var content = doc.getText(0, doc.getLength());

            var pos = 0;

            while ((pos = content.toUpperCase().indexOf(target.toUpperCase(), pos )) >= 0) {
                highlight.addHighlight(pos, pos+target.length(), highlighter);
                pos += target.length();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void find() {
        removeAllHighlights();

        var searchTerm = JOptionPane.showInputDialog(gui.window, "Which word are you searching for?").trim();

        if (!searchTerm.equals(null) && !searchTerm.equals("")) {
            if (gui.textArea.getText().contains(searchTerm)) {
                try {
                    Highlighter highlight = gui.textArea.getHighlighter();
                    Document doc = gui.textArea.getDocument();
                    var content = doc.getText(0, doc.getLength());

                    var pos = 0;

                    while ((pos = content.toUpperCase().indexOf(searchTerm.toUpperCase(), pos )) >= 0) {
                        highlight.addHighlight(pos, pos+searchTerm.length(), highlighter);
                        pos += searchTerm.length();
                    }

                } catch (Exception e) {
                    System.out.println("Failed");
                }
            } else {
                JOptionPane.showMessageDialog(gui.window, "\"" + searchTerm + "\" Does not exist in this document!", "Failed to Find", JOptionPane.WARNING_MESSAGE);
                find();
            }
        } else {
            JOptionPane.showMessageDialog(gui.window, "Can't leave search field blank!", "Error Occurred", JOptionPane.ERROR_MESSAGE);
            find();
        }
    }

    public void findAndReplace() {
        String replaceWith = null;
        String replaceTerm = null;
        replaceTerm = JOptionPane.showInputDialog(gui.window, "Which word do you want to replace?").trim();

        if (!replaceTerm.equals(null) && !replaceTerm.equals("")) {
            if (gui.textArea.getText().contains(replaceTerm)) {
                highlightWord(replaceTerm);
                replaceWith = JOptionPane.showInputDialog(gui.window, "Which word do you want to replace it with?");
                var textAreaContent = gui.textArea.getText();
                textAreaContent = textAreaContent.replaceAll(replaceTerm, replaceWith);
                gui.textArea.setText(textAreaContent);
            } else {
                JOptionPane.showMessageDialog(gui.window, "\"" + replaceTerm + "\" is not found!", "Warning Message", JOptionPane.WARNING_MESSAGE);
                findAndReplace();
            }
        } else {
            JOptionPane.showMessageDialog(gui.window, "Can't leave search field blank!", "Error Occurred", JOptionPane.ERROR_MESSAGE);
            findAndReplace();
        }
    }
}
