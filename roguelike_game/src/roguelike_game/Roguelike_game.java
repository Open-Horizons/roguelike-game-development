package roguelike_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Roguelike_game extends JFrame {
    public Painting painting;

    public Roguelike_game() {
        super("test");
        painting = new Painting(this);
        add(painting);
    }

    public static void main(String[] args) {
        Roguelike_game game = new Roguelike_game();
        game.setSize(1100, 600);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
        Thread thread = new Thread(game.painting);
        thread.start();
    }

    //blends two colors together by a centain percentage of famount
    public Color blend(Color clOne, Color clTwo, float fAmount) {
        float fInverse = (float) (1.0 - fAmount);
        
        float afOne[] = new float[3];
        clOne.getColorComponents(afOne);
        float afTwo[] = new float[3];
        clTwo.getColorComponents(afTwo);

        float afResult[] = new float[3];
        afResult[0] = afOne[0] * fAmount + afTwo[0] * fInverse;
        afResult[1] = afOne[1] * fAmount + afTwo[1] * fInverse;
        afResult[2] = afOne[2] * fAmount + afTwo[2] * fInverse;

        return new Color(afResult[0], afResult[1], afResult[2]);
    }

    private class Painting extends JPanel implements Runnable {

        Roguelike_game game;
        Color color;
        Color darker;
        int time = 0;

        public Painting(Roguelike_game game) {
            this.game = game;
            color = new Color(0, 255, 0);
            darker = blend(color, Color.black, 0.25f);
            this.setPreferredSize(new Dimension(1100, 600));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = 300;
            int x = 1100 / 2 - width / 2 - 50;
            int y = 600 / 2 - width / 2;

            g.setColor(Color.black);
            g.fillRect(0, 0, 1100, 600);
            g.setColor(Color.green);
            g.fillArc(x, y, width, width, time * 6, 6);
            g.setColor(darker);
            g.drawArc(x, y, width, width, 0, 360);
            
            
            Font f = new Font(Font.MONOSPACED, Font.BOLD, 100);
            
            g.setFont(f);
            g.drawString(Integer.toString(time * -1), x + width / 2 - 25, y + width / 2 + 80);
            
        }

        @Override
        public void run() {
            while (true) {
                try {
                    time--;
                    if (time > 60) {
                        time = 0;
                    }
                    repaint();
                    System.out.println("time = " + time);
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
