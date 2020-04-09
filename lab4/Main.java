import java.io.IOException;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;

import java.nio.charset.StandardCharsets;


public class Main 
{
    public static void main(final String args[])
    {
        Scanner in = new Scanner(System.in);
        // Не самое понятное описание того, что нужно вводить, но придумать что-то понятнее я не смог
        System.out.println("Введите путь к исходному файлу");

        String inPath = in.nextLine();

        System.out.println("Введите путь к выходному файлу");
        System.out.println("Выходной файл будет перезаписан, если уже существует");

        String outPath = in.nextLine();

        SymbolCounter counter = new SymbolCounter();

        try 
        {
            // Нашёл замечательный способ обработать все строки в файле в одну строку кода
            // лямбда-выражения это круто
            // там ещё были умные слова про Stream API, но что это такое я не читал
            Files.lines(Paths.get(inPath), StandardCharsets.UTF_8).forEach(counter::count);
        } 
        catch (IOException x) 
        {
            System.err.println("Ошибка чтения файла");
            System.err.println("Проверьте путь к исходному файлу");
        }
    
        try
        {
            // Вообщем, модуль Files мне ну очень понравился
            Files.write(Paths.get(outPath), counter.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } 
        catch (IOException x) 
        {
            System.err.println("Ошибка записи в файл");
        }
    }    
}
