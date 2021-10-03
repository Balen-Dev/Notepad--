package com.notepadMinusMinus;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileMenu {
    GUI gui;
    String fileName;
    String filePath;

    public FileMenu(GUI gui) {
        this.gui = gui;
    }

    public void createNewFile() {
        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        filePath = null;
    }

    public void openFile() {
        FileDialog fd = new FileDialog(gui.window, "Choose File to Open", FileDialog.LOAD);
        fd.setDirectory("C:\\Users\\Latitude\\Desktop");
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            filePath = fd.getDirectory();

            gui.window.setTitle(fileName);
        }

        try {
            var reader = new BufferedReader(new FileReader(filePath + fileName)); // Same purpose as a scanner.
            gui.textArea.setText("");

            String line = null;

            while ( (line = reader.readLine()) != null) {
                gui.textArea.append(line + "\n");
            }

            reader.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(gui.window, e.getMessage(), "File Not Opened!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveFile() {
        if (fileName == null) {
            saveAs();
        } else {
            try {
                var writer = new FileWriter(filePath + fileName);
                writer.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                writer.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(gui.window, e.getMessage(), "File Not Saved!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void saveAs() {
        var fd = new FileDialog(gui.window, "Choose Path for Saving", FileDialog.SAVE);
        fd.setDirectory("C:\\Users\\Latitude\\Desktop");
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            filePath = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try {
            var writer = new FileWriter(filePath + fileName);
            writer.write(gui.textArea.getText());
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(gui.window, e.getMessage(), "File Not Saved!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exitApp() {
        System.exit(0);
    }
}
