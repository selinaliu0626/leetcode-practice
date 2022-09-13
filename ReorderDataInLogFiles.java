import java.util.Arrays;
import java.util.Comparator;

public class ReorderDataInLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> myComp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //solit each log into two parts:<identifier, content>
                String[] split1 = o1.split(" ",2);
                String[] split2 = o2.split(" ",2);

                boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
                boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

                //case 1 ,both are letter-logs
                if(!isDigit1 && !isDigit2){
                    //first compare the content
                    int cmp = split1[1].compareTo(split2[1]); //o1<o2,return -1;equal return 0, otherwise,return 1
                    //if content are not the same
                    if(cmp != 0){
                      return cmp;
                    }
                    //if the content are the same,compare the identifier
                    return split1[0].compareTo(split2[0]);
                }

                //case 2,one is letter log, one is digit-log
                if(!isDigit1 && isDigit2){
                    return -1;//means !isdigit has priority
                }else if(isDigit1 && !isDigit2){
                    return  1; //means isDigit should yield to !isDigit
                }else{
                    //case 3, both are digits-log
                    return 0;
                }
            }
        };
        Arrays.sort(logs, myComp);
        return logs;
    }

    public static void main(String[] args) {
        ReorderDataInLogFiles rdif = new ReorderDataInLogFiles();
        String [] logs ={"dig1 8 1 5 1","let1 art can", "dig2 3 6","let2 own kit dig", "let2 art zero"};
        System.out.println(Arrays.toString(rdif.reorderLogFiles(logs)));

    }
}
