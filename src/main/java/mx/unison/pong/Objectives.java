package mx.unison.pong;

import java.awt.geom.Rectangle2D;
import static mx.unison.pong.Paddle.HEIGHT;
import static mx.unison.pong.Paddle.WIDTH;

/**
 *
 * @author Zahid
 */
public class Objectives {
    private final Rectangle2D.Double objective;
    
    public Objectives() {
        objective = new Rectangle2D.Double(20, 20, 20, 20);
    }
}
