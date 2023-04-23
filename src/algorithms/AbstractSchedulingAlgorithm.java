/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithms;

import classes.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Superclass of scheduling algorithms.
 *
 * This class ensures that subclasses do not have to worry about
 * formatting the Gantt Chart to be displayed.
 *
 * @see classes.GanttChartFactory
 *
 * @author mxiii
 */
public abstract class AbstractSchedulingAlgorithm implements SchedulingAlgorithm {

    private final String name;

    protected ArrayDeque<Job> jobs;
    protected ArrayDeque<Job> pending_jobs;
    protected ArrayDeque<JobInfo> job_infos;

    protected final GanttChart chart;
    private AlgorithmResult result;

    /**
     * @param name The name of the algorithm
     */
    public AbstractSchedulingAlgorithm(String name) {
        this.name = name;
        this.jobs = new ArrayDeque<>();
        this.pending_jobs = new ArrayDeque<>();
        this.chart = GanttChartFactory.create();
    }

    /**
     * Perform the algorithm here.
     *
     * @return The result of the algorithm
     */
    protected abstract AlgorithmResult doAlgorithm();
    
    /**
     * Processes a set of jobs.
     *
     * This method provides its own copy of the jobs,
     * therefore it is safe to reuse the Job array
     * passed into this method to another Scheduling
     * Algorithm.
     *
     * @param jobset The array of jobs
     *
     * @return The algorithm result
     */
    @Override
    public AlgorithmResult processJobs(Job[] jobset) {
        chart.clear();
        jobs.clear();
        pending_jobs.clear();

        Arrays.sort(jobset, new Comparator<Job>(){
            @Override
            public int compare(Job a, Job b) {
                // accurate in the millionths...
                return (int) ((a.arrival_time - b.arrival_time)*1000000);}
        });
        for (Job e: jobset) 
            jobs.add(new Job(e.name, e.arrival_time, e.burst_time, e.priority));

        return doAlgorithm();
    }

    /**
     * Since it's annoying when changing containers.
     *
     * @return the next incoming job in the queue
     */
    protected Job first() {
        return jobs.peekFirst();
    }

    /**
     * Returns the resulting Gantt Chart.
     *
     * This method returns a new DefaultGanttChart every time it
     * is called, more specifically, a clone of the
     * internal chart. This means that the chart returned
     * is perfectly independent of this class, and from
     * the other charts returned by this method.
     *
     * @see processJobs()
     *
     * @return The Gantt Chart
     */
    @Override
    public final GanttChart makeGanttChart() {
        return GanttChartFactory.create(chart);
    }

    /**
     * Returns the name of this algorithm.
     *
     * @return The algorithm name
     */
    @Override
    public final String getName() {
        return this.name;
    }

}
