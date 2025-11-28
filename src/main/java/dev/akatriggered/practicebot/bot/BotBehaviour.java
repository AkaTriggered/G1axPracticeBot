package dev.akatriggered.practicebot.bot;

public class BotBehaviour {
    
    private boolean useShield = true;
    private boolean pearlToOwner = false;
    private boolean aggressive = true;
    private boolean defensive = false;
    private boolean standStill = false;
    private boolean autoTotem = true;
    
    public boolean isUseShield() {
        return useShield;
    }
    
    public void setUseShield(boolean useShield) {
        this.useShield = useShield;
    }
    
    public boolean isPearlToOwner() {
        return pearlToOwner;
    }
    
    public void setPearlToOwner(boolean pearlToOwner) {
        this.pearlToOwner = pearlToOwner;
    }
    
    public boolean isAggressive() {
        return aggressive;
    }
    
    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
        if (aggressive) {
            this.defensive = false;
            this.standStill = false;
        }
    }
    
    public boolean isDefensive() {
        return defensive;
    }
    
    public void setDefensive(boolean defensive) {
        this.defensive = defensive;
        if (defensive) {
            this.aggressive = false;
            this.standStill = false;
        }
    }
    
    public boolean isStandStill() {
        return standStill;
    }
    
    public void setStandStill(boolean standStill) {
        this.standStill = standStill;
        if (standStill) {
            this.aggressive = false;
            this.defensive = false;
        }
    }
    
    public boolean isAutoTotem() {
        return autoTotem;
    }
    
    public void setAutoTotem(boolean autoTotem) {
        this.autoTotem = autoTotem;
    }
}
