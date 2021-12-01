package ua.com.alevel.cities;

public class Town {
    private String name;
    private int index;
    private boolean isInTree;

    public Town(String name, int index) {
        this.name = name;
        this.isInTree = false;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public boolean isInTree() {
        return isInTree;
    }

    public void setInTree(boolean inTree) {
        isInTree = inTree;
    }
}