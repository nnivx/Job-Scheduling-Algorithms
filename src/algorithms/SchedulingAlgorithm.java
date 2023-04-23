/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithm;

/**
 * Scheduling Algorithm Interface.
 *
 * This is to implement the flow
 *
 *      input -> process -> output
 *      /           |            \
 *     /            |             \
 *  jobs        algorithm    (a) Gantt Chart
 *                           (b) Results
 *
 * This is the interface to be used by the end-programmers.
 *
 * @author mxiii
 */
public interface SchedulingAlgorithm {

    /**
     * Processes a set of jobs.
     *
     * This method provides its own copy of the jobs,
     * therefore it is safe to reuse the Job array
     * passed into this method to another Scheduling
     * Algorithm.
     *
     * @param jobs The array of jobs
     * @return The algorithm result
     *
     */
    public AlgorithmResult processJobs(Job[] jobs);

    /**
     * Returns Gantt Chart produced by processJobs()
     *
     * @return The Gantt Chart
     */
    public classes.GanttChart makeGanttChart();

    /**
     * Returns the algorithm name.
     * 
     * @return The name of this algorithm
     */
    public String getName();
}
