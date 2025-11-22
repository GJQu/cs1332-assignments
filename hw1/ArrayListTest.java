import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    @Test
    public void testAddAndRemove() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addToBack(10);
        list.addToFront(5);
        assertEquals(2, list.size());
        assertEquals(5, list.removeFromFront());
        assertEquals(10, list.removeFromBack());
    }
}
