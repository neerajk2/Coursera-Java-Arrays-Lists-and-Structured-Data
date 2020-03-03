import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        this.myWords = new ArrayList<String>();
        this.myFreqs = new ArrayList<Integer>();
    }

    public void findUnique () {
        myWords.clear();
        myFreqs.clear();

        FileResource resource = new FileResource();
        for (String word : resource.words()) {
            word = word.toLowerCase();
                int index = myWords.indexOf(word);
                if (index == -1) {
                    myWords.add(word);
                    myFreqs.add(1);
                } else {
                    int value  = myFreqs.get(index);
                    myFreqs.set(index, value+1);
                }
        }
    }

    public void tester () {
        findUnique();
        for (int i = 0; i<myWords.size(); i++) {
            System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
        }
        int max = findIndexOfMax();
        System.out.println(max+"\t"+myFreqs.get(max)+"\t"+myWords.get(max));
    }

    public int findIndexOfMax () {
        int maxDex = 0;
        int maxFreq = 0;
        for (int i = 0; i<myFreqs.size(); i++) {
            int currFreq = myFreqs.get(i);
            if (currFreq>maxFreq) {
                maxDex = i;
                maxFreq = currFreq;
            }
        }
        return maxDex;
    }

    public static void main (String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }

}
