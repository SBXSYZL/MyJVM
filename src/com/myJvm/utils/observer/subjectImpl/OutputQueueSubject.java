package com.myJvm.utils.observer.subjectImpl;

import com.myJvm.utils.observer.Observer;
import com.myJvm.utils.observer.Subject;
import com.myJvm.utils.observer.oberverImpl.OutputObserver;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 22454
 */
public class OutputQueueSubject implements Subject {
    private final List<Observer> observerList;
    private final LinkedList<String> outputQueue;

    public OutputQueueSubject() {
        observerList = new CopyOnWriteArrayList<>();
        outputQueue = new LinkedList<>();
        //TODO 正式使用需要去除，由调用方自定义
        observerList.add(new OutputObserver(this));
    }

    public void output(String str) {
        outputQueue.addLast(str);
        notifyAllObserver();
    }

    public String getOutput() {
        return outputQueue.removeFirst();
    }

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyAllObserver() {
        observerList.forEach(Observer::update);
    }

    @Override
    public void notifyOne() {

    }
}
