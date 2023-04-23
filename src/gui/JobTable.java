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

import classes.Job;
import classes.Constants.*;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Although a called a table, it is actually a scroll pane with a table.
 * It encapsulates all the basic functions of a table that you would
 * normally access separately from table, table model and selection model.
 *
 * @author mxiii
 */
class JobTable extends JScrollPane {
    /**
     * Custom table model used by JobTable
     */
    private class Model extends javax.swing.table.DefaultTableModel {

        Model() {
            super(null, new String[]{
                "Name", "Arrival", "Burst", "Priority"
            });
        }

        @Override
        public Class getColumnClass(int columnIndex) {
            return String.class; // must be a string
        }

        @Override
        public boolean isCellEditable(int rowindex, int colindex) {
            return false;
        }
    }

    /**
     * Constructor
     */
    public JobTable() {
        super();
        table = new JTable();
        model = new Model();

        table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table.setModel(model);

        table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        table.setRowHeight(32);
        for (int i = 0; i < table.getColumnCount(); ++i) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }

        setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setPreferredSize(new Dimension(300, 200));
        setViewportView(table);
    }

    /**
     * Adds the job to the bottom of the list.
     *
     * @param job the job to append
     */
    public void addJob(Job job) {
        model.addRow(new String[]{
            job.name,
            String.valueOf(job.arrival_time),
            String.valueOf(job.burst_time),
            String.valueOf(job.priority)
        });
    }

    /**
     * Adds literal data (string) to the bottom of the list. This
     * does not perform additional checks for the validity of the
     * strings.
     *
     * @param data string array, which elements are respective to
     * columns
     */
    public void appendLiteralData(String[] data) {
        model.addRow(data);
    }

    /**
     * Sets the selected row, as if selected by the user.
     *
     * @param rowindex the row index
     */
    public void setSelected(int rowindex) {
        table.getSelectionModel().setSelectionInterval(rowindex, rowindex);
    }

    /**
     * Returns the index of the selected job.
     *
     * @return the index of the selected job
     */
    public int getSelectedJobIndex() {
        return table.getSelectedRow();
    }

    /**
     * Returns the job at specified index.
     *
     * @param rowindex the index of the job
     * @return a new Job object, or null if an error occured
     */
    public Job getJob(int rowindex) {
        try { // be safe!
            return new Job(
                    getValueAt(rowindex, JobField.NAME),
                    Float.parseFloat(getValueAt(rowindex, JobField.ARRIVAL_TIME)),
                    Float.parseFloat(getValueAt(rowindex, JobField.BURST_TIME)),
                    Integer.parseInt(getValueAt(rowindex, JobField.PRIORITY))
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Returns an array of all jobs.
     *
     * @return an array of jobs
     */
    public Job[] getJobs() {
        int sz = getJobCount();
        ArrayList<Job> jobs = new ArrayList<>(sz);
        for (int i = 0; i < sz; ++i) {
            jobs.add(getJob(i));
        }
        return jobs.toArray(new Job[sz]);
    }

    /**
     * Returns value at a specific cell.
     *
     * @param rowindex the row/job index
     * @param field the field or cell index
     *
     * @return string representation of the value
     */
    public String getValueAt(int rowindex, JobField field) {
        return (String)table.getValueAt(rowindex, field.ordinal());
    }

    /**
     * Returns the count of the jobs.
     *
     * @return the count of the jobs
     */
    public int getJobCount() {
        return model.getRowCount();
    }

    /**
     * Removes the job at the specified index. Will do nothing
     * if the index is invalid.
     *
     * @param rowindex the index of the job to be removed
     */
    public void removeJob(int rowindex) {
        try {
            model.removeRow(rowindex);
        } catch (ArrayIndexOutOfBoundsException e) {
            // do nothing
        }
    }

    /**
     * Removes multiple jobs sequentially in one call.
     *
     * @param indices an array of job indices
     */
    public void removeJobs(int[] indices) {
        for (int i : indices) {
            removeJob(i);
        }
    }

    /**
     * Clears the table. Will not ask for user prompt!
     *
     */
    public void clear() {
        model.setRowCount(0);
    }

    // Components declaration
    private final JTable table;
    private final Model model;
    // End of components declaration
}
