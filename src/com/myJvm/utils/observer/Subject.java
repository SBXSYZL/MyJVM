package com.myJvm.utils.observer;

/**
 * @author 22454
 */
public interface Subject {
    void attach(Observer observer);

    void notifyAllObserver();

    void notifyOne();
}
