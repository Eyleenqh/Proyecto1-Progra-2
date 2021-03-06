/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotechquirosvargassemestreianno2018;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 * @author Steven
 * @author Eyleen
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    InsertDigital insertDigital;
    InsertPhysical insertPhysical;
    InsertStudent insertStudent;
    InsertLaptop insertLaptop;
    InsertSpeaker insertSpeaker;
    InsertProjector insertProjector;
    InsertCd insertCd;
    InsertDvd insertDvd;
    SearchBook searchBook;
    AudiovisualLoan loanLaptop;
    InputStream background;
    
    public Menu() throws IOException {
        insertDigital = new InsertDigital();
        insertPhysical = new InsertPhysical();
        insertStudent = new InsertStudent();
        insertLaptop = new InsertLaptop();
        insertSpeaker = new InsertSpeaker();
        insertProjector = new InsertProjector();
        insertCd = new InsertCd();
        insertDvd = new InsertDvd();
        searchBook = new SearchBook();
        loanLaptop = new AudiovisualLoan();
        
        background = this.getClass().getResourceAsStream("/image/Icon.jpg");
        
        
        initComponents();
        setMenuIcon(this.desktopPane, background);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        editMenu = new javax.swing.JMenu();
        bookDigital = new javax.swing.JMenuItem();
        bookPhysical = new javax.swing.JMenuItem();
        student = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        laptops = new javax.swing.JMenuItem();
        speaker = new javax.swing.JMenuItem();
        projector = new javax.swing.JMenuItem();
        cds = new javax.swing.JMenuItem();
        dvds = new javax.swing.JMenuItem();
        fileMenu = new javax.swing.JMenu();
        loan = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 51));
        setResizable(false);

        desktopPane.setOpaque(false);

        editMenu.setMnemonic('e');
        editMenu.setText("Register");

        bookDigital.setMnemonic('y');
        bookDigital.setText("Digital book");
        bookDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookDigitalActionPerformed(evt);
            }
        });
        editMenu.add(bookDigital);

        bookPhysical.setText("Physical book");
        bookPhysical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookPhysicalActionPerformed(evt);
            }
        });
        editMenu.add(bookPhysical);

        student.setText("Student");
        student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentActionPerformed(evt);
            }
        });
        editMenu.add(student);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(exitMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Audiovisual register");

        laptops.setMnemonic('c');
        laptops.setText("Laptop");
        laptops.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laptopsActionPerformed(evt);
            }
        });
        helpMenu.add(laptops);

        speaker.setText("Speakers");
        speaker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speakerActionPerformed(evt);
            }
        });
        helpMenu.add(speaker);

        projector.setText("Projector");
        projector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectorActionPerformed(evt);
            }
        });
        helpMenu.add(projector);

        cds.setText("Cd");
        cds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdsActionPerformed(evt);
            }
        });
        helpMenu.add(cds);

        dvds.setMnemonic('a');
        dvds.setText("Dvd");
        dvds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dvdsActionPerformed(evt);
            }
        });
        helpMenu.add(dvds);

        menuBar.add(helpMenu);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Loan");

        loan.setMnemonic('o');
        loan.setText("Book");
        loan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loanActionPerformed(evt);
            }
        });
        fileMenu.add(loan);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Audiovisual");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setMenuIcon(JDesktopPane dp, InputStream icon){
        try{
            BufferedImage menuIcon = ImageIO.read(icon);
            dp.setBorder(new MenuIcon(menuIcon));
        }catch(Exception e){
            JOptionPane.showMessageDialog(desktopPane, "There is a little error whith the program.");
        }
    }
    
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentActionPerformed
        if (!insertStudent.isShowing()) {
            insertStudent.setVisible(true);
            desktopPane.add(insertStudent);

        }
    }//GEN-LAST:event_studentActionPerformed

    private void bookDigitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookDigitalActionPerformed
        if (!insertDigital.isShowing()) {
            insertDigital.setVisible(true);
            desktopPane.add(insertDigital);
        }
    }//GEN-LAST:event_bookDigitalActionPerformed

    private void bookPhysicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookPhysicalActionPerformed
        if (!insertPhysical.isShowing()) {
            insertPhysical.setVisible(true);
            desktopPane.add(insertPhysical);
        }
    }//GEN-LAST:event_bookPhysicalActionPerformed

    private void laptopsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laptopsActionPerformed
        if (!insertLaptop.isShowing()) {
            insertLaptop.setVisible(true);
            desktopPane.add(insertLaptop);
        }
    }//GEN-LAST:event_laptopsActionPerformed

    private void speakerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speakerActionPerformed
        if (!insertSpeaker.isShowing()) {
            insertSpeaker.setVisible(true);
            desktopPane.add(insertSpeaker);
        }
    }//GEN-LAST:event_speakerActionPerformed

    private void projectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectorActionPerformed
        if (!insertProjector.isShowing()) {
            insertProjector.setVisible(true);
            desktopPane.add(insertProjector);
        }
    }//GEN-LAST:event_projectorActionPerformed

    private void cdsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdsActionPerformed
        if (!insertCd.isShowing()) {
            insertCd.setVisible(true);
            desktopPane.add(insertCd);
        }
    }//GEN-LAST:event_cdsActionPerformed

    private void dvdsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dvdsActionPerformed
        if (!insertDvd.isShowing()) {
            insertDvd.setVisible(true);
            desktopPane.add(insertDvd);
        }
    }//GEN-LAST:event_dvdsActionPerformed

    private void loanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loanActionPerformed
        if (!searchBook.isShowing()) {
            searchBook.setVisible(true);
            desktopPane.add(searchBook);
        }
    }//GEN-LAST:event_loanActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        if (!loanLaptop.isShowing()) {
            loanLaptop.setVisible(true);
            desktopPane.add(loanLaptop);
        }
    }//GEN-LAST:event_saveMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Menu().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem bookDigital;
    private javax.swing.JMenuItem bookPhysical;
    private javax.swing.JMenuItem cds;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem dvds;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem laptops;
    private javax.swing.JMenuItem loan;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem projector;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem speaker;
    private javax.swing.JMenuItem student;
    // End of variables declaration//GEN-END:variables

}
