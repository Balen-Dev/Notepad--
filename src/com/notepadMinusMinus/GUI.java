package com.notepadMinusMinus;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUI implements ActionListener {

    JFrame window;

    // TextArea Components
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;

    // MenuBar Components
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, preferencesMenu;

    // File Menu Components
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;

    // Edit Menu Components
    JMenuItem iUndo;
    JMenuItem iRedo;
    JMenuItem iFind;
    JMenuItem iReplace;
    JMenuItem iRemoveHighlights;

    // Preferences Menu Components
    JMenuItem iWrap;
    JMenuItem iFontColor;
    JMenuItem iChangeTheme;

    // Font Properties
    JSpinner fontSize;
    JComboBox fontBox;
    JColorChooser colorChooser;

    FileMenu file = new FileMenu(this);
    PreferencesMenu preferences = new PreferencesMenu(this);
    EditMenu edit = new EditMenu(this);
    FontBox font = new FontBox(this);
    Themes themes = new Themes(this);

    UndoManager um = new UndoManager();

    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();


    public static void main (String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
        new GUI();
    }

    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createPreferencesMenu();
        setShortcuts();
        createFontSizeSpinner();
        createFontBox();
        setIconsLight();
        window.setVisible(true);
    }

    public void createWindow() {
        window = new JFrame("Notepad--");
        window.setSize(600,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
    }

    public void createTextArea() {
        textArea = new JTextArea();
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        window.add(scrollPane);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        editMenu= new JMenu("Edit");
        menuBar.add(editMenu);

        preferencesMenu = new JMenu("Preferences");
        menuBar.add(preferencesMenu);

    }

    public void createFileMenu() {
        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        fileMenu.add(iNew);

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        fileMenu.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        fileMenu.add(iSave);

        iSaveAs = new JMenuItem("SaveAs");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        fileMenu.add(iSaveAs);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        fileMenu.add(iExit);
    }

    public void createEditMenu() {
        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        editMenu.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        editMenu.add(iRedo);

        iFind = new JMenuItem("Find");
        iFind.addActionListener(this);
        iFind.setActionCommand("Find");
        editMenu.add(iFind);

        iReplace = new JMenuItem("Find & Replace");
        iReplace.addActionListener(this);
        iReplace.setActionCommand("Replace");
        editMenu.add(iReplace);

        iRemoveHighlights = new JMenuItem("Clear Highlighted Words");
        iRemoveHighlights.addActionListener(this);
        iRemoveHighlights.setActionCommand("Remove All Highlights");
        editMenu.add(iRemoveHighlights);
    }

    public void createPreferencesMenu() {
        iWrap = new JMenuItem("Word Wrap: on");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        preferencesMenu.add(iWrap);

        iFontColor = new JMenuItem("Set Text Color");
        iFontColor.addActionListener(this);
        iFontColor.setActionCommand("Change Font Color");
        preferencesMenu.add(iFontColor);

        iChangeTheme = new JMenuItem("Change Theme");
        iChangeTheme.addActionListener(this);
        iChangeTheme.setActionCommand("Change Theme");
        preferencesMenu.add(iChangeTheme);
    }

    public void setShortcuts() {
        fileMenu.setMnemonic(KeyEvent.VK_F);
        iNew.setMnemonic(KeyEvent.VK_N);
        iOpen.setMnemonic(KeyEvent.VK_O);
        iSave.setMnemonic(KeyEvent.VK_S);
        iExit.setMnemonic(KeyEvent.VK_E);
        // Skipped Save as Because It Is not Necessary.

        editMenu.setMnemonic(KeyEvent.VK_E);
        iUndo.setMnemonic(KeyEvent.VK_U);
        iRedo.setMnemonic(KeyEvent.VK_R);
        iFind.setMnemonic(KeyEvent.VK_F);
        iReplace.setMnemonic(KeyEvent.VK_I);
        iRemoveHighlights.setMnemonic(KeyEvent.VK_D);

        preferencesMenu.setMnemonic(KeyEvent.VK_S);
        iWrap.setMnemonic(KeyEvent.VK_W);
        iFontColor.setMnemonic(KeyEvent.VK_C);

    }

    public void createFontSizeSpinner() {
        fontSize= new JSpinner();
        fontSize.setPreferredSize(new Dimension(51,18));
        fontSize.setValue(14);
        fontSize.setFocusable(false);
        fontSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSize.getValue()));
            }
        });
        menuBar.add(fontSize);
    }

    public void createFontBox() {
        fontBox = new JComboBox(fonts);
        fontBox.addActionListener(this);
        fontBox.setActionCommand("Change Font");
        fontBox.setSelectedItem("Arial");
        fontBox.setPreferredSize(new Dimension(115, 30));
        menuBar.add(fontBox);
    }

    public void setIconsLight() {

        window.setIconImage(new ImageIcon("DarkMode\\icon-light.png").getImage());

        iNew.setIcon(new ImageIcon("DarkMode\\new-file-light.png"));
        iOpen.setIcon(new ImageIcon("DarkMode\\open-light.png"));
        iSave.setIcon(new ImageIcon("DarkMode\\save-light.png"));
        iSaveAs.setIcon(new ImageIcon("DarkMode\\save-as-light.png"));
        iExit.setIcon(new ImageIcon("DarkMode\\exit-light.png"));

        iUndo.setIcon(new ImageIcon("DarkMode\\undo-light.png"));
        iRedo.setIcon(new ImageIcon("DarkMode\\redo-light.png"));
        iFind.setIcon(new ImageIcon("DarkMode\\find-light.png"));
        iReplace.setIcon(new ImageIcon("DarkMode\\find-replace-light.png"));
        iRemoveHighlights.setIcon(new ImageIcon("DarkMode\\highlighter-light.png"));

        iWrap.setIcon(new ImageIcon("DarkMode\\wrap-text-light.png"));
        iFontColor.setIcon(new ImageIcon("DarkMode\\text-color-light.png"));
        iChangeTheme.setIcon(new ImageIcon("DarkMode\\theme-light.png"));
    }

    public void setIconsDark() {

        window.setIconImage(new ImageIcon("LightMode\\icon-dark.png").getImage());

        iNew.setIcon(new ImageIcon("LightMode\\new-file-dark.png"));
        iOpen.setIcon(new ImageIcon("LightMode\\open-dark.png"));
        iSave.setIcon(new ImageIcon("LightMode\\save-dark.png"));
        iSaveAs.setIcon(new ImageIcon("LightMode\\save-as-dark.png"));
        iExit.setIcon(new ImageIcon("LightMode\\exit-dark.png"));

        iUndo.setIcon(new ImageIcon("LightMode\\undo-dark.png"));
        iRedo.setIcon(new ImageIcon("LightMode\\redo-dark.png"));
        iFind.setIcon(new ImageIcon("LightMode\\find-dark.png"));
        iReplace.setIcon(new ImageIcon("LightMode\\find-replace-dark.png"));
        iRemoveHighlights.setIcon(new ImageIcon("LightMode\\highlighter-dark.png"));

        iWrap.setIcon(new ImageIcon("LightMode\\wrap-text-dark.png"));
        iFontColor.setIcon(new ImageIcon("LightMode\\text-color-dark.png"));
        iChangeTheme.setIcon(new ImageIcon("LightMode\\theme-dark.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var command = e.getActionCommand();

        switch(command) {
            case "New": file.createNewFile(); break;
            case "Open": file.openFile(); break;
            case "Save": file.saveFile(); break;
            case "SaveAs": file.saveAs(); break;
            case "Exit": file.exitApp(); break;
            case "Undo": edit.undo(); break;
            case "Redo": edit.redo(); break;
            case "Find": edit.find(); break;
            case "Replace": edit.findAndReplace(); break;
            case "Remove All Highlights": edit.removeAllHighlights(); break;
            case "Word Wrap": preferences.wordWrap(); break;
            case "Change Font Color": preferences.changeColor(); break;
            case "Change Theme": themes.viewThemesWindow(); break;
            case "Change Font": font.changeFont(); break;
        }
    }
}

