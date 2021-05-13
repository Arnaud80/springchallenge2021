package biz.duhamel.codingame.springchallenge2021;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Cell {
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
