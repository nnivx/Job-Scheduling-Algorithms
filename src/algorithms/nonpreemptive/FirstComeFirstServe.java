/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithms.nonpreemptive;

import classes.Job;

/**
 *
 * @author mxiii
 */
public final class FirstComeFirstServe extends NonPreemptiveAlgorithm {

    public FirstComeFirstServe() {
        super("First Come First Serve (FCFS)");
    }

    @Override
    protected Job getJob() {
        return pending_jobs.peekFirst();
    }

}
