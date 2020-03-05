import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String lines : fr.lines()){
            lines = lines.toLowerCase();
            dictionary.add(lines);
        }
        return dictionary;
    }

    public HashMap<String, HashSet<String>> readAllDictionaries () {
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        String[] langs = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for (String lang : langs) {
            FileResource fr = new FileResource("dictionaries/"+lang);
            HashSet<String> dic = readDictionary(fr);
            dictionaries.put(lang, dic);
        }
        return dictionaries;
    }

    public char mostCommonCharIn (HashSet<String> dictionary) {
        int[] countLetters = new int[26];
        for (String word : dictionary){
            word = word.toLowerCase();
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ch - 'a' < 26 && ch - 'a' > -1) countLetters[ch - 'a']++;
            }
        }
        int maxDex = 0, maxCount = 0;
        for (int i = 0 ; i < countLetters.length; i++ ){
            if (maxCount < countLetters[i]) {
                maxDex = i;
                maxCount = countLetters[i];
            }
        }
        char ch = (char) ('a'+ maxDex);
        return ch;
    }


    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slice = new String();
        for (int i = whichSlice; i < message.length(); i = i + totalSlices) {
            slice += message.charAt(i);
        }
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(slice);
        }
        return key;
    }


    public int countWords (String message, HashSet<String> dictionary) {
        String[] wordsInMessage = message.split("\\W+");
        int count = 0;
        for (String word : wordsInMessage){
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxReadableWords = 0;
        int[] foundKeys = new int[100];
        char mostCommon = mostCommonCharIn(dictionary);
        for (int i = 1; i < 100; i++) {
            int keyLength = i;
            int[] keys = tryKeyLength(encrypted, keyLength, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int counter = countWords(decrypted, dictionary);
            if (counter > maxReadableWords){
                maxReadableWords = counter;
                foundKeys = keys;
            }
        }
        VigenereCipher vc = new VigenereCipher(foundKeys);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("Key Length is: "+foundKeys.length);
        System.out.println("Keys found are: "+Arrays.toString(foundKeys)+"\nReadable words are "+maxReadableWords);
        return decrypted;
    }

    public void breakForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxReadableWords = 0;
        String language = "";
        String message = "";
        for (String lang : languages.keySet()){
            String decrypted = breakForLanguage(encrypted, languages.get(lang));
            int readableWords = countWords(decrypted, languages.get(lang));
            if (readableWords > maxReadableWords) {
                maxReadableWords = readableWords;
                language = lang;
                message = decrypted;
            }
        }
        System.out.println("Language used is: "+language);
        System.out.println(message);
    }


    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>> dictionaries = readAllDictionaries();
        breakForAllLangs(encrypted, dictionaries);
    }
    
}
