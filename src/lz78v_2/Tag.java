/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz78v_2;

/**
 *
 * @author moh
 */
public class Tag {
    
    int index ; 
    char c ; 

    public Tag(int index, char c) {
        this.index = index;
        this.c = c;
    }

    @Override
    public String toString() {
        return "<" + + index + "," + c + '>';
    }
    
    
    
    
    
}
