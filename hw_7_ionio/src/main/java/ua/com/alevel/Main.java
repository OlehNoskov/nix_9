package ua.com.alevel;

//import ua.com.alevel.controller.impl.BaseControllerImpl;
import ua.com.alevel.util.CustomCSVRead;
import ua.com.alevel.util.CustomCSVWrite;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        new BaseControllerImpl().run();

        List<String[]> data = new ArrayList<>();

        String[] header = { "id",  "firstName","lastName", "age"};
        data.add(header);

        String[] strings1 = {"1","Oleg1","Noskov1", "28"};
        String[] strings2 = {"2","Oleg1","Noskov1", "28"};
        String[] strings3 = {"3","Oleg1","Noskov1", "28"};
        String[] strings4 = {"4","Oleg1","Noskov1", "28"};
        data.add(strings1);
        data.add(strings2);
        data.add(strings3);
        data.add(strings4);
        CustomCSVWrite.writeToCSVFile(data,"hw_7_ionio/departments.csv");
        CustomCSVRead.readCSVFile("hw_7_ionio/departments.csv");
    }
}