package Homework_5;

import java.util.Date;

/**
 * Created by ABurykin on 10.08.2016.
 */
// Домашнее задание по теме Exceptions

public class Main {
    public static void main(String[] args){

        TerminalImpl terminal = new TerminalImpl(1234);

        // попытка совершить операцию без ввода пинкода
        terminal.checkMoney();

        terminal.runTerminal(); // вводим пинкод

        // вызов операции после ввода пинкода
        terminal.checkMoney();

        terminal.addMoney(102);
        terminal.checkMoney();

        terminal.addMoney(100);
        terminal.checkMoney();

        terminal.spendMoney(101);
        terminal.checkMoney();

        terminal.spendMoney(100);
        terminal.checkMoney();

        terminal.spendMoney(9000);
        terminal.checkMoney();

        terminal.close();
    }
}
