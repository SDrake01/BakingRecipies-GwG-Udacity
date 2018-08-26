package stevendrake.bakingrecipes.Data;

public class RecipeObject {

    private String id;
    private String name;
    private String ingredients;
    private String steps;
    private String servings;
    private String image;
    private static String title;

    public void setId(String newId){id = newId;}
    public void setName(String newName){name = newName;}
    public void setIngredients(String newIngredients){ingredients = newIngredients;}
    public void setSteps(String newSteps){steps = newSteps;}
    public void setServings(String newServings){servings = newServings;}
    public void setImage(String newImage){image = newImage;}
    public static void setTitle(String newTitle){title = newTitle;}

    public String getId(){return id;}
    public String getName() {return name;}
    public String getIngredients() {return ingredients;}
    public String getSteps() {return steps;}
    public String getServings() {return servings;}
    public String getImage() {return image;}
    public static String getTitle(){return title;}
}
