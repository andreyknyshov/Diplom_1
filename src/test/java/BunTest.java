import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import praktikum.Bun;

@RunWith(Parameterized.class)
public class BunTest {
    private final String bunName;
    private final float bunPrice;

    public BunTest(String bunName, int bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"Вкусная", 100},
                {"Самая вкусная", 120}
        };
    }

    @Test
    public void bunHasGivenNameAndPrice() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals(bunName, bun.getName());
        Assert.assertEquals(bunPrice, bun.getPrice(), 0.001);
    }
}
