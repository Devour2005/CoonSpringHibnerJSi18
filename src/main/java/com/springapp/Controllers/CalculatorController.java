package com.springapp.controllers;

import com.springapp.calculation.Calculation;
import com.springapp.calculation.DataInputForm;
import com.springapp.validators.CalculationValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * Created by Enot on 04.07.14.
 */
@Controller
@RequestMapping(value = "/calculation")
public class CalculatorController {
    private static final Logger logger = Logger.getLogger(CalculatorController.class);
    public static int numberOfThreads;
    public static int precision;
    public static long elapsedTime;

    public CalculatorController() {
    }

    @Qualifier("calculationValidator")
    @Autowired
    private CalculationValidator calculationValidator;

    public CalculatorController(CalculationValidator calculationValidator) {
        this.calculationValidator = calculationValidator;
    }

    @InitBinder("dataInputForm")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(calculationValidator);
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
        BigDecimal result = new Calculation().call();
        model.addAttribute("result", result);
        model.addAttribute("precision", dataInputForm.getPrecision());
        model.addAttribute("elapsedTime", elapsedTime);
        model.addAttribute("numberOfThreads", dataInputForm.getNumberOfThreads());
        return "calculResults";
    }


}





