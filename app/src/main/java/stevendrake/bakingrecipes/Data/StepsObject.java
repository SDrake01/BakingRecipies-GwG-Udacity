package stevendrake.bakingrecipes.Data;

public class StepsObject {

    private static int id;
    private static String shortDesc;
    private static String description;
    private static String videoUrl;
    private static String thumbUrl;

    public void setId(int newId) {id = newId;}
    public void setShortDesc(String newShort) {shortDesc = newShort;}
    public void setDescription(String newDesc) {description = newDesc;}
    public void setVideoUrl(String newVideo) {videoUrl = newVideo;}
    public void setThumbUrl(String newThumb) {thumbUrl = newThumb;}

    public static int getId(){return id;}
    public static String getShortDesc() {return shortDesc;}
    public static String getDescription() {return description;}
    public static String getVideoUrl() {return videoUrl;}
    public static String getThumbUrl() {return thumbUrl;}
}

