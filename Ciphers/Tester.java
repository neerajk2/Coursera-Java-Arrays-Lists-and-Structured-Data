import edu.duke.FileResource;
import java.util.Arrays;

public class Tester {

    public void testCaesarCipher (int key) {
        CaesarCipher cc = new CaesarCipher(key);
        FileResource fr = new FileResource("titus-small.txt");
        String encrypted = cc.encrypt(fr.asString());
        System.out.println(fr.asString()+"\nIs encrypted to: \n"+encrypted);
    }

    public void testCaesarCracker () {
        CaesarCracker cc = new CaesarCracker('a');
        FileResource fr = new FileResource("oslusiadas_key17.txt");
        String decrypted = cc.decrypt(fr.asString());
        System.out.println(fr.asString()+"\nIs decrypted to using key: "+cc.getKey(fr.asString())+"\n"+decrypted);
    }

    public void testVigenereCipher (int[] keys) {
        VigenereCipher vc = new VigenereCipher(keys);
        FileResource fr = new FileResource("titus-small.txt");
        String encrypted = vc.encrypt(fr.asString());
        String decrypted = vc.decrypt(encrypted);
        System.out.println(encrypted+"\nKeys: "+Arrays.toString(keys)+"\n"+decrypted);
    }

    public void testVigenereBreaker() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }

    public static void main (String[] args) {
        Tester test = new Tester();
        //test.testCaesarCipher(10);
        //test.testCaesarCracker();
        //int[] keys = {17, 14, 12, 4};
        //test.testVigenereCipher(keys);
        test.testVigenereBreaker();
    }
}
