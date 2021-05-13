package biz.duhamel.codingame.springchallenge2021;

public class Dataset {
    private int day = 23;
    private int nutrients = 24;
    private int sun = 10;
    private int score = 0;
    private int oppSun = 20;
    private int oppScore = 0;
    private boolean oppIsWaiting = false;
    private int numberOfTrees = 4;
    private Tree[] trees = new Tree[]{ 
        new Tree(0, 0, false, false), 
        new Tree(1, 1, true, false),
        new Tree(2, 2, true, true),
        new Tree(3, 2, false, false),
        new Tree(4, 1, true, true),
        new Tree(5, 3, true, false) };
    private int numberOfCells = 11;
    private Cell[] cells = new Cell[] { 
        new Cell(0, 3, new int[] { 0, 1, 2, 3, 4, 5 }),
        new Cell(1, 3, new int[] { 7, 8, 2, 0, 6, 18 }),
        new Cell(2, 3, new int[] { 8, 9, 10, 3, 0, 1 }),
        new Cell(3, 3, new int[] { 2, 10, 11, 12, 4, 0 }),
        new Cell(4, 3, new int[] { 0, 3, 12, 13, 14, 5 }),
        new Cell(5, 3, new int[] { 6, 0, 4, 14, 15, 16 }),
        new Cell(6, 3, new int[] { 18, 1, 0, 5, 16, 17 }),
        new Cell(7, 2, new int[] { 19, 20, 8, 1, 18, 36 }),
        new Cell(8, 2, new int[] { 20, 21, 9, 2, 1, 7 }), // To update
        new Cell(9, 2, new int[] { 21, 22, 23, 10, 2, 8 }), // To update
        new Cell(10, 2, new int[] { 9, 23, 24, 11, 3, 2 }), // To update
        new Cell(11, 2, new int[] { 10, 24, 25, 26, 12, 3 }), // To update
        new Cell(12, 2, new int[] { 3, 11, 26, 27, 13, 4 }), // To update
        new Cell(13, 2, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(14, 2, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(15, 2, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(16, 2, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(17, 2, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(18, 2, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(19, 1, new int[] { -1, -1, 20, 7, 36, -1 }),
        new Cell(20, 1, new int[] { -1, -1, 21, 8, 7, 19 }),
        new Cell(21, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(22, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(23, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(24, 0, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(25, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(26, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(27, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(28, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(29, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(30, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(31, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(32, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(33, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(34, 0, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(35, 1, new int[] { -1, -1, -1, -1, -1, -1 }), // To update
        new Cell(36, 1, new int[] { -1, 19, 7, 18, 35, -1 }),
     };
    
    public Game getSimpleGameset() {
        return new Game(day, nutrients, sun, score, 
            oppSun, oppScore, oppIsWaiting, numberOfTrees, trees, numberOfCells, cells);
    }

    public Game getTwoMaxGameSet() {
        Tree[] newtrees = new Tree[] { 
            new Tree(0, 0, false, false), 
            new Tree(1, 1, true, false),
            new Tree(2, 3, true, false),
            new Tree(3, 2, false, false),
            new Tree(4, 3, true, false) };
            
        return new Game(day, nutrients, sun, score, 
            oppSun, oppScore, oppIsWaiting, numberOfTrees, newtrees, numberOfCells, cells);
    }

    public Game getWithoutMaxGameSet() {
        Tree[] newtrees = new Tree[] { 
            new Tree(0, 0, false, false), 
            new Tree(1, 1, true, false),
            new Tree(2, 2, true, false),
            new Tree(3, 2, false, false),
            new Tree(4, 2, true, false) };

        return new Game(day, nutrients, sun, score, 
            oppSun, oppScore, oppIsWaiting, numberOfTrees, newtrees, numberOfCells, cells);
    }

    public Game getWithoutGrowableGameSet() {
        Tree[] newtrees = new Tree[] { 
            new Tree(0, 3, false, false), 
            new Tree(1, 3, true, false),
            new Tree(2, 3, true, false),
            new Tree(3, 3, false, false),
            new Tree(4, 3, true, false) };

        return new Game(day, nutrients, sun, score, 
            oppSun, oppScore, oppIsWaiting, numberOfTrees, newtrees, numberOfCells, cells);
    }

    public Game getTreeLocatedAsBronzeLigueStart() {
        Tree[] newtrees = new Tree[]{ 
            new Tree(20, 1, true, false), 
            new Tree(26, 1, true, false),
            new Tree(29, 1, false, false),
            new Tree(35, 1, false, false) };

        return new Game(day, nutrients, sun, score, 
            oppSun, oppScore, oppIsWaiting, numberOfTrees, newtrees, numberOfCells, cells);
    }

    public Game getTreeAndSeedAllDormant() {
        Tree[] newtrees = new Tree[]{ 
            new Tree(8, 0, true, true),
            new Tree(15, 0, true, true),
            new Tree(20, 1, true, true), 
            new Tree(26, 1, true, true),
            new Tree(29, 1, false, true),
            new Tree(35, 1, false, true) };
        
        cells[8].setTree(trees[0]);

        return new Game(day, nutrients, sun, score, 
            oppSun, oppScore, oppIsWaiting, numberOfTrees, newtrees, numberOfCells, cells);
    }

    public Game getTreeWithSeed() {
        Tree[] newtrees = new Tree[]{ 
            new Tree(8, 0, true, false),
            new Tree(15, 0, true, false),
            new Tree(20, 1, true, false), 
            new Tree(26, 1, true, false),
            new Tree(29, 1, false, false),
            new Tree(35, 1, false, false) };
        
        cells[8].setTree(trees[0]);

        return new Game(day, nutrients, sun, score, 
            oppSun, oppScore, oppIsWaiting, numberOfTrees, newtrees, numberOfCells, cells);
    }

    public Game getTreeSize2() {
        Tree[] newtrees = new Tree[]{ 
            new Tree(20, 2, true, false), 
            new Tree(26, 1, true, false),
            new Tree(29, 1, false, false),
            new Tree(35, 1, false, false) };

        return new Game(day, nutrients, sun, score, 
            oppSun, oppScore, oppIsWaiting, numberOfTrees, newtrees, numberOfCells, cells);
    }

    public Game getTreeSize3() {
        Tree[] newtrees = new Tree[]{ 
            new Tree(20, 3, true, false), 
            new Tree(26, 1, true, false),
            new Tree(29, 1, false, false),
            new Tree(35, 1, false, false) };

        return new Game(day, nutrients, sun, score, 
            oppSun, oppScore, oppIsWaiting, numberOfTrees, newtrees, numberOfCells, cells);
    }
}
