/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.entity.particle;

import com.monkeystomp.entity.Entity;
import com.monkeystomp.graphics.Screen;
import com.monkeystomp.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class Particle extends Entity {
    
    private double xCoordinate, yCoordinate;
    private double startingX, startingY;
    private double force;
    // The angle in radians the particle is on.
    private double angle;
    private int color;
    private double anim = 0.0;
    private Sprite sprite;
    private int groundLevel;
    private double lastY;
    private boolean movingDown = false;
    
    public Particle(double x, double y, double force, double angle, int color) {
        this.xCoordinate = startingX = x;
        this.yCoordinate = startingY = y;
        this.force = force;
        this.angle = Math.toRadians(angle);
        this.color = color;
        sprite = new Sprite(2, 2, color);
        groundLevel = (int)(startingY + (random.nextInt(20) - 15));
    }
    
    public void update() {
        if (movingDown && yCoordinate > groundLevel) remove();
        else {
            if (movingDown && yCoordinate > groundLevel - 12) {
                if (level.damageMob((int) xCoordinate, (int) yCoordinate, 5)) remove();
            }
            lastY = yCoordinate;
            xCoordinate = ((anim / 15) * (force * Math.cos(angle)) + startingX);
            yCoordinate = (16 * Math.pow((anim / 15), 2.0)) - ((anim / 15) * (force * Math.sin(angle))) + startingY;
            anim++;
            if (lastY < yCoordinate) movingDown = true;
        }
    }
    
    public void render(Screen screen) {
        screen.renderSprite((int) xCoordinate, (int) yCoordinate, sprite);
    }
}
