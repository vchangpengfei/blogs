package cha.pao.fan.blogs.arithmetic.leetcode;

/**
 * Created by pfchang
 * on 2021/2/1.
 */
public class guibingpaixu {

    public static int[] mergeSort(int[] nums,int l,int h)
    {
        if(l==h)
        {
            return new int[]{nums[l]};
        }

        int mid=(l+h-1)/2;//找到中间数
        int [] leftArr=mergeSort(nums,l,mid);
        int [] rightArr=mergeSort(nums,mid+1,h);
        int [] newNum=new int[leftArr.length+rightArr.length];

        int m=0,i=0,j=0;
        while(i<leftArr.length&& j<rightArr.length)
        {
            newNum[m++]=leftArr[i]<rightArr[j]?leftArr[i++]:rightArr[j++];
        }

        while (i<leftArr.length)
            newNum[m++]=leftArr[i++];
        while (j<rightArr.length)
            newNum[m++]=rightArr[j++];
        return newNum;
    }


    public static void main(String args[])
    {
        int[] nums = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 10 };
//        int[] nums = new int[] { 1, 3, 0 ,8,2};
        int[] newNums = mergeSort(nums, 0, nums.length - 1);
        for (int x : newNums) {
            System.out.println(x);
        }
    }

}
