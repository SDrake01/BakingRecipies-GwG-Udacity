package stevendrake.bakingrecipes.Data;

public class RecipeObject {

    private String id;
    private String name;
    private String ingredients;
    private String steps;
    private String servings;
    private String image;

    public void setId(String newId){id = newId;}
    public void setName(String newName){name = newName;}
    public void setIngredients(String newIngredients){ingredients = newIngredients;}
    public void setSteps(String newSteps){steps = newSteps;}
    public void setServings(String newServings){servings = newServings;}
    public void setImage(String newImage){image = newImage;}

    public String getId(){return id;}
    public String getName() {return name;}
    public String getIngredients() {return ingredients;}
    public String getSteps() {return steps;}
    public String getServings() {return servings;}
    public String getImage() {return image;}
}
