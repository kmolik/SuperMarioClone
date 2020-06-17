package com.mygdx.mario.Sprites.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.mario.Scenes.Hud;
import com.mygdx.mario.Screens.PlayScreen;
import com.mygdx.mario.Sprites.Mario;
import com.mygdx.mario.SuperMario;

public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, MapObject object) {
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(SuperMario.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        if(mario.isBig()) {
            Gdx.app.log("Brick", "Collision");
            setCategoryFilter(SuperMario.DESTROYED_BIT);
            getCell().setTile(null);
            Hud.addScore(200);
            SuperMario.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
        }
        SuperMario.manager.get("audio/sounds/bump.wav", Sound.class).play();
    }
}
