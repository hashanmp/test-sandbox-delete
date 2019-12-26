package com.javatest;

import java.util.Arrays;
import java.util.Date;

import sun.security.ec.point.Point;

public class Main{
    public static void main(final String[] args) {
        // write your code
        System.out.println("gitversion");
        final int myAge = 28;
        System.out.println(myAge);
        Date now = new Date();
        System.out.println(now);
        // Point point1 = new Point(x:1, y:2);
        // Point point2 = point1;
        // System.out.println(point1);
        // point1.x = 4;
        // System.out.println(point2);
        String message = new String("git \t version \n and sha");
        System.out.println(message);
        int [] numbers = new int [5];
        numbers[0] = 1;
        numbers[2] = 3;
        System.out.println(numbers);
        System.out.println(Arrays.toString(numbers));

    }
}