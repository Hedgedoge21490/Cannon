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
public class WindupCannonball extends Projectile {
    
    public WindupCannonball() {
        sprite = Sprite.windup_cannonball;
        damage = Projectile.WINDUPCANNONBALL_DAMAGE;
        areaOfEffect = Projectile.WINDUPCANNONBALL_AREA;
        particleAmount = 130;
        type = "windup";
    }
}
