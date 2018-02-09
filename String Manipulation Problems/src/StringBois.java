/**
 * Created by bryanbeale on 2/8/18.
 */
public class StringBois  {

    private static final String str1 = "forinterviewscoding";
    private static final String str2 = "codingforinterviews";
    private static final String str3 = "interviewscodingfor";
    private static final String str4 = "dingforinterviewsco";
    private static final String str5 = "codngiforinterviews";


    public static void main(String[] args){
        permute("abc");

        System.out.println();

        System.out.println(String.format("%s is a rotation of %s? %s", str1, str2, isRotation(str1, str2)));
        System.out.println(String.format("%s is a rotation of %s? %s", str3, str2, isRotation(str3, str2)));
        System.out.println(String.format("%s is a rotation of %s? %s", str4, str2, isRotation(str4, str2)));
        System.out.println(String.format("%s is a rotation of %s? %s", str5, str2, isRotation(str5, str2)));

        System.out.println();

        System.out.println(String.format("%s is a rotation of %s? %s", str1, str2, isRotation2(str1, str2)));
        System.out.println(String.format("%s is a rotation of %s? %s", str3, str2, isRotation2(str3, str2)));
        System.out.println(String.format("%s is a rotation of %s? %s", str4, str2, isRotation2(str4, str2)));
        System.out.println(String.format("%s is a rotation of %s? %s", str5, str2, isRotation2(str5, str2)));

        System.out.println();

        System.out.println(String.format("%s is a permutation of %s? %s", "abc", "def", isPermutations("abc", "def")));

        System.out.println();

        System.out.println(reverseSentenceQuick("ow, now, brown, cow"));
        System.out.println(reverseSentence("ow, now, brown, cow"));

        System.out.println();

        System.out.println(replaceWhitespace("ow, now, brown, cow", "Yeah I did it boi"));
        System.out.println("ow, now, brown, cow".replace(" ", "Yeah I did it boi"));

        System.out.println();

        System.out.println(compressString("aabbeeeeeeeeettttttgkK"));

        System.out.println(decompressString(compressString("aabbeeeeeeeeettttttgkKo0")));


    }

    //Permutation code

    private static void permute(String str){
        permutation("", str);
    }

    private static void permutation(String ans, String remain){
        int n = remain.length();

        //ans serves as an accumulator variable
        //base case: we print out ans since there are no more letters to be added
        if(n == 0) {
            System.out.println("Permutation: " + ans);
        }else{
            for (int i = 0; i < n; i++){
                // we call permutation, each time combining prefix with the individual letters in str
                // we also remove these letters from str
                permutation(ans + remain.charAt(i), remain.substring(0, i) + remain.substring(i + 1, n));
            }
        }
    }

    public static boolean isPermutations(String a, String b){
        if(a.length() != b.length()){
            return false;
        }
        // change a to char array to be iterated through
        char[] astr = a.toCharArray();
        //init n array as a counter
        int[] count = new int[128];
        //then we count a's characters
        for(char c : astr){
            count[c]++;
        }

        //match a's count with b's
        char[] bstr = b.toCharArray();
        for(char c : bstr){
            if(--count[c] < 0){
                return false;
            }
        }

        return true;

    }

    //String Rotation, Substring code

    private static boolean isSubstring(String a, String b){
        // we first check the length of the strings
        if (a.length() < b.length()){
            return b.contains(a);
        }
        // feel free to use indexOf
        // a.indexOf(b);
        return a.contains(b);
    }

    //assuming that a is the rotated string
    private static boolean isRotation(String a, String b){
        if (a.length() != b.length()){
            return false;
        }

        return isSubstring(a + a, b);
    }

    // the entire function and be optimized to this.
    private static boolean isRotation2(String a, String b){
        return (a.length() == b.length() && isSubstring(a + a, b));
    }

    //Reverse Reverse

    public static String reverse(String x) {
        // convert x into an arrayj
        char[] string = x.toCharArray();
        // create the StringBuffer object
        StringBuffer buffer = new StringBuffer();
        // iterate from the back of the array, each time adding a character to buffer
        for(int i = string.length - 1; i >= 0; i--) {
            buffer.append(string[i]);
        }
        return buffer.toString();
    }

    private static String reverseSentence(String x){
        x = reverse(x);

        String[] words = x.split(" ");

        StringBuilder bob = new StringBuilder();

        for(int i = 0; i < words.length; i++){
            bob.append(reverse(words[i] + " "));
        }

        return bob.toString();
    }

    private static String reverseSentenceQuick(String x){
        String[] words = x.split(" ");

        StringBuilder bob = new StringBuilder();

        for(int i = words.length - 1; i >= 0; i--){
            bob.append(words[i]);
        }

        return bob.toString();
    }

    //Replacements and deletions

    private static String replaceWhitespace(String x, String replace){
        StringBuilder bob = new StringBuilder();

        for(int i = 0; i < x.length(); i++){
            if (x.charAt(i) == ' '){
                bob.append(replace);
            }
            else{
                bob.append(x.charAt(i));
            }
        }

        return bob.toString();
    }

    //Compression Decompression
    private static int countCompress(String x){
        int size = 0;
        int count = 1;
        char current = x.charAt(0);

        for (int i = 1; i < x.length(); i++){
            if (current != x.charAt(i)){
                current = x.charAt(i);

                size += 1 + String.valueOf(count).length();
            }
            else {
                count++;
            }
        }

        size += 1; String.valueOf(count).length();
        return size;
    }

    private static String compressString(String x){
        int length = countCompress(x);
        if(length > x.length()){
            return x;
        }

        StringBuilder bob = new StringBuilder();

        char current = x.charAt(0);

        int count = 1;

        for (int i = 1; i < x.length(); i++){
            if (current!= x.charAt(i)){
                bob.append(current);
                bob.append(count);
                current = x.charAt(i);
                count = 1;
            } else {
                count++;
            }

        }

        bob.append(current);
        bob.append(count);

        return bob.toString();
    }

    private static String decompressString(String x){
        StringBuilder bob = new StringBuilder();

        for (int j = 0; j < x.length(); j++) {
            char c = x.charAt(j);
            int cCount = Character.getNumericValue(x.charAt(++j));

            for (int i = 0; i < cCount; i++) {
                bob.append(c);
            }
        }

        return bob.toString();

    }

}
