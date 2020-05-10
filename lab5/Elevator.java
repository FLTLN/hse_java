/**
 * Elevator
 */

import java.util.ArrayList;

// Собственно, лифт

public class Elevator 
{
    private int currentFloor, destFloop, num, callsNum;
    ArrayList<Call> scedule;

    Elevator(int num)
    {
        this.num = num; // Номер лифта

        this.scedule = new ArrayList<Call>(); // Очередь задач лифта
    }

    public int getCurrent() {return currentFloor; };
    public int getDest() {return destFloop; };
    public int getCallsNum() {return callsNum; };

    // Добавление новой задачи
    public void addCall(Call call)
    {
        System.out.println("Call " + Integer.toString(call.getNum()) + " sceduled to elevator " + Integer.toString(num));
        

        // В зависимости от направления движения лифта, изменяем точку назначения
        if(currentFloor < destFloop)
        {
            if(call.getTo() > destFloop)
            {
                destFloop = call.getTo();
            }
        }
        else if(currentFloor > destFloop)
        {
            if(call.getTo() < destFloop)
            {
                destFloop = call.getTo();
            }        
        }
        else
        {
            // Если лифт стоит, выбираем в качестве точки назначения самую удалённую из вызовов
            // Все точки назначения будут либо выше, либо ниже текушего этажа, так-как остановится лифт в самой последней точке по ходу движения
            int diff = 0;
            for(int i = 0; i < scedule.size(); i++ )
            {
                if (Math.abs(currentFloor - scedule.get(i).getTo()) > diff) 
                {
                    diff = Math.abs(currentFloor - scedule.get(i).getTo());
                    destFloop = scedule.get(i).getTo();
                }
            }
        }
        scedule.add(call);
    }

    // Изменение состояния лифта
    public void tick()
    {
        for(int i = 0; i < scedule.size(); i++ )
        {
            // Для простоты, посадка-высадка пассажиров происходит моментально
            // Сажаем
            if((scedule.get(i).getFrom() == currentFloor) && (scedule.get(i).isTaken() == false))
            {
                callsNum +=1;
                scedule.set(i, scedule.get(i).take());
                System.out.println("Call " + Integer.toString(scedule.get(i).getNum()) + 
                                   " passenger taken on floor " + Integer.toString(scedule.get(i).getFrom()) + 
                                   " by elevator " + Integer.toString(num));
            }
            // Высаживаем
            if((scedule.get(i).getTo() == currentFloor) && (scedule.get(i).isTaken()))
            {
                callsNum -=1;
                System.out.println("Call " + Integer.toString(scedule.get(i).getNum()) + 
                                   " passenger delivered to floor " + Integer.toString(scedule.get(i).getTo()) + 
                                   " by elevator " + Integer.toString(num));
                scedule.remove(i);
            }
        }

        // Двигаем лифт
        if(currentFloor < destFloop)
        {
            currentFloor+=1; 
        }
        else if(currentFloor > destFloop)
        {
            currentFloor-=1;
        }
        else
        {
            // Если лифт стоит, выбираем в качестве точки назначения самую удалённую из вызовов
            // Все точки назначения будут либо выше, либо ниже текушего этажа, так-как остановится лифт в самой последней точке по ходу движения
            int diff = 0;
            for(int i = 0; i < scedule.size(); i++ )
            {
                if (Math.abs(currentFloor - scedule.get(i).getTo()) > diff) 
                {
                    diff = Math.abs(currentFloor - scedule.get(i).getTo());
                    destFloop = scedule.get(i).getTo();
                }
            }
        }
    }
    
}