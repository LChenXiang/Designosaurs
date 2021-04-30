package game;

import edu.monash.fit2099.engine.*;
import game.dinosaur.DinosaurStatus;
import game.growable.Bush;
import game.growable.Tree;

public class JurassicParkLocation extends Location {
    /**
     * Constructor.
     * <p>
     * Locations know which map they are part of, and where.
     *
     * @param map the map that contains this location
     * @param x   x coordinate of this location within the map
     * @param y   y coordinate of this location within the map
     */
    public JurassicParkLocation(GameMap map, int x, int y) {
        super(map, x, y);
    }

    /**
     * Used to check if a bush can grow in the current location.
     */
    public void checkBushGrowth() {
        double chance;
        int numberOfTree = 0;
        int numberOfBush = 0;
        // Get all 8 tiles and note down the number of trees and bushes
        for (Exit exit : getExits()) {
            Location there = exit.getDestination();
            Ground ground = there.getGround();
            if (ground instanceof Tree) {
                numberOfTree++;
            } else if (ground instanceof Bush) {
                numberOfBush++;
            }
        }

        // If there's even a tree, no chance
        // If there's at least 2 bushes, 10%
        // Else, 1% (Default dirt chance)
        if (numberOfTree > 0) {
            chance = 0;
        } else if (numberOfBush >= 2) {
            chance = 0.1;
        } else {
            chance = 0.01;
        }

        // Run RNGesus
        double randomChance = Math.random();
        if (randomChance < chance) {
            setGround(new Bush());
        }
    }

    /**
     * Used to check if a bush will die.
     */
    private void checkBushDeath() {
        double chanceBushDeath = 0.5; // Chance of stomping the bush to death
        // Check if bush in the first place
        if (getGround() instanceof Bush) {
            // Check if the dinosaur has the ability to kill a bush
            Actor dinosaur = getActor();
            if (dinosaur != null && dinosaur.hasCapability(DinosaurStatus.STOMP_BUSH)) {
                // Run RNGesus
                double chance = Math.random();
                if (chance < chanceBushDeath) {
                    setGround(new Dirt());
                }
            }
        }
    }

    /**
     * Called every turn to give the location and anything on it to inform them
     * the passage of time.
     * In this JurassicPark version of location, we will check if there's a chance
     * for a bush to grow, or chance of Brachiosaur killing a bush.
     */
    @Override
    public void tick() {
        if (getGround() instanceof Dirt) {
            checkBushGrowth();
        }
        checkBushDeath();
        super.tick();
    }
}
