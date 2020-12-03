package com.mycompany.sdm;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    
 public static void main(String[] args){
     Reader r = new Reader();
     try {
         r.init("/home/evgenij/products.csv");
     } catch (FileNotFoundException ex) {
         Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
     
}
