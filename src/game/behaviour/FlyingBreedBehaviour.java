package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;
import game.dinosaur.Gender;

public class FlyingBreedBehaviour extends BreedBehaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return null;
    }

    @Override
    Action hasPartner(Location there, Dinosaur dinosaur, Gender targetGender, GameMap map) {
        return null;
    }
}
