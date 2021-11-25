package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.*;

public class Main {


    public static void main(String[] args) throws SQLException {
        UserService usi = new UserServiceImpl();

        //Создание таблицы users
        usi.createUsersTable();

        //Добавление 4 записей в таблицу users
        usi.saveUser("Ivan", "Ivanov", (byte) 100);
        usi.saveUser("Petr", "Petrov", (byte) 50);
        usi.saveUser("Sergey", "Sergeev", (byte) 5);
        usi.saveUser("Fedor", "Fedorov", (byte) 1);

        //Получение всех записей таблицы users
        usi.getAllUsers();

        //Очистка таблицы users
        usi.cleanUsersTable();

        //Удаление таблицы users
        usi.dropUsersTable();
    }
}
