package com.myJvm.utils.observer.oberverImpl;

import com.myJvm.utils.observer.Observer;
import com.myJvm.utils.observer.Subject;
import com.myJvm.utils.observer.subjectImpl.OutputQueueSubject;

/**
 * @author 22454
 */
public class OutputObserver implements Observer {
    private final OutputQueueSubject subject;

    public OutputObserver(OutputQueueSubject subject) {
        this.subject = subject;
    }

    @Override
    public void update() {
        System.out.println("_______________________________________________________________________________________________________________________________________");
        System.out.println(subject.getOutput());
        System.out.println("_______________________________________________________________________________________________________________________________________");
    }

    @Override
    public Subject getSubject() {
        return subject;
    }
}
