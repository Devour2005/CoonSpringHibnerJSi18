package com.springapp.controllers;

import com.springapp.entity.Computer;
import com.springapp.service.computerService.ComputerService;
import com.springapp.validators.UpdateValidator;
import com.springapp.forms.UserForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

class ComputerCollector extends CustomCollectionEditor {
    //Log4j
    private static final Logger logger = Logger.getLogger(ComputerCollector.class);

    public ComputerCollector(Class<? extends Collection> collectionType) {
        super(collectionType);
    }

    @Qualifier("computerServiceImpl")
    @Autowired
    private ComputerService computerService;

    @Qualifier("updateValidator")
    @Autowired
    private UpdateValidator updateValidator;

    @InitBinder("userForm")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(updateValidator);

        binder.registerCustomEditor(Set.class, "computers", new CustomCollectionEditor(Set.class) {

            @Override
            protected Object convertElement(Object element) {
                String pcName = null;
                Set<Computer> computerSet = new LinkedHashSet<>();

                if (element instanceof String && !((String) element).equals("")) {
                    pcName = (String) element;
                    computerSet.add(computerService.getComputerByName(pcName));
                    new UserForm().setComputers(computerSet);
                }
                if (element instanceof String && ((String) element).equals("Delete")) {
                    computerSet.clear();
//                    UserForm userForm = new UserForm();
//                    userForm.setComputers(computerSet);
                    new UserForm().setComputers(computerSet);
                }
                return pcName != null ? computerService.getComputerByName(pcName) : null;
            }
        });
    }
}


/*

public class ReflectiveCollectionEditor extends CustomCollectionEditor {

    private Object dataAccessObject;
    private String dataAccessMethod;
    private PropertyEditor stringConvertor;

    public ReflectiveCollectionEditor(Class collection) {
        super(collection);
    }

    public ReflectiveCollectionEditor(Class collection, boolean nullAsEmptyCollection) {
        super(collection, nullAsEmptyCollection);
    }

    protected Object convertElement(Object element) {
        if (!(element instanceof String)) {
            new IllegalArgumentException("The element to convert must be of type java.lang.String");
        }
        String textValue = (String) element;
        Object result = null;
        try {
            if (this.stringConvertor == null) {
                Method method = this.dataAccessObject.getClass().getMethod(this.dataAccessMethod, new Class[]{String.class});
                result = method.invoke(this.dataAccessObject, new Object[] {textValue});
            }
            else {
                this.stringConvertor.setAsText(textValue);
                Object value = this.stringConvertor.getValue();
                Method method = this.dataAccessObject.getClass().getMethod(this.dataAccessMethod, new Class[]{value.getClass()});
                result = method.invoke(this.dataAccessObject, new Object[] {value});
            }
        }
        catch(Exception ex) {
            throw new ReflectiveCollectionEditorException("An error occurred while executing: " + this.dataAccessMethod, ex);
        }

        return result;
    }
*/







           /* @Override
            protected Object convertElement(Object element) {
                String pcName = null;
                Set<Computer> computerSet = new LinkedHashSet<>();

                if (element instanceof String && !((String) element).equals("")) {
                    pcName = (String) element;
                    computerSet.add(computerService.getComputerByName(pcName));
                    new UserForm().setComputers(computerSet);
                    logger.info("New PC assigned");
                }
                if (element instanceof String && ((String) element).equals("Delete")) {
                    computerSet.clear();
                    new UserForm().setComputers(computerSet);
                    logger.info("PC Unassigned");
                }
                return pcName != null ? computerService.getComputerByName(pcName) : null;
            }
        });
    }
}
 }     */
