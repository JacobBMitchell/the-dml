package dml.models;

public class Character {
    private Integer id;
    private String name;
    private Integer userId;
    private Integer currentHealth;
    private Integer maxHealth;
    private DndClass dndClass;
    private Race race;
    private String alignment;
    private Integer characterLevel;
    private Integer armorClass;
    private Integer speed;
    private Integer str;
    private Integer dex;
    private Integer con;
    private Integer intel;
    private Integer wis;
    private Integer cha;
    boolean savingStr;
    boolean savingDex;
    boolean savingCon;
    boolean savingIntel;
    boolean savingWis;
    boolean savingCha;
    Boolean acrobatics;
    Boolean animalHandling;
    Boolean arcana;
    Boolean athletics;
    Boolean deception;
    Boolean history;
    Boolean insight;
    Boolean intimidation;
    Boolean investigation;
    Boolean medicine;
    Boolean nature;
    Boolean perception;
    Boolean performance;
    Boolean persuasion;
    Boolean religion;
    Boolean sleightOfHand;
    Boolean stealth;
    Boolean survival;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Integer currentHealth) {
        this.currentHealth = currentHealth;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

    public DndClass getDndClass() {
        return dndClass;
    }

    public void setDndClass(DndClass dndClass) {
        this.dndClass = dndClass;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public Integer getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(Integer characterLevel) {
        this.characterLevel = characterLevel;
    }

    public Integer getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(Integer armorClass) {
        this.armorClass = armorClass;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getStr() {
        return str;
    }

    public void setStr(Integer str) {
        this.str = str;
    }

    public Integer getDex() {
        return dex;
    }

    public void setDex(Integer dex) {
        this.dex = dex;
    }

    public Integer getCon() {
        return con;
    }

    public void setCon(Integer con) {
        this.con = con;
    }

    public Integer getIntel() {
        return intel;
    }

    public void setIntel(Integer intel) {
        this.intel = intel;
    }

    public Integer getWis() {
        return wis;
    }

    public void setWis(Integer wis) {
        this.wis = wis;
    }

    public Integer getCha() {
        return cha;
    }

    public void setCha(Integer cha) {
        this.cha = cha;
    }

    public boolean isSavingStr() {
        return savingStr;
    }

    public void setSavingStr(boolean savingStr) {
        this.savingStr = savingStr;
    }

    public boolean isSavingDex() {
        return savingDex;
    }

    public void setSavingDex(boolean savingDex) {
        this.savingDex = savingDex;
    }

    public boolean isSavingCon() {
        return savingCon;
    }

    public void setSavingCon(boolean savingCon) {
        this.savingCon = savingCon;
    }

    public boolean isSavingIntel() {
        return savingIntel;
    }

    public void setSavingIntel(boolean savingIntel) {
        this.savingIntel = savingIntel;
    }

    public boolean isSavingWis() {
        return savingWis;
    }

    public void setSavingWis(boolean savingWis) {
        this.savingWis = savingWis;
    }

    public boolean isSavingCha() {
        return savingCha;
    }

    public void setSavingCha(boolean savingCha) {
        this.savingCha = savingCha;
    }

    public Boolean getAcrobatics() {
        return acrobatics;
    }

    public void setAcrobatics(Boolean acrobatics) {
        this.acrobatics = acrobatics;
    }

    public Boolean getAnimalHandling() {
        return animalHandling;
    }

    public void setAnimalHandling(Boolean animalHandling) {
        this.animalHandling = animalHandling;
    }

    public Boolean getArcana() {
        return arcana;
    }

    public void setArcana(Boolean arcana) {
        this.arcana = arcana;
    }

    public Boolean getAthletics() {
        return athletics;
    }

    public void setAthletics(Boolean athletics) {
        this.athletics = athletics;
    }

    public Boolean getDeception() {
        return deception;
    }

    public void setDeception(Boolean deception) {
        this.deception = deception;
    }

    public Boolean getHistory() {
        return history;
    }

    public void setHistory(Boolean history) {
        this.history = history;
    }

    public Boolean getInsight() {
        return insight;
    }

    public void setInsight(Boolean insight) {
        this.insight = insight;
    }

    public Boolean getIntimidation() {
        return intimidation;
    }

    public void setIntimidation(Boolean intimidation) {
        this.intimidation = intimidation;
    }

    public Boolean getInvestigation() {
        return investigation;
    }

    public void setInvestigation(Boolean investigation) {
        this.investigation = investigation;
    }

    public Boolean getMedicine() {
        return medicine;
    }

    public void setMedicine(Boolean medicine) {
        this.medicine = medicine;
    }

    public Boolean getNature() {
        return nature;
    }

    public void setNature(Boolean nature) {
        this.nature = nature;
    }

    public Boolean getPerception() {
        return perception;
    }

    public void setPerception(Boolean perception) {
        this.perception = perception;
    }

    public Boolean getPerformance() {
        return performance;
    }

    public void setPerformance(Boolean performance) {
        this.performance = performance;
    }

    public Boolean getPersuasion() {
        return persuasion;
    }

    public void setPersuasion(Boolean persuasion) {
        this.persuasion = persuasion;
    }

    public Boolean getReligion() {
        return religion;
    }

    public void setReligion(Boolean religion) {
        this.religion = religion;
    }

    public Boolean getSleightOfHand() {
        return sleightOfHand;
    }

    public void setSleightOfHand(Boolean sleightOfHand) {
        this.sleightOfHand = sleightOfHand;
    }

    public Boolean getStealth() {
        return stealth;
    }

    public void setStealth(Boolean stealth) {
        this.stealth = stealth;
    }

    public Boolean getSurvival() {
        return survival;
    }

    public void setSurvival(Boolean survival) {
        this.survival = survival;
    }
    //TODO: Store list of spell and weapon indexes, and also features

}
