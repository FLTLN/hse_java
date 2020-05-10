import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Main {
    public static void main(String args[ ]) throws InterruptedException
    {
        // Буфер, через который запросы от генератора передаются в класс управления лифтами
        // это thread-save коллекция, взял её, чтобы не синхронизировать всё вручную
        BlockingQueue<Call> queue = new LinkedBlockingQueue<>(10);

        // Для простоты состояние всех лифтов обновляется дискретно
        // Количесво обновлений состояний лифтов, в течении которых будут генерироваться новые запросы
        int nTicks = 200;
        // Количество этажей
        int nFloors = 10;
        // Количество лифтов
        int nElevators = 4;
        // Можно было-бы вынести в параметры

        CallGenerator sender = new CallGenerator(queue, nFloors, nTicks * 500); // nTicks * 500 задержка между обновлениями состояний
        ElevatorSceduler receiver = new ElevatorSceduler(queue, nElevators, nTicks * 500);

        new Thread(sender).start();
        new Thread(receiver).start();
    }
}
 