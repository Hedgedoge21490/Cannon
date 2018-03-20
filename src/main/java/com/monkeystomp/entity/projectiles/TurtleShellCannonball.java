/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.entity.projectiles;

import main.java.com.monkeystomp.graphics.Sprite;

/**
 *
 * @author aaron
 */
public class TurtleShellCannonball extends Projectile {
    
    public TurtleShellCannonball() {
        sprite = Sprite.turtle_cannonball;
        damage = Projectile.TURTLESHELLCANNONBALL_DAMAGE;
        areaOfEffect = Projectile.TURTLESHELLCANNONBALL_AREA;
        particleAmount = 80;
        type = "turtle";
    }
}
