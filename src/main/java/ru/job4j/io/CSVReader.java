package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Читает данные из CSV файла и выводит их
 * -path=file.csv
 * -delimiter=;
 * -out=stdout || -out=fileExit.csv
 * -filter=name,age
 */
public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        try (Scanner scannerPath = new Scanner(Paths.get(path));
             PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            String[] filterWord = filter.split(",");
           List<Integer> indexList = columnIndex(path, delimiter, filterWord);
           StringBuilder rsl = new StringBuilder();
            while (scannerPath.hasNext()) {
                String[] columns = scannerPath.nextLine().split(delimiter);
                for (int i = 0; i < indexList.size(); i++) {
                    if (scannerPath.hasNext()) {
                        rsl.append(i < filterWord.length - 1
                                ? columns[indexList.get(i)] + delimiter : columns[indexList.get(i)] + System.lineSeparator());
                    } else {
                        rsl.append(i < filterWord.length - 1
                                ? columns[indexList.get(i)] + delimiter : columns[indexList.get(i)]);
                    }
                }
            }
            if ("stdout".equals(out)) {
                System.out.print(rsl);
            } else {
                output.println(rsl);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, который ищет индексы колонок, сравнивая фильтр с первой строкой
     * @param path исходный CSV файл
     * @param delimiter разделитель ячеек в CSV файле
     * @param filter набор столбцов, которые нужно отфильтровать в CSV файле
     * @return
     */
    private static List<Integer> columnIndex(String path, String delimiter, String[] filterWords) {
        List<Integer> indexList = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(path));
             Scanner scannerLine = new Scanner(input.readLine()).useDelimiter(delimiter)) {
            Map<String, Integer> map = new HashMap<>();
            int count = -1;
            while (scannerLine.hasNext()) {
                count++;
                map.put(scannerLine.next(), count);
            }
            for (int i = 0; i < filterWords.length; i++) {
                if (map.containsKey(filterWords[i])) {
                    indexList.add(map.get(filterWords[i]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexList;
    }

    private static void validation(ArgsName argsName, int count) throws IOException {
        if (count < 4) {
            throw new IllegalArgumentException("Program arguments are empty or incomplete");
        }
        if (!Files.isReadable(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException("The file is not readable");
        }
        if (!Files.isWritable(Paths.get(argsName.get("out"))) || "stdout".equals(Files.isWritable(Paths.get(argsName.get("out"))))) {
            throw new IllegalArgumentException("The file is not writable");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validation(argsName, args.length);
        handle(argsName);
    }
}