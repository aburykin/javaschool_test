package Homework_5;

/**
 * Created by ABurykin on 10.08.2016.
 */
public class TerminalServer {

    private int cash = 1000; // наличка на счете
    private int port = 1523; // порт по которому нужно подключаться

    public void checkMoney(){
            System.out.println(" На счету сейчас " + cash + " рублей.");
    }

    public void addMoney(int sum){
        this.cash += sum;
    }

    public void spendMoney(int sum) throws ExceptionNoMoney{
        if (sum > this.cash) {
            throw new ExceptionNoMoney("На счету не достаточно денег! ");
        } else {
            this.cash -= sum;
        }
    }

    public void connect(int portConnected){
        if (portConnected != port){
            try {
                throw new PortException("Не возможно подключиться к серверу через порт: " + portConnected);
            } catch (PortException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
