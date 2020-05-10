// Класс-контейнер для одного вызова лифта
public class Call 
{
    private int from, to, num;
    private boolean taken;

    Call(int from, int to, int num)
    {
        this.from = from;
        this.to = to;
        this.num = num;
        this.taken = false;
    }
    
    public int getFrom() {return from; };
    public int getTo() {return to; };
    public int getNum() {return num; };
    public Call take()
    {
        taken = true;
        return this;
    };
    public boolean isTaken() {return taken; };
}