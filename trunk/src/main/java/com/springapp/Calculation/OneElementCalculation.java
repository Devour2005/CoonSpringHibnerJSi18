package com.springapp.Calculation;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

class OneElementCalculation implements Callable<BigDecimal> {
    private final int iter;
    private final int precision;

    OneElementCalculation(int iter, int precision) {
        this.iter = iter;
        this.precision = precision;
    }

//  Bailey — Borwein — Plouffe formula
//  result = (1 / Math.pow(16, i)) * ((4 / ((8 * i) + 1)) - (2 / ((8 * i) + 4)) - (1 / ((8 * i) + 5)) - (1 / ((8 * iter) + 6)));

    @Override
    public BigDecimal call() throws Exception {
        BigDecimal funcOne, funcTwo, funcTree, funcFour, funcFive, result;
        BigDecimal numerA = new BigDecimal(4);
        BigDecimal numerB = new BigDecimal(2);
        BigDecimal numerC = new BigDecimal(1);
        BigDecimal funcOneDenum = new BigDecimal(16);
        BigDecimal funcFourDenum = new BigDecimal(5);
        BigDecimal funcFiveDenum = new BigDecimal(6);

//        long t0 = System.nanoTime();
        BigDecimal denumCoeff = BigDecimal.valueOf(8 * iter);
        funcOne = numerC.divide(funcOneDenum.pow(iter), precision + 3, RoundingMode.HALF_DOWN);
        funcTwo = numerA.divide(denumCoeff.add(numerC), precision + 3, RoundingMode.HALF_DOWN);
        funcTree = numerB.divide(denumCoeff.add(numerA), precision + 3, RoundingMode.HALF_DOWN);
        funcFour = numerC.divide(denumCoeff.add(funcFourDenum), precision + 3, RoundingMode.HALF_DOWN);
        funcFive = numerC.divide(denumCoeff.add(funcFiveDenum), precision + 3, RoundingMode.HALF_DOWN);
        result = funcOne.multiply(funcTwo.subtract(funcTree).subtract(funcFour).subtract(funcFive));
//        long t1 = System.nanoTime();
//        long elapsedTime = (t1 - t0) / 1000;
//        System.out.println("("+iter+"): " +Thread.currentThread().getName() +" "+ elapsedTime);   //Time of calculation of each expression
        return result;
    }
}