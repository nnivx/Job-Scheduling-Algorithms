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

import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author mxiii
 */
public interface Simple {

    /**
     * A simple dialog box for displaying text.
     * 
     */
    static class TextDialog extends JDialog {
        TextDialog(String title, String message) {
            JDialog about = new JDialog();
            JTextArea textarea = new JTextArea(message);

            about.setTitle(title);
            textarea.setBackground(new java.awt.Color(238, 238, 238));
            textarea.setTabSize(4);
            textarea.setFont(new Font("Courier New", Font.PLAIN, 12));

            about.setLayout(new java.awt.FlowLayout());
            about.add(textarea);

            about.pack();
            about.setVisible(true);
            about.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        }
    }

    /**
     * A simple frame for quick deployment.
     * 
     */
    static class ComponentFrame extends JFrame {
        ComponentFrame(String title, java.awt.Component c) {
            javax.swing.JFrame about = new javax.swing.JFrame(title);

            about.add(c);
            about.setLayout(new java.awt.FlowLayout());
            about.add(c);

            about.pack();
            about.setVisible(true);
            about.setResizable(false);
            about.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        }
    }
}
