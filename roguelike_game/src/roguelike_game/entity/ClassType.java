/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.entity;

/**
 *
 * @author andyafw
 */
public class ClassType {
    public static ClassType WARRIOR     = new ClassType(10, 5);
    public static ClassType ASSASSIN    = new ClassType(10, 10);
    public static ClassType MERCENARY   = new ClassType(5, 5);
    public static ClassType ROGUE       = new ClassType(5, 5);
    public static ClassType PALADIN     = new ClassType(5, 5);
    public static ClassType BERSERKER   = new ClassType(5, 5);
    public static ClassType CLERIC      = new ClassType(5, 5);
    public static ClassType DRUID       = new ClassType(5, 5);
    public static ClassType PRIESTIST   = new ClassType(5, 5);
    public static ClassType WITCH       = new ClassType(5, 5);
    public static ClassType NECROMANCER = new ClassType(5, 5);
    
    public int defense = 0;
    public int attack = 0;
    
    public ClassType(int def, int att) {
        defense = def;
        attack = att;
    }
}
