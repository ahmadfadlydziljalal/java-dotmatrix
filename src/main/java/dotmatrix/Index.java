/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotmatrix;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dotmatrix.controllers.Report;
import java.awt.BorderLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import simple.escp.Template;
import simple.escp.data.MapDataSource;

import simple.escp.json.JsonTemplate;
import simple.escp.swing.PrintPreviewPane;

/**
 *
 * @author tmsdzil
 */
public class Index extends javax.swing.JFrame {

    public Index() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPilihFile = new javax.swing.JButton();
        jTextFieldPathFile = new javax.swing.JTextField();
        jButtonCompileGson = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jInternalFrameResult = new javax.swing.JInternalFrame();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonPilihFile.setText("Pilih File");
        jButtonPilihFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPilihFileActionPerformed(evt);
            }
        });

        jButtonCompileGson.setText("Compile");
        jButtonCompileGson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompileGsonActionPerformed(evt);
            }
        });

        jLabel1.setText("Compile Result:");

        jInternalFrameResult.setVisible(true);

        javax.swing.GroupLayout jInternalFrameResultLayout = new javax.swing.GroupLayout(jInternalFrameResult.getContentPane());
        jInternalFrameResult.getContentPane().setLayout(jInternalFrameResultLayout);
        jInternalFrameResultLayout.setHorizontalGroup(
            jInternalFrameResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrameResultLayout.setVerticalGroup(
            jInternalFrameResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jInternalFrameResult, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonPilihFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPathFile, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCompileGson)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPilihFile)
                    .addComponent(jTextFieldPathFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCompileGson))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jInternalFrameResult)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(661, 527));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPilihFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPilihFileActionPerformed

        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new File(
                System.getProperty("user.home") + System.getProperty("file.separator") + "Downloads"
        ));

        Action details = jfc.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
        jfc.setFileFilter(filter);
        
        int result = jfc.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            jTextFieldPathFile.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonPilihFileActionPerformed

    private void jButtonCompileGsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompileGsonActionPerformed

        // Create a report, param => file`s path
        if (!jTextFieldPathFile.getText().isEmpty() || !jTextFieldPathFile.getText().isBlank()) {
            Report report = new Report(jTextFieldPathFile.getText());

            // Read the file, as the return is a hashMap
            HashMap<String, String> result = report.convertJsonToObject();
            
            if (!result.isEmpty()) {

                Gson gsonTemplate = new Gson();
                String jsonStringTemplate = gsonTemplate.toJson(result.get("documentTemplate"));
                Template template = new JsonTemplate(jsonStringTemplate);

                // Use DeepClone
                Gson gson = new Gson();
                String jsonString = new Gson().toJson(result.get("documentValue"));
                java.lang.reflect.Type type = new TypeToken<HashMap<String, Object>>() {
                }.getType();
                Map<String, Object> map = gson.fromJson(jsonString, type);

                // Set DataSource
                MapDataSource dataSource = new MapDataSource(map);
                PrintPreviewPane printPreviewPane = new PrintPreviewPane(); // Error: Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
                printPreviewPane.display(template, dataSource);

                jInternalFrameResult.getContentPane().removeAll();
                jInternalFrameResult.repaint();

                jInternalFrameResult.setLayout(new BorderLayout());
                jInternalFrameResult.add(printPreviewPane, BorderLayout.CENTER);
                jInternalFrameResult.setAutoscrolls(true);

            } else {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan / tidak valid.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih File dulu");
        }


    }//GEN-LAST:event_jButtonCompileGsonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }

                //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  // This line gives Windows Theme
                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCompileGson;
    private javax.swing.JButton jButtonPilihFile;
    private javax.swing.JInternalFrame jInternalFrameResult;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField jTextFieldPathFile;
    // End of variables declaration//GEN-END:variables
}
