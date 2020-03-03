public class OOCaesarCipher {
    private String alphabetUpper;
    private String alphabetLower;
    private String decipherUpper;
    private String decipherLower;

    public OOCaesarCipher (int key) {
        this.alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.alphabetLower = alphabetUpper.toLowerCase();
        this.decipherUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0,key);
        this.decipherLower = alphabetLower.substring(key) + alphabetLower.substring(0,key);
    }


    public String encrypt (String input) {
        StringBuilder encrypted = new StringBuilder(input);

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
        String test1 = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String result1 = encrypt(test1);
        System.out.println("Key is "+10);
        System.out.println(test1+" is encrypted to "+result1);

        OOCaesarCipher cc1 = new OOCaesarCipher(26-10);
        String decrypt1 = cc1.encrypt(result1);
        System.out.println("Key is "+16);
        System.out.println(result1+" is decrypted again to "+decrypt1);

    }


    public String encryptTwokeys (String input, int key1, int key2) {
        String result = new String();
        OOCaesarCipher cc1 = new OOCaesarCipher(key1);
        OOCaesarCipher cc2 = new OOCaesarCipher(key2);
        for (int i = 0; i<input.length(); i++) {
            if (i%2==0) {
                result += cc1.encrypt(input.substring(i, i+1));
            }
            else {
                result += cc2.encrypt(input.substring(i, i+1));
            }
        }
        return result;
    }

    public void testEncryptTwoKeys () {
        //String test1 = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String test1 = "Top ncmy qkff vi vguv vbg ycpx";
        String result1 = encryptTwokeys(test1, 24, 6);
        System.out.println("Keys are "+24+" and "+6);
        System.out.println(test1+" is decrypted to "+result1);

        String decrypt1 = encryptTwokeys(result1, 2, 20);
        System.out.println("Keys are "+2+" and "+20);
        System.out.println(result1+" is encrypted again to "+decrypt1);

    }


    public static void main(String[] args) {
        OOCaesarCipher cc = new OOCaesarCipher(10);
        cc.testEncrypt();
        cc.testEncryptTwoKeys();
    }

}
