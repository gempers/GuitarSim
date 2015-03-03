
public class GuitarHero {
      public static void main(String[] args) {
          String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
          synthesizer.GuitarString[] strings = new synthesizer.GuitarString[37];
          for (int i = 0; i < strings.length; i++){
            strings[i] = new synthesizer.GuitarString(440*Math.pow(2,(((double)i - 24)/12)));
          }
          while (true) {

              // check if the user has typed a key; if so, process it   
              if (StdDraw.hasNextKeyTyped()) {
                  char key = StdDraw.nextKeyTyped();
                  int ind = keyboard.indexOf(key);
                  if (ind != -1)
                    strings[ind].pluck();
              
              }
              // compute the superposition of samples
              double sample = 0;
              for (int i = 0; i < strings.length; i++){
                sample = sample + strings[i].sample();
              }
              // play the sample on standard audio
              StdAudio.play(sample);
  
              // advance the simulation of each guitar string by one step   
              for (int i = 0; i < strings.length; i++){
                strings[i].tic();
              }

          }
       }
  }

