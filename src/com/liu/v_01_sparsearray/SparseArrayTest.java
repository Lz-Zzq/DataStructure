package com.liu.v_01_sparsearray;


import java.io.*;

/**
 * 整体思想_稀疏数组
 * 1.创建一个二维数组 A,数组中存在N个非0数字
 * 2.得到 A 中的非0数字的个数
 * 3.使用for循环,行数与列数分别为 A.length 与A[0].length
 * 4.使用if判断非0数字,使用计数器Cou读取非0数字是第 N 个 存放在稀疏数组 B[Cou][0] = i , B[Cou][1] = j , B[Cou][2] = A[i][j]
 * 5.将稀疏数组的第一列 B[0][0] B[0][1] 存放到 二维数组 C 中作用于行与列
 * 6.使用单层 for 将 B[i][0] B[i][1] 存放到 C[][] 中括号中作用于非0数据的位置  =  B[i][2] 最后将非0数值存放到数组中
 */
public class SparseArrayTest {
    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        System.out.println("=========================原始的二维数组=========================");

        //1.得到0的个数
        int sum = 0;
        for (int[] ints : chessArr1) {
            for (int anInt : ints) {
                if (anInt != 0) sum += 1;
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

        //将二维数组转换为稀疏数组  始终拥有一行  一定是3列
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //将非0的值存放到数组中    获取二维数组的非0数字个数 与值 if判断非0值 计数器统计个数
        int count = 0;//记录第几个是非零数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    //第一个非0数就存放在第一位
                    count += 1;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出
        System.out.println("==============================输出稀疏数组==============================");
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

        System.out.println("==============================恢复普通数组==============================");
        //获取行列或者非0数值的位置
        int row = sparseArr[0][0];
        int lie = sparseArr[0][1];


        //恢复思路   N行 1列 的数据 = [n][2]
        int[][] chessArr2 = new int[row][lie];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }


    }
}