package game;

import edu.monash.fit2099.engine.*;

public class JurassicWorld extends World {

    private int turnElapsed;
    private boolean rain;

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public JurassicWorld(Display display) {
        super(display);
        turnElapsed = 0;
    }

    public int getTurnElapsed() {
        return turnElapsed;
    }

    /**
     * Add a GameMap to the World.
     *
     * @param gameMap the GameMap to add
     */
    @Override
    public void addGameMap(GameMap gameMap) {
        super.addGameMap(gameMap);
        if (gameMap instanceof JurassicParkGameMap){
            ((JurassicParkGameMap)gameMap).world = this;
        }
    }

    /**
     * Run the game.
     * <p>
     * On each iteration the gameloop does the following: - displays the player's
     * map - processes the actions of every Actor in the game, regardless of map
     * <p>
     * We could either only process the actors on the current map, which would make
     * time stop on the other maps, or we could process all the actors. We chose to
     * process all the actors.
     *
     * @throws IllegalStateException if the player doesn't exist
     */
    @Override
    public void run() {
        if (player == null)
            throw new IllegalStateException();

        // initialize the last action map to nothing actions;
        for (Actor actor : actorLocations) {
            lastActionMap.put(actor, new DoNothingAction());
        }

        // This loop is basically the whole game
        // TODO: To Amos: Add turn related stuff (Challenge mode)
        // TODO: To Amos: put menu for selecting mode here
        // TODO: To Amos: add a new action that quits the game or something
        while (stillRunning()) {
            // Turn timer
            turnElapsed++;

            // Map processing
            GameMap playersMap = actorLocations.locationOf(player).map();
            // Show current turn
            display.println(String.format("Turn %s", turnElapsed));
            playersMap.draw(display);

            // Check rain
            if (turnElapsed % 10 == 0){
                double chance = Math.random();
                if (chance < 0.2){
                    rain = true;
                    display.println("The world rains.");
                }

            }

            // Process all the actors.
            for (Actor actor : actorLocations) {
                if (stillRunning())
                    processActorTurn(actor);
            }

            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                gameMap.tick();
            }
            // Reset rain
            rain = false;

        }
        display.println(endGameMessage());
    }

    public boolean isRain() {
        return rain;
    }
}