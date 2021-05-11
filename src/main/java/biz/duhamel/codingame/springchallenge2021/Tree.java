package biz.duhamel.codingame.springchallenge2021;

public class Tree {
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