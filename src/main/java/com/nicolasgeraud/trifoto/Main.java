package com.nicolasgeraud.trifoto;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    public static void main(String... args) {
        try {
            System.out.printf("read images from %s", args[0]);
            
            System.out.printf(" to %s", args[1]);
            ImageSorter.process(
                    Paths.get(args[0]), 
                    Paths.get(args[1]));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
