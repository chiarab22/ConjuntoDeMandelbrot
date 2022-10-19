package  conjuntodemandelbrot;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;


public class Mandelbrot extends JPanel {

    public static void dibujarMandelbrot(String[] args) {
        JFrame ventana = new JFrame("Conjunto de mandelbrot");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.getContentPane().add(new Mandelbrot());
        ventana.setSize(640, 640);
        ventana.setLocation(200, 50);
        ventana.setVisible(true);
        JButton boton = new JButton();
    }

    public void paint(Graphics g) {
        int i, j, n, h, w;
        double x, y, cx, cy, xt, yt;
        super.paintComponent(g);
        setBackground(Color.black);
        h = getHeight() / 2;
        w = getWidth() / 2;
        for (i = 0; i < getHeight(); i++) {
            for (j = 0; j < getWidth(); j++) {
                cx = (j - w) / 240.0 - 0.4;
                cy = (i - h) / 240.0;
                x = 0;
                y = 0;
                n = 0;
                while (n < 64 && x * x + y * y < 4) {
                    xt = x * x - y * y + cx;
                    yt = 2 * x * y + cy;
                    x = xt;
                    y = yt;
                    ++n;
                }
                if (n < 64) {
                    g.setColor(new Color(255 - (n * 16) % 256, 0, n * 2));
                    g.fillRect(j, i, 1, 1);
                }
            }
        }
    }
}
