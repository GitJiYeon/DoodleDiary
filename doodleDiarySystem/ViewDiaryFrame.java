package doodleDiarySystem;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class ViewDiaryFrame extends JFrame {
    public ViewDiaryFrame(String dateKey, DiaryData data, String weatherSymbol) {
        setTitle("View Diary");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 날짜 라벨과 날씨 아이콘을 함께 표시
        JLabel dateLabel = new JLabel(dateKey + " " + weatherSymbol, SwingConstants.CENTER);
        dateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 30)); // 날짜 글씨 폰트
        add(dateLabel, BorderLayout.NORTH);

        // 저장된 이미지를 중앙에 표시 (이미지가 있을 경우)
        if (data.getImage() != null) {
            JLabel imageLabel = new JLabel(new ImageIcon(data.getImage()));
            add(imageLabel, BorderLayout.CENTER);
        }

        // 텍스트를 한 글자씩 GridLayout으로 표시 (6행 10열)
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(6, 10)); // 6행 10열

        // 텍스트를 한 글자씩 분리해서 추가
        String text = data.getText();
        for (int i = 0; i < 60; i++) {
            String charText = (i < text.length()) ? String.valueOf(text.charAt(i)) : " "; // 빈칸 처리
            JLabel charLabel = new JLabel(charText, SwingConstants.CENTER);
            charLabel.setFont(new Font("온글잎 박다현체", Font.PLAIN, 30)); // 원하는 폰트
            charLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 각 글자에 테두리 추가
            textPanel.add(charLabel);
        }

        // 텍스트를 표시할 패널 추가
        add(textPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
