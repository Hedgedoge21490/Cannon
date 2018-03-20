/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.level;

import main.java.com.monkeystomp.controls.Mouse;
import main.java.com.monkeystomp.entity.projectiles.Projectile;
import main.java.com.monkeystomp.graphics.Screen;
import main.java.com.monkeystomp.graphics.Sprite;
import java.util.ArrayList;


/**
 *
 * @author Aaron
 */
class PurpleLevel extends Level {

    public static Level purpleLevel = new PurpleLevel("/levels/purple_ground_level.png");


    private ArrayList<Projectile> projectiles = new ArrayList<>();

    public PurpleLevel(String path) {
        super(path);
    }
    
    @Override
    protected void generateLevel() {
        int[] pixels = new int[levelBackgroundImage.getWidth() * levelBackgroundImage.getHeight()];
        levelBackgroundImage.getRGB(0, 0, levelBackgroundImage.getWidth(), levelBackgroundImage.getHeight(), pixels, 0, levelBackgroundImage.getWidth());
        levelBackgroundSprite = new Sprite(0, 50, pixels, levelBackgroundImage.getWidth(), levelBackgroundImage.getHeight());
        //backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    
    @Override
    public void addProjectile(int x, int y) {
        projectiles.add(Projectile.getProjectile(cannon.loadedProjectile));
        projectiles.get(projectiles.size() - 1).setPosition(x, y);
    }
    
    private boolean feildIsRightClicked() {
        return Mouse.getMouseB() == 3 && mouseX > 100 && mouseY > 164;
    }
    
    //private int anim = 0;
    @Override
    public void update() {
//        if (anim > 10000) anim = 0;
//        else anim++;
        setMousePossition();
        if (feildIsRightClicked()) {
            renderClicks = true;
            cannon.FireCannon();
        }
        else renderClicks = false;
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) projectiles.remove(i);
            else projectiles.get(i).update();
        }
    }
    
    @Override
    public void render(Screen screen) {
        screen.renderSprite(0, 50, levelBackgroundSprite);
        if (renderClicks) screen.renderSprite(mouseX - (Sprite.basic_ground_click.getWidth() / 2), mouseY - (Sprite.basic_ground_click.getHeight() / 2), Sprite.basic_ground_click);
        for (Projectile pro: projectiles) {
            pro.render(screen);
        }
    }
    
}
