/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 * Utility class for wrapping algorithm results.
 *
 * @author mxiii
 */
public class AlgorithmResult {
    
    public float tt;
    public float wt;
    public float cpu_utilization;
    public int job_count;

    public AlgorithmResult() {
        this(0, 0, 0, 0);
    }

    public AlgorithmResult(int job_count, float tt, float wt,
            float cpu_utilization) {
        this.tt = tt;
        this.wt = wt;
        this.cpu_utilization = cpu_utilization;
        this.job_count = job_count;
    }

    /**
     * @return The average turn around time
     */
    public final float getAverageTurnAroundTime() {
        return tt/job_count;
    }

    /**
     * @return The average waiting time
     */
    public final float getAverageWaitingTime() {
        return wt/job_count;
    }
}
