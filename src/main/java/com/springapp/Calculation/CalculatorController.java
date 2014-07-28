package com.springapp.Calculation;

import com.springapp.Calculation.DataInputForm;
import com.springapp.Calculation.IParallelPiEx;
import com.springapp.Calculation.OneElementCalculation;
import com.springapp.Calculation.PiCalculator;
import com.springapp.Validators.CalculationValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by Enot on 04.07.14.
 */
@Controller
@RequestMapping(value = "/calculation")
public class CalculatorController implements Callable<BigDecimal>, IParallelPiEx {
    private static final Logger logger = Logger.getLogger(CalculatorController.class);
    public static int numberOfThreads;
    public static int precision;
    public static long elapsedTime;

    public CalculatorController() {
    }

    @Qualifier("piCalculator")
    @Autowired
    private PiCalculator piCalculator;

    @Qualifier("calculationValidator")
    @Autowired
    private CalculationValidator calculationValidator;

    public CalculatorController(CalculationValidator calculationValidator) {
        this.calculationValidator = calculationValidator;
    }

    @InitBinder("dataInputForm")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(calculationValidator);
//        binder.setValidator(new LoginValidator());
    }

    @RequestMapping(value = "/GET", method = RequestMethod.GET)
    private ModelAndView getThreadsAndPrecision() {
        return new ModelAndView("calculation", "dataInputForm", new DataInputForm());
    }

    @RequestMapping(value = "/POST", method = RequestMethod.POST)
    public String calculMethod(@ModelAttribute(value = "dataInputForm")
                               DataInputForm dataInputForm,
                               BindingResult bindingResult,
                               Model model) throws Exception {
        calculationValidator.validate(dataInputForm, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.error("Calculation validation error");
            return "calculation";
        }
        precision = Integer.valueOf(dataInputForm.getPrecision());
        numberOfThreads = Integer.valueOf(dataInputForm.getNumberOfThreads());
        BigDecimal result = call();
        model.addAttribute("elapsedTime", dataInputForm.getElapsedTime());
        model.addAttribute("result", result);
        model.addAttribute("precision", dataInputForm.getPrecision());
        model.addAttribute("numberOfThreads", dataInputForm.getNumberOfThreads());
        return "calculResults";
    }

    @Override
    public BigDecimal call() throws Exception {
        return calculatePi(numberOfThreads, precision);
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

            elapsedTime = (t1 - t0) / 1_000_000;

            logger.info("End with time = " + elapsedTime);
//            System.out.println("End with time = " + elapsedTime);
            logger.info("End of calculation!");
            es.shutdown();
        }
        return sum;
    }












    /*@RequestMapping(value = "/POST", method = RequestMethod.POST)
    public ModelAndView calculMethod(@ModelAttribute(value = "dataInputForm")
                                     DataInputForm dataInputForm,
                                     BindingResult bindingResult,
                                     Model model) throws Exception {
        calculationValidator.validate(dataInputForm, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.error("Calculation validation error");
            return new ModelAndView("calculation", "dataInputForm", new DataInputForm());
        }
        precision = Integer.valueOf(dataInputForm.getPrecision());
        numberOfThreads = Integer.valueOf(dataInputForm.getNumberOfThreads());
        BigDecimal result = piCalculator.call();
        model.addAttribute("elapsedTime", elapsedTime);
        model.addAttribute("result", result);
        model.addAttribute("precision", dataInputForm.getPrecision());
        model.addAttribute("numberOfThreads", dataInputForm.getNumberOfThreads());
        return new ModelAndView("calculResults");
    }*/
}
