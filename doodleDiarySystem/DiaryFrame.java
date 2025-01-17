package doodleDiarySystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DiaryFrame extends JFrame{
	JPanel days; //날짜를 담는 패널
	JButton dateButton; //그리드에 표시될 날짜들
	
	YearMonth currentMonth; //현재 달
	String currentDate; //현재 날짜
	
	String currentYear; //선택 년도
	String choiceMonth; //선택 달
	String choiceDay; //선택 날짜
	
	LocalDate today;
	
	JLabel showMonth; //현재 날짜를 상단에 표시할 레이블

    public DiaryFrame() { 
    	setTitle("DoodleDiary");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //현재 날짜 불러오기
        today = LocalDate.now();
        currentDate = today.toString();
        currentMonth = YearMonth.from(today);

        int year = today.getYear();
        int month = today.getMonthValue();
        int dayCount = currentMonth.lengthOfMonth();
        int firstDayOfWeek = LocalDate.of(year, month, 1).getDayOfWeek().getValue() % 7;

        //상단 달 표시 패널
        JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout(FlowLayout.CENTER,20, 10));

        //상단 달 조정버튼
        JButton left = new JButton("<");
        JButton right = new JButton(">");

        //버튼, 글씨 디자인
        left.setBackground(Color.WHITE);
        left.setFont(new Font("Arial",Font.BOLD,30));
        right.setBackground(Color.WHITE);
        right.setFont(new Font("Arial",Font.BOLD,30));
        showMonth = new JLabel(year + ", "+ String.valueOf(month) + "월",  SwingConstants.CENTER);
        showMonth.setFont(new Font("맑은 고딕",Font.PLAIN,25));

        //상단 요소들 추가
        topBar.add(left, BorderLayout.WEST);
        topBar.add(showMonth, BorderLayout.CENTER);
        topBar.add(right, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);
        
        //중앙 메인 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        //요일 패널
        JPanel dayOfWeekGrid = new JPanel();
        dayOfWeekGrid.setLayout(new GridLayout(1, 7)); //1행 7열

        String[] week = new String[]{"일","월","화","수","목","금","토"};
        for(int i = 0; i < week.length; i++) {
        	JLabel weekLabel = new JLabel(week[i], SwingConstants.CENTER);
        	weekLabel.setFont(new Font("맑은 고딕",Font.PLAIN, 20));
        	dayOfWeekGrid.add(weekLabel); //그리드에 추가
        }
        centerPanel.add(dayOfWeekGrid, BorderLayout.NORTH);

        //days 패널 생성
        days = new JPanel();
        days.setLayout(new GridLayout(6, 7)); // 6행 7열
        centerPanel.add(days, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
        
        setVisible(true);

        //메서드를 통해 현재 날짜에 맞는 그리드를 표시
        updateDays(year, month, dayCount, firstDayOfWeek);
        updateScreen(); //화면 갱신
        left.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1); // 달 --
            updateScreen(); // 화면 갱신
        });
        right.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1); // 달++
            updateScreen(); // 화면 갱신
            
        });
    }

    //화면 갱신 메서드
    void updateScreen(){
        int year = currentMonth.getYear();
        int month = currentMonth.getMonthValue();
        int dayCount = currentMonth.lengthOfMonth();
        int firstDayOfWeek = LocalDate.of(year, month, 1).getDayOfWeek().getValue() % 7;
        
        showMonth.setText(year + ", " + month + "월"); //인자로 받은 년, 달 표시
        
        days.removeAll(); // 현재 days에 있는 값 삭제
        updateDays(year, month, dayCount, firstDayOfWeek);
        
        days.revalidate();
        days.repaint();
    }

    void updateDays(int year, int month, int dayCount, int firstDayOfWeek){

        for(int i = 0; i < firstDayOfWeek; i++) {
            JButton NullButton = new JButton("");
            NullButton.setBackground(Color.WHITE);
            days.add(NullButton);
        }

        int dayTempCount = 1;
        for(int i = firstDayOfWeek; i < (firstDayOfWeek + dayCount); i++) {
            int buttonDate = dayTempCount;
            dateButton = new JButton(String.valueOf(dayTempCount));
            dateButton.addActionListener(e ->{
                choiceDay = String.valueOf(buttonDate);
                choiceMonth = String.valueOf(month);
                showMixFrame(String.valueOf(today.getYear()),choiceMonth, choiceDay);
            });

            if ((i % 7) == 0) { 
                dateButton.setBackground(Color.WHITE);
                dateButton.setForeground(Color.RED);
            }
            else if ((i % 7) == 6) {
                dateButton.setBackground(Color.WHITE);
                dateButton.setForeground(Color.BLUE);
            }
            else if (year == today.getYear() && month == today.getMonthValue() && dayTempCount == today.getDayOfMonth()) {
                dateButton.setBackground(Color.decode("#606dab"));
                dateButton.setForeground(Color.WHITE);
            } else {
                dateButton.setBackground(Color.WHITE);
            }

            dateButton.setFont(new Font("Arial", Font.PLAIN, 30));
            days.add(dateButton);
            dayTempCount++;
        }
        for(int i = firstDayOfWeek + dayCount; i < 42; i++) {
            JButton NullButton = new JButton("");
            NullButton.setBackground(Color.WHITE);
            days.add(NullButton);
        }
    }

    void showMixFrame(String currentYear, String choiceMonth,String choiceDate) {
    	new MixFrame(currentYear, choiceMonth, choiceDate);
    }
}