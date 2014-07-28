package com.springapp.Calculation;

import java.math.BigDecimal;

/**
 * The test assignment class entry point. Please implement the following interface.
 * The algorithm must be parallelizable
 * to any number of threads and have the specified precision of the return value.
 */
public interface IParallelPiEx {

    /**
     * Parallel calculation of the Pi value.
     *
     * @param numberOfThreads - number of threads which will do the calculation (including the caller thread)
     * @param precision - the position of the significant value digit after the decimal separator.
     * @return - the Pi value.
     */
    public BigDecimal calculatePi(int numberOfThreads, int precision);
}
