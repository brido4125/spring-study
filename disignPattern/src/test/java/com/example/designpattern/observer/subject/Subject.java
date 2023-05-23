package com.example.designpattern.observer.subject;

import com.example.designpattern.observer.Observer;

public interface Subject {

    /*
    * register : add an observer
    * remove : remove an observer
    * notify : notify all observers
    * */
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
