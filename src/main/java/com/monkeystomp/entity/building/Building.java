/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.entity.building;

import main.java.com.monkeystomp.entity.Entity;
import main.java.com.monkeystomp.graphics.Screen;
import main.java.com.monkeystomp.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public abstract class Building extends Entity {
    
    protected Sprite sprite;
    protected int points;
    protected int multiplier;
    protected boolean multiplierActive = false;
    protected int multiplierTimer;
    protected int leftEdge, rightEdge, topEdge, bottomEdge;
    protected int hitPoints, hitPointsMax;
    
    public boolean buildingHere(int x, int y) {
        return false;
    }
    
    public void damageBuilding(int damage) {
    }
    
    @Override
    public void render(Screen screen) {
        screen.renderSprite(leftEdge, topEdge, sprite);
    }
    
}
