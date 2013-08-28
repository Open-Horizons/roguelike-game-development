/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.transport;

/**
 *
 * @author andyafw
 */
public class Transport {
    public static Transport CAR = new Transport(2);
    public static Transport WALK = new Transport(1);
    
    private int speed = 0;
    
    public Transport(int speed) {
        this.speed = speed;
    }
    
    public int getSpeed() {
        return speed;
    }
}
