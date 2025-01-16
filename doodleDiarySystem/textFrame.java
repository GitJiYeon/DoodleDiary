package doodleDiarySystem;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.io.*;

public class textFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // 메인 패널
	private JPanel northPane; // 글자수 보여주는 거 위치
	private JPanel southPane; // 입력창 위치시킬거
	private JTextArea textArea; // 입력 창
	private JLabel textCount; // 글자 수 세서 보여줌
	private char text[];

	/**
	 * Create the frame.
	 */
	public textFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300); // 절대 위치
		
		contentPane = new JPanel();
		northPane = new JPanel();
		southPane = new JPanel();
		textArea = new JTextArea();
		textCount = new JLabel("글자 수 : 0 / 60");
		text = new char[60];
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.add(northPane, BorderLayout.NORTH);
		contentPane.add(southPane, BorderLayout.SOUTH);
		
		northPane.setPreferredSize(new Dimension(450, 25));
		southPane.setPreferredSize(new Dimension(450, 275));
		
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
                textCount.setText("글자 수: " + charCount + " / 60");
            }
        });
		
        northPane.add(textCount);
		southPane.add(textArea);
		textArea.setPreferredSize(new Dimension(430, 275));
		textArea.setLineWrap(true); // 자동 줄바꿈
		
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
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					textFrame frame = new textFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}