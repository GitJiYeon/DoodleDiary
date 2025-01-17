package doodleDiarySystem;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MixFrame extends JFrame {
    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel bottomPanel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel southPanel = new JPanel();

    JLabel date;
    JButton exitButton = new JButton("EXIT");
    JButton saveButton = new JButton("SAVE");

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("날씨");

    JMenuItem sunny = new JMenuItem("☀️");
    JMenuItem cloudysun = new JMenuItem("⛅");
    JMenuItem cloudy = new JMenuItem("☁️");
    JMenuItem rainy = new JMenuItem("🌧️");
    JMenuItem windy = new JMenuItem("💨");
    JMenuItem snowy = new JMenuItem("❄️");

    private String selectedWeather = "날씨";
    private drawingFrame df = new drawingFrame();
    private textFrame tf = new textFrame();

    public MixFrame(String currentYear, String choiceMonth, String choiceDate) {

        setTitle("DoodleDiary");
        setSize(650, 850);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        date = new JLabel(currentYear + "년 " + choiceMonth + "월 " + choiceDate + "일");

        topPanel.setPreferredSize(new Dimension(650, 45));
        centerPanel.setPreferredSize(new Dimension(650, 780));
        bottomPanel.setPreferredSize(new Dimension(650, 25));

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        centerPanel.add(northPanel, BorderLayout.NORTH);
        centerPanel.add(southPanel, BorderLayout.SOUTH);

        topPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        menu.add(sunny);
        menu.add(cloudysun);
        menu.add(cloudy);
        menu.add(rainy);
        menu.add(windy);
        menu.add(snowy);

        Weather weatherListener = new Weather(menu);
        sunny.addActionListener(weatherListener);
        cloudysun.addActionListener(weatherListener);
        cloudy.addActionListener(weatherListener);
        rainy.addActionListener(weatherListener);
        windy.addActionListener(weatherListener);
        snowy.addActionListener(weatherListener);

        menuBar.add(menu);

        leftPanel.add(date);
        leftPanel.add(menuBar);
        rightPanel.add(saveButton);
        rightPanel.add(exitButton);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);

        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        date.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        northPanel.setPreferredSize(new Dimension(650, 550));
        southPanel.setPreferredSize(new Dimension(650, 230));

        northPanel.setLayout(new BorderLayout());
        northPanel.add(df, BorderLayout.CENTER);

        southPanel.setLayout(new BorderLayout());
        southPanel.add(tf, BorderLayout.CENTER);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData(currentYear, choiceMonth, choiceDate);
            }
        });

        setVisible(true);
    }

    private void saveData(String currentYear, String choiceMonth, String choiceDate) {
        // 날짜를 yyyyMMdd 형식으로 변환
        String formattedDate = String.format("%04d%02d%02d", Integer.parseInt(currentYear), Integer.parseInt(choiceMonth), Integer.parseInt(choiceDate));

        // Get the image and text from respective frames
        BufferedImage image = df.getImage();
        String text = tf.getText();

        // Create a DiaryData object
        DiaryData diaryData = new DiaryData(image, text, formattedDate);

        // Save the data
        diaryData.saveData();

        // Show confirmation
        JOptionPane.showMessageDialog(this, "일기 저장이 완료되었습니다.");
    }

    private class Weather implements ActionListener {
        private JMenu menu;

        public Weather(JMenu menu) {
            this.menu = menu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem clickedItem = (JMenuItem) e.getSource();
            menu.setText(clickedItem.getText());
            selectedWeather = clickedItem.getText();
        }
    }
}
