package com.springapp.calculation;

import com.springapp.controllers.CalculatorController;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Calculation implements Callable<BigDecimal>, IParallelPiEx{

    private static final Logger logger = Logger.getLogger(Calculation.class);

    @Override
    public BigDecimal call() throws Exception {
        return calculatePi(CalculatorController.numberOfThreads, CalculatorController.precision);
    }

    @Override
    public BigDecimal calculatePi(int numberOfThreads, int precision) {
        ExecutorService es = Executors.newFixedThreadPool(numberOfThreads);
        List<Callable<BigDecimal>> callabBD = new ArrayList<Callable<BigDecimal>>();
        for (int i = 0; i <= precision + 2; i++) {
            callabBD.add(new OneElementCalculation(i, precision));
        }
        logger.info("Start of calculation!");
        long t0 = System.nanoTime();

        BigDecimal sum = BigDecimal.ZERO;
        try {
            List<Future<BigDecimal>> futures = es.invokeAll(callabBD);
            for (Future<BigDecimal> fut : futures) {
                sum = sum.add(fut.get(), new MathContext(precision + 1, RoundingMode.HALF_DOWN));
            }
        } catch (InterruptedException | ExecutionException exc) {
            logger.error("Exception while calculation" + exc.getMessage());
        } finally {
            System.out.println("Real Pi = 3.1415926535897932384626433832795028841971693993" +
                    "7510582097494459230781640628620899862803482534211706798214808651328230" +
                    "664709384460955058223172535940812848111745028410270193852110555964462294895493038196");
            long t1 = System.nanoTime();
            System.out.println("Calc Pi = " + sum);

            CalculatorController.elapsedTime = (t1 - t0) / 1_000_000;

            logger.info("End with time = " + CalculatorController.elapsedTime);
            logger.info("End of calculation!");
            es.shutdown();
        }
        return sum;
    }

}
