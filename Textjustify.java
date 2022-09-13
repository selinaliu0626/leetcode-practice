import java.util.ArrayList;
import java.util.List;
//关于错误的几个点
//1。加单词的时候，不能直接从左到右，一一插入空格，这样会在最后多一个
//2。当这一行不是最后一行，且只有一个单词的时候，需要区别讨论
public class Textjustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int left = 0;
        while(left <words.length){
            int right = findRight(words, maxWidth,left);
            int extra = maxWidth - getLength(left,right, words);
            String line = justifiy(words,left,right, extra,maxWidth);
            res.add(line);
            left = right+1;
        }
        return res;

    }
    private String justifiy(String[] words,int left, int right, int extra, int maxWidth){
        boolean lastLine = right==words.length-1? true: false;
        StringBuilder sb = new StringBuilder();
        int len = 0;
        if(lastLine){
            while(left< right){
                sb.append(words[left]);

                sb.append(' ');
                len += words[left++].length()+1;
            }
            sb.append(words[right]);
            len += words[right].length();
            if(len < maxWidth){
                sb.append(spaceString(maxWidth - len));
            }
            if(len < maxWidth){
                sb.append(spaceString(maxWidth - len));
            }
        }else{
            int count = right -left;
            if(count!= 0){
                int space = extra/count;
                String spaceString = spaceString(space);
                int remainder = extra % count;
                for( int i =left; i<right;i++){
                    sb.append(words[i]);
                    sb.append(spaceString);
                    if(remainder != 0){
                        sb.append(' ');
                        remainder --;
                    }
                }
                sb.append(words[right]);
            }else{//只有一个单词，且不是最后一行
                sb.append(words[right]);
                sb.append(spaceString(maxWidth-words[right].length()));
            }
        }
        return sb.toString();

    }
    private int findRight(String[] words, int maxWidth,int left){
        int right = left;
        int sum = words[right++].length();
        while(right <= words.length-1 && sum + 1 + words[right].length() <= maxWidth){
            sum += 1 + words[right].length();
            right++;
        }
        return right-1;
    }

    private int getLength(int left, int right, String[] words){
        int length = 0;
        for( int i =left; i<=right;i++){
            length += words[i].length();
        }
        return length;
    }
    private String spaceString(int length){
        StringBuilder sb = new StringBuilder();
        while(length>0){
            sb.append(' ');
            length--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Textjustify tj = new Textjustify();
        String[] words ={"what","must","be","acknowledgement","shall","be"};
        System.out.println(tj.fullJustify(words, 16));
    }

}
