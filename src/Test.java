/**
 * Created by bryanbeale on 10/1/17.
 */
public class Test {

    public static void main(String[] args){

        String[] strArr = {"<>>>", "<>>>>"};

        int[] intArr= {2,2};

        printThis(balancedOrNot(strArr, intArr));
    }

    private static void printThis(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }

    static int[] balancedOrNot(String[] expressions, int[] maxReplacements) {
        int[] replacements = new int[maxReplacements.length];

        if(expressions.length != maxReplacements.length){
            return new int[0];
        }

        int j = 0;

        for (String expression: expressions) {

            //cannot balance
            if (expression.endsWith("<")){
                replacements[j] = 0;
                j++;
                continue;
            }

            //balanced
            int counter = 0;
            int replacement = 0;
            int success = 1;
            for (int i = 0; i < expression.length(); i++){
                if(expression.charAt(i) == '<'){
                    counter++;
                }
                else if (expression.charAt(i) == '>'){
                    if (counter <= 0){
                        replacement++;
                        if (replacement > maxReplacements[j]){
                            success = 0;
                            break;
                        }
                    }
                    counter--;
                }

                if (i == expression.length() -1){
                    if (counter > 0){
                        success = 0;
                    }
                }
            }



            replacements[j] = success;
            j++;

        }

        return  replacements;



    }

}
