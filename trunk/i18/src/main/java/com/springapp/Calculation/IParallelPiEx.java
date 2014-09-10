package com.springapp.Calculation;

import java.math.BigDecimal;

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
