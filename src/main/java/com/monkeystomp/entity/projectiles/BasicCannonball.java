/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.entity.projectiles;

import main.java.com.monkeystomp.graphics.Screen;
import main.java.com.monkeystomp.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class BasicCannonball extends Projectile {
    
    public BasicCannonball() {
        sprite = Sprite.basic_cannonball;
        damage = Projectile.BASICCANNONBALL_DAMAGE;
        areaOfEffect = Projectile.BASICCANNONBALL_AREA;
        particleAmount = 50;
        type = "basic";
    }
}
