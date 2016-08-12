package Homework_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Created by ABurykin on 10.08.2016.
 */


/*
3) Реализовать интерфейс Terminal, c помощью которого можно:
1) Проверить состояние счета
2) Снять/ положить деньги

+Доступ к терминалу должен предоставлять только после корректного ввода пина.
+При попытке вызова любого метода без ввода пина, должен кидаться ексепшен. // возможно только если нет интерфейса

+При вводе 3 неправильных пинов, аккаунт лочится на 5сек
+(при попытке обраться к нему вылетает AccountIsLockedException c информацией о времени разлочения).

+ Класть и снимать деньги можно только, если сумма кратна 100.

Т.к банкоматы, которые стоят на улице ничего сами не делают с вашим счетом,
они всего лишь делают проверку введенных данных
и отправляют запросы на удаленный сервер(TerminalServer).
TerminalServer может кидать исключения связанные с проблемами сети; когда недостаточно денег


Вопросы:
1) как если аккаунт залочен на 5 сек, как без второго потока мне к нему обратиться?

 */

public class TerminalImpl implements Terminal
{
    private int correctPin; // верный пинкод
    private boolean InputPinCorrect = false; // пинкод был введен верно
    private int countTryPin = 0; // количество попыток ввести верный пинкод
    private long timeLocked = 0L; // время разблокирования

    TerminalServer server = new TerminalServer();

    TerminalImpl(int newPin){
        this.correctPin = newPin;

        server.connect(2222);
        server.connect(1523);
    }

    //Запуск терминала для ввода пинкода
    @Override
    public void runTerminal(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.print("Введите пинкод: ");

            int inputPin = 0;
            try {
                inputPin = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("Ошибка в методе  runTerminal: " + e);
            } catch (NumberFormatException e) {
                System.out.println("Введено не число!");
            }

            if (correctPin  != inputPin){
                countTryPin++;
                InputPinCorrect = false;
                if (countTryPin == 3){
                    System.out.println("Терминал заблокирован на 5 секунд.");
                    Date currTime = new Date();
                    timeLocked = currTime.getTime()+5000;
                    isLocked();

                }
            } else {
                System.out.println("Пин код введен верно. Теперь можно выполнить действие.");
                InputPinCorrect = true;
                break;
            }
        }

    }

    // проверяет, был ли введен пинкод
    @Override
    public boolean checkInputPinCorrect(){
        if (!InputPinCorrect){
            // кидаю исключание что попытка выполнения операции без пинкода
            try {
                throw new Exception("Попытка выполнения операции без ввода пинкода.");
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }
        return true;



}

    // проверяет, заблокирован ли Терминал, и если заблокирован, то выводит количество времени, которое осталось для разблокировки
    @Override
    public void isLocked(){
        Date date = new Date();
        long currTime = date.getTime();
        while (currTime < timeLocked) {
            Date currDate = new Date();
            currTime =currDate.getTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double zzz = (timeLocked - currTime)/1000;
            try {
                throw new AccountIsLockedException();
            } catch (AccountIsLockedException e) {
                System.out.println("Осталось до разблокирования "+ zzz); // создаю ситуацию, как будто к терминалу обращаются и он ругается, что заблокирован.
            }

        }
        System.out.println("Терминал разблокирован.");
    }

    // проверяет кратность суммы
    @Override
    public boolean checkSum(int sum){
        if (sum%100 != 0){
            System.out.println("Сумма "+ sum +" не кратна 100. Операция отменена.");
            return false;
        }
        return true;
    }

    @Override
    public void checkMoney(){
        if (checkInputPinCorrect())
            server.checkMoney();
    }

    @Override
    public void addMoney(int sum){
        if (checkSum(sum))
            server.addMoney(sum);
    }

    @Override
    public void spendMoney(int sum){
        if (checkSum(sum))
            try {
                server.spendMoney(sum);
            } catch (ExceptionNoMoney e) {
                    System.out.println(e.getMessage());
            }
    }

    @Override
    public void close() {
        System.out.println("Работа с терминалом завершена");
    }
}
