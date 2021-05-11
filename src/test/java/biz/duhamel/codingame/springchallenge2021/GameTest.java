package biz.duhamel.codingame.springchallenge2021;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("All tests for Game class")
public class GameTest {
    Game simpleGameSet;
    Game twoMaxGameSet;
    Game withoutMaxGameSet;
    Game withoutGrowableGameSet;

    @BeforeEach
    void initilizeDataset() {
        int day = 23;
        int nutrients = 24;
        int sun = 20;
        int score = 0;
        int oppSun = 20;
        int oppScore = 0;
        boolean oppIsWaiting = false;
        int numberOfTrees = 5;
        Tree[] trees = new Tree[] { 
            new Tree(0, 0, false, false), 
            new Tree(1, 1, true, false),
            new Tree(2, 3, true, true),
            new Tree(3, 2, false, false),
            new Tree(4, 1, true, true),
            new Tree(5, 3, true, false) };

        int numberOfCells = 3;
        Cell[] cells = new Cell[] { 
            new Cell(0, 1, new int[] { 0, 1, 2, 3, 4, 5 }),
            new Cell(0, 1, new int[] { 0, 1, 2, 3, 4, 5 }),
            new Cell(0, 1, new int[] { 0, 1, 2, 3, 4, 5 })
        };

        simpleGameSet = new Game(day, nutrients, sun, score, oppSun, oppScore, oppIsWaiting, numberOfTrees, trees,
                numberOfCells, cells);

        trees = new Tree[] { 
            new Tree(0, 0, false, false), 
            new Tree(1, 1, true, false),
            new Tree(2, 3, true, false),
            new Tree(3, 2, false, false),
            new Tree(4, 3, true, false) };
        
        twoMaxGameSet = new Game(day, nutrients, sun, score, oppSun, oppScore, oppIsWaiting, numberOfTrees, trees,
        numberOfCells, cells);

        trees = new Tree[] { 
            new Tree(0, 0, false, false), 
            new Tree(1, 1, true, false),
            new Tree(2, 2, true, false),
            new Tree(3, 2, false, false),
            new Tree(4, 2, true, false) };
        
        withoutMaxGameSet = new Game(day, nutrients, sun, score, oppSun, oppScore, oppIsWaiting, numberOfTrees, trees,
        numberOfCells, cells);

        trees = new Tree[] { 
            new Tree(0, 3, false, false), 
            new Tree(1, 3, true, false),
            new Tree(2, 3, true, false),
            new Tree(3, 3, false, false),
            new Tree(4, 3, true, false) };
        
        withoutGrowableGameSet = new Game(day, nutrients, sun, score, oppSun, oppScore, oppIsWaiting, numberOfTrees, trees,
        numberOfCells, cells);
    }
    @Nested
    @DisplayName("Should return the richnest tree")
    class shouldReturnRichnestTree {
        @Test
        @DisplayName("with simple trees list")
        void whitSimpleTreeList() {
            Tree higherTree = simpleGameSet.getHigherTree(true);
            assertEquals(5, higherTree.getCellIndex(),
                    "Expected tree index should be 5 and we get " + higherTree.getCellIndex());
        }

        @Test
        @DisplayName("with two max in trees list")
        void whithTwoMaxInTreeList() {
            Tree higherTree = twoMaxGameSet.getHigherTree(true);
            assertEquals(2, higherTree.getCellIndex(),
                    "Expected tree index should be 2 and we get " + higherTree.getCellIndex());
        }

        @Test
        @DisplayName("without max in trees list whom throw exception")
        void whithoutMaxInTreeList() {
            Throwable error = assertThrows(NoSuchElementException.class, () -> withoutMaxGameSet.getHigherTree(true));
            assertEquals("No max tree found", error.getMessage());
        }
    }   
    @Nested
    @DisplayName("Should return the best growable tree")
    class shouldReturnBestGrowableTree {
        @Test
        @DisplayName("with simple trees list")
        void whitSimpleTreeList() {
            Tree growableTree = simpleGameSet.getBestGrowableTree(true);
            assertEquals(1, growableTree.getCellIndex(),
                    "Expected tree index should be 1 and we get " + growableTree.getCellIndex());
        }

        @Test
        @DisplayName("without growable in trees list whom throw exception")
        void whithoutGrowableInTreeList() {
            Throwable error = assertThrows(NoSuchElementException.class, () -> withoutGrowableGameSet.getBestGrowableTree(true));
            assertEquals("No growable tree found", error.getMessage());
        }
    }   
}
