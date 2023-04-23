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

import classes.Constants.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/**
 * Controller for the table and input.
 * 
 * @author mxiii
 */
class ControlBar extends JToolBar {

    /**
     * Constructs a control bar. Will not check if the
     * arguments are non-null, and may trigger a
     * NullPointerException.
     *
     * @param jt the job table instance
     * @param jb the
     */
    public ControlBar(JobTable jt, JobInputBar jb) {
        super("Control Bar", SwingConstants.HORIZONTAL);

        setFloatable(false);
        setRollover(true);
        setMaximumSize(new java.awt.Dimension(400, 40));
        setMinimumSize(new java.awt.Dimension(400, 40));
        setPreferredSize(new java.awt.Dimension(400, 40));
        
        btn_addjob = new JButton("Add");
        btn_addjob.setToolTipText("Add job from input below");
        btn_addjob.setFocusable(false);
        //btn_addjob.setInheritsPopupMenu(true);
        btn_addjob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { cb_addJob(evt); }
        });
        add(btn_addjob);

        btn_removejob = new JButton("Remove");
        btn_removejob.setToolTipText("Remove selected job");
        btn_removejob.setFocusable(false);
        //btn_addjob.setInheritsPopupMenu(true);
        btn_removejob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { cb_removeJob(evt); }
        });
        add(btn_removejob);

        lbl_status = new JLabel();
        lbl_status.setFont(new java.awt.Font("Courier New", 0, 12));
        lbl_status.setText("To start, input job data below");
        lbl_status.setMaximumSize(new java.awt.Dimension(300, 32));
        lbl_status.setMinimumSize(new java.awt.Dimension(300, 32));
        lbl_status.setPreferredSize(new java.awt.Dimension(0, 32));
        add(lbl_status);

        btn_clearjobs = new JButton("Clear");
        btn_clearjobs.setToolTipText("Clear jobs");
        btn_clearjobs.setFocusable(false);
        btn_clearjobs.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { cb_clearJobs(evt); }
        });
        add(btn_clearjobs);

        jobtable = jt;
        jobinput = jb;
    }

    /**
     * Displays a brief message on the status bar.
     *
     * @param s the message
     */
    public void displayMessage(String s) {
        lbl_status.setText(s);
    }

    /**
     * Resets the status message.
     */
    protected void refresh() {
        lbl_status.setText(null);
    }

    // Button callback methods
    private void cb_addJob(ActionEvent e) {
        refresh();
        try {
            jobinput.getJob();
        } catch (InvalidJobEntryException ex) {
            displayMessage(ex.getMessage());
            return;
        }

        for (int i = 0; i < jobtable.getJobCount(); ++i)
            if (jobinput.getJobName().equals(jobtable.getValueAt(i, JobField.NAME))) {
                displayMessage("Job name already present.");
                jobtable.setSelected(i);
                return;
            }

        jobtable.appendLiteralData(jobinput.getLiteralData());
        displayMessage("Added.");
    }

    private void cb_removeJob(ActionEvent e) {
        refresh();
        if (jobtable.getSelectedJobIndex() == -1) {
            displayMessage("Select job to remove.");
            return;
        }

        int selectedindex = jobtable.getSelectedJobIndex();
        String jobname = jobtable.getValueAt(selectedindex, JobField.NAME);
        displayMessage(String.format("Removed \"%s\"", jobname));
        jobtable.removeJob(selectedindex);
    }

    private void cb_clearJobs(ActionEvent e) {
        refresh();
        jobtable.clear();
        displayMessage("Cleared.");
    }

    // End of button callback methods

    // Components declaration
    private final JLabel lbl_status;
    
    private final JButton btn_addjob;
    private final JButton btn_removejob;
    private final JButton btn_clearjobs;

    private final JobTable jobtable;
    private final JobInputBar jobinput;
    // End of components declaration

}
