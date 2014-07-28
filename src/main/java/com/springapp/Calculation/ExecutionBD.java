package com.springapp.Calculation;

import java.util.Scanner;

/*Нужно реализовать параллельное вычисление числа пи.
Входными параметрами являются количество потоков,
включая текущий, и точность - до какого знака после
запятой должно быть посчитано число.*/


public class ExecutionBD {
    protected static int numderOfThreads;
    protected static int precision;

    public static void main(String args[]) throws Exception {
        numderOfThreads = threadsNumberInput();
        precision = precisionIn();

//        for(int k = 0; k < 100000; k++) {         //"Warming-up" the method call
//           new OneElementCalcul(100, 110).call();
//        }

        PiCalculator cpi = new PiCalculator();
        cpi.call();
    }

    //Input of the number of threads from keyboard
    public static int threadsNumberInput() {
        int theadsNumbInpt= 0;
        Scanner scThrdsNumb = new Scanner(System.in);
        System.out.print("Enter the number of threads: ");
        while (scThrdsNumb.hasNextInt()) {
            theadsNumbInpt = scThrdsNumb.nextInt();
            if (theadsNumbInpt <= 0) {
                System.out.print("Enter correct number of threads:");
            } else {
                if (theadsNumbInpt > 0) {
                    break;
                }
            }
        }
        return theadsNumbInpt;
    }

    //Input of the precision from keyboard
    public static int precisionIn() {
        int precision = 0;
        Scanner scanPrecision = new Scanner(System.in);
        System.out.print("Enter the desired precision: ");
        while (scanPrecision.hasNextInt()) {
            precision = scanPrecision.nextInt();
            if (precision <= 0) {
                System.out.print("Enter correct precision:");
            } else {
                if (precision > 0) {
                    break;
                }
            }
        }
        return precision;
    }
}




