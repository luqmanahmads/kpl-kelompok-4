package com.study.dwika.kplchat.utils;

import io.reactivex.Scheduler;

/**
 * Created by A.I on 15/12/2017.
 */

public interface BaseSchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
