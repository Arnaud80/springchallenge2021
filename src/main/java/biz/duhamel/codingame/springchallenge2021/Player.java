package biz.duhamel.codingame.springchallenge2021;

import java.util.*;

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
            
            if(game.getDay()<10 && game.getTreeQtyBySize(0,true)<2) {
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
