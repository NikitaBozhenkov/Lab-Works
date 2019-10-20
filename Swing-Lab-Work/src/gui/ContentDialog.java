package gui;

import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentDialog extends JDialog {
    private JPanel panel;
    private JButton closeButton;
    private JButton deleteButton;
    private JButton addButton;
    private JList<Shape> list;
    private DefaultListModel<Shape> defaultListModel;
    private MainFrame mainFrame;

    ContentDialog(MainFrame mainFrame) {
        super();

        this.mainFrame = mainFrame;
        $$$setupUI$$$();
        setContentPane(panel);

        defaultListModel = new DefaultListModel<>();
        list.setModel(defaultListModel);

        setLocation(600, 150);
        setSize(770, 700);

        update();

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainFrame.getBag().deleteShape(list.getSelectedValue());
                    update();
                } catch (NullPointerException ex) {
                    ErrorDialog errorDialog = new ErrorDialog("No selection or empty bag.");
                    errorDialog.setVisible(true);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrame.getBag().getCapacity() == 0) {
                    ErrorDialog errorDialog = new ErrorDialog("You haven't created a bag");
                    errorDialog.setVisible(true);
                } else {
                    AddDialog addDialog = new AddDialog(mainFrame, ContentDialog.this);
                    addDialog.setVisible(true);
                    update();
                }
            }
        });
    }

    void update() {
        defaultListModel.clear();
        for (int i = 0; i < mainFrame.getBag().getSize(); ++i) {
            defaultListModel.addElement(mainFrame.getBag().getElement(i));
        }
    }

//    private void createUIComponents() {
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.setSelectedIndex(1);
//    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-14737632));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-1118482));
        label1.setText("Bag Content");
        panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        closeButton = new JButton();
        closeButton.setBackground(new Color(-11316654));
        Font closeButtonFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, closeButton.getFont());
        if (closeButtonFont != null) closeButton.setFont(closeButtonFont);
        closeButton.setForeground(new Color(-1776412));
        closeButton.setHorizontalTextPosition(0);
        closeButton.setRequestFocusEnabled(false);
        closeButton.setText("Close");
        closeButton.setVerticalAlignment(0);
        closeButton.setVerticalTextPosition(1);
        panel.add(closeButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(140, -1), null, 2, false));
        deleteButton = new JButton();
        deleteButton.setBackground(new Color(-11316654));
        deleteButton.setFocusPainted(false);
        Font deleteButtonFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, deleteButton.getFont());
        if (deleteButtonFont != null) deleteButton.setFont(deleteButtonFont);
        deleteButton.setForeground(new Color(-4188401));
        deleteButton.setHorizontalTextPosition(0);
        deleteButton.setLabel("Delete");
        deleteButton.setRequestFocusEnabled(false);
        deleteButton.setText("Delete");
        deleteButton.setVerticalAlignment(0);
        deleteButton.setVerticalTextPosition(1);
        panel.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(160, -1), null, 2, false));
        addButton = new JButton();
        addButton.setBackground(new Color(-11316654));
        Font addButtonFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, addButton.getFont());
        if (addButtonFont != null) addButton.setFont(addButtonFont);
        addButton.setForeground(new Color(-14959828));
        addButton.setHorizontalTextPosition(0);
        addButton.setLabel("Add");
        addButton.setRequestFocusEnabled(false);
        addButton.setText("Add");
        addButton.setVerticalAlignment(0);
        addButton.setVerticalTextPosition(1);
        panel.add(addButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(120, -1), null, 1, false));
        list = new JList();
        list.setBackground(new Color(-14737632));
        Font listFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 28, list.getFont());
        if (listFont != null) list.setFont(listFont);
        list.setForeground(new Color(-199437));
        list.setOpaque(true);
        list.setSelectionBackground(new Color(-11711666));
        list.setSelectionForeground(new Color(-923159));
        list.setSelectionMode(0);
        panel.add(list, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 350), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
