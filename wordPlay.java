public class wordPlay {
    public boolean isVowel(char ch) {
        String vowels = "AEIOUaeiou";
        if (vowels.indexOf(ch) != -1) {
            return true;
        } else return false;
    }

    public void testIsVowel () {
        if (isVowel('a')) System.out.println("Test passed");
        if (!isVowel('F')) System.out.println("Test passed");
        if (isVowel('U')) System.out.println("Test passed");
    }

    public String replaceVowels (String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i = 0; i<newPhrase.length(); i++) {
            if (isVowel(newPhrase.charAt(i))) {
                newPhrase.setCharAt(i, ch);
            }
        }
        return newPhrase.toString();
    }

    public void testReplaceVowels () {
        String input = "This is a test example."
        +" All vowels will be replaced by *.";
        String replaced = replaceVowels(input, '*');
        System.out.println(replaced);
    }

    public String emphasize (String phrase, char ch) {
        String uch = String.valueOf(ch);
        uch = uch.toUpperCase();
        String lch = uch.toLowerCase();
        char upper = uch.charAt(0);
        char lower = lch.charAt(0);
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i = 0; i<newPhrase.length(); i++) {
            if (newPhrase.charAt(i) == upper || newPhrase.charAt(i) == lower) {
                if (i%2 == 0) {
                    newPhrase.setCharAt(i, '*');
                }
                else {
                    newPhrase.setCharAt(i, '+');
                }
            }
        }
        return newPhrase.toString();
    }

    public void testEmphasize () {
        String test1 = "dna ctgaaactga";
        String result1 = emphasize(test1, 'a');
        System.out.println(result1);

        String test2 = "Mary Bella Abracadabra";
        String result2 = emphasize(test2, 'a');
        System.out.println(result2);
    }

    public static void main (String[] args) {
        wordPlay words = new wordPlay();
        words.testIsVowel();
        words.testReplaceVowels();
        words.testEmphasize();
    }
}

