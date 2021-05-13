package biz.duhamel.codingame.springchallenge2021;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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
    Dataset dataset = new Dataset();

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
    @DisplayName("getNetightsCell should return Neight List of Cells")
    class getNeightsCellShouldReturn {
        @Test
        @DisplayName("from the center of map : position 0")
        void onTheCenterOfMap() {
            Game game = dataset.getSimpleGameset();
            Cell currentCell = game.getCells()[0];
            List<Cell> nightsList = currentCell.getNeightsCell(game.getCells(),1);
            List<Cell> expectedNeightsList = new ArrayList<Cell>();
            expectedNeightsList.add(game.getCells()[0]);
            expectedNeightsList.add(game.getCells()[1]);
            expectedNeightsList.add(game.getCells()[2]);
            expectedNeightsList.add(game.getCells()[3]);
            expectedNeightsList.add(game.getCells()[4]);
            expectedNeightsList.add(game.getCells()[5]);

            assertEquals(expectedNeightsList, nightsList);
        }

        @Test@DisplayName("from Cell at the border of map : position 19")
        void atTheBorderOfMap() {
            Game game = dataset.getSimpleGameset();
            Cell currentCell = game.getCells()[19];
            List<Cell> nightsList = currentCell.getNeightsCell(game.getCells(),1);
            List<Cell> expectedNeightsList = new ArrayList<Cell>();
            expectedNeightsList.add(game.getCells()[20]);
            expectedNeightsList.add(game.getCells()[7]);
            expectedNeightsList.add(game.getCells()[36]);

            String message = "Should have Cells 20, 7, 36 and we have " + nightsList.get(0).getIndex() + " " + nightsList.get(1).getIndex() + " " + nightsList.get(2).getIndex();

            assertEquals(expectedNeightsList, nightsList, message);
        }
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
