package dml.models;

public enum Race {
    DWARF("dwarf"),
    ELF("elf"),
    HALFLING("halfling"),
    HUMAN("human"),
    DRAGONBORN("dragonborn"),
    GNOME("gnome"),
    HALF_ELF("half-elf"),
    HALF_ORC("half-orc"),
    TIEFLING("tiefling");

    public final String index;

    private Race(String index){
        this.index = index;
    }

    public static Race readIndex(String index){
        for(Race r : Race.values()){
            if( r.index.equals(index)) return r;
        }
        return null;
    }
}
