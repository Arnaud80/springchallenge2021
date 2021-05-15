//Version Fri May 14 17:29:42 CEST 2021
import java.util.*;
import java.util.ArrayList;
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
    private Tree tree = null;
    private List<Tree> potentialFather = new ArrayList<>(); 

    public int[] getNeights() {
        return neights;
    }

    public void setNeights(int[] neights) {
        this.neights = neights;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

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

    public List<Cell> getNeightsCell(Cell[] cells, int profondeur) {
        List<Cell> cellNeights = new ArrayList<>();

        for(int i=0;i<6;i++) {
            if(neights[i]!=-1) {
                cellNeights.add(cells[neights[i]]);
                if(profondeur>1) {
                    int[] neihgtLevel1=cells[neights[i]].getNeights();
                    for(int j=0;j<6;j++) {
                        if(neihgtLevel1[j]!=-1) {
                            cellNeights.add(cells[neihgtLevel1[j]]);
                            if(profondeur>2) {
                                int[] neihgtLevel2=cells[neihgtLevel1[j]].getNeights();
                                for(int k=0;k<6;k++) {
                                    if(neihgtLevel2[k]!=-1) {
                                        cellNeights.add(cells[neihgtLevel2[k]]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return cellNeights;
    }

    public Tree getActiveTreeAround() {
        return null;
    }

    public void addPotentialFater(Tree t) {
        potentialFather.add(t);
    }

    public Tree getBestPotentialFather() {
        return potentialFather
            .stream()
            .filter(t -> !t.isDormant())
            .max(Comparator.comparing(Tree::getSize))
            .orElseThrow(() -> new NoSuchElementException("No best father found"));
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

class Dataset {
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
                cells[cellIndex].setTree(trees[i]);
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

            String action="WAIT";
            
            if(game.getDay()<10 && game.getTreeQtyBySize(2, true)>1 && game.getTreeQtyBySize(0,true)<2) {
                action="SEED";
                try {
                    int [] pairTarget=game.getBestSeedTarget(true);
                    action+=" " + pairTarget[0] + " " + pairTarget[1]; 
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    action="WAIT";
                }
            }
            
            if((game.getDay() <20 || game.getTreeQtyBySize(3, true)==0) && action.equals("WAIT")) {
                action="GROW";
                try {
                    Tree growableTree = game.getBestGrowableTree(true);
                    action+=" " + growableTree.getCellIndex();
                } catch (Exception growException) {
                    System.err.println(growException.getMessage());
                    action="WAIT";
                }
            }

            if(action.equals("WAIT")) {
                action="COMPLETE";
                try {
                    Tree higherTree = game.getHigherTree(true);
                    action+=" " + higherTree.getCellIndex();
                } catch (Exception compleException) {
                    System.err.println("No tree to complete");
                    action="WAIT";
                }
            }
            
            // GROW cellIdx | SEED sourceIdx targetIdx | COMPLETE cellIdx | WAIT <message>

            System.out.println(action);
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
