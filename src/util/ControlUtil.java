package util;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class ControlUtil {

    /*

    Controls are a list, with the last control being the most recent

    only currently pressed keys are in the list

     */

    public static LinkedList<Character> controls = new LinkedList<>();
    public static boolean chatting = false;

    public static void keyPress(KeyEvent e) {
        char c = e.getKeyChar();

        if(!controls.contains(c)) {
            controls.add(0, c);
        }
    }

    public static void keyRelease(KeyEvent e) {
        char c = e.getKeyChar();

        if(controls.contains(c)) {
            controls.remove(new Character(c));
        }
    }
}
