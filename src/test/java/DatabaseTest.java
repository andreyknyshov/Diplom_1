import org.junit.Assert;
import org.junit.Test;
import java.util.List;

import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;

public class DatabaseTest {
    @Test
    public void databaseHasBuns() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        Assert.assertTrue(buns.size() > 0);
    }

    @Test
    public void databaseHasIngredients() {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertTrue(ingredients.size() > 0);
    }
}
