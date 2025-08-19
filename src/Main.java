import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main1(String[] args) {

        String filePath = "src\\Pink Floyd-Hey You.wav";
        File file = new File(filePath);

        try (Scanner scanner =  new Scanner(System.in); AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){
            Clip clip =  AudioSystem.getClip();
            clip.open(audioStream);

            String response = "";

            while(!response.equals("Q")){
                System.out.println("P = Play");
                System.out.println("S = Stop");
                System.out.println("R = Reset");
                System.out.println("Q = Quit");
                System.out.print("Enter your choice : ");
                response = scanner.next().toUpperCase();


                switch (response){
                    case "P":
                        clip.start();
                        break;
                    case "S":
                        clip.stop();
                        break;
                    case "R":
                        clip.setMicrosecondPosition(0);
                        break;
                    case "Q":
                        clip.close();
                        break;
                    default:
                        System.out.println("Invalid choice");
                }

            }


        }
        catch(FileNotFoundException e){
            System.out.println("could not locate the music file");
        }
        catch(LineUnavailableException e){
            System.out.println("unable to access the file");
        }
        catch (UnsupportedAudioFileException e){
            System.out.println("file type not supported!");
        }
        catch(IOException e){
            System.out.println("Something went wrong");
        }
        finally {
            System.out.println("Bye!");
        }
    }
}