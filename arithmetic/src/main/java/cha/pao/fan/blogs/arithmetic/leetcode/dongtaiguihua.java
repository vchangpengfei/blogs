package cha.pao.fan.blogs.arithmetic.leetcode;

/**
 * Created by pfchang
 * on 2021/2/1.
 */
public class dongtaiguihua {


    private char[] a="mitcmu".toCharArray();
    private char[] b="mtacnu".toCharArray();
    private int n=6;
    private int m=6;
    private int minDist=Integer.MAX_VALUE;

    public void dongtaiguihua(int i,int j,int edist){
        if(i<n||j==m){
            if(i<n) edist+=(n-i);
            if(i<m) edist+=(m-j);
            if(edist<minDist) minDist=edist;
            return ;
        }

        if(a[i]==b[j]){
            dongtaiguihua(i+1,j+1,edist);
        }else {
            dongtaiguihua(i+1,j,edist+1);
            dongtaiguihua(i,j+1,edist+1);
            dongtaiguihua(i+1,j+1,edist+1);
        }

    }


    public static void main(String args[])
    {




    }

}
