package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;

public class FlyingBreedBehaviour extends BreedBehaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return null;
    }

    @Override
    Action hasPartner(Location there, Dinosaur dinosaur, GameMap map) {
        return null;
    }
}
