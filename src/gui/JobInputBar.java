/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import classes.Job;
import classes.Constants.*;

import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

/**
 * The input bar/field. 
 *
 * @author mxiii
 */
class JobInputBar extends JToolBar {

    /**
     * Constructor
     */
    public JobInputBar() {
        super("Job Input Bar", SwingConstants.HORIZONTAL);
        name = new JTextField();
        arrivaltime = new JTextField();
        bursttime = new JTextField();
        priority = new JTextField();

        setFloatable(false);
        setRollover(true);
        setMaximumSize(new Dimension(400, 40));
        setMinimumSize(new Dimension(400, 40));
        setPreferredSize(new Dimension(400, 40));

        name.setToolTipText("Input job name here");
        name.setPreferredSize(new Dimension(64, 32));
        name.setHorizontalAlignment(JTextField.RIGHT);
        add(name);

        arrivaltime.setToolTipText("Input arrival time here");
        arrivaltime.setPreferredSize(new Dimension(64, 32));
        arrivaltime.setHorizontalAlignment(JTextField.RIGHT);
        add(arrivaltime);

        bursttime.setToolTipText("Input burst time here");
        bursttime.setPreferredSize(new Dimension(64, 32));
        bursttime.setHorizontalAlignment(JTextField.RIGHT);
        add(bursttime);

        priority.setToolTipText("Input priority here");
        priority.setPreferredSize(new Dimension(64, 32));
        priority.setHorizontalAlignment(JTextField.RIGHT);
        add(priority);
    }

    /**
     * Returns the job object from input fields.
     *
     * @return a new job object
     * @throws InvalidJobEntryException
     *
     * @see #getJobName
     * @see #getJobArrivalTime
     * @see #getJobBurstTime
     * @see #getJobPriority
     */
    public Job getJob() throws InvalidJobEntryException {
        return new Job(getJobName(),
                getJobArrivalTime(),
                getJobBurstTime(),
                getJobPriority());
    }

    /**
     * Returns the text input as-is. Please be warned that the
     * elements of the string are not arranged in accordance
     * to classes.Constants.JobFields, instead, according to
     * the index of the respective text field.
     *
     * @return the inputs as strings
     */
    public String[] getLiteralData() {
        return new String[]{
            name.getText(), arrivaltime.getText(), bursttime.getText(), priority.getText()
        };
    }

    /**
     * Returns the text at field.
     *
     * @param field the field to get the text from
     * @return the input as-is
     */
    public String getLiteralJobData(JobField field) {
        switch (field) {
            case NAME:
                return name.getText();
            case ARRIVAL_TIME:
                return name.getText();
            case BURST_TIME:
                return name.getText();
            case PRIORITY:
                return name.getText();
            default:
                throw new UnsupportedOperationException(
                        String.format("Unhandled field <%s>", field.name()));
        }
    }

    /**
     * Returns the job name.
     *
     * @return the job name
     */
    public String getJobName() {
        return name.getText();
    }

    /**
     * Returns the job arrival time.
     *
     * @return the job arrival time
     * @throws InvalidJobEntryException if the input is not a proper number
     */
    public float getJobArrivalTime() throws InvalidJobEntryException {
        try {
            return Float.parseFloat(arrivaltime.getText());
        } catch (NumberFormatException e) {
            throw new InvalidJobEntryException("Invalid job arrival time");
        }
    }

    /**
     * Returns the job burst time.
     *
     * @return the job burst time
     * @throws InvalidJobEntryException if the input is not a proper number
     */
    public float getJobBurstTime() throws InvalidJobEntryException {
        try {
            return Float.parseFloat(bursttime.getText());
        } catch (NumberFormatException e) {
            throw new InvalidJobEntryException("Invalid job burst time");
        }
    }

    /**
     * Returns the job priority.
     *
     * @return the job priority
     * @throws InvalidJobEntryException if the input is not an integer
     */
    public int getJobPriority() throws InvalidJobEntryException {
        try {
            return Integer.parseInt(priority.getText());
        } catch (NumberFormatException e) {
            throw new InvalidJobEntryException("Invalid job priority");
        }
    }

    /**
     * Sets the literal value of job arrival time, as if inputted by the user.
     *
     * @param s the string input
     */
    public void setJobName(String s) {
        name.setText(s);
    }

    /**
     * Sets the literal value of job arrival time, as if inputted by the user.
     *
     * @param s the string input
     */
    public void setJobArrivalTime(String s) {
        arrivaltime.setText(s);
    }

    /**
     * Sets the literal value of job burst time, as if inputted by the user.
     *
     * @param s the string input
     */
    public void setJobBurstTime(String s) {
        bursttime.setText(s);
    }

    /**
     * Sets the literal value of job priority, as if inputted by the user.
     *
     * @param s the string input
     */
    public void setJobPriority(String s) {
        priority.setText(s);
    }

    /**
     * Sets the job arrival time.
     *
     * @param s the string input
     */
    public void setJobArrivalTime(float value) {
        arrivaltime.setText(String.valueOf(value));
    }

    /**
     * Sets the job burst time.
     *
     * @param s the string input
     */
    public void setJobBurstTime(float value) {
        bursttime.setText(String.valueOf(value));
    }

    /**
     * Sets the job priority.
     *
     * @param s the string input
     */

    public void setJobPriority(int value) {
        priority.setText(String.valueOf(value));
    }

    // Components decleration
    private final JTextField name;
    private final JTextField arrivaltime;
    private final JTextField bursttime;
    private final JTextField priority;
    // End of components decleration
}
