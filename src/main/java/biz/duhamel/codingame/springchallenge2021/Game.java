package biz.duhamel.codingame.springchallenge2021;

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

    public Tree getHigherTree(boolean b) {
        Stream<Tree> stream = Stream.of(trees);

        List<Tree> myTrees;

        myTrees = stream
            .filter(t -> t.isMine())
            .filter(t -> t.getSize()>2)
            .filter(t -> !t.isDormant())
            .collect(Collectors.toList());

        Tree higherTree = myTrees
            .stream()
            .max(Comparator.comparing(Tree::getSize))
            .orElseThrow(() -> new NoSuchElementException("No max tree found"));

        return higherTree;
    }

    public Tree getBestGrowableTree(boolean b) {
        Stream<Tree> stream = Stream.of(trees);

        List<Tree> myTrees;

        myTrees = stream
            .filter(t -> t.isMine())
            .filter(t -> t.getSize()<3)
            .filter(t -> !t.isDormant())
            .collect(Collectors.toList());

        Tree growableTree = myTrees
            .stream()
            .max(Comparator.comparing(Tree::getSize))
            .orElseThrow(() -> new NoSuchElementException("No growable tree found"));

        return growableTree;
    }
}
