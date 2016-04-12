/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import roguelike_game.Game;
import roguelike_game.entity.item.Item;

/**
 *
 * @author andyafw
 */
public class ItemPickupListener implements ActionListener {
    Game game;
    private int x, y;
    
    public ItemPickupListener(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String mes = ae.getActionCommand();
        if(mes.equals("Add to Inventory")) {
            Item i = game.tilemap.items[y][x];
            if(game.player.getInventory().addItem(i)) {
            	game.tilemap.items[y][x] = null;
            }
            game.inventorypane.repaint();
        }
        if(mes.equals("Equip Item")) {
            Item i = game.tilemap.items[y][x];
            if(game.player.getInventory().addEquip(i)) {
            	game.tilemap.items[y][x] = null;
            }
            game.inventorypane.repaint();
        }
    }
}
