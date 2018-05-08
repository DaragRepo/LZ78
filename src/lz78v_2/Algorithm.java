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
public class Algorithm {

    private String s;
    private LinkedList<Tag> li;

    public Algorithm(String s) {
        this.s = s;
    }

    private List compress() {
        Map<String, Integer> dictionary = new HashMap();

        this.li = new LinkedList();
        // the input string 

//        String s = "ABAABABAABABBBBBBBBBBA";
        // loop through the string 
        int c1 = 1;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {

            if (j > i) {
                continue;
            }
            // if the current character doesn't exist in the dictionary 

            if (!dictionary.containsKey(String.valueOf(s.charAt(i)))) {

                Tag tag = new Tag(0, s.charAt(i));
                li.add(tag);
                dictionary.put(String.valueOf(s.charAt(i)), c1);
                 System.out.println(dictionary);
                c1++;

            } // if the current character already in the dictionary 
            else {

                // loop till you find a sequence that doesn't exist in the dictionary 
                String history = "";
                history += s.charAt(i);
                j = i + 1;
                // while it does exist keep looping till you find a string that doesn't exist 

                while (j != s.length() - 1 && j < s.length() - 1 && dictionary.containsKey(history)) {

                    history += s.charAt(j);

                    j++;
                }

                // add this new string to the dictionary 
                if (!dictionary.containsKey(history)) {
                    dictionary.put(history, c1);
                    c1++;
                }
                if (i == s.length() - 1 && history.length() != 0) {
                    int index = dictionary.get(history);
                    Tag t = new Tag(index, '\u0000');
                    li.add(t);
                } else {
                    // get last index in the dictionary which contains this letter 
                    int index = dictionary.get(history.substring(0, history.length() - 1));
//                System.out.println(history + " " + index + " " + i + " " + j);
                    char addedChar = history.charAt(history.length() - 1);
                    Tag t = new Tag(index, addedChar);
                    li.add(t);
                }
            }

        }
       
        return li;
    }

    private String decompress() {
        String decompressed = "";
        int counter = 1;
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (Tag tag : this.li) {

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
        return decompressed;
    }

    @Override
    public String toString() {
        return "String " + s + " After Compression " + compress()+ " \n " + "String After Decompression " + decompress();
    }

}
