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
	String choiceDay; //클릭 날짜
	JButton dateButton;
	
    public DiaryFrame() { 
    	setTitle("DoodleDiary");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        LocalDate today = LocalDate.now();
        String currentDate = today.toString();
        YearMonth currentMonth = YearMonth.from(today);
        
        int year = today.getYear();
        int month = today.getMonthValue();
        int dayCount = currentMonth.lengthOfMonth();
        int firstDayOfWeek = LocalDate.of(year, month, 1).getDayOfWeek().getValue() % 7;
        
        JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout(FlowLayout.CENTER,20, 10));
        
        JButton left = new JButton("<");
        JButton right = new JButton(">");
        
        left.setBackground(Color.WHITE);
        left.setFont(new Font("Arial",Font.BOLD,30));
        right.setBackground(Color.WHITE);
        right.setFont(new Font("Arial",Font.BOLD,30));
        JLabel showMonth = new JLabel(year + ", "+ String.valueOf(month) + "월",  SwingConstants.CENTER);
        showMonth.setFont(new Font("맑은 고딕",Font.PLAIN,25));
        
        add(left);
        add(right);
        
        topBar.add(left, BorderLayout.WEST);
        topBar.add(showMonth, BorderLayout.CENTER);
        topBar.add(right, BorderLayout.EAST);
        
        add(topBar, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        
        JPanel dayOfWeekGrid = new JPanel();
        dayOfWeekGrid.setLayout(new GridLayout(1, 7));
        
        String[] week = new String[]{"일","월","화","수","목","금","토"};
        for(int i = 0; i < week.length; i++) {
        	JLabel weekLable = new JLabel(week[i], SwingConstants.CENTER);
        	weekLable.setFont(new Font("맑은 고딕",Font.PLAIN, 20));
        	dayOfWeekGrid.add(weekLable);
        	centerPanel.add(dayOfWeekGrid);
        }
        JPanel days = new JPanel();
        days.setLayout(new GridLayout(5, 7));
        for(int i = 0; i < firstDayOfWeek; i++) {
        	JButton NullButton= new JButton("");
        	NullButton.setBackground(Color.WHITE);
        	days.add(NullButton);
        }
        
        int dayTempCount = 1;
        for(int i = firstDayOfWeek; i < (firstDayOfWeek + dayCount); i++) {
        	int buttonDate = dayTempCount;
        	dateButton = new JButton((dayTempCount)+"");
        	
        	dateButton.addActionListener(e ->{
            	choiceDay = String.valueOf(buttonDate);
            	showDrawingFrame(choiceDay);
            });
        	
        	if(dayTempCount % 7 == 4) {
        		dateButton.setBackground(Color.WHITE);
        		dateButton.setForeground(Color.RED);
        	}
        	else if(dayTempCount % 7 == 5) {
        		dateButton.setBackground(Color.WHITE);
        		dateButton.setForeground(Color.BLUE);
        	}
        	else if(dayTempCount == today.getDayOfMonth()) {
        		dateButton.setBackground(Color.decode("#606dab"));
        		dateButton.setForeground(Color.WHITE);
        	}
        	else {
        		dateButton.setBackground(Color.WHITE);
        	}
        	dateButton.setFont(new Font("Arial",Font.PLAIN, 30));
        	days.add(dateButton);
        	dayTempCount++;
        	
        	
        }
        for(int i = firstDayOfWeek + dayCount; i < 35; i++) {
        	JButton NullButton= new JButton("");
        	NullButton.setBackground(Color.WHITE);
        	days.add(NullButton);
        }
        
        centerPanel.add(dayOfWeekGrid, BorderLayout.NORTH);
        centerPanel.add(days, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    void showDrawingFrame(String choiceDate ) {
    	new drawingFrame(choiceDate);
    }
}