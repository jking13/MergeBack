package com.jking.mergeback;

import com.google.inject.Guice;
import com.jking.mergeback.module.MergeModule;
import com.jking.mergeback.worker.MergeWorker;

/**
 * Created by john on 3/30/17.
 */
public class Application {

    public static void main(String[] args){
        MergeWorker worker = Guice.createInjector(new MergeModule()).getInstance(MergeWorker.class);
        Thread thread = new Thread(worker);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
