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

import java.io.OutputStream;

/**
 * A displayable Gantt Chart interface. To maintain simplicity,
 * it only require clear and append functions. Plus necessary
 * utility functions.
 * 
 * Implementing classes may use internal copy of the jobs if
 * there is a possibility of the job information changing while
 * the algorithm is processing. If the algorithm already uses
 * an internal copy, then this is not a concern for the least
 * (unless you messed up, that is).
 *
 * @author mxiii
 */
public interface GanttChart {

    /**
     * Appends data to Gannt chart.
     *
     * @param node
     */
    public void append(GanttChartNode node);
    
    /**
     * Appends data to Gannt chart.
     * 
     * @param job
     * @param cpu_time
     * @param end_time
     * @param pending_jobs
     * @throws NullPointerException if {@code pending_jobs} or
     * {@code job} is null
     */
    public void append(Job job, float cpu_time, float end_time,
            Job[] pending_jobs) throws NullPointerException;

    /**
     * Appends data to Gannt chart.
     *
     * @param job
     * @param cpu_time
     * @param end_time
     * @throws NullPointerException if {@code job} is null
     */
    public void append(Job job, float cpu_time, float end_time)
            throws NullPointerException;

    /**
     * Appends an idle time. It depends on the subclasses how
     * to handle idle cpu time.
     *
     * @param duration
     */
    public void appendIdle(float duration);

    /**
     * Clears the Gantt Chart.
     */
    public void clear();

    /**
     * Displays the Gantt Chart output.
     *
     * @param out output stream to print to
     */
    public void printOutput(OutputStream out);

}
