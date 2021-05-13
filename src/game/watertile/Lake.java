package game.watertile;

public class Lake extends WaterTile {

    public Lake() {
        super('~', 25, 5);
    }

    @Override
    protected void increaseSipCapacity() {
        double modifier = Math.random() * (0.6 - 0.2) + 0.2;
        sipCapacity += (20 * modifier);
    }

    @Override
    protected void checkFishGrowth() {
        double chance = Math.random();
        if (chance < 0.6){
            fishCount += 1;
        }
    }

    @Override
    public String toString() {
        return "Lake";
    }
}
