/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.entity.projectiles;

import main.java.com.monkeystomp.entity.particle.Particle;
import main.java.com.monkeystomp.graphics.Screen;
import main.java.com.monkeystomp.graphics.Sprite;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineEvent;

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
    
    private double getRandomForce() {
        return (16 + (random.nextDouble() * 45));
    }
    
    private double getRandomAngle() {
        return (70 + (random.nextDouble() * 30));
    }
    
    private int getColor() {
        return 0x3401ff;
    }
    
    @Override
    public void update() {
        super.update();
        // Check to see if the cannonball has gone past the x point that was clicked
        if (xd >= endingX) {
            remove();
            // generate an array of particles new Particle(double startingX, double startingY, double force, double angle, int angle);
            for (int i = 0; i < particleAmount; i++) {
                level.addParticle(new Particle(endingX, endingY, getRandomForce(), getRandomAngle(), getColor()));
            }
            // play explosion sound!
            Thread audioClipThread = new Thread("Audio Clip") {
                public void run() {
                    try {
                        AudioInputStream ais = AudioSystem.getAudioInputStream(Projectile.class.getResource("/audio/sfx/explosions/master_explosion.wav"));
                        explosion = AudioSystem.getClip();
                        explosion.open(ais);
                        ais.close();
                        explosion.start();
                        explosion.addLineListener((LineEvent e) -> {
                            if (e.getType() == LineEvent.Type.STOP) {
                                e.getLine().close();
                            }
                        });
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            audioClipThread.start();
            // send damage information to the level
            level.damageBuilding(xCollision, yCollision, damage);
        }
        else {
            xd = ((anim / 15) * (force * Math.cos(angle)) + startingX);
            yd = (16 * Math.pow((anim / 15), 2.0)) - ((anim / 15) * (force * Math.sin(angle))) + startingY;
            anim++;
        }
    }
    
    @Override
    public void render(Screen screen) {
        screen.renderSprite((int)xd - (sprite.getWidth() / 2), (int)yd - (sprite.getHeight() / 2), sprite);
    }
    
}
