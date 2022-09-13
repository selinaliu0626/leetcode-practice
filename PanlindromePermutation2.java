import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//267. Palindrome Permutation II
public class PanlindromePermutation2 {
    Set< String > set = new HashSet< >();
    public List< String > generatePalindromes(String s) {
        int[] map = new int[128];

        //存储一般的字符
        char[] st = new char[s.length() / 2];
        //判断是否可以成为panlidrome
        if (!canPermutePalindrome(s, map))
            return new ArrayList< >();


        char ch = 0;
        int k = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 == 1)
                ch = (char) i;
            for (int j = 0; j < map[i] / 2; j++) {
                st[k++] = (char) i;
            }
        }
        permute(st, 0, ch);
        return new ArrayList < String > (set);
    }

    // 如果奇数个字母超过一个，它无法成为palindrome，不必进行dfs
    public boolean canPermutePalindrome(String s, int[] map) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
    }


    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    //dfs因为pandlindrome 是对称的，所以当我们计算出一半的时候，我们就可以知道这个string 是什么样子的，比如abba,我们计算出ab，然后reverse就可以得到ba， 从而得到abba

    void permute(char[] s, int l, char ch) {
        if (l == s.length) {
            set.add(new String(s) + (ch == 0 ? "" : ch) + new StringBuffer(new String(s)).reverse());
        } else {
            for (int i = l; i < s.length; i++) {
                //剪枝，字母不一样再交换
                if (s[l] != s[i] || l == i) {
                    swap(s, l, i);
                    permute(s, l + 1, ch);
                    swap(s, l, i);
                }
            }
        }
    }
}

