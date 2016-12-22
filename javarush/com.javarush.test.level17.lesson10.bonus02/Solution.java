package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        try
        {
            if (args.length > 0)
            {
                if (args[0].equals("-c"))
                {
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(args));
                    list.remove(0);

                    adding(list);
                } else if (args[0].equals("-u"))
                {
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(args));
                    list.remove(0);

                    updating(list);
                } else if (args[0].equals("-d"))
                {
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(args));
                    list.remove(0);

                    deleting(list);
                } else if (args[0].equals("-i"))
                {
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(args));
                    list.remove(0);
                    print(list);
                }

            }
        } catch (ParseException ex) { ex.printStackTrace(); }


    }
    public static synchronized void adding(ArrayList<String> list) throws ParseException {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(1).equals("м"))
            {
                Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(list.get(2));
                allPeople.add(Person.createMale(list.get(0), date));
                list.remove(0);
                list.remove(0);
                list.remove(0);
            } else if (list.get(1).equals("ж"))
            {
                Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(list.get(2));
                allPeople.add(Person.createFemale(list.get(0), date));
                list.remove(0);
                list.remove(0);
                list.remove(0);
            }
            System.out.println(allPeople.size()-1);
        }
    }
    public static synchronized void updating(ArrayList<String> list) throws ParseException {
        for (int i = 0; i < list.size(); i++)
        {
            allPeople.get(Integer.parseInt(list.get(0))).setName(list.get(1));
            if (list.get(2).equals("м")) { allPeople.get(Integer.parseInt(list.get(0))).setSex(Sex.MALE); }
            else if (list.get(2).equals("ж")) { allPeople.get(Integer.parseInt(list.get(0))).setSex(Sex.FEMALE); }
            Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(list.get(3));
            allPeople.get(Integer.parseInt(list.get(0))).setBirthDay(date);
            list.remove(0);
            list.remove(0);
            list.remove(0);
            list.remove(0);
        }
    }
    public static void deleting(ArrayList<String> list) throws ParseException {
        for (int i = 0; i < list.size(); i++)
        {
            allPeople.get(Integer.parseInt(list.get(i))).setName(null);
            allPeople.get(Integer.parseInt(list.get(i))).setSex(null);
            allPeople.get(Integer.parseInt(list.get(i))).setBirthDay(null);

        }

    }
    public static void print(ArrayList<String> list) throws ParseException {
        for (int i = 0; i < list.size(); i++)
        {
            if (allPeople.get(Integer.parseInt(list.get(i))).getSex().equals(Sex.MALE))
                System.out.println(allPeople.get(Integer.parseInt(list.get(i))).getName() + " м " + output.format(allPeople.get(Integer.parseInt(list.get(i))).getBirthDay()));
            else if (allPeople.get(Integer.parseInt(list.get(i))).getSex().equals(Sex.FEMALE))
                System.out.println(allPeople.get(Integer.parseInt(list.get(i))).getName() + " ж " + output.format(allPeople.get(Integer.parseInt(list.get(i))).getBirthDay()));
        }

    }
}