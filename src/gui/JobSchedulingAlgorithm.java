/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The main frame for job Scheduling Algorithm GUI.
 * @author mxiii
 */
public class JobSchedulingAlgorithm extends javax.swing.JFrame {

    /**
     * The menu bar. Inner class for easy access on outer class.
     */
    private class MenuBar extends JMenuBar {
        public MenuBar() {
            super();
            
            mnu_file = new JMenu();
            mni_clear = new JMenuItem();
            mni_exit = new JMenuItem();
            mnu_algorithms = new JMenu();
            mni_execute = new JMenuItem();
            mnu_help = new JMenu();
            mni_credits = new JMenuItem();
            mni_about = new JMenuItem();

            Font font = new Font("Deja Vu Sans", Font.PLAIN, 12);
            
            mni_clear.setText("Clear");
            mni_clear.setFont(font);
            mni_clear.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    table.clear();
                }
            });
            mni_clear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                    java.awt.event.KeyEvent.VK_D,
                    java.awt.event.InputEvent.ALT_MASK
                ));
            
            mni_exit.setText("Exit");
            mni_exit.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) { cb_exit(); }
            });
            mni_exit.setFont(font);
            
            mnu_file.setText("File");
            mnu_file.add(mni_clear);
            mnu_file.add(new javax.swing.JPopupMenu.Separator());
            mnu_file.add(mni_exit);
            
            mni_execute.setText("Execute...");
            mni_execute.setFont(font);
            mni_execute.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    // fcfs test
                    algorithms.nonpreemptive.FirstComeFirstServe fcfs =
                            new algorithms.nonpreemptive.FirstComeFirstServe();
                    classes.Job[] jobs = table.getJobs();
                    if (jobs.length == 0)
                        return;
                    else
                        fcfs.processJobs(jobs);

                    classes.GanttChart gc = fcfs.makeGanttChart();

                    System.out.format("Executing %s\n", fcfs.getName());
                    gc.showOutput();
                    if (gc.getClass() == GanttChartGUI.class)
                        new Simple.ComponentFrame("FCFS", (GanttChartGUI)gc);

                    controlbar.displayMessage("Test mode (FCFS only)");
                }

            });
            mni_execute.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                    java.awt.event.KeyEvent.VK_X,
                    java.awt.event.InputEvent.ALT_MASK
                ));
            
            mnu_algorithms.setText("Algorithms");
            mnu_algorithms.add(mni_execute);

            mni_credits.setText("Credits");
            mni_credits.setFont(font);
            mni_credits.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Simple.TextDialog("Credits", classes.Cubes.about);
                }

            });
            mni_about.setText("About");
            mni_about.setFont(font);
            mni_about.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Simple.TextDialog("About", classes.Cubes.about);
                }

            });
            mnu_help.setText("Help");
            mnu_help.add(mni_credits);
            mnu_help.add(new javax.swing.JPopupMenu.Separator());
            mnu_help.add(mni_about);

            add(mnu_file);
            add(mnu_algorithms);
            add(mnu_help);
        }

        // Components declaration
        private final JMenu mnu_file;
        private final JMenuItem mni_clear;
        private final JMenuItem mni_exit;

        private final JMenu mnu_algorithms;
        private final JMenuItem mni_execute;

        private final JMenu mnu_help;
        private final JMenuItem mni_credits;
        private final JMenuItem mni_about;
        // End of components declaration
    }

    private void cb_exit() {
        this.dispose();
    }

    /**
     * Constructor.
     */
    public JobSchedulingAlgorithm() {
        super("Job Scheduling Algorithms");
        
        menubar = new MenuBar();
        table = new JobTable();
        inputbar = new JobInputBar();
        controlbar = new ControlBar(table, inputbar);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(controlbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(inputbar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(controlbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(inputbar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
    }

    // Variables declaration - do not modify
    private final MenuBar       menubar;
    private final JobTable      table;
    private final JobInputBar   inputbar;
    private final ControlBar    controlbar;
    // End of variables declaration
}
