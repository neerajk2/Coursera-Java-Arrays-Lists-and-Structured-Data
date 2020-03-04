import edu.duke.FileResource;

import java.util.HashMap;

public class CountCodons {

    private HashMap<String, Integer> countCodon;

    public CountCodons (){
        this.countCodon = new HashMap<String, Integer>();
    }

    public void buildCodonMap (int start, String dna) {
        for (int i = start; i<dna.length()-2; i = i+3) {
            String key = dna.substring(i, i+3);
            if (countCodon.isEmpty()) {
                countCodon.put(key, 1);
                continue;
            }
            if (countCodon.keySet().contains(key)) {
                countCodon.put(key, countCodon.get(key)+1);
            } else {
                countCodon.put(key, 1);
            }
        }
    }

    public String getMostCommonCodon () {
        int maxFreq = 0;
        String maxKey = "";
        for (String key : countCodon.keySet()) {
            if (countCodon.get(key) > maxFreq) {
                maxFreq = countCodon.get(key);
                maxKey = key;
            }
        }
        return maxKey;
    }

    public void numOfUniqueCodons (){
        int unique = 0;
        for (String key : countCodon.keySet()){
            unique++;
        }
        System.out.println("Number of unique codons: "+unique);
    }

    public void printCountCodons (int start, int end) {
        for (String key : countCodon.keySet()){
            if (countCodon.get(key) >= start && countCodon.get(key) <= end ){
                System.out.println("Key: "+key+"\t"+"Frequency: "+countCodon.get(key));
            }
        }
    }

    public void tester () {
        FileResource f = new FileResource();
        String dna  = f.asString();
        dna = dna.trim();
        dna  = dna.toUpperCase();
        for (int i = 0; i<3; i++){
            System.out.println("Frame is at "+i);
            buildCodonMap(i, dna);
            numOfUniqueCodons();
            System.out.println("Most common: "+getMostCommonCodon()+"\tFrequency: "+countCodon.get(getMostCommonCodon()));
            printCountCodons(0, 10);
            countCodon.clear();
        }
    }

    public static void main(String[] args) {
        CountCodons cc = new CountCodons();
        cc.tester();
    }
}
