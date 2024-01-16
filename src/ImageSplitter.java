import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ImageSplitter extends JFrame {

    private JLabel imageLabel;

    public ImageSplitter() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 이미지를 JLabel에 설정 (이미지 파일 경로에 맞게 수정)
        ImageIcon imageIcon = new ImageIcon("C:/Users/ghddm/Desktop/코딩캠프/Images/part1.png");
        imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // 버튼을 눌렀을 때 이미지 쪼개기
        JButton splitButton = new JButton("이미지 쪼개기");
        splitButton.addActionListener(e -> splitAndSaveImages());
        add(splitButton, BorderLayout.SOUTH);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void splitAndSaveImages() {
        Icon icon = imageLabel.getIcon();
        if (icon instanceof ImageIcon) {
            BufferedImage originalImage = convertImageIconToBufferedImage((ImageIcon) icon);

            int tileWidth = originalImage.getWidth() / 4;
            int tileHeight = originalImage.getHeight() / 4;

            // 이미지를 4x4로 나누어 저장
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    BufferedImage subImage = originalImage.getSubimage(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                    saveImage(subImage, "output/image_part_" + i + "_" + j + ".png");
                }
            }

            System.out.println("이미지가 성공적으로 분할되어 저장되었습니다.");
        }
    }

    private BufferedImage convertImageIconToBufferedImage(ImageIcon icon) {
        Image image = icon.getImage();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }

    private void saveImage(BufferedImage image, String outputPath) {
        try {
            ImageIO.write(image, "png", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImageSplitter());
    }
}
