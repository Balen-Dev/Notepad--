package com.notepadMinusMinus;

import com.formdev.flatlaf.intellijthemes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Themes implements MouseListener {

    ImageIcon themes_window_icon;
    JLabel label1, label2, label3, label4, label5, label6;
    JFrame themesWindow;
    GUI gui;

    Themes(GUI gui) {
        this.gui = gui;
    }

    public void viewThemesWindow() {
        createThemesWindow();
        viewAvailableThemes();
        themesWindow.setVisible(true);
    }

    public void createThemesWindow() {
        themes_window_icon = new ImageIcon("DarkMode\\icon-light.png");

        themesWindow = new JFrame("Choose Theme to Apply");
        themesWindow.setSize(500,480);
        themesWindow.setLocationRelativeTo(gui.window);
        themesWindow.setLayout(new GridLayout(2,3,9,1));
        themesWindow.setIconImage(themes_window_icon.getImage());
    }

    public void viewAvailableThemes() {
        ImageIcon icon = new ImageIcon("Look&Feels\\flatone.png");
        label1 = new JLabel(icon);
        label1.setText("FlatOne (Default)");
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.addMouseListener(this);
        themesWindow.add(label1);

        ImageIcon icon2 = new ImageIcon("Look&Feels\\carbon.png");
        label2 = new JLabel(icon2);
        label2.setText("Carbon");
        label2.setVerticalTextPosition(JLabel.BOTTOM);
        label2.setHorizontalTextPosition(JLabel.CENTER);
        label2.addMouseListener(this);
        themesWindow.add(label2);

        ImageIcon icon3 = new ImageIcon("Look&Feels\\dracula.png");
        label3 = new JLabel(icon3);
        label3.setText("Dracula");
        label3.setVerticalTextPosition(JLabel.BOTTOM);
        label3.setHorizontalTextPosition(JLabel.CENTER);
        label3.addMouseListener(this);
        themesWindow.add(label3);

        ImageIcon icon4 = new ImageIcon("Look&Feels\\gruvbox-dark-medium.png");
        label4 = new JLabel(icon4);
        label4.setText("Gruvbox Dark Medium");
        label4.setVerticalTextPosition(JLabel.BOTTOM);
        label4.setHorizontalTextPosition(JLabel.CENTER);
        label4.addMouseListener(this);
        themesWindow.add(label4);

        ImageIcon icon5 = new ImageIcon("Look&Feels\\hiberbee-dark.png");
        label5 = new JLabel(icon5);
        label5.setText("Hiberbee Dark");
        label5.setVerticalTextPosition(JLabel.BOTTOM);
        label5.setHorizontalTextPosition(JLabel.CENTER);
        label5.addMouseListener(this);
        themesWindow.add(label5);

        ImageIcon icon6 = new ImageIcon("Look&Feels\\gray.png");
        label6 = new JLabel(icon6);
        label6.setText("Gray");
        label6.setVerticalTextPosition(JLabel.BOTTOM);
        label6.setHorizontalTextPosition(JLabel.CENTER);
        label6.addMouseListener(this);
        themesWindow.add(label6);
    }

    public void applyFlatOne() {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
            SwingUtilities.updateComponentTreeUI(gui.window);
            SwingUtilities.updateComponentTreeUI(themesWindow);
            gui.setIconsLight();
            gui.textArea.setForeground(new Color(0xf1f1f1));
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(gui.window, "Failed to load Look&Feel, please try another one.", "Error Occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void applyCarbon() {
        try {
            UIManager.setLookAndFeel(new FlatCarbonIJTheme());
            SwingUtilities.updateComponentTreeUI(gui.window);
            SwingUtilities.updateComponentTreeUI(themesWindow);
            gui.setIconsLight();
            gui.textArea.setForeground(new Color(0xf1f1f1));
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(gui.window, "Failed to load Look&Feel, please try another one.", "Error Occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void applyDracula() {
        try {
            UIManager.setLookAndFeel(new FlatDraculaIJTheme());
            SwingUtilities.updateComponentTreeUI(gui.window);
            SwingUtilities.updateComponentTreeUI(themesWindow);
            gui.setIconsLight();
            gui.textArea.setForeground(new Color(0xf1f1f1));
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(gui.window, "Failed to load Look&Feel, please try another one.", "Error Occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void applyGruvboxDarkMedium() {
        try {
            UIManager.setLookAndFeel(new FlatGruvboxDarkMediumIJTheme());
            SwingUtilities.updateComponentTreeUI(gui.window);
            SwingUtilities.updateComponentTreeUI(themesWindow);
            gui.setIconsLight();
            gui.textArea.setForeground(new Color(0xf1f1f1));
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(gui.window, "Failed to load Look&Feel, please try another one.", "Error Occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void applyHiberbeeDark() {
        try {
            UIManager.setLookAndFeel(new FlatHiberbeeDarkIJTheme());
            SwingUtilities.updateComponentTreeUI(gui.window);
            SwingUtilities.updateComponentTreeUI(themesWindow);
            gui.setIconsLight();
            gui.textArea.setForeground(new Color(0xf1f1f1));
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(gui.window, "Failed to load Look&Feel, please try another one.", "Error Occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void applyGray() {
        try {
            UIManager.setLookAndFeel(new FlatGrayIJTheme());
            SwingUtilities.updateComponentTreeUI(gui.window);
            SwingUtilities.updateComponentTreeUI(themesWindow);
            gui.setIconsDark();
            gui.textArea.setForeground(new Color(0x1f1f1f));
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(gui.window, "Failed to load Look&Feel, please try another one.", "Error Occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == label1) {
            applyFlatOne();
        } else if (e.getSource() == label2) {
            applyCarbon();
        } else if (e.getSource() == label3) {
            applyDracula();
        } else if (e.getSource() == label4) {
            applyGruvboxDarkMedium();
        } else if (e.getSource() == label5) {
            applyHiberbeeDark();
        } else if (e.getSource() == label6) {
            applyGray();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
