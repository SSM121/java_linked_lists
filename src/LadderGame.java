import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;

public class LadderGame {
    static int MaxWordSize = 15;
    ArrayList<String>[] allList;  // Array of ArrayLists of words of each length.
    Random random;

    public LadderGame(String file) {
        random = new Random();
        allList = new ArrayList[MaxWordSize];
        for (int i = 0; i < MaxWordSize; i++)
            allList[i] = new ArrayList<String>();

        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNext()) {
                String word = reader.next();
                if (word.length()< MaxWordSize) allList[word.length()].add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void play(String a, String b) {
        MyQueue<WordInfo> q  = new MyQueue<WordInfo>();
        int size = a.length();
        if (size != b.length()){
            System.out.println("Words are not the same length");
            return;
         }
        if (size  >= MaxWordSize) return;
        ArrayList list = new ArrayList();
        ArrayList<String> l = allList[a.length()];
        list = (ArrayList) l.clone();
        System.out.println("Seeking a solution from " + a + " ->" + b + " Size of List " + list.size());
        WordInfo top = new WordInfo(a, 0, a); //creates a word info object for the start
        q.enqueue(top); //adds the top to the queue
        WordInfo curr; //used to store different words that are popped;
        list.remove(a); //removes the start from the dictionary
        while(!q.isEmpty()) { // loops while the queue is not empty
            curr = q.dequeue(); //takes the item out of the queue
            //System.out.println("checking " + curr.word); //debugging used to print the value just popped
            StringBuilder word = new StringBuilder(curr.word); //used to have a mutable string to avoid having to constantly be building strings
            for(int x = 0; x < size; ++x){ //used to loop over each letter in the string
                char orig = word.charAt(x); //gets the original charachter so after manipulation it can be returned
                for(char c = 'a'; c <= 'z'; c++){ //loops over all 26 possibilities for replacing the letter
                    word.replace(x, x+1, String.valueOf(c)); //replaces letter at x with a current c

                    //System.out.println("comparing to " + word.toString()); //used for debugging what comparisson is happening
                    if(b.equals(word.toString())){ //if you find the solution print it and leave the method
                        WordInfo fin = new WordInfo(b, curr.moves + 1, curr.history + " -> " + b);
                        System.out.println(fin);
                        System.out.println("It took " + q.enques + " enqueues and " + q.dequeues + " dequeues");
                        return;
                    }
                    else if(list.contains(word.toString())){ //if the word is a valid word from the dictionary then it addes it to queue and removes it from dictionary
                        //System.out.println("test");//debug for if we enter this if statment
                        q.enqueue(new WordInfo(word.toString(), curr.moves + 1, curr.history + " -> " + word.toString()));
                        list.remove(word.toString());
                    }
                }
                word.replace(x, x+1, String.valueOf(orig)); //puts the original char back
            }

        }
        System.out.println("No solution found!!");
        // Solve the word ladder problem
      
    }


    public void play(int len) {
       if (len >= MaxWordSize) return;
        ArrayList<String> list = allList[len];
        String a = list.get(random.nextInt(list.size()));
        String b = list.get(random.nextInt(list.size()));
        play(a, b);

    }

}
