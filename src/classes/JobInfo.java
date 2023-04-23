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
 * Stores Additional info about a job.
 *
 * @author mxiii
 */
public class JobInfo {
    
    /**
     * The job this info holds
     */
    public Job job;
    
    /**
     * Total idle time, always zero unless job is null
     */
    public float idle_time;
    
    /**
     * Total waiting time
     */
    public float waiting_time;
    
    /**
     * Total turn around time
     */
    public float turnaround_time;

    /**
     * Time when the job has fully processed
     */
    public float end_time;
    
    /**
     * Constructs an empty job info.
     * 
     * @param job 
     */
    public JobInfo(Job job) {
        this(job, 0, 0, 0, 0);
    }

    /**
     * Constructs new job info. This only takes a reference to the job.
     * Any changes to the job will reflect on the job info.
     *
     * @param job
     * @param idle
     * @param waiting
     * @param turnaround
     * @param end
     */
    public JobInfo(Job job, float idle, float waiting, float turnaround, float end) {
        this.job = job;
        this.idle_time = idle;
        this.waiting_time = waiting;
        this.turnaround_time = turnaround;
        this.end_time = end;
    }
}
