/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.monkeystomp.entity.platform;

import main.java.com.monkeystomp.graphics.Sprite;

/**
 *
 * @author Aaron
 */
public class BasicPlatform extends Platform {

    public static Platform globalBasicPlatform = new BasicPlatform();

    public BasicPlatform() {
        x = 0;
        y = 132;
        hitPointsMax = 3000;
        hitPoints = hitPointsMax;
        sprite = Sprite.woodPlatform;
    }
}
