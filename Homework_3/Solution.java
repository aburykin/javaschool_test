package Homework_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ABurykin on 10.08.2016.
 */
public class Solution {

    public static void main(String[] args) throws IOException {

        //String input = "a aa aaa aa a a";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Integer> map = new HashMap();


        while (true){
           String[] words =  br.readLine().split(" ");
            //String[] words =input.split(" ");

            for (int i = 0; i < words.length; i++ ){
                if ( map.get(words[i]) == null){
                    map.put(words[i], 1);
                } else {
                    map.put(words[i], map.get(words[i])+1);
                }

            }
            break;
        }

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            //получение «пары» элементов
            Map.Entry<String, Integer> pair = iterator.next();
            String key = pair.getKey();            //ключ
            int value = pair.getValue();        //значение
           // System.out.println(key + ":" + value);
            System.out.println(key);
        }

    }
}
