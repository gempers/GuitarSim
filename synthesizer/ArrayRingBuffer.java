
package synthesizer;

public class ArrayRingBuffer extends AbstractBoundedQueue {
  /* Index for the next dequeue or peek. */
  private int first;           
  /* Index for the next enqueue. */
  private int last;             
  /* Array for storing the buffer data. */
  private double[] rb;


  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int capacity) {
    rb  = new double[capacity];
    int i = 0;
    while (i<capacity){
      rb[i]= 0.0;
      i +=1;
    }

    first = 0;
    last = 0;
    fillCount = 0;
    this.capacity = capacity;
    
  }

  /** Adds x to the end of the ring buffer. If there is no room, then
    * throw new RuntimeException("Ring buffer overflow") 
    */
  public void enqueue(double x) {
    if (this.isFull()){

      throw new RuntimeException("Ring buffer overflow"); 
    }
    else{
      
      fillCount += 1;
      rb[last] = x;
      last +=1;
      if (last == capacity)
        last = 0;
  }
}

  /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
    * throw new RuntimeException("Ring buffer underflow");
    */
  public double dequeue() {
    if (isEmpty()){
      throw new RuntimeException("Ring buffer underflow");
    }
    else{
      double temp = rb[first];
      rb[first] = 0;
      fillCount -=1;
      first +=1;
      if (first == capacity)
        first = 0;
      return temp;

    }
  }
  /** Return oldest item, but don't remove it. */
  public double peek() {
    if (isEmpty()){
      throw new RuntimeException("Ring buffer underflow");
    }else{

      return rb[first];
    }

}
}
