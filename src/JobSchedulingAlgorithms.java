/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gui.JobSchedulingAlgorithm;

/**
 *
 * @author mxiii
 */
public class JobSchedulingAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JobSchedulingAlgorithm().setVisible(true);
            }
        });
        
        /*
        // Input: Job array
        Job[] jobs = {
            new Job("J1", 0, 8, 5),
            new Job("J2", 5, 4, 3),
            new Job("J3", 6, 9, 2),
            new Job("J4", 13, 5, 1)
        };

        // Process: Scheduling Algorithm
        FirstComeFirstServe fcfs = new FirstComeFirstServe();
        AlgorithmResult res1 = fcfs.processJobs(jobs);

        // Output: Gantt Chart, Data
        System.out.format("Algorithm: %s\n", fcfs.getName());
        GanttChart gc1 = fcfs.makeGanttChart();
        gc1.showOutput();
        System.out.format("average tt: %.2f\naverage wt: %.2f\ncpu utilization:%.2f%%\n\n",
                res1.getAverageTurnAroundTime(),
                res1.getAverageWaitingTime(),
                res1.cpu_utilization);
        
        // Process: Scheduling Algorithm
        NonPreemptivePriority npp = new NonPreemptivePriority();
        AlgorithmResult res2 = npp.processJobs(jobs);

        // Output: Gantt Chart, Data
        System.out.format("Algorithm: %s\n", npp.getName());
        GanttChart gc2 = npp.makeGanttChart();
        gc2.showOutput();
        System.out.format("average tt: %.2f\naverage wt: %.2f\ncpu utilization:%.2f%%\n\n",
                res2.getAverageTurnAroundTime(),
                res2.getAverageWaitingTime(),
                res2.cpu_utilization);

        // Process: Scheduling Algorithm
        ShortestJobFirst sjf = new ShortestJobFirst();
        AlgorithmResult res3 = sjf.processJobs(jobs);

        // Output: Gantt Chart, Data
        System.out.format("Algorithm: %s\n", sjf.getName());
        GanttChart gc3 = sjf.makeGanttChart();
        gc3.showOutput();
        System.out.format("average tt: %.2f\naverage wt: %.2f\ncpu utilization:%.2f%%\n\n",
                res3.getAverageTurnAroundTime(),
                res3.getAverageWaitingTime(),
                res3.cpu_utilization);
        */

    }

}
