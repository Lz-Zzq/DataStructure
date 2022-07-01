package com.liu.v_02_queue;

import java.util.Scanner;

/**
 * 队列数组实现
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试 创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add) : 添加数据到队列");
            System.out.println("s(get) : 从队列中取出数据");
            System.out.println("h(head): 查看队列头部的数据");
            //接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数:");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int queue = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", queue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int i = arrayQueue.headQueue();
                        System.out.println("队列头部值" + i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e'://退出
                    scanner.close();
                    loop = false;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//编写一个ArrayQueue类
class ArrayQueue {
    private final int maxSize; //表示数组中的最大容量
    private int front; //指向队列的头
    private int rear; //指向队列的尾部
    private final int[] arr; //该数据用于存放数据,模拟队列

    //创建队列构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;  //指向队列头部的前一个位置
        rear = -1; //指向队列尾部
    }

    //判断队列是否满  队列尾部指向最大容量-1
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为null
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满,不能加入数据");
            return;
        }
        rear++; //让rear后移
        arr[rear] = n;//让n存放到队尾
    }

    //获取队列的数据,出队列
    public int getQueue() {
        //判断队列是否为null
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空,不能取出数据");
        }
        front++; //front后移
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为null,无法打印");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列头部数据,注意不是取出数据
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("队列为空");
        return arr[front + 1];
    }


}