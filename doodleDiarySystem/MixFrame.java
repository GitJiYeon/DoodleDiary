package doodleDiarySystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MixFrame extends JFrame {
	public MixFrame(String choiceDate) {
		setTitle("DoodleDiary");
        setSize(800, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        requestFocusInWindow();
        Panel drawingPanel = new Panel();
        Panel toolPanel = new Panel();
        Panel topPanel = new Panel();
        
        JButton exitButton = new JButton("EXIT");
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        topPanel.add(exitButton);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        add(topPanel, BorderLayout.NORTH);
        
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                drawingPanel.setFocusable(true);
            }
        });
	}
}
