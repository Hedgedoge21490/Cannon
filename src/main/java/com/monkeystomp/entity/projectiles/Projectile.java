/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.entity.projectiles;

import main.java.com.monkeystomp.entity.Entity;
import main.java.com.monkeystomp.entity.particle.Particle;
import main.java.com.monkeystomp.graphics.Screen;
import main.java.com.monkeystomp.graphics.Sprite;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

/**
 *
 * @author Aaron
 */
public abstract class Projectile extends Entity {
    protected String type;
    protected int offset = 6;
    // Variables used to calculate phisics
    // The force the projectile is shot at
    protected double force;
    // The angle in radians the projectile is fired.
    protected double angle;
    
    // Variables used on impact
    // Number of particles the projectile causes
    protected int particleAmount;
    // Damage of the projectile
    protected int damage;
    // Area around the impact site that is effected
    protected int areaOfEffect;
    
    // Coordinate variables
    // These are the coordinates that the projectile originated at.
    protected int startingX, startingY;
    // These are the coordinates that the projectile is targeted at.
    protected int endingX, endingY;
    // The coordinates of the cannonball
    protected double xd, yd;
    // The coordinates of the collision
    protected int xCollision, yCollision;
    
    // Used to animate the projectile
    protected double anim;
    
    protected Clip explosion;
    
    // The sprite of the projectile.
    protected Sprite sprite;
    
    // The name constants of the projectile.
    public static final String BASICCANNONBALLNAME = "BASIC CANNONBALL";
    public static final String TURTLECANNONBALLNAME = "TURTLE SHELL CANNONBALL";
    public static final String MASTERCANNONBALLNAME = "MASTER CANNONBALL";
    public static final String WINDUPCANNONBALLNAME = "WIND-UP CANNONBALL";
    
    // Projectile reload time constants.
    public static final int BASICCANNONBALL_RELOAD_TIME = 500000000;
    public static final int TURTLESHELLCANNONBALL_RELOAD_TIME = 750000000;
    public static final int MASTERCANNONBALL_RELOAD_TIME = 850000000;
    public static final int WINDUPCANNONBALL_RELOAD_TIME = 1000000000;
    
    // Projectile damage constants.
    public static final int BASICCANNONBALL_DAMAGE = 100;
    public static final int TURTLESHELLCANNONBALL_DAMAGE = 180;
    public static final int MASTERCANNONBALL_DAMAGE = 275;
    public static final int WINDUPCANNONBALL_DAMAGE = 400;
    
    // Projectile area of effect constants.
    public static final int BASICCANNONBALL_AREA = 10;
    public static final int TURTLESHELLCANNONBALL_AREA = 18;
    public static final int MASTERCANNONBALL_AREA = 25;
    public static final int WINDUPCANNONBALL_AREA = 38;
    
    // Projectile type constants
    public static final int BASICCANNONBALL = 1;
    public static final int TURTLESHELLCANNONBALL = 2;
    public static final int MASTERCANNONBALL = 3;
    public static final int WINDUPCANNONBALL = 4;
    
    public void setPosition(int x, int y) {
        xd = x;
        yd = x;
        this.x = x;
        this.y = y;
        startingX = x;
        startingY = y;
    }
    
    public void setTrajectory(int endingX, int endingY, double force, double angle) {
        this.endingX = endingX;
        this.endingY = endingY;
        this.force = force;
        this.angle = Math.toRadians(angle);
    }
    
    public static Projectile getProjectile(int type) {
        switch (type) {
            case BASICCANNONBALL:
                return new BasicCannonball();
            case WINDUPCANNONBALL:
                return new WindupCannonball();
            case MASTERCANNONBALL:
                return new MasterCannonball();
            case TURTLESHELLCANNONBALL:
                return new TurtleShellCannonball();
            default:
                return new BasicCannonball();
        }
    }

    private double getRandomForce() {
        return (20 + (random.nextDouble() * 45));
    }

    private double getRandomAngle() {
        return (70 + (random.nextDouble() * 30));
    }

    private int getColor(){
        switch(type){
            case "basic":
                return 0xffffff;
            case "master":
                return 0x3401ff;
            case "turtle":
                return 0x15ff15;
            case "windup":
                return 0x111111;
            default:
                return 0xffffff;
        }
    }

    private void checkBuildingCollision(){
        // Check for collision with building
        offset = 6;
        if(type == "basic"){
            offset = 3;
        }

        if (level.buildingHere((int)xd + offset, (int)yd + offset)) {
            endingX = (int) xd - 1;
            endingY = (int) yd + offset;
            xCollision = (int)xd + offset;
            yCollision = (int)yd + offset;
        }
        else if (level.buildingHere((int)xd - offset, (int)yd + offset)) {
            endingX = (int) xd - 1;
            endingY = (int) yd + offset;
            xCollision = (int)xd - offset;
            yCollision = (int)yd + offset;
        }
        else if (level.buildingHere((int)xd, (int)yd)) {
            endingX = (int) xd - 1;
            endingY = (int) yd + offset;
            xCollision = (int)xd;
            yCollision = (int)yd;
        }
    }


    public void checkImpact(){
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
                        AudioInputStream ais = AudioSystem.getAudioInputStream(Projectile.class.getResource("/audio/sfx/explosions/windup_explosion.wav"));
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
    public void update() {
        checkBuildingCollision();
        checkImpact();
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int)xd - (sprite.getWidth() / 2), (int)yd - (sprite.getHeight() / 2), sprite);
    }
}