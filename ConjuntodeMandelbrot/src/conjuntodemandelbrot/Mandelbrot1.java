package conjuntodemandelbrot;

import java.awt.Color;
import java.awt.Graphics;

public class Mandelbrot1 extends javax.swing.JFrame {

    public Mandelbrot1() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conjunto de Mandelbrot");

        jButton1.setText("Dibujar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelMouseReleased(evt);
            }
        });
        panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 260, Short.MAX_VALUE)
        );

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addContainerGap(256, Short.MAX_VALUE))
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap())
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        pintaMandelbrot();
    }


    int cx1, cy1;
    int cx2, cy2;

    private void panelMousePressed(java.awt.event.MouseEvent evt) {
        cx1=cx2=evt.getX();
        cy1=cy2=evt.getY();
    }


    private void panelMouseReleased(java.awt.event.MouseEvent evt) {
        cx2=evt.getX();
        cy2=evt.getY();

        x1 = cx1*(x2 - x1)/panel.getWidth() + x1;
        y1 = y1 - cy1*(y1 - y2)/panel.getHeight();

        x2 = cx2*(x2 - x1)/panel.getWidth() + x1;
        y2 = y1 - cy2*(y1 - y2)/panel.getHeight();

        pintaMandelbrot();
    }//GEN-LAST:event_panelMouseReleased

    private void panelMouseDragged(java.awt.event.MouseEvent evt) {
        Graphics g = panel.getGraphics();

        g.setXORMode(Color.RED);

        g.drawRect(cx1, cy1, Math.abs(cx2 - cx1), Math.abs(cy2 - cy1));

        cx2=evt.getX();
        cy2=evt.getY();

        g.drawRect(cx1, cy1, Math.abs(cx2 - cx1), Math.abs(cy2 - cy1));
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        x1 = -2;
        y1 =  1;
        x2 =  1;
        y2 = -1;

        pintaMandelbrot();
    }


    public static void dibujaMandelbrot(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mandelbrot1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mandelbrot1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mandelbrot1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mandelbrot1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Mandelbrot1().setVisible(true);
            }
        });
    }


    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel panel;


    private int mandelbrot(double x, double y){
        double zn1r=0, zn1i=0;
        double zn2r=0, zn2i=0;
        int contador=0;

        while(contador<300 &&  (zn2r*zn2r + zn2i*zn2i) < 10000 ){
            zn2r = zn1r*zn1r - zn1i*zn1i + x;
            zn2i = 2*zn1r*zn1i + y;

            zn1r = zn2r;
            zn1i = zn2i;

            contador++;
        }

        return contador;
    }


    double x1 = -2;
    double y1 =  1;
    double x2 =  1;
    double y2 = -1;


    private void pintaMandelbrot() {
        Graphics g = panel.getGraphics();
        double x=0, y=0;
        int w = panel.getWidth();
        int h = panel.getHeight();

        for(int i=0; i<panel.getWidth(); i++){
            for(int j=0; j<panel.getHeight(); j++){

                x =  i * (x2 - x1)/w + x1;
                y = y1 - j * (y1 - y2)/h;

                int velocidad = mandelbrot(x, y);

                g.setColor(Color.getHSBColor((velocidad)/(float)160, 1, 1));
                g.drawRect(i, j, 1, 1);
            }
        }
    }
}