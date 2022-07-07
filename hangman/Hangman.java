
import java.util.Scanner;
import java.util.Arrays;

public class Hangman {
 
    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

       String word = randomWord(words);
       char [] array = new char[word.length()];
       
       for(int i =0; i<array.length; i++) {
           array[i] = word.charAt(i);
           
       }

       char [] placeHolders = Arrays.copyOf(array, array.length);

       for (int i =0; i<placeHolders.length; i++) {
           placeHolders[i] = '_';
       }

       String missedChar ="";
       int gallowCount =0;
       while(gallowCount < 6 && !Arrays.equals(array,placeHolders)) {
           

        printGallow(gallowCount);
        printPlaceHolders(placeHolders);
        printMissedGuesses(missedChar);
        System.out.print("Guess: ");

        String str = scan.next();
        System.out.print("\n");
        char answer = str.charAt(0);

        if(checkGuess(answer, array, placeHolders)) {
            continue;
        }

        else {
           gallowCount++;
           missedChar = missedChar.concat(str);
           System.out.println (missedChar);
        }

    }
        if (testArrays(array, placeHolders)) {
            printGallow(gallowCount);
            printPlaceHolders(placeHolders);
            System.out.println("\n Good work!");
        }
        else {
            printGallow(gallowCount);
            printPlaceHolders(placeHolders);
            System.out.println("\n RIP!");
            System.out.println("\n The word is:'"+ word +"'");
        }


        
      
        
            

       
    
       
     


     
       

       



    

       











     scan.close();

    }



    public static String randomWord(String [] words) {
         double randomNumber = Math.random() * words.length;
         int randomNum = (int) randomNumber;
         String word = words[randomNum];
         return word;


    }

    public static void printPlaceHolders(char [] placeHolders) {
          System.out.print("Word: ");
          for (int i=0; i< placeHolders.length; i++) {
              System.out.print(placeHolders[i] + " ");
          }
          System.out.print("\n");

    }

    public static boolean checkGuess(char guess, char [] array, char [] placeHolders) {
        boolean bool = false;
        
        for(int i=0; i< array.length; i++) {
            if (guess == array[i]) {
                placeHolders[i] = guess;
                bool=true;
            }
        }

        return bool;

    }

    public static void printMissedGuesses( String missedChar) {

        System.out.println("Misses: " + missedChar + "\n\n");
    }
 
    public static void printGallow(int gallowCount) {

        System.out.println(gallows[gallowCount]);
    }

    public static boolean testArrays(char [] array, char [] placeHolders) {

        boolean bool = Arrays.equals(array, placeHolders);
        return bool;
    }

  
}

