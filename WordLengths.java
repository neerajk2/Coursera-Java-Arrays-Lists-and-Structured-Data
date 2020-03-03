import edu.duke.*;

public class WordLengths {

    public void countWordLengths (FileResource resource, int[] counts) {
        for (String words : resource.words()) {
            if (words.length()==1) {
                if(Character.isLetter(Integer.parseInt(words)));
                break;
            }
            if (words.length()>counts.length) {
                counts[counts.length-1]++;
            }
            else counts[words.length()]++;
        }
    }

    public void testCountWordLengths () {
        FileResource f = new FileResource();
        int[] counts = new int[31];
        countWordLengths(f,counts);
        int maxDex = indexOfMax(counts);
        for (int i=0; i<counts.length; i++) {
            System.out.println("Number of words with length "+i+" are "+counts[i]);
            if (i==maxDex) System.out.println("This is the most common length of words.");
        }
    }

    public int indexOfMax (int[] values) {
        int maxVal = 0;
        for (int i =0 ; i<values.length; i++){
            if (values[i] > maxVal) maxVal = values[i];
        }
        return maxVal;
    }

    public static void main (String[] args) {
        WordLengths w = new WordLengths();
        w.testCountWordLengths();
    }
}
