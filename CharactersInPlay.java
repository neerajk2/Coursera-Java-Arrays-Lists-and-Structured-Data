import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {

    private ArrayList<String> characterNames;
    private ArrayList<Integer> counts;

    public CharactersInPlay() {
        this.characterNames = new ArrayList<String>();
        this.counts = new ArrayList<Integer>();
    }

    public void update (String person) {
        int index = characterNames.indexOf(person);
        if (index==-1) {
            characterNames.add(person);
            counts.add(1);
        } else {
            int freq = counts.get(index);
            counts.set(index, freq+1);
        }
    }

    public void findAllCharacters () {
        FileResource resource = new FileResource();
        for (String line : resource.lines()) {
            int i = 0;
            String person  = new String();
            int index = line.indexOf('.');
            if (index > -1) {
                int nonblank = 0;
                while (nonblank < index) {
                    if (!line.substring(nonblank, nonblank+1).equals(" ")) break;
                    else nonblank = nonblank + 1;
                }
                person = line.substring(nonblank, index);
            }
            update(person);
        }
    }

    public void charactersWithNumParts (int num1, int num2) {
        for (int i = 0; i<characterNames.size(); i++) {
            if (counts.get(i)>=num2 && counts.get(i)<=num1) {
                System.out.println(counts.get(i)+"\t"+characterNames.get(i));
            }
        }

    }

    public void tester () {
        findAllCharacters();
        /*
            for (int i = 0; i<characterNames.size(); i++) {
                System.out.println(characterNames.get(i)+"\t"+counts.get(i));
            }
         */
        charactersWithNumParts(1000, 5);
    }

    public static void main (String[] args) {
        CharactersInPlay chplay = new CharactersInPlay();
        chplay.tester();
    }

}
