package Service;

import org.example.Model.Point;
import org.example.Service.Functions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;



public class FunctionsTest {

    private Functions functions = new Functions();

    @Test
    public void testVisiblePoints() throws Exception {
        ArrayList<Point> expected = new ArrayList<>();
        expected.add(new Point(27,46,2, Point.Direction.EAST));

        ArrayList<Point> result = functions.visiblePoints(1, 45, 20);

        Assertions.assertEquals(expected,result);
    }
}
