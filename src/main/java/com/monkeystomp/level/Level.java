/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.level;

import main.java.com.monkeystomp.controls.Mouse;
import main.java.com.monkeystomp.entity.cannon.Cannon;
import main.java.com.monkeystomp.entity.particle.Particle;
import main.java.com.monkeystomp.entity.platform.Platform;
import main.java.com.monkeystomp.graphics.Display;
import main.java.com.monkeystomp.graphics.Screen;
import main.java.com.monkeystomp.graphics.Sprite;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author aaron
 */
public abstract class Level {
    
    // Mouse possition adjusted for SCALE.
    protected int mouseX, mouseY;
    // The target of the cannonball adjusted for the cannon's accuracy
    protected int targetX, targetY;
    // Location on the ground where the mouse was clicked.
    protected int groundClickX, groundClickY;
    // Tells the render method to show buton click animation
    protected boolean renderClicks = false;
    // Allows to request the cannon to fire when player right clicks on battlefield.
    protected Cannon cannon;
    // Allows to send damage information from the mob to the platform.
    protected Platform platform;
    // Number of updates untill the next wave of enemies is sent
    protected int nextWaveTimer;
    protected int width, height;
    protected int difficulty;
    protected int score = 0;
    protected int multiplier = 1;
    protected Random random;
    protected BufferedImage levelBackgroundImage;
    protected Sprite levelBackgroundSprite;
    protected Sprite mouseClickSprite;
    protected int anim = 0;
    protected int clicksAnim = 0;
    
    protected Clip backgroundMusic;
    
    public static Level randomLevel = new RandomLevel();
    public static Level grassLevel = new GrassLevel("/levels/grass_level.png");
    public static Level purpleLevel = new PurpleLevel("/levels/purple_ground_level.png");
    
    
    public Level() {
        random = new Random();
        generateLevel();
    }
    
    public Level(String path) {
        random = new Random();
        loadLevel(path);
        generateLevel();
    }

    protected void loadLevel(String path) {
        try {
            levelBackgroundImage = ImageIO.read(Level.class.getResource(path));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Level could not load background image!");
        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Level.class.getResource("/audio/songs/sousa-semperfidelis.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(ais);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void generateLevel() {
    }
    
    public void init(Cannon cannon, Platform platform) {
        this.cannon = cannon;
        this.platform = platform;
    }
    
    public void addProjectile(int x, int y) {
    }
    
    public void addParticle(Particle particle) {
    }
    
    public void stopMusic() {
        backgroundMusic.stop();
    }
    
    public void playMusic() {
        backgroundMusic.setFramePosition(0);
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void addMultiplier(int multiplier) {
        this.multiplier += multiplier;
    }
    
    public void subtractMultiplier(int multiplier) {
        this.multiplier -= multiplier;
    }
    
    public int getMultiplier() {
        return multiplier;
    }
    
    public void increaseScore(int points) {
        score += points * multiplier;
    }
    
    public void decreaseScore(int points) {
        score -= points;
    }
    
    public  int getScore() {
        return score;
    }
    
    public boolean buildingHere(int x, int y) {
        return false;
    }
    
    public boolean mobHere(int x, int y) {
        return false;
    }
    
    public boolean mobHere(int xa, int ya, int x, int y) {
        return false;
    }
    
    public void damageBuilding(int x, int y, int damage) {
    }
    
    public boolean damageMob(int x, int y, int damage) {
        return false;
    }
    
    public void damagePlatform(int damage) {
    }

    public void setMousePossition() {
        mouseX = Mouse.getMouseX() / Display.SCALE;
        mouseY = Mouse.getMouseY() / Display.SCALE;
    }
    
    protected void setMouseClickSprite() {
        if (clicksAnim == 30) {
            renderClicks = false;
            clicksAnim = 0;
            return;
        }
        if (clicksAnim % 30 < 5) mouseClickSprite = Sprite.ground_click1;
        else if (clicksAnim % 30 >= 5 && clicksAnim % 30 < 10) mouseClickSprite = Sprite.ground_click2;
        else if (clicksAnim % 30 >= 10 && clicksAnim % 30 < 15) mouseClickSprite = Sprite.ground_click3;
        else if (clicksAnim % 30 >= 15 && clicksAnim % 30 < 20) mouseClickSprite = Sprite.ground_click4;
        else if (clicksAnim % 30 >= 20 && clicksAnim % 30 < 25) mouseClickSprite = Sprite.ground_click5;
        else if (clicksAnim % 30 >= 25) mouseClickSprite = Sprite.ground_click6;
    }
    
    public void update() {
    }
    
    public void render(Screen screen) {
    }
    
}
