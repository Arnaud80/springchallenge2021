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
    Dataset dataset=new Dataset();
    Game simpleGameSet;
    Game twoMaxGameSet;
    Game withoutMaxGameSet;
    Game withoutGrowableGameSet;
    Game treeLocatedAsBronzeLigueStart;
    Game getTreeWithSeed;
    Game getTreeAndSeedAllDormant;
    Game treeSize2;
    Game treeSize3;

    @BeforeEach
    void initilizeDataset() {
        simpleGameSet = dataset.getSimpleGameset();
        twoMaxGameSet = dataset.getTwoMaxGameSet();
        withoutMaxGameSet = dataset.getWithoutMaxGameSet();
        withoutGrowableGameSet = dataset.getWithoutGrowableGameSet();
        treeLocatedAsBronzeLigueStart = dataset.getTreeLocatedAsBronzeLigueStart();
        getTreeWithSeed = dataset.getTreeWithSeed();
        getTreeAndSeedAllDormant = dataset.getTreeAndSeedAllDormant();
        treeSize2 = dataset.getTreeSize2();
        treeSize3 = dataset.getTreeSize3();
    }
    @Nested
    @DisplayName("Should return the higher tree")
    class shouldReturnHigherTree {
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
    @Nested
    @DisplayName("Should return the best Cell to SEED")
    class shouldReturnBestCelltoSeed {
        @Test
        @DisplayName("with Tree located as Bronze Ligue Start")
        void whitTreeLocatedAsBronzeLigueStart() {
            int[] pairResult;
            int[] expectedPairResult= new int[]{20,7};
            pairResult = treeLocatedAsBronzeLigueStart.getBestSeedTarget(true);
            
            assertEquals(expectedPairResult[0], pairResult[0]);
            assertEquals(expectedPairResult[1], pairResult[1]);
        }

        @Test
        @DisplayName("with Tree and seed all dormant")
        void whitTreeAndSeedAllDormant() {       
            Throwable error = assertThrows(NoSuchElementException.class, () -> getTreeAndSeedAllDormant.getBestSeedTarget(true));
            assertEquals("No seed place found", error.getMessage());
        }

        @Test
        @DisplayName("with Tree of Size 2")
        void withTreeSize2() {
            int[] pairResult;
            int[] expectedPairResult= new int[]{20,1};
            pairResult = treeSize2.getBestSeedTarget(true);
            
            assertEquals(expectedPairResult[0], pairResult[0]);
            assertEquals(expectedPairResult[1], pairResult[1]);
        }

        @Test
        @DisplayName("with Tree of Size 3")
        void withTreeSize3() {
            int[] pairResult;
            int[] expectedPairResult= new int[]{20,0};
            pairResult = treeSize3.getBestSeedTarget(true);
            
            assertEquals(expectedPairResult[0], pairResult[0]);
            assertEquals(expectedPairResult[1], pairResult[1]);
        }
    }

    @Test
    @DisplayName("Should return quantity of Seed (TreeSize=0)")
    void shouldReturnQtyOfSeed() {
        assertEquals(2, getTreeWithSeed.getTreeQtyBySize(0,true));
        assertEquals(0, simpleGameSet.getTreeQtyBySize(0,true));
        assertEquals(2, getTreeWithSeed.getTreeQtyBySize(1, true));
    }

    @Test
    @DisplayName("Should return the grow cost")
    void shouldReturnGrowCost() {
        Tree tree=new Tree(0, 0, true, false);
        long cost=getTreeWithSeed.getGrowCost(tree);
        assertEquals(3, cost);
    }
}
