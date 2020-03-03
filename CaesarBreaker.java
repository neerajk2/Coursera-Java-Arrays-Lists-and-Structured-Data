public class CaesarBreaker {

    public int[] decrypt (String encrypted) {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        int[] counter = new int[26];
        encrypted = encrypted.toLowerCase();
        for (int i = 0; i<encrypted.length(); i++) {
            int index = alphabets.indexOf(encrypted.charAt(i));
            if (index != -1){
                counter[index]++;
            }
        }
        return counter;
    }

    public int findingKey (int[] counter) {
        int maxCount = 0;
        int maxDex = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > maxCount) {
                maxCount = counter[i];
                maxDex = i;
            }
        }
        int key = maxDex - 4;
        if (maxDex < 4) {
            key = 26 - (4 - maxDex);
        }
        return 26-key;
    }

    public void decryptTwoKeys(String encrypted) {
        System.out.println(encrypted);
        String decryptKey1 = new String();
        String decryptKey2 = new String();
        for (int i = 0; i<encrypted.length(); i++) {
            if (i%2==0) {
                decryptKey1 += encrypted.substring(i, i+1);
                System.out.println(decryptKey1);
            }
            else {
                decryptKey2 += encrypted.substring(i, i+1);
                System.out.println(decryptKey2);
            }
        }
        CaesarBreaker cb = new CaesarBreaker();
        System.out.println("Key1 :"+cb.findingKey(cb.decrypt(decryptKey1)));
        System.out.println("Key2 :"+cb.findingKey(cb.decrypt(decryptKey2)));
    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        String test = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus.";
        String encrypted = cc.encrypt(test, 15);
        String encTwoKeys = cc.encryptTwokeys(test, 10,20);
        CaesarBreaker cb = new CaesarBreaker();
        System.out.println(cb.findingKey(cb.decrypt(encrypted)));
        cb.decryptTwoKeys(encTwoKeys);
    }


}
