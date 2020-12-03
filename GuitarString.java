// Ranveer Singh
// 4/18/19
// CSE 143 A
// TA: Tyler Mi
// Assignment #2
//
// This class will model a vibrating guitar string of a given frequency

import java.util.*;

public class GuitarString {
   // a list that stores real numbers between -1/2 and +1/2
   private Queue<Double> ringBuffer;
   // a random object
   private Random r;
   // the sampling rate divided by the fundamental frequency 
   // (rounded to nearest integer)
   private double N;
   
   // how fast samples are taken
   public static final int SAMPLE_RATE = 44100;
   // this is the energy decay factor which models the dissipation 
   // in energy as a wave makes a roundtrip through the string
   public static final double ENERGY_DECAY = .996;
   
   // pre: frequency > 0 and N >= 2 (throws IllegalArgumentException if not)
   // constructs a ringbuffer of size N 
   // from a given frequency (double frequency)
   public GuitarString(double frequency) {
      ringBuffer = new LinkedList<Double>();
      r = new Random();
      N = Math.round(SAMPLE_RATE / frequency);
      if (frequency <= 0 || N < 2 ) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < N; i++) {
         ringBuffer.add(0.0);  
     }
   }
   
   // pre: init.length >= 2 (throws IllegalArgumentException if not)
   // constructs a ring buffer of the same size 
   // as the given list(double[] init) and fills in the values
   // of ring buffer, with the same values in the given list
   public GuitarString(double[] init) {
      ringBuffer = new LinkedList<Double>();
      r = new Random();
      N = init.length;
      if (N < 2) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < N; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // replaces the values in the ring buffer with random values
   // between -0.5 inclusive and +0.5 exclusive
   public void pluck() {
      for (int i = 0; i < N; i++) {
         double rand = r.nextDouble() - 0.5;
         ringBuffer.remove();
         ringBuffer.add(rand);
      }
   }
   
   // applies Karplus-Strong algorithm and adds this value
   // to the end of the list after removing the very front
   // of the list
   public void tic() {
      double first = ringBuffer.remove();
      double second = ringBuffer.peek();
      double avg = (0.5 * (first + second)) * ENERGY_DECAY;
      ringBuffer.add(avg);    
   }
   
   // returns the front of the ring buffer
   public double sample() {
      return ringBuffer.peek();
   }
}