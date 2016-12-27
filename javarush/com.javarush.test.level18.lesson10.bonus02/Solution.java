package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName = reader.readLine();
        FileReader fileReader = new FileReader(fileName);
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        String id, price, quantity;

        if (args.length > 0 && args[0].equals("-c"))
        {

            String s;
            ArrayList<Integer> ids = new ArrayList<>();
            StringBuilder line = null;
            reader = new BufferedReader(fileReader);
            // читаем файл и парсим id каждой строки
            while ((s = reader.readLine()) != null)
            {
                if (s.length() > 8)
                {
                    line = new StringBuilder(s);
                    ids.add(Integer.parseInt(line.substring(0, 8).trim()));
                }
            }

            // определяем максимальный id, инкрементируем его, переводим в String и добавляем проблемы до необходимой длины
            int tempid = max(ids) + 1;
            id = String.valueOf(tempid);
                if (id.length() < 8) {
                    while (id.length() != 8) { id += " "; }
                }
            // добавление в переменную line productName
            line = new StringBuilder();
                for (int i = 1; i < args.length - 2; i++)
                {
                    line.append(args[i]);
                    line.append(" ");

                }
            // выщипываение из line quantity (последние 4 символа) и price (последние 8 символов)

            quantity = args[args.length - 1];
            price = args[args.length - 2];

            // проверка длины строк, если символов недостаточно, то добавляются пробелы, если много, то строки обрезаются
            if (quantity.length() < 4 && quantity.length() != 0)
            {
                while (quantity.length() != 4)
                {
                    quantity += " ";
                }
            } else if (quantity.length() > 4)
            {
                quantity = quantity.substring(0, 4);
            }

            if (price.length() < 8 && price.length() != 0)
            {
                price = (String.format(Locale.ENGLISH, "%(.2f", Double.parseDouble(price.trim())));
                while (price.length() != 8)
                {
                    price += " ";
                }
            } else if (price.length() > 8) {
                price = price.substring(0, 8);
                price = (String.format(Locale.ENGLISH, "%(.2f", Double.parseDouble(price.trim())));
            }
            if (line.length() < 30 && line.length() != 0)
            {
                while (line.length() != 30)
                {
                    line.append(" ");
                }
            } else if (line.length() > 30)
            {
                line = new StringBuilder(line.substring(0, 30));
            }


            // результирующее значение, объединяющее необходимые строки
            StringBuilder result = new StringBuilder();
            result.append(id);
            result.append(line);
            result.append(price);
            result.append(quantity);

            // запись в файл
            writer.write("\n");
            writer.write(result.toString());

            writer.close();
            reader.close();
        }

    }

    static public int max(ArrayList<Integer> ids) {
        int max = 0;
            for(int i = 0; i < ids.size(); i++)
            {
                if (ids.get(i) > max) { max = ids.get(i); }
            }
        return max;
    }
}
