package cha.pao.fan.blogs.arithmetic.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pfchang
 * on 2020/6/3.
 *
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class ZigzagConvert {


    @ParameterizedTest
    @CsvSource({"LEETCODEISHIRING,3","LEETCODEISHIRING,4","AB,1"})
    public void LongestPalindrome1(String s, int numRows){

        if(numRows==1){

        }

        int length=s.length();//总字符数
        int zcol=numRows-1;//每个z需要的列数

        List<StringBuilder> list=new ArrayList<StringBuilder>(numRows);

        int row=0;
        int col=0;
        for(int i=0;i<length;i++){
            StringBuilder sb;
            if(list.size()>row)
            {
                sb=list.get(row);
            }else{
                sb=new StringBuilder();
                list.add(sb);
            }


            if(row<numRows-1&&col%zcol==0){
                sb.append(s.charAt(i));
                row++;
            }else if((row<numRows-1&&col%zcol!=0)||row==numRows-1){
                sb.append(s.charAt(i));
                row--;
                col++;
            }

        }

        System.out.println(String.join("",list));

    }

}
