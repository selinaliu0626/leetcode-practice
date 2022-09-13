import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0;
        List<String> res = new ArrayList<>();
        while(left < words.length){
            int right = findRight(left,words,maxWidth);
            res.add(justify(left,right,words,maxWidth));
            left = right + 1;
        }
        return res;
    }

    //find how many words we can put in one line
    private int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int sum = words[right++].length();
        //when  current length + 1 + next word's length is less or equal to maxWidth
        //we could continue add this word

        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth){
            sum += 1 + words[right++].length();
        }
        return right -1;
    }

    private String justify(int left, int right, String[] words, int maxWidth){
        //if there is only one word here
        if (right - left == 0) return padResult(words[left],maxWidth);

      // If it's the last line then the result is all words separated by a single space.
        boolean isLastLine = right == words.length-1;
        int numSpaces = right - left ;
        int totalSpace = maxWidth - wordsLength(left,right,words);

        // Otherwise we calculate the size of each space evenly
        // and if there is a remainder we distribute an extra space until it is gone.
        String space = isLastLine? " " : blank(totalSpace / numSpaces);
        int remainder = isLastLine? 0 : totalSpace % numSpaces;

        StringBuilder sb = new StringBuilder();
        for(int i = left; i <= right; i++) {
            sb.append(words[i])
                    .append(space)
                    .append(remainder-- > 0 ? " " : "");
        }
      return padResult(sb.toString().trim(),maxWidth);
    }

    //calculate the lengths from left to right
    private int wordsLength(int left, int right, String[] words) {
        int wordsLength = 0;
        for (int i = left; i <= right; i++) wordsLength += words[i].length();
        return wordsLength;
    }

    private String padResult(String s, int maxWidth){
        return s + blank(maxWidth - s.length());
    }

    private String blank(int length){
        return new String (new char[length]).replace('\0',' ');
    }

    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
//       String[] words ={"what","must","be","acknowledgement","shall","be"};
        String[] words ={"This", "is", "an", "example", "of", "text", "justification."};
       System.out.println(tj.fullJustify(words, 16));
    }
}
