package exam.controller;

/**
 * Interface used to provide the model with a method to notify observing controllers of a change to its state.
 */
public interface ModelObserver {

    /**
     * Notifies the observer of a change to the model's state.
     */
    void notifyObserver();

}
