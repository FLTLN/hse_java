/**
 * ElevatorSceduler
 */

import java.util.concurrent.BlockingQueue;

// Управление лифтами
public class ElevatorSceduler implements Runnable
{
    private BlockingQueue<Call> queue;
    private int tick;
    private long liveTime, startTime;
    Elevator[] elevators;

    ElevatorSceduler(BlockingQueue<Call> queue, int elevatorsNum, long liveTime)
    {
        this.queue = queue;
        this.liveTime = liveTime;
        this.startTime = System.currentTimeMillis();
        this.elevators = new Elevator[elevatorsNum];
        for(int i = 0; i < elevators.length; i++)
        {
            elevators[i] = new Elevator(i);
        }
    }
    // Алгоритм выбора лифта для запроса
    // Не претендует на оптимальность и адекватность, он просто есть
    private int chooseElevator(Elevator[] elevators, Call call)
    {
        int cheduleTo = 0;
        int score = -1000;
        // Определяем направление вызова
        int callDirection = call.getFrom() - call.getTo();
        for(int i = 0; i < elevators.length; i ++)
        {
            // Определяем направление движения лифта
            int elevatorDirection = elevators[i].getCurrent() - elevators[i].getDest();
            boolean isSameDirection = true;
            if ((callDirection * elevatorDirection) < 0)
            {
                isSameDirection = false;
            }

            int currentScore = 0;

            // Если лифт движется по пути с вызовом
            if(isSameDirection)
            {
                // Если лифт на этаже вызова, значительно повышаем приоритет
                if(call.getFrom() == elevators[i].getCurrent())
                {
                    currentScore += 500;
                }

                // Понижаем приоритет на количество человек в лифте
                currentScore -= elevators[i].getCallsNum();
                
                // Если лифт выше(или ниже, в зависимоти от направления движения) точки вызова
                // Значительно понижаем приоритет
                if(elevatorDirection < 0)
                {
                    if(call.getFrom() < elevators[i].getCurrent())
                    {
                        currentScore -= 500;
                    }
                }
                else
                {
                    if(call.getFrom() > elevators[i].getCurrent())
                    {
                        currentScore -= 500;
                    }
                }
                
                // Понижаем приоритет на расстояние между текущей точкой и точкой вызова
                currentScore -= Math.abs(call.getFrom() - elevators[i].getCurrent());
                // Повышаем приоритет на расстояние, которое лифт уже гарантированно пройдёт
                currentScore += Math.abs(elevators[i].getCurrent() - elevators[i].getDest());
            }
            // Если лифт движется в обратном направлении с вызовом
            else if(!isSameDirection)
            {
                // Понижаем приоритет на количество человек в лифте
                currentScore -= elevators[i].getCallsNum();
                // Понижаем приоритет на расстояние, которое лифт уже гарантированно пройдёт
                currentScore -= Math.abs(elevators[i].getCurrent() - elevators[i].getDest());
                // Понижаем приоритет на расстояние между точкой назначения лифта и точкой вызова
                currentScore -= Math.abs(call.getFrom() - elevators[i].getDest());
            }
            else
            {
                // Понижаем приоритет на расстояние между текущей точкой и точкой вызова
                currentScore -= Math.abs(call.getFrom() - elevators[i].getCurrent());   
            }
            // Возможно, рандомный выбор работал бы не хуже этого алгоритма
            // В любом случае, два потока есть, а дз больше про них чем про алгоритм

            if(currentScore > score)
            {
                score = currentScore;
                cheduleTo = i;
            }
        }

        return cheduleTo;
    }

    public void run(){
        int stillProcessing = 0;
        while (((System.currentTimeMillis() - startTime) < liveTime) || (stillProcessing > 0))
        {
            stillProcessing = 0;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { }

            System.out.println("\nTick " + Integer.toString(tick) + "\n");
            tick+=1;

            while (queue.peek() != null) 
            {
                try 
                {
                    Call call = queue.take();
                    elevators[chooseElevator(elevators, call)].addCall(call);
                } 
                catch ( InterruptedException e ) 
                {
                    Thread.currentThread().interrupt();
                }
            }

            for(int i = 0; i < elevators.length; i++)
            {
                elevators[i].tick();
                stillProcessing += elevators[i].getCallsNum();
            }

        }
    }
    
}