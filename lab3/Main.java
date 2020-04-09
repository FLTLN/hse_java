import java.util.Scanner;

public class Main 
{
    public static void main(final String args[])
    {
        // Работает с ФИО + дата рождения и с просто ФИО, выводя NA вместо возраста
        System.out.print("Введите ФИО и (опционально) дату рождения в формате дд.мм.гггг\n");
        Scanner in = new Scanner(System.in);
        String rawString = in.nextLine();

        // Перехватываем исключения при обработке, чтобы не пугать пользователя страшным стеком вызова
        try 
        {
            // Основная логика вынесена в отдельный класс, так её можно будет легко перенести в другой проект
            NameSplitter splitted = new NameSplitter(rawString);
            System.out.println(splitted.getF() + " " + 
                               splitted.getIShort() + "." + 
                               splitted.getOShort() + ". пол " + 
                               splitted.getSex() + ". возраст " +
                               splitted.getAge());
            in.close();
        } 
        catch (Exception e) 
        {
            System.out.print("Ошибка обработки!\nПроверьте введённые данные.\n");
            in.close();
        }
    }    
}
