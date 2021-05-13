package biz.duhamel.codingame.springchallenge2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("All tests for Tree class")
public class TreeTest {
    Dataset dataset = new Dataset();
    Game simpleGameSet;

    @BeforeAll
    void initilizeDataset() {
        simpleGameSet = dataset.getSimpleGameset();
    }
/*
    @Nested
    @DisplayName("Should return the best Cell to SEED")
    class shouldReturnBestCelltoSeed {
        @Test
        @DisplayName("with simple gameset")
        void whitSimpleGameset() {
            Cell bestSeedCell = simpleGameSet.getBestSeedTarget(true);
            assertEquals(55, bestSeedCell.getIndex(),
                    "Expected tree index should be 55 and we get " + bestSeedCell.getIndex());
        }

        @Test
        @DisplayName("without max in trees list whom throw exception")
        void whithoutMaxInTreeList() {
            Throwable error = assertThrows(NoSuchElementException.class, () -> withoutMaxGameSet.getHigherTree(true));
            assertEquals("No max tree found", error.getMessage());
        }
    }   */
}
