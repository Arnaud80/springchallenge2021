package biz.duhamel.codingame.springchallenge2021;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    private int day; // the game lasts 24 days: 0-23
    private int nutrients; // the base score you gain from the next COMPLETE action
    private int sun; // your sun points
    private int score; // your current score
    private int oppSun; // opponent's sun points
    private int oppScore; // opponent's score
    private boolean oppIsWaiting; // whether your opponent is asleep until the next day
    private int numberOfTrees; // the current amount of trees
    private Tree[] trees;
    private int numberOfCells;
    private Cell[] cells;

    public Game(int day, int nutrients, int sun, int score, int oppSun, int oppScore, boolean oppIsWaiting,
            int numberOfTrees, Tree[] trees, int numberOfCells, Cell[] cells) {
        this.day = day;
        this.nutrients = nutrients;
        this.sun = sun;
        this.score = score;
        this.oppSun = oppSun;
        this.oppScore = oppScore;
        this.oppIsWaiting = oppIsWaiting;
        this.numberOfTrees = numberOfTrees;
        this.trees = trees;
        this.numberOfCells = numberOfCells;
        this.cells = cells;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getNutrients() {
        return nutrients;
    }

    public void setNutrients(int nutrients) {
        this.nutrients = nutrients;
    }

    public int getSun() {
        return sun;
    }

    public void setSun(int sun) {
        this.sun = sun;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getOppSun() {
        return oppSun;
    }

    public void setOppSun(int oppSun) {
        this.oppSun = oppSun;
    }

    public int getOppScore() {
        return oppScore;
    }

    public void setOppScore(int oppScore) {
        this.oppScore = oppScore;
    }

    public boolean isOppIsWaiting() {
        return oppIsWaiting;
    }

    public void setOppIsWaiting(boolean oppIsWaiting) {
        this.oppIsWaiting = oppIsWaiting;
    }

    public int getNumberOfTrees() {
        return numberOfTrees;
    }

    public void setNumberOfTrees(int numberOfTrees) {
        this.numberOfTrees = numberOfTrees;
    }

    public Tree[] getTrees() {
        return trees;
    }

    public void setTrees(Tree[] trees) {
        this.trees = trees;
    }

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public void setNumberOfCells(int numberOfCells) {
        this.numberOfCells = numberOfCells;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public Tree getHigherTree(boolean isMine) {
        Stream<Tree> stream = Stream.of(trees);

        List<Tree> myTrees;

        myTrees = stream
            .filter(t -> t.isMine()==isMine)
            .filter(t -> t.getSize()>2)
            .filter(t -> !t.isDormant())
            .collect(Collectors.toList());

        Tree higherTree = myTrees
            .stream()
            .max(Comparator.comparing(Tree::getSize))
            .orElseThrow(() -> new NoSuchElementException("No max tree found"));

        return higherTree;
    }

    // It is best to begin by growin smaller tree ?
    public Tree getBestGrowableTree(boolean isMine) {
        long cost0=0;
        long cost1=0;
        long cost2=0;
        Tree bestTree;
        Tree growableTreeSize0, growableTreeSize1, growableTreeSize2;
        try {
            growableTreeSize0= getBestGrowableTreeBySize(0, true);
            cost0=getGrowCost(growableTreeSize0);
        } catch (Exception e) {
            cost0 = 999;
            growableTreeSize0=null;
        }
        
        try {
            growableTreeSize1= getBestGrowableTreeBySize(1, true);
            cost1=getGrowCost(growableTreeSize1);
        } catch (Exception e) {
            cost1 = 999;
            growableTreeSize1=null;
        }
        
        try {
            growableTreeSize2= getBestGrowableTreeBySize(2, true);
            cost2=getGrowCost(growableTreeSize2);
        } catch (Exception e) {
            cost2 = 999;
            growableTreeSize2=null;
        }
        
        if(cost2!=-1 && cost2<cost1 && cost2<cost0 && cost2<=this.sun) {
            bestTree = growableTreeSize2;
        } else {
            if(cost1!=-1 && cost1<cost0 && cost1<=this.sun) {
                bestTree = growableTreeSize1;
            } else {
                if(cost0!=-1 && cost0<=this.sun) {
                    bestTree = growableTreeSize0;
                } else throw new NoSuchElementException("No growable tree found");
            }
        }

        return bestTree;
    }

    public Tree getBestGrowableTreeBySize(int size, boolean isMine) {
        Stream<Tree> stream = Stream.of(trees);

        List<Tree> myTrees;

        myTrees = stream
            .filter(t -> t.isMine()==isMine)
            .filter(t -> t.getSize()==size)
            .filter(t -> !t.isDormant())
            .collect(Collectors.toList());

        return myTrees
            .stream()
            .min(Comparator.comparing(Tree::getSize))
            .orElseThrow(() -> new NoSuchElementException("No growable tree found"));
    }

    // Return array of two int with Tree source index and cell seed index target
    public int[] getBestSeedTarget(boolean isMine) {
        int[] pairResult=new int[]{-1, -1};
        Stream<Tree> stream = Stream.of(trees);

        List<Tree> myTrees;

        myTrees = stream
            .filter(t -> t.isMine()==isMine)
            .filter(t -> t.getSize()>1)
            .filter(t -> !t.isDormant())
            .collect(Collectors.toList());

        List<Cell> myCells = new ArrayList<>();

        myTrees.forEach(t -> {
                List<Cell> cellTargets=cells[t.getCellIndex()].getNeightsCell(cells, t.getSize());
                cellTargets.forEach(cell -> cell.addPotentialFater(t));
                myCells.addAll(cellTargets);
            }
        );

        Cell seedCellTarget = myCells
            .stream()
            .sorted(Comparator.comparing(Cell::getIndex))
            .filter(c -> c.getTree()==null)
            .max(Comparator.comparing(Cell::getIndex))
            //.max(Comparator.comparing(Cell::getRichness))
            .orElseThrow(() -> new NoSuchElementException("No seed place found"));

        Tree treeSource = seedCellTarget.getBestPotentialFather();
        pairResult[0]=treeSource.getCellIndex();
        pairResult[1]=seedCellTarget.getIndex();
        
        return pairResult;
    }

    public long getTreeQtyBySize(int size, boolean isMine) {
        Stream<Tree> stream = Stream.of(trees);

        return stream
            .filter(t -> t.isMine()==isMine)
            .filter(t -> t.getSize()==size)
            .count();
    }

    public long getGrowCost(Tree tree) {
        long qty=getTreeQtyBySize(1, true);
        return ( tree.getSize() + 1 ) +  getTreeQtyBySize(tree.getSize() + 1, tree.isMine());
    }
}
