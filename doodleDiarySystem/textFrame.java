package doodleDiarySystem;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.io.*;

// 일기 쓰는 패널

public class textFrame extends JPanel {
	private JTextArea textArea; // 입력 창
	private JLabel textCount; // 글자 수 세서 보여줌

	public textFrame() {
		setLayout(new BorderLayout()); // 전체 레이아웃
		setBackground(Color.YELLOW); // 디버깅을 위해 배경색 설정
	    setPreferredSize(new Dimension(650, 230)); // 패널 크기 설정
		
		JPanel northPane = new JPanel(new FlowLayout());
		JPanel southPane = new JPanel(new BorderLayout());
		
		textArea = new JTextArea();
		textCount = new JLabel("글자 수 : 0 / 60");

		// DocumentListener로 글자 수 제한 및 글자수 표시(event 중에 하나임)
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				limitText();
				updateCharCount();
			}

			@Override
			// 삭제는 제한이 없으므로 처리할 필요 없음
			public void removeUpdate(DocumentEvent e) {
				updateCharCount();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				limitText();
				updateCharCount();
			}

			private void limitText() { // 글자 수 초과 시 삭제하는 메소드
				if (textArea.getText().length() > 60) {
					SwingUtilities.invokeLater(() -> {
						textArea.setText(textArea.getText().substring(0, 60)); // 60자만 남김
					});
				}
			}

			private void updateCharCount() { // 글자 수 보여주는 메소드
				int charCount = textArea.getText().length(); // 현재 글자 수
				textCount.setText("글자 수 : " + charCount + " / 60");
			}
		});

		try {
			// 폰트 파일 경로 설정
			String fontPath = "src/fonts/온글잎 박다현체.ttf"; // 실제 경로 확인

			// 폰트 로드
			InputStream is = new FileInputStream(new File(fontPath));
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f); // 크기 설정(40f)

			// textArea에 폰트 적용
			textArea.setFont(customFont);
		} catch (Exception e) {
			e.printStackTrace();
		}

		northPane.add(textCount);
		southPane.add(textArea, BorderLayout.CENTER);
		
		textArea.setLineWrap(true); // 자동 줄바꿈
		textCount.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		
		add(northPane, BorderLayout.NORTH);
        add(southPane, BorderLayout.CENTER);
	}
	
	public String getText() {
        return textArea.getText();
    }
}