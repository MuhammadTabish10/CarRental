package org.carrental.ui;

import javax.swing.*;
import java.awt.*;

public class BaseUI
{
    public JButton createStyledButton(String name) {
        JButton button = new JButton(name);
        button.setBackground(new Color(46, 204, 113));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        return button;
    }

    public JLabel createStyledLabel(String name) {
        JLabel label = new JLabel(name);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        return label;
    }

}
