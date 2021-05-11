package biz.duhamel.codingame.springchallenge2021;

public class Cell {
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
