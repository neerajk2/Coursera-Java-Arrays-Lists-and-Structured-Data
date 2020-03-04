import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {

    private HashMap<String , ArrayList<String>> wordMap;

    public WordsInFiles () {
        wordMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile (File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
            ArrayList<String> wordList = new ArrayList<String>();
            if (wordMap.isEmpty()){
                wordList.add(f.getName());
                wordMap.put(word, wordList);
                continue;
            }
            if (wordMap.keySet().contains(word)) {
                ArrayList<String> oldwordList = wordMap.get(word);
                oldwordList.add(f.getName());
                wordMap.put(word, oldwordList);
            } else {
                wordList.add(f.getName());
                wordMap.put(word, wordList);
            }
        }
    }

    public void buildWordFileMap () {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    public void maxNumber () {
        int max = 0;
        String maxWord = "";
        for (String key : wordMap.keySet()){
            ArrayList<String> filenames = wordMap.get(key);
            if (filenames.size() > max) {
                max = filenames.size();
                maxWord = key;
            }
        }
        System.out.println("the word "+maxWord+" appears in max number of files i.e. "+max);
    }

    public ArrayList<String> wordsInNumFiles (int number) {
        ArrayList<String> numWords = new ArrayList<String>();
        for (String key : wordMap.keySet()){
            ArrayList<String> filenames = wordMap.get(key);
            if (filenames.size() == number) {
                numWords.add(key);
            }
        }
        return numWords;
    }

    public void printFilesIn (String word) {
        System.out.println(word+" appears in "+wordMap.get(word));
    }

    public void tester (){
        buildWordFileMap();
        maxNumber();
        for (String key : wordMap.keySet()){
            System.out.println("Word: "+key+"\t Filenames: "+wordMap.get(key));
        }
    }

    public static void main(String[] args) {
        WordsInFiles wf = new WordsInFiles();
        wf.tester();
    }
}
