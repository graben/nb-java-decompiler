/*
 * Copyright (C) 2021 Moacir da Roza Flores <moacirrf@gmail.com>
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
package com.mrf.javadecompiler.openapi.ui;

import javax.swing.Action;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.text.CloneableEditorSupport;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
  dtd = "-//com.mrf.javadecompiler.openapi.ui//SourceWindow//EN",
  autostore = false
)
@TopComponent.Description(
  preferredID = "SourceWindowTopComponent",
  iconBase = "com/mrf/javadecompiler/openapi/jd_icon_16.png",
  persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "com.mrf.javadecompiler.openapi.ui.SourceWindowTopComponent")
@ActionReference(path = "Menu/Window/Tools", position = 2000)
@TopComponent.OpenActionRegistration(
  displayName = "#CTL_SourceWindowAction",
  preferredID = "SourceWindowTopComponent"
)
@Messages({
    "CTL_SourceWindowAction=Java Decompiler"
})
public final class SourceWindowTopComponent extends TopComponent {
    
    public SourceWindowTopComponent() {
        initComponents();
        initEditorPane();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        editorPane = new javax.swing.JEditorPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setViewportView(editorPane);
        editorPane.getAccessibleContext().setAccessibleParent(editorPane);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane editorPane;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        editorPane.setCaretPosition(0);
    }

    @Override
    public void componentClosed() {
        editorPane.setText("");
    }

    void writeProperties(java.util.Properties p) {
        //Not used
    }

    void readProperties(java.util.Properties p) {
        //Not used
    }

    @Override
    public Action[] getActions() {
        return super.getActions();
    }

    private void initEditorPane() {
        editorPane.setContentType("text/x-java");
        editorPane.setEditorKit(CloneableEditorSupport.getEditorKit(editorPane.getContentType()));
        editorPane.setEditable(false);
    }

    public void setDecompiledSource(String decompiledSource) {
        this.editorPane.setText(decompiledSource);
    }
}
