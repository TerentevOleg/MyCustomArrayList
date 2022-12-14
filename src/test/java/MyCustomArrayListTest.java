import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCustomArrayListTest {
    private MyCustomArrayList<Object> testList;
    @BeforeEach
    public void setUp(){
        testList = new MyCustomArrayList<>();
    }

    @Test
    void givenAddToTheEndToList_whenAdd_thenListHasTheSize() {
        testList.add("aaa");

        assertEquals(1, testList.size());
        assertEquals("aaa", testList.get(0));
    }

    @Test
    void givenAddToList_whenAdd_thenListHasTheSize() {
        testList.add(0, "aaa");
        testList.add(1, "bbb");
        testList.add(2,"ccc");

        assertEquals(3, testList.size());
        assertEquals("ccc", testList.get(2));
    }

    @Test
    void givenAddToListWithInvalidIndex_whenAdd_thenGetException() {
        int index = 24;

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> testList.add(index, "aaa"));
    }

    @Test
    void givenGetFromList_whenGet_thenValue() {
        testList.add(0, "aaa");
        testList.add(1, "bbb");
        testList.add(2,"ccc");

        assertEquals(testList.get(2), "ccc");
    }

    @Test
    void givenGetInvalidIndexFromList_whenGet_thenGetException() {
        testList.add(0, "aaa");
        testList.add(1, "bbb");
        testList.add(2,"ccc");

        assertEquals(3, testList.size());
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> testList.get(25));
    }

    @Test
    void givenSetToList_whenSet_thenValueUpdated() {
        testList.add(0, "aaa");
        testList.add(1, "bbb");
        testList.add(2,"ccc");
        testList.set(2, "mmm");

        assertEquals(testList.get(2), "mmm");
    }

    @Test
    void givenSetInvalidIndexToList_whenSet_thenGetException() {
        testList.add(0, "aaa");
        testList.add(1, "bbb");
        testList.add(2,"ccc");

        assertEquals(3, testList.size());
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> testList.set(25, "mmm"));
    }

    @Test
    void givenRemoveFromList_whenRemove_thenListSizeUpdate() {
        testList.add(0, "aaa");
        testList.add(1, "bbb");
        testList.add(2,"ccc");

        assertEquals(3, testList.size());

        testList.remove(1);

        assertEquals(testList.size(), 2);
    }

    @Test
    void givenRemoveWithInvalidIndexFromList_whenRemove_thenGetException() {
        testList.add(0, "aaa");
        testList.add(1, "bbb");
        testList.add(2,"ccc");

        assertEquals(3, testList.size());
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> testList.remove(25));

    }

    @Test
    void givenClearList_whenClear_thenListSizeZero() {
        testList.add(0, "aaa");
        testList.add(1, "bbb");
        testList.add(2,"ccc");

        assertEquals(3, testList.size());

        testList.clear();

        assertEquals(testList.size(), 0);
    }

    @Test
    void givenIndexOfFromList_whenGetIndex_thenIndexOf() {
        testList.add(0, "aaa");
        testList.add(1, "bbb");
        testList.add(2,"ccc");
        assertEquals(3, testList.size());
        assertEquals(testList.indexOf("bbb"), 1);
    }

    @Test
    void givenContainsInList_whenContains_thenContainsTrue() {
        testList.add(0, 1);
        testList.add(1, 14);
        testList.add(2,10);
        assertEquals(3, testList.size());
        assertTrue(testList.contains(10));
        assertFalse(testList.contains(15));
    }

    @Test
    void givenListSize_whenGetSize_thenSize() {
        testList.add(0, 1);
        testList.add(1, 14);
        testList.add(2,10);
        assertEquals(3, testList.size());
    }

    @Test
    void givenListSort_whenSort_thenListSorted() {
        testList.add(0, 1);
        testList.add(1, 14);
        testList.add(2,10);
        testList.sort();
        assertEquals(testList.get(1), 10);

        testList.clear();

        testList.add("aaa");
        testList.add("kkk");
        testList.add("ccc");
        testList.add("bbb");
        testList.sort();
        assertEquals(testList.get(1), "bbb");
    }
}