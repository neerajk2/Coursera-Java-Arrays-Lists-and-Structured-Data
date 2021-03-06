import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.Random;
import java.util.*;


public class GladLibMap {

    private HashMap<String, ArrayList<String>> myMap;

    private ArrayList<String> usedWords;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        usedWords = new ArrayList<String>();
        myRandom = new Random();
    }

    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        usedWords = new ArrayList<String>();
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};

        for (String key : labels) {
            ArrayList<String> labelList = new ArrayList<String>();
            labelList = readIt(source+"/"+key+".txt");
            myMap.put(key, labelList);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (!label.equals("number")){
        String result = randomFrom(myMap.get(label));
        while (!usedWords.isEmpty() && usedWords.contains(result)) {
            result = randomFrom(myMap.get(label));
        }
        usedWords.add(result);
        return result;
        }
        else {
            return ""+myRandom.nextInt(50)+5;
        }
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Number of replaced words: "+usedWords.size());
        totalWordsInMap();
    }

    public void printUsedWords() {
        for (int i = 0; i<usedWords.size(); i++) {
            System.out.println(usedWords.get(i));
        }
    }
    
    public void totalWordsInMap() {
        int totalWords = 0;
        for (String key : myMap.keySet()) {
            totalWords += myMap.get(key).size();
        }
        System.out.println("Total words to choose from are: "+totalWords);
    }


    public static void main (String[] args) {
        GladLibMap story = new GladLibMap();
        story.makeStory();
    }
}
