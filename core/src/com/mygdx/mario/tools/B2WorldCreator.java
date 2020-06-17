package com.mygdx.mario.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.mario.Screens.PlayScreen;
import com.mygdx.mario.Sprites.Enemies.Enemy;
import com.mygdx.mario.Sprites.Enemies.Turtle;
import com.mygdx.mario.Sprites.Objects.Brick;
import com.mygdx.mario.Sprites.Objects.Coin;
import com.mygdx.mario.Sprites.Enemies.Goomba;
import com.mygdx.mario.SuperMario;

public class B2WorldCreator {
    private Array<Goomba> goombas;
    private Array<Turtle> turtles;

    public B2WorldCreator(PlayScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        // creating ground bodies/fixtures
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / SuperMario.PPM, (rect.getY() + rect.getHeight() /2) / SuperMario.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() /2 / SuperMario.PPM, rect.getHeight() / 2 / SuperMario.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // creating pipe bodies/fixtures
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / SuperMario.PPM, (rect.getY() + rect.getHeight() /2) / SuperMario.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() /2 / SuperMario.PPM, rect.getHeight() / 2 / SuperMario.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = SuperMario.OBJECT_BIT;
            body.createFixture(fdef);
        }
        // creating brick bodies/fixtures
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {

            new Brick(screen, object);
        }
        // creating coin bodies/fixtures
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {


            new Coin(screen, object);
        }

        //create all goombas
        goombas = new Array<Goomba>();

        for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            goombas.add(new Goomba(screen, rect.getX() / SuperMario.PPM, rect.getY() / SuperMario.PPM));
        }

        turtles = new Array<Turtle>();

        for(MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            turtles.add(new Turtle(screen, rect.getX() / SuperMario.PPM, rect.getY() / SuperMario.PPM));
        }
    }

//    public  Array<Goomba> getGoombas() {
//        return goombas;
//    }
    public  Array<Enemy> getEnemies() {
        Array<Enemy> enemies = new Array<Enemy>();
        enemies.addAll(goombas);
        enemies.addAll(turtles);
        return enemies;
    }
}
