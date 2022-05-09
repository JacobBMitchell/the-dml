package dml.models;

public enum DndClass {
    BARBARIAN("barbarian"),
    BARD("bard"),
    CLERIC("cleric"),
    DRUID("druid"),
    FIGHTER("fighter"),
    MONK("monk"),
    PALADIN("paladin"),
    RANGER("ranger"),
    ROGUE("rogue"),
    SORCERER("sorcerer"),
    WARLOCK("warlock"),
    WIZARD("wizard");

    public final String index;

    private DndClass(String index) {
        this.index = index;
    }

    public static DndClass readIndex(String index){
        for(DndClass d : DndClass.values()){
            if( d.index.equals(index)) return d;
        }
        return null;
    }

}
