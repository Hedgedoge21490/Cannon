/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.entity.building;

import main.java.com.monkeystomp.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class Brick5Story extends Building {
    
    public Brick5Story(int x, int y) {
        this.x = x;
        this.y = y;
        hitPointsMax = 2500;
        hitPoints = hitPointsMax;
        points = 2000;
        multiplier = 3;
        multiplierTimer = 300;
        sprite = Sprite.brick_5story_building;
        leftEdge = x - (sprite.getWidth() / 2);
        rightEdge = x + (sprite.getWidth()) / 2;
        topEdge = y - (sprite.getHeight() / 2);
        bottomEdge = y + (sprite.getHeight() / 2);
    }
    
    @Override
    public void damageBuilding(int damage) {
        hitPoints -= damage;
        if (hitPoints <= 0) {
            level.increaseScore(points);
            level.addMultiplier(multiplier);
            multiplierActive = true;
        }
    }
    
    @Override
    public void update() {
        if (hitPoints < hitPointsMax && hitPoints > hitPointsMax * .6) sprite = Sprite.brick_5Stroy_building_low_damage;
        else if (hitPoints < hitPointsMax * .6 && hitPoints > hitPointsMax * .3) sprite = Sprite.brick_5Stroy_building_medium_damage;
        else if (hitPoints < hitPointsMax * .3 && hitPoints > 0) sprite = Sprite.brick_5Stroy_building_high_damage;
        else if (hitPoints <= 0) sprite = Sprite.brick_5story_building_destroyed;
        if (multiplierActive) {
            multiplierTimer--;
            if (multiplierTimer == 0) {
                level.subtractMultiplier(multiplier);
                multiplierActive = false;
            }
        }
    }
    
}
