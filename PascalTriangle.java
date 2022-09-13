import java.util.ArrayList;
import java.util.List;

//119. Pascal's Triangle II
public class PascalTriangle {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<=rowIndex;i++){
            result.add(0,1);
            for(int j =1;j<result.size()-1;j++){
                result.set(j,result.get(j)+result.get(j+1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PascalTriangle pt= new PascalTriangle();
        List<Integer> res= pt.getRow(4);
        System.out.println(res);
    }
}
