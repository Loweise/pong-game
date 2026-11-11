package mx.unison.pong;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class Paddle {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private static final int SCREEN_WIDTH = 640;
    private static final double SPEED = 5.0;
    private final int[] xPositions = { 0,80,160,240,320,400,480,560};
    private final Rectangle2D.Double paddle;
    private int pos ;
    private double dx;

    public Paddle() {
        super();
        double initialX = (SCREEN_WIDTH - WIDTH) / 2.0;
        pos = 4; // raqueta inicia al centro
        paddle = new Rectangle2D.Double(xPositions[pos],450,WIDTH,HEIGHT);
    }

    public synchronized void setDx(double dx) {
        this.dx = dx;
    }
    
    public synchronized void moveRight() {
        setDx(SPEED);
    }

    public synchronized void moveLeft() {
        setDx(-SPEED);
    }
    
    public synchronized void stop() {
        setDx(0);
    }
    
    public synchronized void update() {
        paddle.x += dx;
        
        if (paddle.x < 0) {
            paddle.x = 0;
        } else if (paddle.x + WIDTH > SCREEN_WIDTH ) {
            paddle.x = SCREEN_WIDTH - WIDTH;
        }
    }

    // regresa el rectangulo que se va a dibujar
    public Rectangle2D.Double getPaddle() {
        return paddle;
    }

    // verifica si la pelota toco la raqueta
    public boolean check( int x, int y) {
        return paddle.contains(x,y);
    }

    // verifica si la pelota toco la raqueta
    public boolean intersects(RectangularShape shape) {
        boolean result;

        result = paddle.intersects( shape.getBounds2D() );

        return result;
    }
}
