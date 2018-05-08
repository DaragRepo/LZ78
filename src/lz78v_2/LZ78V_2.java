/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz78v_2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author moh
 */
public class LZ78V_2 {

    public static int getKeyFromValue(Map<Integer, String> hm, String value) {
        for (int o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return 0;

    }

    public static void main(String[] args) {

        Map<Integer, String> dictionary = new HashMap();

        LinkedList<Tag> li = new LinkedList();
        // the input string 

        String s = "ABAABABAABABBBBBBBBBBA";

//        String s = "/WED/WE/WEE/WEB";
        // -->  1 : /  
        // -->  2 : W
        // 

        boolean flag = false;

//        String s = "sir_sid_eastman_easily_teases";
//        String s = "AABABBBABAABABBBABBABB";
        // loop through the string 
        int c1 = 1;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {

            if (j > i) {
                continue;
            }
            if (j == s.length() - 1 && flag == false) {
                break;
            }
            // if the current character doesn't exist in the dictionary 

            if (!dictionary.containsValue(String.valueOf(s.charAt(i)))) {
                Tag tag = new Tag(0, s.charAt(i));
                li.add(tag);
                dictionary.put(c1, String.valueOf(s.charAt(i)));
                c1++;

            } // if the current character already in the dictionary 
            else {

                // loop till you find a sequence that doesn't exist in the dictionary 
                String history = "";
                history += s.charAt(i);
                j = i + 1;
                // while it does exist keep looping till you find a string that doesn't exist 

                while (j != s.length() - 1 && j < s.length() - 1 && dictionary.containsValue(history)) {

                    history += s.charAt(j);

                    j++;
                }
                if (j == s.length() - 1 && dictionary.containsValue(history)) {
                    history += s.charAt(s.length() - 1);
                } else {
                    flag = true;
                }

                // add this new string to the dictionary 
                if (!dictionary.containsValue(history)) {

                    dictionary.put(c1, history);
                    c1++;
                }
                if (i == s.length() - 1 && history.length() != 0) {
                    int index = getKeyFromValue(dictionary, history);
                    Tag t = new Tag(index, '\u0000');
                    li.add(t);
                } else {

                    // get last index in the dictionary which contains this letter 
                    int index = getKeyFromValue(dictionary, history.substring(0, history.length() - 1));
//                System.out.println(history + " " + index + " " + i + " " + j);
                    char addedChar = history.charAt(history.length() - 1);
                    Tag t = new Tag(index, addedChar);
                    li.add(t);
                }
            }

        }
        System.out.println(dictionary);
        System.out.println(li);
        String decompressed = "";
        int counter = 1;
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (Tag tag : li) {

            if (tag.index == 0) {
                decompressed += tag.c;
                map.put(counter, String.valueOf(tag.c));
                counter++;
            } else {
                int index = tag.index;
                String str = map.get(index);
                System.out.println(str);
                decompressed += str;
                decompressed += tag.c;
                map.put(counter, str + tag.c);
                counter++;
            }

        }
        System.out.println(decompressed);

    }

}
