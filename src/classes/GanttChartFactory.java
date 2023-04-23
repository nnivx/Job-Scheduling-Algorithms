/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 * Factory class used by Scheduling algorithms.
 *
 * This class exist to create GanttCharts without
 * worrying about refactoring all the algorithms.
 *
 * Modify this if you want a different Gantt chart
 *
 * @author mxiii
 */
public final class GanttChartFactory {

    /**
     * Create a new Gantt chart.
     *
     * @return a new Gantt chart
     */
    public static GanttChart create() {
        // return Regular Gantt Chart, or a subclass, or an overriden display()
        return new gui.GanttChartGUI();
    }

    /**
     * Create a new Gantt chart copy.
     *
     * @param a the Gantt chart to copy
     *
     * @return a new Gantt chart
     */
    public static GanttChart create(GanttChart a) {
        return a.copy();
    }
}
