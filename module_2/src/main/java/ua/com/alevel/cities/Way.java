package ua.com.alevel.cities;

import java.util.ArrayList;
import java.util.List;

public class Way {
    private int distance;
    private List<Integer> parentVertices;

    public Way(int distance) {
        this.distance = distance;
        this.parentVertices = new ArrayList<>();
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Integer> getParentVertices() {
        return parentVertices;
    }

    public void setParentVertices(List<Integer> parentVertices) {
        this.parentVertices = parentVertices;
    }
}