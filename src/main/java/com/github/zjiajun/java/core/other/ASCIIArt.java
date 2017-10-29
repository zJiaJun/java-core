package com.github.zjiajun.java.core.other;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zhujiajun
 * @since 2017/10/29
 */
public class ASCIIArt {

    public static void main(String[] args) throws IOException {

        int width = 100;
        int height = 30;

        //BufferedImage image = ImageIO.read(new File("/users/zhujiajun/ascii-art.png"));

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 24));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("zjiajun", 10, 20);

        //save this image
        //ImageIO.write(image, "png", new File("/users/zhujiajun/ascii-art.png"));

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");
            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }

    }
}
