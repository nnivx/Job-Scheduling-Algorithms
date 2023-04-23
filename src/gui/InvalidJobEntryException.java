/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

/**
 *
 * @author mxiii
 */
public class InvalidJobEntryException extends Exception {

    /**
     * Comstructor.
     *
     * @param man super(man)
     */
    public InvalidJobEntryException(String man) {
        super(man); // lol
    }
}
