package ie.wit.rocksmithapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul on 3/8/2018.
 */


public class SongRecord {

    @SerializedName("songName")
    @Expose
    private String songName;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("difficulty")
    @Expose
    private Integer difficulty;
    @SerializedName("speed")
    @Expose
    private Integer speed;
    @SerializedName("levelUp")
    @Expose
    private Boolean levelUp;
    @SerializedName("accelerate")
    @Expose
    private Boolean accelerate;
    @SerializedName("advancedSettings")
    @Expose
    private String advancedSettings;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("difficultyRepeats")
    @Expose
    private String difficultyRepeats;
    @SerializedName("speedRepeats")
    @Expose
    private String speedRepeats;
    @SerializedName("tolerance")
    @Expose
    private String tolerance;
    @SerializedName("speedIncrement")
    @Expose
    private String speedIncrement;
    @SerializedName("rewindAnimation")
    @Expose
    private String rewindAnimation;
    @SerializedName("masterMode")
    @Expose
    private String masterMode;
    @SerializedName("autoContinue")
    @Expose
    private String autoContinue;
    @SerializedName("showMistakes")
    @Expose
    private String showMistakes;

    public SongRecord(String songName, String artistName, Integer difficulty, Integer speed, Boolean levelUp,
                      Boolean accelerate, String advancedSettings, String dateCreated, String difficultyRepeats,
                      String speedRepeats, String tolerance, String speedIncrement, String rewindAnimation, String masterMode,
                      String autoContinue, String showMistakes) {
        this.songName = songName;
        this.artistName = artistName;
        this.difficulty = difficulty;
        this.speed = speed;
        this.levelUp = levelUp;
        this.accelerate = accelerate;
        this.advancedSettings = advancedSettings;
        this.dateCreated = dateCreated;
        this.difficultyRepeats = difficultyRepeats;
        this.speedRepeats = speedRepeats;
        this.tolerance = tolerance;
        this.speedIncrement = speedIncrement;
        this.rewindAnimation = rewindAnimation;
        this.masterMode = masterMode;
        this.autoContinue = autoContinue;
        this.showMistakes = showMistakes;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Boolean getLevelUp() {
        return levelUp;
    }

    public void setLevelUp(Boolean levelUp) {
        this.levelUp = levelUp;
    }

    public Boolean getAccelerate() {
        return accelerate;
    }

    public void setAccelerate(Boolean accelerate) {
        this.accelerate = accelerate;
    }

    public String getAdvancedSettings() {
        return advancedSettings;
    }

    public void setAdvancedSettings(String advancedSettings) {
        this.advancedSettings = advancedSettings;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDifficultyRepeats() {
        return difficultyRepeats;
    }

    public void setDifficultyRepeats(String difficultyRepeats) {
        this.difficultyRepeats = difficultyRepeats;
    }

    public String getSpeedRepeats() {
        return speedRepeats;
    }

    public void setSpeedRepeats(String speedRepeats) {
        this.speedRepeats = speedRepeats;
    }

    public String getTolerance() {
        return tolerance;
    }

    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }

    public String getSpeedIncrement() {
        return speedIncrement;
    }

    public void setSpeedIncrement(String speedIncrement) {
        this.speedIncrement = speedIncrement;
    }

    public String getRewindAnimation() {
        return rewindAnimation;
    }

    public void setRewindAnimation(String rewindAnimation) {
        this.rewindAnimation = rewindAnimation;
    }

    public String getMasterMode() {
        return masterMode;
    }

    public void setMasterMode(String masterMode) {
        this.masterMode = masterMode;
    }

    public String getAutoContinue() {
        return autoContinue;
    }

    public void setAutoContinue(String autoContinue) {
        this.autoContinue = autoContinue;
    }

    public String getShowMistakes() {
        return showMistakes;
    }

    public void setShowMistakes(String showMistakes) {
        this.showMistakes = showMistakes;
    }

}