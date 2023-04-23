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

package gui;

import classes.*;
import java.awt.Color;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.*;

/**
 * Gantt Chart with GUI. Actually a scroll pane with a panel.
 *
 * @author mxiii
 */
public class GanttChartGUI extends JScrollPane implements classes.GanttChart {

    /**
     * Helper class to build the Gantt Chart.
     *
     */
    private class NodeGUI extends JPanel {

        /**
         * Top of the constructor chain.
         *
         * @param copy
         */
        public NodeGUI(NodeGUI copy) {
            this(copy.node);
        }

        /**
         * The nodes are always duplicated internally. Only that
         * whether the nodes share the jobs will be affected by
         * use_internal_copy policy.
         *
         * @param node the node, will be duplicated
         */
        public NodeGUI(GanttChartNode node) {
            this(node.job, node.cpu_time, node.end_time, node.pending_jobs);
        }

        /**
         * Constructs a new node from data.
         *
         * @param job
         * @param cpu_time
         * @param end_time
         * @param pending_jobs
         */
        public NodeGUI(Job job, float cpu_time, float end_time,
                Job[] pending_jobs) {
            node = new GanttChartNode(job, cpu_time, end_time, pending_jobs,
                    use_internal_copy);
            lbl_name = new JLabel();
            lbl_cputime = new JLabel();
            hovered = false;

            setBorder(new SoftBevelBorder(BevelBorder.RAISED));
            setPreferredSize(new java.awt.Dimension(40, 40));
            
            lbl_name.setFont(new Font("Courier New", Font.BOLD, 18));
            lbl_name.setText(node.job.name);

            lbl_cputime.setFont(new Font("Courier New", Font.PLAIN, 12));
            if (cpu_time - (int)cpu_time > 0)
                lbl_cputime.setText(String.format("%.2f", node.cpu_time));
            else
                lbl_cputime.setText(String.format("%d", (int)node.cpu_time));

            GroupLayout layout = new GroupLayout(this);
            setLayout(layout);

            layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_name)
                    .addComponent(lbl_cputime)
             );

            layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_name)
                        .addComponent(lbl_cputime))
            );
            
            setToolTipText(formatInfo());
            // ta3 kase. alam mo yung undecorated dapat na, popup,
            // tas tungunu decorated parin, hindi pa nag ha-hide.
            // leche.
        }

        /**
         * Formats information for the tooltip info.
         *
         * @return A formatted string info
         */
        public final String formatInfo() {
            Job j = node.job;
            java.lang.StringBuilder str = new java.lang.StringBuilder();
            str.append("name:").append(j.name).append(' ');
            str.append("at:");
            if (j.arrival_time - (int)j.arrival_time > 0)
                str.append(String.format("%.2f", j.arrival_time));
            else
                str.append((int)j.arrival_time);
            str.append(' ');

            str.append("bt:");
            if (j.burst_time - (int)j.burst_time > 0)
                str.append(String.format("%.2f", j.burst_time));
            else
                str.append((int)j.burst_time);
            
            str.append(' ');

            if (node.pending_jobs != null && node.pending_jobs.length > 0) {
                str.append('[');
                for (Job e: node.pending_jobs)
                    str.append(" ").append(e.name).append(' ');
                str.append(" ]");
            }

            return str.toString();
        }

        private boolean hovered;

        private final GanttChartNode node;
        private final JLabel lbl_name;
        private final JLabel lbl_cputime;
    }

    /**
     * Constructs a default Gantt Chart.
     */
    public GanttChartGUI() {
        this(false);
    }

    /**
     * Constructs a Gantt Chart with custom node policy. If
     * {@code use_internal_copy} is true, all jobs are
     * duplicated. If false, the jobs are stored references,
     * and the chart will respond to external changes.
     *
     * @param use_internal_copy whether the nodes will duplicate the jobs
     * internally, or store only references.
     */
    public GanttChartGUI(boolean use_internal_copy) {
        this.nodes = new ArrayList<>();
        this.use_internal_copy = use_internal_copy;
        panel = new JPanel();
        initComponents();
    }

    /**
     * Constructs a GanttChart GUI from a copy.
     * 
     * @param copy
     */
    public GanttChartGUI(GanttChartGUI copy) {
        panel = new JPanel();
        this.use_internal_copy = copy.use_internal_copy;
        if (use_internal_copy) {
            nodes = new ArrayList<>(copy.nodes.size());
            for (NodeGUI e: copy.nodes)
                nodes.add(new NodeGUI(e));
        } else {
            nodes = new ArrayList<>(copy.nodes);
        }
        for (NodeGUI e: copy.nodes)
            panel.add(e);
        initComponents();
    }

    /**
     * Initializes the panel.
     */
    private void initComponents() {

        panel.setPreferredSize(new java.awt.Dimension(400, 800));
        setPreferredSize(new java.awt.Dimension(420, 240));
        
        java.awt.FlowLayout layout = new java.awt.FlowLayout();
        panel.setLayout(layout);

        layout.setHgap(20); // so it looks like a legit chart
        layout.setHgap(0); // so it looks like a legit chart

        setViewportView(panel); // add the panel to this scroll pane
    }

    /**
     * @inheritdoc
     */
    @Override
    public void append(GanttChartNode node) {
        if (node != null) {
            NodeGUI x = new NodeGUI(node);
            nodes.add(x);
            panel.setSize(new java.awt.Dimension(400, 64*(nodes.size()/10)));
            panel.add(x);
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public void append(Job job, float cpu_time, float end_time, Job[] pending_jobs) {
        append(new GanttChartNode(job, cpu_time, end_time, pending_jobs,
                use_internal_copy));
    }

    /**
     * @inheritdoc
     */
    @Override
    public void appendIdle(float duration) {
        float begin = nodes.get(nodes.size() - 1).node.end_time;
        append( Job.idle(duration, begin),
                duration,
                begin + duration,
                null );
    }

    /**
     * @inheritdoc
     */
    @Override
    public void clear() {
        nodes.clear();
    }

    /**
     * @inheritdoc
     */
    @Override
    public GanttChart copy() {
        return new GanttChartGUI(this);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void showOutput() {
        System.out.println("-------------------------------------\nformat:\n[<name> <cpu_time>] <pending_jobs...>\n\tend time: <end_time>\n-------------------------------------");
        for (NodeGUI node : nodes) {
            GanttChartNode e = node.node;

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

    private JPanel      panel;
    private ArrayList<NodeGUI> nodes;
    private boolean use_internal_copy;

}
