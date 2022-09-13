import java.util.HashSet;

//36. Valid Sudoku
//rows,cols,boxs
//box from 0 to eight  (rows/3)*3+cols/3
public class ValidSuduko {
    public boolean isValidSudoku(char[][] board) {
        int n = 9;
        HashSet<Character>[] rows = new HashSet[n];
        HashSet<Character>[] cols = new HashSet[n];
        HashSet<Character>[] boxes = new HashSet[n];
        for (int i = 0; i < n; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char a = board[i][j];


                //check if the position has number or empty
                if (a == '.') {
                    continue;
                }
                //check rows
                if (rows[i].contains(a)) {
                    return false;
                }
                //if rows are not contain,add to set
                rows[i].add(a);

                //check cols
                if (cols[j].contains(a)) {
                    return false;
                }
                //if not in this column now, add to col set
                cols[j].add(a);

                //check the box
                int index = (i / 3) * 3 + j / 3; //now box are labeled to 0 to 8
                if (boxes[index].contains(a)) {
                    return false;
                }
                //if not in box
                boxes[index].add(a);
            }
        }
        return true;
    }

    // to reduce the space complexity, since this size are fixed to 9
//could use bit mask to record the situation
    public boolean isValidSudoku2(char[][] board) {
        int n = 9;
        //use a binary number to record previous occurrence
        int[] rows = new int[n];
        int[] cols = new int[n];
        int[] boxes = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // if not a number ,continue;
                if (board[i][j] == '.') {
                    continue;
                }
                //if it is a number
                int val = board[i][j] - '0';
                int pos = 1 << (val - 1);// 2 is convert to 10;3to 100...

                //check the row
                if ((rows[i] & pos) > 0) {
                    return false;
                }
                rows[i] |= pos;

                //check cols
                if ((cols[j] & pos) > 0) {
                    return false;
                }
                cols[j] |= pos;

                //check boxes
                int index = (i / 3) * 3 + j / 3;
                if ((boxes[index] & pos) > 0) {
                    return false;
                }
                boxes[index] |= pos;
            }
        }
        return true;
    }
}