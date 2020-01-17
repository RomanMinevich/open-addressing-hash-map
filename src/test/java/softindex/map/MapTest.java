package softindex.map;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MapTest {
    private static Map testMap;

    @Before
    public void setUp() {
        testMap = new Map();
    }

    @Test
    public void putOk() {
        assertEquals(-1, testMap.put(0, 1));
    }

    @Test
    public void getOk() {
        testMap.put(1, 9);
        testMap.put(2, 5);
        assertEquals(9, testMap.get(1));
        assertEquals(-1, testMap.get(3));
    }

    @Test
    public void sizeOk() {
        assertEquals(0, testMap.size());
        testMap.put(1, 9);
        testMap.put(2, 5);
        assertEquals(2, testMap.size());
    }

    @Test
    public void replaceOk() {
        testMap.put(0, 1);
        assertEquals(1, testMap.put(0, 2));
        assertEquals(2, testMap.get(0));
        assertEquals(1, testMap.size());
    }

    @Test
    public void capacityIncreaseOk() {
        for (int key = 0; key < 1000; key++) {
            testMap.put(key, key + 1);
        }
        assertEquals(1000, testMap.size());
        assertEquals(1000, testMap.get(999));
    }
}
