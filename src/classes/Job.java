/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;


/**
 * Utility class for wrapping job information.
 *
 * @author mxiii
 */
public class Job {
    /**
     * Default string for idle time
     */
    public final static String IDLE = "idle";

    /**
     * Job name
     */
    public String name;

    /**
     * Arrival time
     */
    public float arrival_time;

    /**
     * Burst time
     */
    public float burst_time;

    /**
     * Priority
     */
    public int priority;

    /**
     * Construct new job.
     *
     * @param name
     * @param arrival_time
     * @param burst_time
     * @param priority
     */
    public Job(String name, float arrival_time, float burst_time, int priority) {
        this.name = name;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
        this.priority = priority;
    }

    /**
     * Construct new job from copy.
     *
     * @param copy job to be copied
     */
    public Job(Job copy) {
        this.name = copy.name;
        this.arrival_time = copy.arrival_time;
        this.burst_time = copy.burst_time;
        this.priority = copy.priority;
    }

    /**
     * Create an idle job. CPU idle time are treated as special jobs
     * under the hood...
     *
     * @param duration how long the CPU started idling
     * @param start_time when did the CPU started idling
     *
     * @return a special job for idle
     */
    public static Job idle(float duration, float start_time) {
        return new Job(IDLE, start_time, duration, 0);
    }

    /**
     * Create an idle job. CPU idle time are treated as special jobs
     * under the hood...
     *
     * @param start_time when did the CPU started idling
     * @param end_time when did the CPU ended idling (arrival time of
     * the next available job)
     *
     * @return a special job for idle
     */
    public static Job idleUntil(float start_time, float end_time) {
        return idle(end_time - start_time, start_time);
    }

}
