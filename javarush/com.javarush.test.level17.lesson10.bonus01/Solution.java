package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1

    }

    public static void main(String[] args) {
        // input = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        try
        {
            if (args.length > 0 && args.length <= 5)
            {
                if (args[0].equals("-c") && args.length == 4)
                {
                    String name = args[1];
                    String sex = args[2];
                    String bd = args[3];
                    Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(bd);

                    adding(name, sex, date);
                } else if (args[0].equals("-u") && args.length == 5)
                {
                    int index = Integer.parseInt(args[1]);
                    String name = args[2];
                    String sex = args[3];
                    String bd = args[4];
                    Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(bd);

                    updating(index, name, sex, date);
                } else if (args[0].equals("-d") && args.length == 2)
                {
                    int index = Integer.parseInt(args[1]);

                    deleting(index);
                } else if (args[0].equals("-i") && args.length == 2)
                {
                    int index = Integer.parseInt(args[1]);

                    print(index);
                }

            }
        } catch (ParseException ex) { ex.printStackTrace(); }

    }
    public static void adding(String name, String sex, Date bd) {
        if (sex.equals("м")) {
            allPeople.add(Person.createMale(name, bd));
        } else if (sex.equals("ж")) {
            allPeople.add(Person.createFemale(name, bd));
        }
        System.out.println(allPeople.size()-1);
    }
    public static void updating(int index, String name, String sex, Date bd) {
        allPeople.get(index).setName(name);
        if (sex.equals("м")) { allPeople.get(index).setSex(Sex.MALE); }
        else if (sex.equals("ж")) { allPeople.get(index).setSex(Sex.FEMALE); }
        allPeople.get(index).setBirthDay(bd);
    }
    public static void deleting(int index) {
        allPeople.get(index).setName(null);
        allPeople.get(index).setSex(null);
        allPeople.get(index).setBirthDay(null);
    }
    public static void print(int index) {
        if (allPeople.get(index).getSex().equals(Sex.MALE))
        System.out.println(allPeople.get(index).getName() + " м " + output.format(allPeople.get(index).getBirthDay()));
        else if (allPeople.get(index).getSex().equals(Sex.FEMALE))
            System.out.println(allPeople.get(index).getName() + " ж " + output.format(allPeople.get(index).getBirthDay()));
    }
}
