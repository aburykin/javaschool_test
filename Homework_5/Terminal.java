package Homework_5;

/**
 * Created by ABurykin on 10.08.2016.
 */
public interface Terminal {



    //Запуск терминала для ввода пинкода
    public void runTerminal();

    // проверяет, был ли введен пинкод
    public boolean checkInputPinCorrect();

    // проверяет, заблокирован ли Терминал, и если заблокирован, то выводит количество времени, которое осталось для разблокировки
    public void isLocked();

    // проверяет кратность суммы
    public boolean checkSum(int sum);


    public void close();

    public void checkMoney();


    public void addMoney(int sum);


    public void spendMoney(int sum);
}
