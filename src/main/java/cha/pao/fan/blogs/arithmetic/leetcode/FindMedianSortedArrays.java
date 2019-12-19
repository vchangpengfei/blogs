package cha.pao.fan.blogs.arithmetic.leetcode;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMedianSortedArrays {


    /**
     * 时间复杂度为 O(m+n)
     * 不合题
     */
    @Test
    public void test0(){
        int[] nums1={1,3};
        int[] nums2={2};

        int np1=0;
        int np2=0;
        ArrayList<Integer> list=new ArrayList<>();
        while(np1<nums1.length||np2<nums2.length){
            int v1=((np1<nums1.length)?nums1[np1]:Integer.MAX_VALUE);
            int v2=((np2<nums2.length)?nums2[np2]:Integer.MAX_VALUE);

            if(v1<v2&&v1!=Integer.MAX_VALUE)
            {
                list.add(v1);
                np1++;
            }else if(v2!=Integer.MAX_VALUE){
                list.add(v2);
                np2++;
            }
        }



        System.out.println(list.size()%2==0?((list.get(list.size()/2)+list.get(list.size()/2-1))/2.0):(list.get(list.size()/2))+"");


    }



    /**
     * 时间复杂度为 O log(m+n) 有log应该用二分法
     *
     */
    @Test
    public void test1(){
        int[] nums1={1,3};
        int[] nums2={2};

        int np1=0;
        int np2=0;
        ArrayList<Integer> list=new ArrayList<>();
        while(np1<nums1.length||np2<nums2.length){
            int v1=((np1<nums1.length)?nums1[np1]:Integer.MAX_VALUE);
            int v2=((np2<nums2.length)?nums2[np2]:Integer.MAX_VALUE);

            if(v1<v2&&v1!=Integer.MAX_VALUE)
            {
                list.add(v1);
                np1++;
            }else if(v2!=Integer.MAX_VALUE){
                list.add(v2);
                np2++;
            }
        }



        System.out.println(list.size()%2==0?((list.get(list.size()/2)+list.get(list.size()/2-1))/2.0):(list.get(list.size()/2))+"");


    }

}
