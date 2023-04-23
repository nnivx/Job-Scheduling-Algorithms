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

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A displayable Gantt Chart.
 *
 * This class only serves as a way to display a Gantt Chart. In no way will it
 * check if the entries actually makes sense at all. You can subclass this to
 * display it in GUI.
 *
 * This class is actually unused and just serve as a model class.
 *
 * @author mxiii
 */
public class DefaultGanttChart implements GanttChart {

    protected ArrayList<GanttChartNode> nodes;
    private final boolean uses_internal_copy;

    public DefaultGanttChart() {
        this(false);
    }

    public DefaultGanttChart(boolean uses_internal_copy) {
        this.uses_internal_copy = uses_internal_copy;
        this.nodes = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public DefaultGanttChart(DefaultGanttChart copy) {
        this.nodes = new ArrayList(copy.nodes);
        this.uses_internal_copy = copy.uses_internal_copy;
    }

    public Iterator<GanttChartNode> begin() {
        return nodes.iterator();
    }
    
    public GanttChartNode getNodeAt(int index) {
        try {
            return nodes.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public GanttChartNode getLastNode() {
        return getNodeAt(getNodeCount() - 1);
    }

    public int getNodeCount() {
        return nodes.size();
    }

    public boolean isUsingInternalCopy() {
        return uses_internal_copy;
    }

    @Override
    public void append(GanttChartNode node) {
        if (node != null)
            nodes.add(node);
    }

    @Override
    public void append(Job job, float cpu_time, float end_time,
            Job[] pending_jobs) {
        append(new GanttChartNode(job, cpu_time, end_time,
                pending_jobs, uses_internal_copy));
    }

    @Override
    public void appendIdle(float duration) {
        float begin = nodes.get(nodes.size() - 1).end_time;
        append( Job.idle(duration, begin),
                duration,
                begin + duration,
                null);
    }

    @Override
    public void clear() {
        nodes.clear();
    }

    @Override
    public DefaultGanttChart copy() {
        return new DefaultGanttChart(this);
    }

    @Override
    public void showOutput() {
        System.out.println("-------------------------------------\nformat:\n[<name> <cpu_time>] <pending_jobs...>\n\tend time: <end_time>\n-------------------------------------");
        for (GanttChartNode e : nodes) {
            System.out.format("[%s ", e.job.name);

            if (e.cpu_time - (int) e.cpu_time > 0) {
                System.out.format("%.2f", e.cpu_time);
            } else {
                System.out.format("%.0f", e.cpu_time);
            }

            System.out.format("] ");
            if (e.pending_jobs != null)
                for (Job j : e.pending_jobs)
                        System.out.format(" %s", j.name);

            System.out.print("\n\tend time:");

            if (e.end_time - (int) e.end_time > 0) {
                System.out.format("%.2f", e.end_time);
            } else {
                System.out.format("%.0f", e.end_time);
            }
            System.out.print('\n');
        }
        System.out.print("-------------------------------------\n");
    }

}
