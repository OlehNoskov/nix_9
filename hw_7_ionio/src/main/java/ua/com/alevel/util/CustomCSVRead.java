package ua.com.alevel.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomCSVRead {

    public static void readCSVFile(String fileName){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            List<String[]> listEntity = new ArrayList<>();
            while (bufferedReader.ready()){
                String[] line = bufferedReader.readLine().split(",");
                listEntity.add(line);
            }
            for(String[] str: listEntity){
                for(String str2: str){
                    System.out.print(str2+" ");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла!");
        }
    }
}