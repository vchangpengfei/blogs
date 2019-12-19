package cha.pao.fan.blogs.arithmetic.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void test0(){
        String s="babad";
        Map<Integer,Character> map=new HashMap<>();
        String res="";
        for(int i=0;i<s.length();i++)
        {
            char t=s.charAt(i);
            map.put(i,t);
            //最长串
            int clen=i+1;
//                    (i+1)/2+((i+1)%2==0?0:1);
            if(res.length()<clen){

            }



        }



    }


}
