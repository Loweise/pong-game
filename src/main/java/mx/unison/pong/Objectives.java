package mx.unison.pong;

import java.awt.geom.Rectangle2D;
import static mx.unison.pong.Paddle.HEIGHT;
import static mx.unison.pong.Paddle.WIDTH;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Zahid
 */
public class Objectives {
    
    public int cantBricks =0;
    private ArrayList<Rectangle2D.Double> bricks;

    public Objectives() {
        initBricks();
    }

    // Inicializa todos los bloques
    public void initBricks() {
        bricks = new ArrayList<>();
        int rows = 5;
        int cols = 7;
        int brickWidth = 80;
        int brickHeight = 20;
        int padding = 10;
        int startX = 10;
        int startY = 40;
        int multiSize = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double bx = startX + j * (brickWidth + padding);
                double by = startY + i * (brickHeight + padding);
                bricks.add(new Rectangle2D.Double(bx*multiSize, by, brickWidth*multiSize, brickHeight));
                cantBricks++;
            }
            multiSize++;
        }
    }

    // Dibuja los bloques en la pantalla
    public void draw(Graphics2D g) {
        for (Rectangle2D.Double brick : bricks) {
            g.setColor(Color.GREEN);
            g.fill(brick);
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(2.0f));
            g.draw(brick);
        }
    }

    
    public int checkCollision(Ellipse2D.Double ball) {
        Iterator<Rectangle2D.Double> it = bricks.iterator();
        while (it.hasNext()) {
            Rectangle2D.Double brick = it.next();
            if (brick.intersects(ball.getBounds2D())) {
                Rectangle2D intersection = brick.createIntersection(ball.getBounds2D());
                it.remove(); 
                cantBricks--;
                if (intersection.getWidth() >= intersection.getHeight()) {
                return 1;
            } else {
                return 2; 
            }
            }
        }
        return 0; 
    }
}
