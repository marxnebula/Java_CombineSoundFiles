/**
 * Jordan Marx
 * 
 * Class for combing two sound files. 
 * One is a foreground and the other is a background(one third the original volume).
 */

import java.awt.Color;
import java.io.File;

 
public class CombineSound
{
  
  public static void main (String[] args) 
  {
    // Open a foreground and background sound file
    String filename1 = FileChooser.pickAFile();
    System.out.println(filename1);
    String filename2 = FileChooser.pickAFile();
    System.out.println(filename2);

    // Create the foreground and background sounds
    Sound sound1;
    sound1 = new Sound (filename1);
    Sound sound2;
    sound2 = new Sound (filename2);
    
    System.out.println ("Sampling Rate: " + sound1.getSamplingRate());
    System.out.println ("Number of Samples: " + sound1.getLength() );
 
    // Call a method to modify the sound
    Sound sound3;
    sound3 = combineSounds (sound1, sound2);
    
    // Play the new sound file
    sound3.play();

    // Save the new sound file
    String filename3 = FileChooser.pickAFile();
    sound3.write(filename3);
    
    System.out.println("");
    System.out.println("End Java Exection");
  }  // end of main
  
 
  // Method for combining two sounds
 public static Sound combineSounds (Sound s1, Sound s2)
 {
   SoundSample[] ssArr = s1.getSamples();
   SoundSample[] ssArr2 = s2.getSamples();
   
   // Set the length to be the shortest sound
   int totalLength;
   if (s1.getLength() < s2.getLength())
   {
     totalLength = s1.getLength();
   }
   else
   {
     totalLength = s2.getLength();
   }
   
   // Create the return sound
   Sound result = new Sound (totalLength);
   SoundSample[] ssArrR = result.getSamples();
   
   
   int i;
   for ( i = 0 ; i < ssArr.length ; ++i)
   {
     int amplitude1 = ssArr[i].getValue();
     // s2(the background sound) is reduced by 1/3
     int amplitude2 = (ssArr2[i].getValue())/3;
     // Combine the two sounds
     int amplitude = amplitude1 + amplitude2;
     
     // Determine if the amplitude goes out of range
     if (amplitude > 32767)
     {
       amplitude = 32767;
     }
     else if (amplitude < -32768)
     {
       amplitude = -32768;
     }
     // Store the new amplitude
     ssArrR[i].setValue(amplitude);
   }
   return result;
 }
  
}