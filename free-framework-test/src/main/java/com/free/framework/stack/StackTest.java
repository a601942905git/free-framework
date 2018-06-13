package com.free.framework.stack;

import java.util.Stack;

/**
 * com.free.framework.stack.StackTest
 *
 * @author lipeng
 * @dateTime 2018/5/8 上午10:58
 */
public class StackTest {

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("初始栈元素：");
        stack.stream().forEach(System.out::print);
        System.out.println();
        System.out.println("=======================");

        // 从栈顶取出一个元素，但是不从栈中删除该元素
        System.out.println("调用peek从栈顶取出元素为：" + stack.peek());
        System.out.println("当前栈中的元素为：");
        stack.stream().forEach(System.out::print);
        System.out.println();
        System.out.println("=======================");

        // 从栈顶取出一个元素，并且从栈中删除该元素
        System.out.println("调用peek从栈顶取出元素为：" + stack.pop());
        System.out.println("当前栈中的元素为：");
        stack.stream().forEach(System.out::print);
        System.out.println();
        System.out.println("=======================");

        // 取第一个元素
        System.out.println(stack.get(0));

        // 取最后一个元素
        System.out.println(stack.get(stack.size() - 1));
    }
}
