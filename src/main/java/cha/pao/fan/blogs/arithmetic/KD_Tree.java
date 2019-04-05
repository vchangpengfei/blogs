package cha.pao.fan.blogs.arithmetic;

public class KD_Tree {

    private class Node{
        //分割的维度
        int partitionDimention;
        //分割的值
        double partitionValue;
        //如果为非叶子节点，该属性为空
        //否则为数据
        double[] value;
        //是否为叶子
        boolean isLeaf=false;
        //左树
        Node left;
        //右树
        Node right;
        //每个维度的最小值
        double[] min;
        //每个维度的最大值
        double[] max;
    }


}
