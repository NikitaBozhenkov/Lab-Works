package gui;

import bag.Bag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JPanel controlPanel;
    private JLabel headerLabel;
    private JButton createBagButton;
    private JButton exitButton;
    private JButton contentButton;
    private JButton clearButton;
    private JButton deleteBagButton;
    private CreateBagDialog createBagDialog;
    private ContentDialog contentDialog;
    private Bag bag;

    public MainFrame() {
        setContentPane(controlPanel);
        setSize(600, 600);
        setLocation(550, 100);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        bag = new Bag(0);
        addListeners();
        update();
    }

    void update() {
        createBagButton.setEnabled(bag.getCapacity() == 0);
        deleteBagButton.setEnabled(bag.getCapacity() != 0);
        clearButton.setEnabled(bag.getCapacity() != 0);
    }

    Bag getBag() {
        return bag;
    }

    private void addListeners() {
        createBagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBagDialog = new CreateBagDialog(MainFrame.this);
                createBagDialog.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        contentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentDialog = new ContentDialog(MainFrame.this);
                contentDialog.setVisible(true);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bag.clear();
            }
        });

        deleteBagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bag.setCapacity(0);
                bag.clear();
                update();
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 5, new Insets(0, 0, 0, 0), -1, -1));
        controlPanel.setAutoscrolls(false);
        controlPanel.setBackground(new Color(-14737632));
        controlPanel.setForeground(new Color(-15921907));
        contentButton = new JButton();
        contentButton.setBackground(new Color(-11316654));
        contentButton.setContentAreaFilled(true);
        contentButton.setEnabled(true);
        contentButton.setFocusPainted(false);
        Font contentButtonFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, contentButton.getFont());
        if (contentButtonFont != null) contentButton.setFont(contentButtonFont);
        contentButton.setForeground(new Color(-10053221));
        contentButton.setHideActionText(false);
        contentButton.setHorizontalTextPosition(0);
        contentButton.setOpaque(true);
        contentButton.setRequestFocusEnabled(false);
        contentButton.setText("Go to Bag");
        contentButton.setVerifyInputWhenFocusTarget(false);
        controlPanel.add(contentButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(439, 80), null, 0, false));
        createBagButton = new JButton();
        createBagButton.setBackground(new Color(-11316654));
        createBagButton.setContentAreaFilled(true);
        createBagButton.setEnabled(true);
        createBagButton.setFocusPainted(false);
        Font createBagButtonFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, createBagButton.getFont());
        if (createBagButtonFont != null) createBagButton.setFont(createBagButtonFont);
        createBagButton.setForeground(new Color(-15623389));
        createBagButton.setHideActionText(false);
        createBagButton.setHorizontalAlignment(0);
        createBagButton.setHorizontalTextPosition(0);
        createBagButton.setInheritsPopupMenu(false);
        createBagButton.setOpaque(true);
        createBagButton.setRequestFocusEnabled(false);
        createBagButton.setText("Create Bag");
        createBagButton.setVerifyInputWhenFocusTarget(false);
        createBagButton.setVerticalAlignment(0);
        controlPanel.add(createBagButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(439, 80), null, 0, false));
        headerLabel = new JLabel();
        Font headerLabelFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, headerLabel.getFont());
        if (headerLabelFont != null) headerLabel.setFont(headerLabelFont);
        headerLabel.setForeground(new Color(-986896));
        headerLabel.setHorizontalAlignment(0);
        headerLabel.setHorizontalTextPosition(0);
        headerLabel.setText("Bag Simulator");
        headerLabel.setVerticalAlignment(0);
        controlPanel.add(headerLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(439, 79), null, 0, false));
        deleteBagButton = new JButton();
        deleteBagButton.setBackground(new Color(-11316654));
        deleteBagButton.setContentAreaFilled(true);
        deleteBagButton.setDoubleBuffered(false);
        deleteBagButton.setEnabled(true);
        deleteBagButton.setFocusCycleRoot(false);
        deleteBagButton.setFocusPainted(false);
        deleteBagButton.setFocusTraversalPolicyProvider(false);
        Font deleteBagButtonFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, deleteBagButton.getFont());
        if (deleteBagButtonFont != null) deleteBagButton.setFont(deleteBagButtonFont);
        deleteBagButton.setForeground(new Color(-6608085));
        deleteBagButton.setHideActionText(false);
        deleteBagButton.setHorizontalTextPosition(0);
        deleteBagButton.setInheritsPopupMenu(false);
        deleteBagButton.setOpaque(true);
        deleteBagButton.setRequestFocusEnabled(false);
        deleteBagButton.setRolloverEnabled(true);
        deleteBagButton.setSelected(false);
        deleteBagButton.setText("Delete Bag");
        deleteBagButton.setVerifyInputWhenFocusTarget(false);
        deleteBagButton.setVisible(true);
        deleteBagButton.putClientProperty("hideActionText", Boolean.FALSE);
        deleteBagButton.putClientProperty("html.disable", Boolean.FALSE);
        controlPanel.add(deleteBagButton, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(439, 80), null, 0, false));
        clearButton = new JButton();
        clearButton.setBackground(new Color(-11316654));
        clearButton.setContentAreaFilled(true);
        clearButton.setEnabled(true);
        clearButton.setFocusPainted(false);
        Font clearButtonFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, clearButton.getFont());
        if (clearButtonFont != null) clearButton.setFont(clearButtonFont);
        clearButton.setForeground(new Color(-921103));
        clearButton.setHideActionText(false);
        clearButton.setHorizontalTextPosition(0);
        clearButton.setOpaque(true);
        clearButton.setRequestFocusEnabled(false);
        clearButton.setText("Clear Bag");
        clearButton.setVerifyInputWhenFocusTarget(false);
        controlPanel.add(clearButton, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(439, 80), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        controlPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        controlPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        controlPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setBackground(new Color(-11316654));
        exitButton.setContentAreaFilled(true);
        exitButton.setEnabled(true);
        exitButton.setFocusPainted(false);
        Font exitButtonFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, exitButton.getFont());
        if (exitButtonFont != null) exitButton.setFont(exitButtonFont);
        exitButton.setForeground(new Color(-6710502));
        exitButton.setHideActionText(false);
        exitButton.setHorizontalTextPosition(0);
        exitButton.setInheritsPopupMenu(false);
        exitButton.setOpaque(true);
        exitButton.setRequestFocusEnabled(false);
        exitButton.setText("EXIT");
        exitButton.setVerifyInputWhenFocusTarget(false);
        controlPanel.add(exitButton, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(439, 80), null, 0, false));
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
        return controlPanel;
    }

}
