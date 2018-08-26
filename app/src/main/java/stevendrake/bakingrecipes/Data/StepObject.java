package stevendrake.bakingrecipes.Data;

public class StepObject {

    private String id;
    private String shortDesc;
    private String description;
    private String videoUrl;
    private String thumbUrl;
    private static String stepString;

    public void setId(String newId) {id = newId;}
    public void setShortDesc(String newShort) {shortDesc = newShort;}
    public void setDescription(String newDesc) {description = newDesc;}
    public void setVideoUrl(String newVideo) {videoUrl = newVideo;}
    public void setThumbUrl(String newThumb) {thumbUrl = newThumb;}
    public static void setStepString(String newStepString){stepString = newStepString;}

    public String getId(){return id;}
    public String getShortDesc() {return shortDesc;}
    public String getDescription() {return description;}
    public String getVideoUrl() {return videoUrl;}
    public String getThumbUrl() {return thumbUrl;}
    public static String getStepString(){return stepString;}
}

