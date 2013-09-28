/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import roguelike_game.Roguelike_game;
import roguelike_game.entity.item.Item;

/**
 *
 * @author andyafw
 */
public class ItemPickupListener implements ActionListener {
    Roguelike_game game;
    private int x, y;
    
    public ItemPickupListener(Roguelike_game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String mes = ae.getActionCommand();
        if(mes.equals("Add to Inventory")) {
            Item i = game.tilemap.items[y][x];
            game.tilemap.items[y][x] = null;
            game.player.addItem(i);
            game.inventorypane.repaint();
        }
        if(mes.equals("Equip Item")) {
            Item i = game.tilemap.items[y][x];
            game.tilemap.items[y][x] = null;
            game.player.addEquip(i);
            game.inventorypane.repaint();
        }
    }
  
    
}
