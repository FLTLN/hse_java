/**
 * CallGenerator
 */
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.BlockingQueue;

// Генератор рандомных запросов

public class CallGenerator implements Runnable
{
    private BlockingQueue<Call> queue;
    private int floorNum, callNum; 
    private long liveTime, startTime;

    CallGenerator(BlockingQueue<Call> queue, int floorNum, long liveTime)
    {
        this.queue = queue;
        this.floorNum = floorNum;
        this.liveTime = liveTime;
        this.startTime = System.currentTimeMillis();
    }
    public void run(){
        while ((System.currentTimeMillis() - startTime) < liveTime) 
        {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(0, 500 + 1));
            } catch (InterruptedException e) { }
            try 
            {
                int from = ThreadLocalRandom.current().nextInt(0, floorNum + 1);
                int to = ThreadLocalRandom.current().nextInt(0, floorNum + 1);
                while (from == to) 
                {
                    to = ThreadLocalRandom.current().nextInt(0, floorNum + 1);
                }
                queue.put(new Call(from, to, callNum));
            } 
            catch ( InterruptedException e ) 
            {
                Thread.currentThread().interrupt();
            }
            callNum++;
        }
    }
}