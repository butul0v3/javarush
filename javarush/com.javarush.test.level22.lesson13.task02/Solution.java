package com.javarush.test.level22.lesson13.task02;

import java.io.*;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    

    public static void main(String[] args) throws IOException {
        if (args.length == 2)
        {
            if (!args[0].isEmpty() && !args[1].isEmpty())
            {
                String fileName = args[0];
                String fileOutput = args[1];
                try{
                    File file1 = new File(fileName);
                    File file2 = new File(fileOutput);
                    InputStream inputStream = new FileInputStream(file1);
                    InputStreamReader ins = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(ins);
                    OutputStream outputStream = new FileOutputStream(fileOutput);
                    OutputStreamWriter os = new OutputStreamWriter(outputStream);
                    BufferedWriter writer = new BufferedWriter(os);
                    String s = "";
                    while ((s = reader.readLine()) != null)
                    {
                        String utf8String = new String(s.getBytes("Cp1251"), "UTF-8");
                        writer.write(utf8String);
                    }
                    writer.close();
                    reader.close();
                    inputStream.close();
                    outputStream.close();
                } catch (IOException ex) { ex.printStackTrace(); }
            }
        }
    }
}
