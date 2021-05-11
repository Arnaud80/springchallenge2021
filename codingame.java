//Version Tue May 11 19:58:31 CEST 2021
import java.util.*;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Cell {
    private int index;
    private int richness;
    private int[] neights = new int[5];

    public Cell(int index, int richness, int[] neights) {
        this.index = index;
        this.richness = richness;
        this.neights = neights;
    }

    public int getIndex() {
        return this.index;
    }

    public int getRichness() {
        return this.richness;
    }
}


class Game {

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

    public Tree getHigherTree(boolean b) {
        Stream<Tree> stream = Stream.of(trees);

        List<Tree> myTrees;

        myTrees = stream.filter(t -> t.isMine()).collect(Collectors.toList());

        Tree higherTree = myTrees.stream().max(Comparator.comparing(Tree::getSize))
                .orElseThrow(NoSuchElementException::new);

        return higherTree;
    }
}


/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int numberOfCells = in.nextInt(); // 37

        Cell[] cells = new Cell[numberOfCells];

        for (int i = 0; i < numberOfCells; i++) {
            int index = in.nextInt(); // 0 is the center cell, the next cells spiral outwards
            int richness = in.nextInt(); // 0 if the cell is unusable, 1-3 for usable cells
            int neigh0 = in.nextInt(); // the index of the neighbouring cell for each direction
            int neigh1 = in.nextInt();
            int neigh2 = in.nextInt();
            int neigh3 = in.nextInt();
            int neigh4 = in.nextInt();
            int neigh5 = in.nextInt();
            int[] neighs = new int[] { neigh0, neigh1, neigh2, neigh3, neigh4, neigh5 };
            cells[index] = new Cell(index, richness, neighs);
        }

        // game loop
        while (true) {
            int day = in.nextInt(); // the game lasts 24 days: 0-23
            int nutrients = in.nextInt(); // the base score you gain from the next COMPLETE action
            int sun = in.nextInt(); // your sun points
            int score = in.nextInt(); // your current score
            int oppSun = in.nextInt(); // opponent's sun points
            int oppScore = in.nextInt(); // opponent's score
            boolean oppIsWaiting = in.nextInt() != 0; // whether your opponent is asleep until the next day

            int numberOfTrees = in.nextInt(); // the current amount of trees
            Tree[] trees = new Tree[numberOfTrees];

            for (int i = 0; i < numberOfTrees; i++) {
                int cellIndex = in.nextInt(); // location of this tree
                int size = in.nextInt(); // size of this tree: 0-3
                boolean isMine = in.nextInt() != 0; // 1 if this is your tree
                boolean isDormant = in.nextInt() != 0; // 1 if this tree is dormant
                trees[i] = new Tree(cellIndex, size, isMine, isDormant);
            }

            Game game = new Game(day, nutrients, sun, score, oppSun, oppScore,
                oppIsWaiting, numberOfTrees, trees, numberOfCells, cells);

            int numberOfPossibleActions = in.nextInt(); // all legal actions
            if (in.hasNextLine()) {
                in.nextLine();
            }
            for (int i = 0; i < numberOfPossibleActions; i++) {
                String possibleAction = in.nextLine(); // try printing something from here to start with
                System.err.println(possibleAction);
            }

            Tree higherTree = game.getHigherTree(true);

            // GROW cellIdx | SEED sourceIdx targetIdx | COMPLETE cellIdx | WAIT <message>

            System.out.println("COMPLETE " + higherTree.getCellIndex());
        }
    }
}

class Tree {
    private int cellIndex; // location of this tree
    private int size; // size of this tree: 0-3
    private boolean isMine; // 1 if this is your tree
    private boolean isDormant; // 1 if this tree is dormant

    public Tree(int cellIndex, int size, boolean isMine, boolean isDormant) {
        this.cellIndex = cellIndex;
        this.size = size;
        this.isMine = isMine;
        this.isDormant = isDormant;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isDormant() {
        return isDormant;
    }

    public void setDormant(boolean isDormant) {
        this.isDormant = isDormant;
    }
}
