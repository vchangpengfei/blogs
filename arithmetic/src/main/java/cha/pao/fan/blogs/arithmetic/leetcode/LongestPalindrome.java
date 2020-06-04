package cha.pao.fan.blogs.arithmetic.leetcode;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestPalindrome {

    /**
     * 暴力算法
     * @param s
     */
    @ParameterizedTest
    @CsvSource({"babad","ab"})
    public void LongestPalindrome1(String s){
        if(s!=null&&s.length()>0){
            int pos=0;
            int length=0;
            char[] charArray = s.toCharArray();
            for(int i=0;i<charArray.length-1;i++){
                for(int j=i+1;j<charArray.length;j++){
                    //判断
                    if(judge(charArray,i,j)){
                        if(length<(j-i)){
                            length=j-i;
                            pos=i;
                        }
                    }
                }
            }
            System.out.println(s.substring(pos,pos+length+1));
        }
        System.out.println("");
    }
    private static boolean judge(char[] charArray, int left, int right) {
        while(left<right)
        {
            if(charArray[left]!=charArray[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 优化算法
     * @param s
     */
    @ParameterizedTest
    @CsvSource({"a","babad","cbbd"})
    public void LongestPalindrome2(String s){
        String res="";
        if(s!=null&&s.length()>0){

            char[] charArray = s.toCharArray();
            for(int i=2;i<=charArray.length*2;i++){
//                中心扩展算法
                int left=i/2;
                int right=(i%2==0)?i/2:(i/2+1);
                if(left>0&&right<=charArray.length){
                    String re=centerJudge(left-1,right-1,charArray,s);
                    if(re.length()>res.length())
                    {
                        res=re;
                    }
                }

            }
        }
        System.out.println(res);
    }

    private String centerJudge(int left,int right, char[] charArray,String s) {
        String res="";
        while(left>=0&&right<charArray.length){
            if(charArray[left]==charArray[right]){
                res= s.substring(left,right+1);
            }else{
                return res;
            }
            left--;
            right++;
        }
        return res;
    }


}
