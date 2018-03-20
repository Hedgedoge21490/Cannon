/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.level;

import main.java.com.monkeystomp.graphics.Screen;

/**
 *
 * @author aaron
 */
class RandomLevel extends Level {

    public static Level globalRandomLevel = new RandomLevel();

    private int backgroundColor;
    private int groundColor;
    

    public RandomLevel() {
        super();
    }

    @Override
    protected void generateLevel() {
        backgroundColor = random.nextInt();
        groundColor = random.nextInt();
    }

    @Override
    public void render(Screen screen) {
        screen.renderLevel(backgroundColor, groundColor);
    }
}
