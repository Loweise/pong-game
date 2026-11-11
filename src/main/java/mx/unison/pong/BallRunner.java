package mx.unison.pong;

import java.awt.geom.Ellipse2D;

public class BallRunner implements Runnable {

    public static final int MAX_X = 640;
    public static final int MAX_Y = 480;
    public static final int SIGN = -1;

    public static int DX = 5;
    public static int DY = 5;
    private final  Ellipse2D.Double ball;

    private final Paddle paddle;

    private int ballX;
    private int ballY;

    private boolean isPaused ;
    public boolean itsOver = false;
    public boolean aKeyIsPressed = false;
    private boolean stop = false ;

    public BallRunner( Ellipse2D.Double shape, Paddle p) {
        super();
        ball =  shape;
        paddle = p;
        ballX = 320;
        ballY = 240;
        ball.x = ballX ;
        ball.y = ballY;
        isPaused = false;
    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }
    public void speedRestart() {
        DX = 5;
        DY = 5;
    }

    @Override
    public void run() {
        int directionY = 1;
        int directionX = 0;

        while( !stop ) {

            if( isPaused ) {
               Thread.yield();
               continue;
            }

           // int y = (int) ball.getMaxY();
           // int x = (int)  ball.getX();

            if( paddle.intersects(ball) ) {
                DX++;
                DY++;
                if (directionX == 0) {
                directionX = Math.random() > 0.5 ? 1 : -1; 
            }
                
                directionY = directionY * SIGN;
               // sX = sX * SIGN;
                ballX = ballX + (DX * directionX) ;
                ballY = ballY + (DY * directionY);
                ball.x = ballX ;
                ball.y = ballY;
                continue;
            }

            if( ball.getMinY() < 0 ) {
                directionY = directionY * SIGN;
            }

            if( ball.getMinX() < 0 ) {
                directionX = directionX * SIGN;
            }
            if( ball.getMaxY() > MAX_Y ) {
                itsOver = true;
                stop();
                
                
                continue;
            }

            if( ball.getMaxX() > MAX_X ) {
                directionX = directionX * SIGN;
            }
            ballX = ballX + (DX * directionX) ;
            ballY = ballY + (DY * directionY);
            ball.x = ballX ;
            ball.y = ballY;
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        stop = true;
    }

}
