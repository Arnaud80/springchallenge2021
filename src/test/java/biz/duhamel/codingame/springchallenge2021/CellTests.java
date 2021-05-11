package biz.duhamel.codingame.springchallenge2021;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit test for Cell class.
 */
@DisplayName("All tests for class Cell")
class CellTests {
    /**
     * Rigorous Test :-)
     */
    Cell cell;

    @BeforeEach
    void initBeforeEach() {
        int[] neights = new int[] { 0, 1, 2, 3, 4, 5 };
        cell = new Cell(0, 100, neights);
    }

    @Test
    void allowCreationOfCell() {
        assertNotNull(cell);
        int cellIndex = cell.getIndex();
        assertEquals(0, cellIndex, "Expected index should be 0 and we get " + cellIndex);
    }

    @Nested
    @DisplayName("check Cell getters")
    class CellGettersTests {
        @Test
        @DisplayName("getIndex should be 0")
        void getIndexTest() {
            assertNotNull(cell);
            int cellIndex = cell.getIndex();
            assertEquals(0, cellIndex, "Expected index should be 0 and we get " + cellIndex);
        }

        @Test
        @DisplayName("getRichness should be 100")
        void getRichnessTest() {
            assertNotNull(cell);
            int richness = cell.getRichness();
            assertEquals(100, richness);
        }
    }

}
