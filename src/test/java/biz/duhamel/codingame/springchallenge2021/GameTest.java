package biz.duhamel.codingame.springchallenge2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("All tests for Game class")
public class GameTest {
    @Test
    @DisplayName("Should return the richnest tree")
    void shouldReturnRichnestTree() {
        int day = 23;
        int nutrients = 24;
        int sun = 20;
        int score = 0;
        int oppSun = 20;
        int oppScore = 0;
        boolean oppIsWaiting = false;
        int numberOfTrees = 5;
        Tree[] trees = new Tree[] { 
            new Tree(0, 1, false, false), 
            new Tree(1, 3, true, false),
            new Tree(2, 10, true, false),
            new Tree(3, 2, false, false),
            new Tree(4, 1, true, false) };

        int numberOfCells = 3;
        Cell[] cells = new Cell[] { new Cell(0, 1, new int[] { 0, 1, 2, 3, 4, 5 }),
                new Cell(0, 1, new int[] { 0, 1, 2, 3, 4, 5 }), new Cell(0, 1, new int[] { 0, 1, 2, 3, 4, 5 }) };

        Game game = new Game(day, nutrients, sun, score, oppSun, oppScore, oppIsWaiting, numberOfTrees, trees,
                numberOfCells, cells);

        Tree higherTree = game.getHigherTree(true);
        assertEquals(2, higherTree.getCellIndex(),
                "Expected tree index should be 2 and we get " + higherTree.getCellIndex());
    }
}
