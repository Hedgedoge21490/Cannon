/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.entity.projectiles;

import main.java.com.monkeystomp.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class MasterCannonball extends Projectile {
    
    public MasterCannonball() {
        sprite = Sprite.master_cannonball;
        damage = Projectile.MASTERCANNONBALL_DAMAGE;
        areaOfEffect = Projectile.MASTERCANNONBALL_AREA;
        particleAmount = 100;
        type = "master";
    }
}
