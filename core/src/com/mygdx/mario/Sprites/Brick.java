package com.mygdx.mario.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mario.Scenes.Hud;
import com.mygdx.mario.Screens.PlayScreen;
import com.mygdx.mario.SuperMario;

public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(SuperMario.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(SuperMario.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(200);
        SuperMario.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
    }
}
