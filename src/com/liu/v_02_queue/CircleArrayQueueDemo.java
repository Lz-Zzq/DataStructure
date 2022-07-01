package com.liu.v_02_queue;

import java.util.Scanner;

/**
 * 队列数组实现
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println(3%4);
        //测试 创建一个队列
        CircleArray arrayQueue = new CircleArray(4);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add) : 添加数据到队列");
            System.out.println("g(get) : 从队列中取出数据");
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

;/**
 * 环形队列总体思想
 * front 指向 第一个元素
 * rear 指向 第一个元素
 * ----判断队列是否为null-------
 * 判断队列是否满  rear + 1 % maxSize == front  尾元素下标+1 % 长度  例如 f = 0  长度 4  rear下标到达末尾+1等同  3+1 % 4 == 0 队列满,
 * 又或者 f=1 长度 4    rear下标为 0  0+1 % 4 == 1 队列满
 * ----添加数据----
 * arr[rear] = n  将数据直接添加   随后将  rear = rear+1 % maxSize  rear 后移
 * f = 1  rear = 3  rear = rear+1 % maxSize === 0
 * ----获取数据----
 * value = arr[front] 直接将数据读取出来
 * front = front+1 % maxSize   让front向后移位
 * ----显示数据-----         [每次添加完毕rear都会+1]  [[想办法从头开始执行,头下标+有效数组个数]]                            `
 * i = front   i < front + {(rear + maxSize - front) % maxSize} 队列尾+最大数组-头 % maxSize  + front得到有效数组  front从头开始遍历
 * i % maxSize   arr[i % maxSize]   输出头[[超出临界值会给0后面的包括0进行赋值]]
 *
 */
class CircleArray {
    private final int maxSize; //表示数组中的最大容量
    private int front; //指向队列的第一个元素,也就是arr[front] 就是队列的第一个元素 front的初始值为0
    private int rear; //指向队列的最后一个元素   为空出的空间作为一个约定 rear初始值为0
    private final int[] arr; //该数据用于存放数据,模拟队列

    //创建队列构造器
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满  当rear指向最后一个元素时+1
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        //直接将数据加入
        arr[rear] = n;
        //将rear后移,这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据,出队列
    public int getQueue() {
        //判断队列是否为null
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空,不能取出数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //先把这个front 对应的值保存到一个临时的变量
        //将front 后移  考虑取模
        //将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为null,无法打印");
            return;
        }
        //从front开始遍历,遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示队列头部数据,注意不是取出数据
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("队列为空");
        return arr[front];
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


}