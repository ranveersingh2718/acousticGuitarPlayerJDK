// Ranveer Singh
// 4/18/19
// CSE 143 A
// TA: Tyler Mi
// Assignment #2
//
// This class creates a virtual guitar with 37 strings

public class Guitar37 implements Guitar {
   // a list of 37 guitar strings
   private GuitarString[] strings;
   // stores current time
   private int tic;
   
   // acceptable keys the user can input
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard inputs
   
   // constructs the 37 guitar strings
   public Guitar37() {
      strings = new GuitarString[37];
      for (int i = 0; i < strings.length; i++) {
         strings[i] = new GuitarString(440.0 * Math.pow(2, (i - 24.0) * 1.0 / 12.0));
      }
   }
   
   // plays a note from a given pitch(int pitch)
   public void playNote(int pitch) {
      if (pitch >= -24 && pitch <= 12) {
         strings[24 + pitch].pluck();
      }
   }
   
   // returns if a given key(char key) is a legal input
   public boolean hasString(char key) {
      return KEYBOARD.contains(Character.toString(key));  
   }
   
   // pre: the key must be one of the 37 keys 
   // (throws IllegalArgumentException if not)
   // plays the note that corresponds to the given key(char string)
   public void pluck(char string) {
      int value = KEYBOARD.indexOf(string);
      if (value == - 1) {
         throw new IllegalArgumentException();
      }
      strings[value].pluck();
    
   }   
   
   // returns the sum of the current samples
   public double sample() {
      double sum = 0;
      for (int i = 0; i < strings.length; i++) {
         sum = sum + strings[i].sample();   
      }
      return sum;
   }
   
   // advances time forward by one
   public void tic() {
      tic++;
      for (int i = 0; i < strings.length; i++) {
         strings[i].tic();
      }
   }
   
   // returns current time
   public int time() {
      return tic; 
   }
}