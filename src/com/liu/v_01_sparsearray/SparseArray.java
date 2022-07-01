package com.liu.v_01_sparsearray;

import java.io.*;

/**
 * @Author 刘政
 * @Date 2022/4/20 22:27
 * @Software IDEA
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组
        //0:表示没有值
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始二维数组
        System.out.println("---------  原始的二维数组  ---------");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组 转 稀疏数组
        //1.先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println(sum);

        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组第一行赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组,将非0的值存放到sparseArr中
        //count用于记录是第几个非0数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    //行
                    count++;
                    //的到不为0数值第几行
                    sparseArr[count][0] = i;
                    //得到不为0数值第几列
                    sparseArr[count][1] = j;
                    //得到不为0数值的数值
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println("得到的稀疏数组");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        //将稀疏数组存储到文件中
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("E:\\a.txt"));

        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[0].length; j++) {
                fileWriter.write(String.valueOf(sparseArr[i][j]) + "-");
            }
            fileWriter.newLine();
        }
        fileWriter.close();

        //读取稀疏数组
        BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\a.txt"));
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();

        String[] split = stringBuilder.toString().split("-");
        int d = Integer.parseInt(split[2]) + 1;
        int co = 0;
        int[][] ints1 = new int[3][d];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < 3; j++) {
                ints1[i][j] = Integer.parseInt(split[co++]);
            }
        }

        System.out.println("=============================恢复之后的稀疏数组=============================");
        //先读取稀疏数组的第一行,根据第一行的数据,创建原始的二维数组
//        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        int[][] chessArr2 = new int[ints1[0][0]][ints1[0][1]];

        //读取稀疏数组的后几行数据,并赋给原始的二维数组即可
        for (int i = 1; i < ints1.length; i++) {
            chessArr2[ints1[i][0]][ints1[i][1]] = ints1[i][2];
        }

        for (int[] ints : chessArr2) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

    }
}