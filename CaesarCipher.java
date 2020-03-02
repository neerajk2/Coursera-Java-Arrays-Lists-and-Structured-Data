public class CaesarCipher {
    public String encrypt (String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = alphabetUpper.toLowerCase();
        String decipherUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0,key);
        String decipherLower = alphabetLower.substring(key) + alphabetLower.substring(0,key);

        for (int i =0 ; i< encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int val = currChar; //Converting using ASCII values of currChar
            if (val < 91) {
                int index = alphabetUpper.indexOf(currChar);
                if (index != -1){
                    char newChar = decipherUpper.charAt(index);
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                int index = alphabetLower.indexOf(currChar);
                if (index != -1){
                    char newChar = decipherLower.charAt(index);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public void testEncrypt () {
        String test1 = "HeY, ThIs Is TeSt 1.";
        String result1 = encrypt(test1, 20);
        System.out.println("Key is "+20);
        System.out.println(test1+" is encrypted to "+result1);

        String decrypt1 = encrypt(result1, 26-20);
        System.out.println("Key is "+6);
        System.out.println(result1+" is decrypted again to "+decrypt1);

    }


    public String encryptTwokeys (String input, int key1, int key2) {
        String result = new String();
        for (int i = 0; i<input.length(); i++) {
            if (i%2==0) {
                result += encrypt(input.substring(i, i+1), key1);
            }
            else {
                result += encrypt(input.substring(i, i+1), key2);
            }
        }
        return result;
    }

    public void testEncryptTwoKeys () {
        String test1 = "HeY, ThIs Is TeSt 1. Adding a bunch of random text HERE!";
        String result1 = encryptTwokeys(test1, 2, 1);
        System.out.println("Key is "+2+" and "+1);
        System.out.println(test1+" is encrypted to "+result1);

        String decrypt1 = encryptTwokeys(result1, 24, 25);
        System.out.println("Key is "+24+" and "+25);
        System.out.println(result1+" is decrypted again to "+decrypt1);

    }


    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        cc.testEncrypt();
        cc.testEncryptTwoKeys();
    }
}
