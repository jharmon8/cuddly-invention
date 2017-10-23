package driver;

import panel.ChatPanel;
import panel.GamePanel;
import panel.GreenPanel;
import panel.InventoryPanel;
import panel.Panel;
import panel.RedPanel;
import panel.ScrollGamePanel;
import util.ControlUtil;
import util.ResourceUtil;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class EngineDriver {
    public static boolean squarePixels = true;

    public static void main(String[] args) {
        System.out.println("init");
        ResourceUtil.init();
        System.out.println("test");

        ArrayList<Panel> panels = new ArrayList<>();

        panels.add(new ScrollGamePanel(0, 0));
        panels.add(new ChatPanel(0, 6*32));
        panels.add(new InventoryPanel(384, 88));

        JFrame frame = new JFrame("string");
        frame.setUndecorated(true);
        JPanel canvas = new Canvas(panels);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private static class Canvas extends JPanel implements KeyListener, MouseListener {

        private ArrayList<Panel> panels;
        private int width;
        private int height;

        Rectangle screen;
        int squareWidth, squareHeight;

        int offsetx, offsety;

        int size;

        public Canvas(ArrayList<Panel> panels) {

            Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {
                ArrayList<Panel> timerPanels = panels;

                @Override
                public void run() {
                    for(Panel p : timerPanels) {
                        p.update();
                    }

                    repaint();
                }
            }, 100, 100);

            this.panels = panels;

            int[] bounds = getNecessaryBounds(panels);
            this.width = bounds[0];
            this.height = bounds[1];

            this.addKeyListener(this);
            this.addMouseListener(this);

            this.setFocusable(true);
            this.requestFocus();
        }

        public void paint(Graphics g) {

            screen = getBounds();

            squareWidth = screen.width/width;
            squareHeight = screen.height/height;

            offsetx = 0;
            offsety = 0;

            if(squarePixels) {
                size = Math.min(squareWidth, squareHeight);

                squareHeight = size;
                squareWidth = size;

                // puts black bars
                offsetx = (screen.width - width*size) / 2;
                offsety = (screen.height - height*size) / 2;
            }

            g.setColor(Color.darkGray);
            g.fillRect(0, 0, screen.width, screen.height);

            for(Panel p : panels) {
                BufferedImage img = p.draw();

                g.drawImage(
                        img,
                        p.getMinX()*squareWidth + offsetx,
                        p.getMinY()*squareHeight + offsety,
                        img.getWidth()*squareWidth,
                        img.getHeight()*squareHeight,
                        this
                );
            }
        }

        private static int[] getNecessaryBounds(ArrayList<Panel> panels) {
            int maxX = 0;
            int maxY = 0;

            for(Panel p : panels) {
                int x = p.getMinX()+p.getWidth();
                int y = p.getMinY()+p.getHeight();

                if(x > maxX) {
                    maxX = x;
                }
                if(y > maxY) {
                    maxY = y;
                }
            }

            int[] output = new int[2];

//            System.out.println(maxX + ":" + maxY);

            output[0] = maxX;
            output[1] = maxY;

            return output;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            for(Panel p : panels) {
                p.keyType(e);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            ControlUtil.keyPress(e);

            for(Panel p : panels) {
                p.keyPress(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            ControlUtil.keyRelease(e);

            for(Panel p : panels) {
                p.keyRelease(e);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            for(Panel p : panels) {
                p.mousePress((e.getX() - offsetx) / squareWidth, (e.getY() - offsety) / squareHeight);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
