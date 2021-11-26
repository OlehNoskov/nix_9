package ua.com.alevel.graphs;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestWays {
    private final String INPUT_FILE = "inputCities.txt";
    private final String OUTPUT_FILE = "outputCostPaths.txt";
    private int countCities = 10000;
    private final int INFINITY = 10000000;

    private List<Path> shortestPaths;
    private Town citiesArray[];
    private int relationMatrix[][];

    private int countOfVertices;
    private int countOfVertexInTree;
    private int currentVertex;
    private int startToCurrent;

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            while (reader.ready()) {
                countCities = Integer.parseInt(reader.readLine());
                addingAllTown();
                for (int i = 0; i < countCities; i++) {
                    String nameTown = reader.readLine();
                    addTown(nameTown, i);
                    int countOfNeighbours = Integer.parseInt(reader.readLine());
                    for (int j = 0; j < countOfNeighbours; j++) {
                        String pathToNeighbour = reader.readLine();
                        String[] substrings = pathToNeighbour.split(" ");
                        if (substrings.length != 2) {
                            throw new RuntimeException("Некорректные данные!");
                        }
                        addRegion(i, Integer.parseInt(substrings[0]) - 1, Integer.parseInt(substrings[1]));
                    }
                }
                int countOfWays = Integer.parseInt(reader.readLine());
                for (int j = 0; j < countOfWays; j++) {
                    String citiesToFindWay = reader.readLine();
                    String[] substrings = citiesToFindWay.split(" ");
                    if (substrings.length != 2) {
                        throw new RuntimeException("Некорректные входящие данные!");
                    }
                    Town currentCity = Arrays.stream(citiesArray)
                            .filter(city -> city.getName().equals(substrings[0]))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Некорректные входящие данные!"));

                    Town finalCity = Arrays.stream(citiesArray)
                            .filter(city -> city.getName().equals(substrings[1]))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Некорректные входящие данные!!"));
                    shortestWays(currentCity.getIndex(), finalCity.getIndex(), writer);
                    cleanData();
                }
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Некорректные данные!");
        }
    }

    private void writeToFileCostWays(int startTree, int finalIndex, BufferedWriter writer) {
        String shortestWay = "";
        if (shortestPaths.get(finalIndex).getDistance() == INFINITY) {
            shortestWay = "Путь очень длинный!";
        } else {
            String result = shortestPaths.get(finalIndex).getDistance() + " ";
            shortestWay = shortestWay + result;
        }
        try {
            writer.write(shortestWay);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showingCostWaysToConsole(int startTree, int finalIndex) {
        System.out.print("Стоимость пути из города " + citiesArray[startTree].getName() + " до города "
                + citiesArray[finalIndex].getName() + " = ");
        if (shortestPaths.get(finalIndex).getDistance() == INFINITY) {
            System.out.println("Очень длинный путь!");
        } else {
            String costWay = shortestPaths.get(finalIndex).getDistance() + " ";
            System.out.println(costWay);
        }
    }

    private void shortestWays(int start, int end, BufferedWriter bufferedWriter) {
        citiesArray[start].setInTree(true);
        countOfVertexInTree = 1;

        for (int i = 0; i < countOfVertices; i++) {
            int tempDist = relationMatrix[start][i];
            Path cityPath = new Path(tempDist);
            cityPath.getParentVertices().add(start);
            shortestPaths.add(cityPath);
        }
        while (countOfVertexInTree < countOfVertices) {
            int indexMin = getMin();
            int minDist = shortestPaths.get(indexMin).getDistance();
            if (minDist == INFINITY) {
                break;
            } else {
                currentVertex = indexMin;
                startToCurrent = shortestPaths.get(indexMin).getDistance();
            }

            citiesArray[currentVertex].setInTree(true);
            countOfVertexInTree++;
            updateCostWays();
        }
        showingCostWaysToConsole(start, end);
        writeToFileCostWays(start, end, bufferedWriter);
    }

    private void updateCostWays() {
        int vertexIndex = 1;
        while (vertexIndex < countOfVertices) {
            if (citiesArray[vertexIndex].isInTree()) {
                vertexIndex++;
                continue;
            }
            int currentToFringe = relationMatrix[currentVertex][vertexIndex];
            int startToFringe = startToCurrent + currentToFringe;
            int shortPathDistance = shortestPaths.get(vertexIndex).getDistance();
            if (startToFringe < shortPathDistance) {
                List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentVertex).getParentVertices());
                newParents.add(currentVertex);
                shortestPaths.get(vertexIndex).setParentVertices(newParents);
                shortestPaths.get(vertexIndex).setDistance(startToFringe);
            }
            vertexIndex++;
        }
    }

    private int getMin() {
        int min = INFINITY;
        int indexMin = 0;
        for (int i = 1; i < countOfVertices; i++) {
            if (!citiesArray[i].isInTree() && shortestPaths.get(i).getDistance() < min) {
                min = shortestPaths.get(i).getDistance();
                indexMin = i;
            }
        }
        return indexMin;
    }


    private void addTown(String nameTown, int index) {
        citiesArray[countOfVertices++] = new Town(nameTown, index);
    }

    private void addRegion(int start, int end, int cost) {
        relationMatrix[start][end] = cost;
    }

    private void addingAllTown() {
        citiesArray = new Town[countCities];
        relationMatrix = new int[countCities][countCities];
        countOfVertices = 0;
        countOfVertexInTree = 0;
        for (int i = 0; i < countCities; i++) {
            for (int k = 0; k < countCities; k++) {
                relationMatrix[i][k] = INFINITY;
                shortestPaths = new ArrayList<>();
            }
        }
    }

    private void cleanData() {
        countOfVertexInTree = 0;
        for (int i = 0; i < countOfVertices; i++) {
            citiesArray[i].setInTree(false);
        }
        countOfVertexInTree = 0;
        for (int i = 0; i < countCities; i++) {
            for (int k = 0; k < countCities; k++) {
                shortestPaths = new ArrayList<>();
            }
        }
    }
}