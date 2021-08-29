package exam.controller;

import exam.model.facade.CryptoFacade;

import java.util.concurrent.ExecutorService;

/**
 * Abstract class used to define and implement methods shared by all controllers.
 */
public abstract class AbstractController {

    protected CryptoFacade model;

    protected ExecutorService modelTaskExecutor;

    // Adapted from Task 3 and https://github.com/james-d/SimpleMVP

    /**
     * Sets the model to be used by the controller.
     *
     * @param model the model to be used
     * @throws IllegalArgumentException if a model has already been set
     */
    public void setModel(CryptoFacade model) {
        if (null != this.model) {
            throw new IllegalStateException();
        }
        this.model = model;
    }

    /**
     * Sets the ExecutorService to be used by the controller.
     *
     * @param executorService the ExecutorService to be used
     */
    public void setModelTaskExecutor(ExecutorService executorService) {
        if (null != this.modelTaskExecutor) {
            throw new IllegalStateException();
        }
        this.modelTaskExecutor = executorService;
    }
}
