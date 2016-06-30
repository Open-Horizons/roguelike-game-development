/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.events;

import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import roguelike_game.Game;
import roguelike_game.entity.item.Item;

/**
 *
 * @author andyafw
 */
public class ItemPopupMenu extends JPopupMenu {
    public ItemPopupMenu(Game game, int x, int y) {
        Color foreground = Color.green;
        Color background = Color.black;
        
        setForeground(foreground);
        setBackground(background);
        
        JMenuItem item;
        add(item = new JMenuItem("Add to Inventory"));
        item.setForeground(foreground);
        item.setBackground(background);
        item.addActionListener(new ItemPickupListener(game, x, y));
        add(item = new JMenuItem("Equip Item"));
        if(game.tilemap.items[y][x].getType() != Item.Type.SWORD) {
            item.setEnabled(false);
        }
        item.setForeground(foreground);
        item.setBackground(background);
        item.addActionListener(new ItemPickupListener(game, x, y));
        add(item = new JMenuItem("Exit Menu"));
        item.setForeground(foreground);
        item.setBackground(background);
        item.addActionListener(new ItemPickupListener(game, x, y));
    }
}
