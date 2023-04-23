/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithms.nonpreemptive;

import algorithms.AbstractSchedulingAlgorithm;
import classes.AlgorithmResult;
import classes.Job;

/**
 * Provides functions for non-preemptive algorithms
 *
 * @author mxiii
 */
public abstract class NonPreemptiveAlgorithm extends AbstractSchedulingAlgorithm{

    /**
     * @param name The name of the algorithm
     */
    public NonPreemptiveAlgorithm(String name) {
        super(name);
    }

    protected abstract Job getJob();

    /**
     * Pends all arrived jobs.
     *
     * A unique characteristic of np-algorithms is that
     * jobs will pend (wait) if they arrive while there
     * is a job being processed. This function will move
     * the jobs that has arrived before the end time to
     * pending queue.
     *
     * This functions requires that the jobs are already
     * ascending-ordered beforehand.
     * 
     * @param endtime The end time
     */
    protected void pendArrivedJobs(float endtime) {
        if (!jobs.isEmpty() && first().arrival_time <= endtime) {
            pending_jobs.add(first());
            jobs.remove(first());
            pendArrivedJobs(endtime);
        }
    }

    @Override
    protected AlgorithmResult doAlgorithm() {
        AlgorithmResult re = new AlgorithmResult();
        re.job_count = jobs.size();
        
        float et = first().arrival_time;

        // we will loop in an unoptimized way and
        // process only either per loop
        //  (a) process a job
        //  (b) set the idle time
        while (true) {
            if (!jobs.isEmpty()) {
                // pend arrived jobs first
                pendArrivedJobs(et);

                // if there is a job to process, but:

                // (a) no jobs arrived until the end time, therefore see (b)
                // (b) no pended jobs from previous call, therefore see (c)
                // (c) no pending jobs to process at all

                // it means the cpu is idling
                if (pending_jobs.isEmpty()) {
                    float at = first().arrival_time;

                    chart.appendIdle(at - et);
                    et = at; // idle time ends when a job has arrived.

                    // we finished our task, so loop again
                    continue;
                }
            }
            // Below is self explanatory, just read the statement (^_^)
            else if (pending_jobs.isEmpty()) break;

            Job j = getJob();
            pending_jobs.remove(j);

            et = et + j.burst_time;
            float tt = et - j.arrival_time;
            re.tt += tt;
            re.wt += tt - j.burst_time;

            // for now, cpu_utilization is summation of burst times
            re.cpu_utilization += j.burst_time;

            chart.append(j, j.burst_time, et,
                    pending_jobs.toArray(new Job[pending_jobs.size()]));

        }

        // finalize cpu utilization value
        re.cpu_utilization = re.cpu_utilization/et*100f;

        return re;
    }

}
