package gui;

import converter.Converter;
import exceptions.VolumeException;
import exceptions.OverflowException;
import geometricFigures.Circle;
import geometricFigures.Square;
import geometricFigures.Triangle;
import shapes.Cube;
import shapes.Cylinder;
import shapes.Pyramid;
import shapes.Sphere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddDialog extends JDialog {

    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton oKButton;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel mainLabel;
    private JList<String> figureList;
    private JButton closeButton;
    private DefaultListModel<String> figureModel;
    private MainFrame mainFrame;
    private ContentDialog contentDialog;

    private void changeInputMenu(boolean label1State, boolean label2State, boolean label3State,
                                 String label1Text, String label2Text, String label3Text) {
        label1.setVisible(label1State);
        label1.setText(label1Text);
        textField1.setVisible(label1State);
        textField1.setText("");
        label2.setVisible(label2State);
        label2.setText(label2Text);
        textField2.setVisible(label2State);
        textField2.setText("");
        label3.setVisible(label3State);
        label3.setText(label3Text);
        textField3.setVisible(label3State);
        textField3.setText("");
    }

    AddDialog(MainFrame mainFrame, ContentDialog contentDialog) {
        this.mainFrame = mainFrame;
        this.contentDialog = contentDialog;
        setContentPane(panel);
        addListeners();
        setLocation(600, 150);
        setSize(770, 750);

        figureModel = new DefaultListModel<>();
        figureModel.addElement("Circle");
        figureModel.addElement("Cube");
        figureModel.addElement("Cylinder");
        figureModel.addElement("Pyramid");
        figureModel.addElement("Sphere");
        figureModel.addElement("Square");
        figureModel.addElement("Triangle");
        figureList.setModel(figureModel);

        label1.setVisible(false);
        textField1.setVisible(false);
        label2.setVisible(false);
        textField2.setVisible(false);
        label3.setVisible(false);
        textField3.setVisible(false);
    }

    private void addListeners() {
        oKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!figureList.isSelectionEmpty()) {
                        if (textField1.getText().isEmpty() && textField2.getText().isEmpty() && textField3.getText().isEmpty()) {
                            dispose();
                            return;
                        }
                        switch (figureList.getSelectedValue()) {
                            case ("Cube"): {
                                mainFrame.getBag().addFigure(new Cube(Double.parseDouble(textField2.getText())));
                                break;
                            }
                            case ("Square"): {
                                Converter converter = new Converter();
                                Square figure = new Square(Double.parseDouble(textField2.getText()));
                                mainFrame.getBag().addFigure(converter.convert(figure, mainFrame.getBag()));
                                break;
                            }
                            case ("Circle"): {
                                Converter converter = new Converter();
                                Circle figure = new Circle(Double.parseDouble(textField2.getText()));
                                mainFrame.getBag().addFigure(converter.convert(figure, mainFrame.getBag()));
                                break;
                            }
                            case ("Triangle"): {
                                Converter converter = new Converter();
                                Triangle figure = new Triangle(Double.parseDouble(textField1.getText()),
                                        Double.parseDouble(textField3.getText()));
                                mainFrame.getBag().addFigure(converter.convert(figure, mainFrame.getBag()));
                                break;
                            }
                            case ("Sphere"): {
                                mainFrame.getBag().addFigure(new Sphere(Double.parseDouble(textField2.getText())));
                                break;
                            }
                            case ("Cylinder"): {
                                mainFrame.getBag().addFigure(new Cylinder(Double.parseDouble(textField1.getText()),
                                        Double.parseDouble(textField3.getText())));
                                break;
                            }
                            case ("Pyramid"): {
                                mainFrame.getBag().addFigure(new Pyramid(Double.parseDouble(textField1.getText()),
                                        Double.parseDouble(textField2.getText()),
                                        Double.parseDouble(textField3.getText())));
                                break;
                            }
                        }
                    }
                    contentDialog.update();
                    dispose();
                } catch (VolumeException ex) {
                    ErrorDialog errorDialog = new ErrorDialog(ex.getMessage());
                    errorDialog.setVisible(true);
                } catch (OverflowException ex) {
                    ErrorDialog errorDialog = new ErrorDialog(ex.getMessage() + ". Volume = " + ex.getVolume());
                    errorDialog.setVisible(true);
                } catch (NumberFormatException ex) {
                    ErrorDialog errorDialog = new ErrorDialog("Input Field is empty");
                    errorDialog.setVisible(true);
                }

            }
        });

        figureList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                switch (figureList.getSelectedValue()) {
                    case ("Cube"):
                    case ("Square"): {
                        changeInputMenu(false, true, false,
                                "", "Side", "");
                        break;
                    }
                    case ("Circle"):
                    case ("Sphere"): {
                        changeInputMenu(false, true, false,
                                "", "Radius", "");
                        break;
                    }
                    case ("Cylinder"): {
                        changeInputMenu(true, false, true,
                                "Radius", "", "Height");
                        break;
                    }
                    case ("Pyramid"): {
                        changeInputMenu(true, true, true,
                                "Base side", "Base height", "Height");
                        break;
                    }
                    case ("Triangle"): {
                        changeInputMenu(true, false, true,
                                "Side", "", "Height");
                        break;
                    }
                    default: {
                        changeInputMenu(false, false, false,
                                "", "", "");
                        break;
                    }
                }
            }

        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-14737632));
        panel.setForeground(new Color(-2107690));
        mainLabel = new JLabel();
        Font mainLabelFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, mainLabel.getFont());
        if (mainLabelFont != null) mainLabel.setFont(mainLabelFont);
        mainLabel.setForeground(new Color(-1118482));
        mainLabel.setText("Choose a figure to add");
        panel.add(mainLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(400, 50), new Dimension(-1, 93), 0, false));
        label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 28, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-921103));
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Label");
        panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 28, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-921103));
        label2.setHorizontalAlignment(0);
        label2.setHorizontalTextPosition(0);
        label2.setText("Label");
        panel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 28, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-921103));
        label3.setHorizontalAlignment(0);
        label3.setHorizontalTextPosition(0);
        label3.setText("Label");
        panel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField1 = new JTextField();
        textField1.setBackground(new Color(-921103));
        Font textField1Font = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 28, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        textField1.setForeground(new Color(-15921907));
        panel.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField2 = new JTextField();
        textField2.setBackground(new Color(-921103));
        Font textField2Font = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 28, textField2.getFont());
        if (textField2Font != null) textField2.setFont(textField2Font);
        textField2.setForeground(new Color(-15921907));
        panel.add(textField2, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField3 = new JTextField();
        textField3.setBackground(new Color(-921103));
        Font textField3Font = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 28, textField3.getFont());
        if (textField3Font != null) textField3.setFont(textField3Font);
        textField3.setForeground(new Color(-15921907));
        panel.add(textField3, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        figureList = new JList();
        figureList.setBackground(new Color(-11842740));
        figureList.setEnabled(true);
        Font figureListFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, figureList.getFont());
        if (figureListFont != null) figureList.setFont(figureListFont);
        figureList.setForeground(new Color(-1513240));
        figureList.setLayoutOrientation(0);
        figureList.setSelectionBackground(new Color(-11842740));
        figureList.setSelectionForeground(new Color(-923159));
        figureList.setSelectionMode(0);
        panel.add(figureList, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, -1), null, 0, false));
        oKButton = new JButton();
        oKButton.setBackground(new Color(-11316654));
        Font oKButtonFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, oKButton.getFont());
        if (oKButtonFont != null) oKButton.setFont(oKButtonFont);
        oKButton.setForeground(new Color(-921103));
        oKButton.setRequestFocusEnabled(false);
        oKButton.setText("OK");
        panel.add(oKButton, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 50), new Dimension(100, 50), 0, false));
        closeButton = new JButton();
        closeButton.setBackground(new Color(-11316654));
        Font closeButtonFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 36, closeButton.getFont());
        if (closeButtonFont != null) closeButton.setFont(closeButtonFont);
        closeButton.setForeground(new Color(-921103));
        closeButton.setRequestFocusEnabled(false);
        closeButton.setText("Close");
        panel.add(closeButton, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(130, 50), new Dimension(130, 50), 0, false));
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
