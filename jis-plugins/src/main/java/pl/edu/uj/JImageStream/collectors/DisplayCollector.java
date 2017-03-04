package pl.edu.uj.JImageStream.collectors;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import pl.edu.uj.JImageStream.api.core.Collector;

public class DisplayCollector implements Collector<BufferedImage> {

    @Override
    public BufferedImage collect(BufferedImage bufferedImage) {
        new Thread(() -> {
            JFrame frame = new JFrame();
            frame.getContentPane().setLayout(new FlowLayout());
            frame.getContentPane().add(new JLabel(new ImageIcon(bufferedImage)));
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }).start();
        return bufferedImage;
    }

}
