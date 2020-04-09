import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.lang.*; 

public class NameSplitter 
{
    // Работает с ФИО + дата рождения и с просто ФИО, записывая NA вместо возраста
    // По уму надо выбрасывать разные исключения, это позволит лучше их обратавать (например, писать что именно неправильно в строке)
    // Это я не сделал, потому что было лень
    NameSplitter(String text) // throws something я бы дописал, если бы класс выбрасывал какие-то самописные исключения
                              // с перечислением этих исключений вместо something
    {
        String[] splitted = text.split(" ");
        if((splitted.length < 3) || (splitted.length > 5))
        {
            throw new RuntimeException();
        }

        F = splitted[0];
        I = splitted[1];
        O = splitted[2];

        char s = O.charAt(O.length() - 1);

        switch (s) 
        {
            case 'а':
                sex = "жен";
                break;
            case 'ч':
                sex = "муж";
                break;
            default:
                throw new RuntimeException();
        }

        if (splitted.length > 3)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            // Здесь может возникнуть исключение, try/catch нет так как он есть в мейне
            LocalDate burthDate = LocalDate.parse(splitted[3], formatter);
            
            Period period = Period.between(burthDate, LocalDate.now());

            int ageInt = period.getYears();
 
            if (ageInt < 0)
            {
                throw new RuntimeException();
            }

            age = Integer.toString(ageInt);
            bDate = splitted[3];
        }
        else
        {
            age = "NA";
            bDate = "NA";
        }

    };

    public String getF() {return F; };
    public String getI() {return I; };
    public String getO() {return O; };

    public String getSex() {return sex; };
    public String getAge() {return age; };
    public String getDateOfBirth() {return bDate; };

    public char getIShort() {return I.charAt(0); };
    public char getOShort() {return O.charAt(0); };    

    private String F, I, O; //Да, транслитом
    private String age, bDate;
    private String sex;

}