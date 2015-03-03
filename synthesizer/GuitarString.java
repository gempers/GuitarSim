
package synthesizer;

public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    
    /* Buffer for storing sound data. */
    private BoundedQueue buffer;
    
    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int rounded = (int) Math.round(SR/frequency);
        buffer = new ArrayRingBuffer(rounded);
        for (int i = 0; i < buffer.capacity(); i+=1)
            buffer.enqueue(0);
            
    }
    
    
    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty())
            buffer.dequeue();
        while(!buffer.isFull())
            buffer.enqueue(Math.random()-0.5);
    }
    
    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {

            double deq = buffer.dequeue();
            buffer.enqueue((deq+sample())/2 * 0.996);
        
    }
    
    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
    
}
