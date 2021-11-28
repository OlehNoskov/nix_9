package ua.com.alevel.util;

import ua.com.alevel.db.impl.DepartmentDBImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CustomCSVWrite {

    public static boolean addHeaderCSVFile(List listEntity) {
        if (listEntity.size() == 0) {
            return true;
        } else
            return false;
    }

    public CustomCSVWrite(String nameFile) {
        Path pathFile = Paths.get(nameFile);
        try{
            boolean existsFile = Files.exists(pathFile);
            if(!existsFile){
                Files.createFile(pathFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSVFile(List<String[]> entity) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(DepartmentDBImpl
                .getPathFileDepartments()))) {
            for (int i = 0; i < entity.size(); i++) {
                for (int b = 0; b < entity.get(i).length; b++) {
                    bufferedWriter.write(entity.get(i)[b]);
                    if (b != entity.get(i).length - 1) {
                        bufferedWriter.write(",");
                    }
                    if (i != entity.size() - 1) {
                        bufferedWriter.write("\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        }
    }
}