package ProyectoU2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Ventana extends JPanel{

    // ventana
    private JFrame ventana;
    // contenedor
    private Container contenedor;
    //Label
    private JLabel etiq;
    //Menus
    private JMenuBar MenuBar;
    private JMenu menu1, menu2, menu3,
                  menu4, menu5, menu6, 
                  menu7, info;
    //Botones
    private JButton botSubir, botBajar,
            botDerecha, botIzquierda,
            botEscalar, botRP, botRN,
            botRX, botRY, botRXY, botFO;
    //Panel
    JPanel panelbots;
    // declarar la figura
    private Punto[] figura;

    public Ventana(String titulo, Punto[] figura) {
        // inicializar la ventana
        ventana = new JFrame("");
        // definir tamaño a ventana
        ventana.setSize(1000, 800);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(true);
        ventana.setLocationRelativeTo(null);

        contenedor = ventana.getContentPane();
        contenedor.setSize(1000, 800);
        // agregar la ventana en el contenedor
        contenedor.add(this, BorderLayout.CENTER);

        panelbots = new JPanel();
        panelbots.setBackground(Color.GRAY);
        panelbots.setLayout(new BoxLayout(panelbots, BoxLayout.PAGE_AXIS));        
        //inicializar label y añadirlo
        etiq = new JLabel("Informacion", SwingConstants.CENTER);
        contenedor.add(etiq, BorderLayout.SOUTH);
        contenedor.add(panelbots, BorderLayout.WEST);

        //Inicializar Menu y añadirlo
        MenuBar = new JMenuBar();
        menu1 = new JMenu("Escalar");
        menu2 = new JMenu("Rotacion +");
        menu3 = new JMenu("Rotacion -");
        menu4 = new JMenu("Reflexion X");
        menu5 = new JMenu("Reflexion Y");
        menu6 = new JMenu("Reflexion XY");
        menu7 = new JMenu("Restaurar figura");
        info = new JMenu("AYUDA");
        MenuBar.add(menu1);
        MenuBar.add(menu2);
        MenuBar.add(menu3);
        MenuBar.add(menu4);
        MenuBar.add(menu5);
        MenuBar.add(menu6);
        MenuBar.add(menu7);
        MenuBar.add(info);
        ventana.setJMenuBar(MenuBar);

        //Inicializar boton y añadirlo
        botSubir = new JButton("Subir");
        botBajar = new JButton("Bajar");
        botDerecha = new JButton("Derecha");
        botIzquierda = new JButton("Izquierda");
        botEscalar = new JButton("Escalar");
        botRP = new JButton("Rotacion +");
        botRN = new JButton("Rotacion -");
        botRX = new JButton("Reflexion X");
        botRY = new JButton("Reflexion Y");
        botRXY = new JButton("Reflexion XY");
        botFO = new JButton("Restaurar figura");

        panelbots.add(botSubir);
        panelbots.add(botBajar);
        panelbots.add(botIzquierda);
        panelbots.add(botDerecha);
        panelbots.add(botEscalar);
        panelbots.add(botRP);
        panelbots.add(botRN);
        panelbots.add(botRX);
        panelbots.add(botRY);
        panelbots.add(botRXY);
        panelbots.add(botFO);

        // definiendo la figura
        this.figura = figura;

        //Adaptando solo los eventos requeridos con MouseAdapter
        MouseAdapter ma = new MouseAdapter() {
            @Override
    public void mouseClicked(MouseEvent e) {
        double x, y, ang;
        if (e.getSource().equals(menu1)) {
            x = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor de x"));
            y = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor de y"));
            escalar(x, y);
            repaint();
        }
        if (e.getSource().equals(menu2)) {
            ang = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor del angulo"));
            rotacionP(ang);
            repaint();
        }
        if (e.getSource().equals(menu3)) {
            ang = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor del angulo"));
            rotacionN(ang);
            repaint();
        }
        if (e.getSource().equals(menu4)) {
            reflexionX();
            repaint();
        }
        if (e.getSource().equals(menu5)) {
            reflexionY();
            repaint();
        }
        if (e.getSource().equals(menu6)) {
            reflexionXY();
            repaint();
        }
        if (e.getSource().equals(menu7)) {
            FigOriginal();
        }
        if (e.getButton()==2) {
            FigOriginal();
        }
        if (e.getSource().equals(info)) {
            JOptionPane.showMessageDialog(null, 
                    "Funciones de teclas: Presiona\n"
                  + "\n↑ (flecha arriba): Para mover la figura hacia arriba"
                  + "\n↓ (flecha abajo): Para mover la figura hacia abajo"
                  + "\n← (flecha izquierda): Para mover la figura hacia la izquierda"
                  + "\n→ (flecha derecha): Para mover la figura hacia la derecha"
                  + "\ne o E: Para escalar la figura en un 50%"
                  + "\nx o X: Para reflejar la figura en el eje x" 
                  + "\ny o Y: Para reflejar la figura en el eje y" 
                  + "\nz o Z: Para reflejar la figura en los ejes XY" 
                  + "\nr o R: Para restaurar la figura original", 
                    "Menu de ayuda", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton()==1) {
            traslacion(-2, 0);
            repaint();
        }
        if (e.getButton()==3) {
            traslacion(2, 0);
            repaint();
        }
        if (e.getSource().equals(botSubir)) {            
            traslacion(0, -10);
            repaint();
        }
        if (e.getSource().equals(botBajar)) {            
            traslacion(0, 10);
            repaint();
        }
        if (e.getSource().equals(botDerecha)) {            
            traslacion(10, 0);
            repaint();
        }
        if (e.getSource().equals(botIzquierda)) {            
            traslacion(-10, 0);
            repaint();
        }
        if (e.getSource().equals(botEscalar)) {            
            escalar(.9, .9);
            repaint();
        }
        if (e.getSource().equals(botRP)) {            
            rotacionP(5);
            repaint();
        }
        if (e.getSource().equals(botRN)) {            
            rotacionN(5);
            repaint();
        }
        if (e.getSource().equals(botRX)) {            
            reflexionX();
            repaint();
        }
        if (e.getSource().equals(botRY)) {            
            reflexionY();
            repaint();
        }
        if (e.getSource().equals(botRXY)) {            
            reflexionXY();
            repaint();
        }
        if (e.getSource().equals(botFO)) {
            FigOriginal();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        etiq.setText("X:" + e.getX() + " Y:" + e.getY());        
    }              
};
        KeyListener ke = new KeyListener() {                        
        @Override
        public void keyTyped(KeyEvent ke) {
        if (ke.getKeyChar() == '+') {
            rotacionP(2);
            repaint();
        }
        if (ke.getKeyChar() == '-') {
            rotacionN(2);
            repaint();
        }
        if (ke.getKeyChar() == 'x' || ke.getKeyChar() == 'X') {
            reflexionX();
            repaint();
        }
        if (ke.getKeyChar() == 'y' || ke.getKeyChar() == 'Y') {
            reflexionY();
            repaint();
        }
        if (ke.getKeyChar() == 'z' || ke.getKeyChar() == 'Z') {
            reflexionXY();
            repaint();
        }
                if (ke.getKeyChar() == 'e' || ke.getKeyChar() == 'E') {
            escalar(.5, .5);
            repaint();
        }
        if (ke.getKeyChar() == 'r' || ke.getKeyChar() == 'R') {
            FigOriginal();            
        }
    }
            @Override
            public void keyPressed(KeyEvent ke) {
        switch (ke.getExtendedKeyCode()) {
            case KeyEvent.VK_UP:
                traslacion(0, -10);
                repaint();                
                break;
            case KeyEvent.VK_DOWN:                
                traslacion(0, 10);
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                traslacion(10, 0);
                repaint();
                break;
            case KeyEvent.VK_LEFT:
                traslacion(-10, 0);
                repaint();
                break;
            default:            
        }        
    }

            @Override
            public void keyReleased(KeyEvent ke) {
                System.out.println("");
            }
        };
        //Añadiendo eventos a la ventana
        ventana.addMouseListener(ma);
        ventana.addMouseMotionListener(ma);
        botSubir.addMouseListener(ma);
        botBajar.addMouseListener(ma);
        botDerecha.addMouseListener(ma);
        botIzquierda.addMouseListener(ma);
        botEscalar.addMouseListener(ma);
        botRP.addMouseListener(ma);
        botRN.addMouseListener(ma);
        botRX.addMouseListener(ma);
        botRY.addMouseListener(ma);
        botRXY.addMouseListener(ma);
        botFO.addMouseListener(ma);
        menu1.addMouseListener(ma);
        menu2.addMouseListener(ma);
        menu3.addMouseListener(ma);
        menu4.addMouseListener(ma);
        menu5.addMouseListener(ma);
        menu6.addMouseListener(ma);
        menu7.addMouseListener(ma);
        info.addMouseListener(ma);        
        ventana.addKeyListener(ke);
        ventana.setFocusable(true);                                
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        //dibujar figura 
        dibujar(g);
    }

    private void escalar(double fx, double fy) {
        int tx = figura[0].GetX();
        int ty = figura[0].GetY();
        for (Punto punto : figura) {
            //nueva x: ((a - tx) * fx) + tx
            //nueva y: ((a - ty) * fy) + ty
            punto.SetX((int) ((punto.GetX() - tx) * fx + tx));
            punto.SetY((int) ((punto.GetY() - ty) * fy + ty));
        }
    }

    private void traslacion(double xf, double yf) {
        for (Punto punto : figura) {
            punto.SetX((int) (punto.GetX() + xf));
            punto.SetY((int) (punto.GetY() + yf));
        }
    }

    private void rotacionP(double angulo) {
        int tx = figura[0].GetX(),
                ty = figura[0].GetY();
        for (Punto punto : figura) {
            //punto x: ()
            punto.SetX((int) ((punto.GetX() - tx) * Math.cos(Math.toRadians(angulo)) - (punto.GetY() - ty)
                    * Math.sin(Math.toRadians(angulo)) + tx));
            //punto y
            punto.SetY((int) ((punto.GetX() - ty) * Math.sin(Math.toRadians(angulo)) + (punto.GetY() - ty)
                    * Math.cos(Math.toRadians(angulo)) + ty));
        }
    }

    private void rotacionN(double angulo) {
        int tx = figura[0].GetX(),
                ty = figura[0].GetY();
        for (Punto punto : figura) {
            punto.SetX((int) ((punto.GetX() - tx) * Math.cos(Math.toRadians(angulo)) + (punto.GetY() - ty)
                    * Math.sin(Math.toRadians(angulo)) + tx));
            punto.SetY((int) ((-1) * (punto.GetX() - tx) * Math.sin(Math.toRadians(angulo)) + (punto.GetY() - ty)
                    * Math.cos(Math.toRadians(angulo)) + ty));
        }
    }

    private void reflexionX() {
        int tx = figura[0].GetX();
        int ty = figura[0].GetY();
        for (Punto punto : figura) {
            punto.SetX(-(punto.GetX() - tx) + tx);
            punto.SetY((punto.GetY() - ty) + ty);
        }
    }

    private void reflexionY() {
        int tx = figura[0].GetX();
        int ty = figura[0].GetY();
        for (Punto punto : figura) {
            punto.SetX((punto.GetX() - tx) + tx);
            punto.SetY(-(punto.GetY() - ty) + ty);
        }
    }

    private void reflexionXY() {
        int tx = figura[0].GetX();
        int ty = figura[0].GetY();
        for (Punto punto : figura) {
            punto.SetX(-(punto.GetX() - tx) + tx);
            punto.SetY(-(punto.GetY() - ty) + ty);
        }
    }

    private void dibujar(Graphics g) {
        for (int i = 0; i < figura.length - 1; i++) {
            g.drawLine(figura[i].GetX(),
                    figura[i].GetY(),
                    figura[i + 1].GetX(),
                    figura[i + 1].GetY());
        }
    }      

    public void setFigura(Punto[] figura) {
        this.figura = figura;
    }

    public void FigOriginal() {
        Punto figura[] = {
            new Punto(290, 230),
            new Punto(450, 150),
            new Punto(395, 175),
            new Punto(425, 130),
            new Punto(425, 130),
            new Punto(430, 95),
            new Punto(430, 95),
            new Punto(470, 100),
            new Punto(470, 100),
            new Punto(495, 60),
            new Punto(495, 60),
            new Punto(515, 45),
            new Punto(515, 45),
            new Punto(550, 55),
            new Punto(550, 55),
            new Punto(580, 30),
            new Punto(580, 30),
            new Punto(740, 130),
            new Punto(740, 130),
            new Punto(745, 160),
            new Punto(745, 160),
            new Punto(795, 290),
            new Punto(745, 160),
            new Punto(613, 162),
            new Punto(613, 162),
            new Punto(682, 95),
            new Punto(613, 162),
            new Punto(700, 212),
            new Punto(795, 290),
            new Punto(780, 315),
            new Punto(780, 315),
            new Punto(750, 420),
            new Punto(750, 420),
            new Punto(700, 212),
            new Punto(735, 360),
            new Punto(720, 415),
            new Punto(720, 415),
            new Punto(680, 385),
            new Punto(680, 385),
            new Punto(640, 460),
            new Punto(640, 460),
            new Punto(615, 440),
            new Punto(643, 396),
            new Punto(570, 510),
            new Punto(570, 510),
            new Punto(560, 430),
            new Punto(560, 430),
            new Punto(515, 540),
            new Punto(560, 430),
            new Punto(630, 290),
            new Punto(515, 540),
            new Punto(475, 515),
            new Punto(515, 540),
            new Punto(545, 390),
            new Punto(515, 540),
            new Punto(475, 420),
            new Punto(475, 420),
            new Punto(475, 630),
            new Punto(475, 630),
            new Punto(416, 448),
            new Punto(430, 490),
            new Punto(420, 555),
            new Punto(420, 555),
            new Punto(380, 465),
            new Punto(380, 465),
            new Punto(390, 435),
            new Punto(390, 435),
            new Punto(380, 400),
            new Punto(380, 400),
            new Punto(390, 380),
            new Punto(390, 380),
            new Punto(355, 390),
            new Punto(355, 390),
            new Punto(320, 345),
            new Punto(320, 345),
            new Punto(330, 315),
            new Punto(330, 315),
            new Punto(290, 230),
            new Punto(340, 240),
            new Punto(290, 230),
            new Punto(305, 260),
            new Punto(340, 240),
            new Punto(500, 115),
            new Punto(515, 45),
            new Punto(500, 115),
            new Punto(470, 100),
            new Punto(500, 115),
            new Punto(425, 130),
            new Punto(500, 115),
            new Punto(450, 150),
            new Punto(500, 115),
            new Punto(550, 55),
            new Punto(545, 107),
            new Punto(550, 55),
            new Punto(500, 115),
            new Punto(625, 95),
            new Punto(625, 95),
            new Punto(580, 30),
            new Punto(625, 95),
            new Punto(682, 95),
            new Punto(380, 465),
            new Punto(545, 388),
            new Punto(545, 388),
            new Punto(745, 160),
            new Punto(700, 212),
            new Punto(780, 315),
            new Punto(735, 360),
            new Punto(653, 265),
            new Punto(680, 385),
            new Punto(628, 293),
            new Punto(628, 293),
            new Punto(643, 396),
            new Punto(525, 135),
            new Punto(545, 108),
            new Punto(525, 135),
            new Punto(545, 170),
            new Punto(545, 170),
            new Punto(615, 162),
            new Punto(560, 253),
            new Punto(655, 265),
            new Punto(560, 253),
            new Punto(615, 162),
            new Punto(545, 388),
            new Punto(560, 253),
            new Punto(545, 170),
            new Punto(518, 245),
            new Punto(545, 170),
            new Punto(470, 177),
            new Punto(450, 150),
            new Punto(470, 177),
            new Punto(518, 245),
            new Punto(560, 252),
            new Punto(518, 245),
            new Punto(475, 420),
            new Punto(380, 400),
            new Punto(465, 375),
            new Punto(465, 375),
            new Punto(380, 465),
            new Punto(465, 375),
            new Punto(498, 325),
            new Punto(435, 206),
            new Punto(340, 240),
            new Punto(435, 206),
            new Punto(470, 175),
            new Punto(435, 206),
            new Punto(390, 380),
            new Punto(435, 206),
            new Punto(495, 192),
            new Punto(435, 206),
            new Punto(435, 275),
            new Punto(435, 275),
            new Punto(440, 315),
            new Punto(495, 192),
            new Punto(470, 175),
            new Punto(495, 230),
            new Punto(435, 240),
            new Punto(495, 230),
            new Punto(518, 245),
            new Punto(420, 265),
            new Punto(320, 345),
            new Punto(420, 265),
            new Punto(340, 240),
            new Punto(420, 265),
            new Punto(465, 375),
            new Punto(480, 215),
            new Punto(495, 230),
            new Punto(480, 215),
            new Punto(495, 190),
            new Punto(475, 275),
            new Punto(520, 245),
            new Punto(475, 275),
            new Punto(440, 310)
        };
        setFigura(figura);
        repaint();
    }
}