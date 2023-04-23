/*
 * Copyright (C) 2014 mxiii
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package classes;

/**
 * Utility class for Gantt chart nodes.
 * 
 * @author mxiii
 */
public class GanttChartNode {

    /**
     * The job this node will display
     */
    public Job job;

    /**
     * How long the job stayed in the CPU
     */
    public float cpu_time;

    /**
     * The end time
     */
    public float end_time;

    /**
     * The pending jobs, can be null
     */
    public Job[] pending_jobs;

    /**
     * Constructs a new node.
     *
     * @param job
     * @param cpu_time
     * @param end_time
     * @param pending_jobs
     * @param use_internal_copy
     */
    public GanttChartNode(Job job, float cpu_time, float end_time,
            Job[] pending_jobs, boolean use_internal_copy) {
        if (use_internal_copy) {
            this.job = new Job(job);
            this.pending_jobs = new Job[pending_jobs.length];
            for (int i = 0; i < pending_jobs.length; ++i) {
                this.pending_jobs[i] = new Job(pending_jobs[i]);
            }
        } else {
            // causes to share the same array
            this.job = job;
            this.pending_jobs = pending_jobs;
        }
        this.cpu_time = cpu_time;
        this.end_time = end_time;
    }
}
