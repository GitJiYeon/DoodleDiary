package doodleDiarySystem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

public class DiaryData {
    private BufferedImage image;
    private String text;
    private String date; // 날짜 필드 추가

    public DiaryData(BufferedImage image, String text, String date) {
        this.image = image;
        this.text = text;
        this.date = date; // 날짜 저장
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    // 날짜에 맞는 폴더에 이미지와 텍스트 파일을 저장하는 메서드
    public void saveData() {
        // 날짜를 yyyyMMdd 형식으로 변환
        String formattedDate = date; // 이미 yyyyMMdd 형식으로 저장된 날짜

        // Create directories if they don't exist
        File imageDir = new File("./Images/" + formattedDate);
        File textDir = new File("./Text/" + formattedDate);
        if (!imageDir.exists()) imageDir.mkdirs();
        if (!textDir.exists()) textDir.mkdirs();

        // Save drawing panel as image
        File imageFile = new File(imageDir, formattedDate + ".png");
        try {
            ImageIO.write(image, "png", imageFile);
            System.out.println("그림이 저장되었습니다: " + imageFile.getAbsolutePath());
        } catch (IOException ex) {
            System.err.println("그림 저장에 실패했습니다.");
        }

        // Save text input as file
        File textFile = new File(textDir, formattedDate + ".txt");
        try (PrintWriter writer = new PrintWriter(textFile)) {
            writer.write(text);
            System.out.println("텍스트가 저장되었습니다: " + textFile.getAbsolutePath());
        } catch (IOException ex) {
            System.err.println("텍스트 저장에 실패했습니다.");
        }
    }
}
