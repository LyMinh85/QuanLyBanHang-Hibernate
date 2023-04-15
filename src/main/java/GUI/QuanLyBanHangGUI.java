/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class QuanLyBanHangGUI extends javax.swing.JFrame {

    private List<JButton> listNavbarBtn;

    /**
     * Creates new form QuanLyBanHang
     */
    public QuanLyBanHangGUI() {
        initComponents();
        setLocationRelativeTo(null);
        listNavbarBtn = new ArrayList<>();
        listNavbarBtn.add(btnCustomer);
        listNavbarBtn.add(btnVegetable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnCustomer = new javax.swing.JButton();
        btnVegetable = new javax.swing.JButton();
        btnCustomer1 = new javax.swing.JButton();
        btnCustomer2 = new javax.swing.JButton();
        cardLayout = new javax.swing.JPanel();
        vegetableGUI1 = new GUI.VegetableGUI();
        customerGUI11 = new GUI.CustomerGUI();
        orderGUI11 = new GUI.OrderGUI1();
        revenueProductStatisticsGUI1 = new GUI.RevenueProductStatisticsGUI();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 5));

        btnCustomer.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        btnCustomer.setText("Customer");
        btnCustomer.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)));
        btnCustomer.setPreferredSize(new java.awt.Dimension(90, 35));
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(btnCustomer);

        btnVegetable.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        btnVegetable.setText("Vegetable");
        btnVegetable.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)));
        btnVegetable.setPreferredSize(new java.awt.Dimension(90, 35));
        btnVegetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVegetableActionPerformed(evt);
            }
        });
        jPanel1.add(btnVegetable);

        btnCustomer1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        btnCustomer1.setText("Order");
        btnCustomer1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)));
        btnCustomer1.setPreferredSize(new java.awt.Dimension(90, 35));
        btnCustomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomer1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCustomer1);

        btnCustomer2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        btnCustomer2.setText("Statistics");
        btnCustomer2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 255)));
        btnCustomer2.setPreferredSize(new java.awt.Dimension(90, 35));
        btnCustomer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomer2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCustomer2);

        cardLayout.setLayout(new java.awt.CardLayout());
        cardLayout.add(vegetableGUI1, "vegetablecard");
        cardLayout.add(customerGUI11, "customercard");
        cardLayout.add(orderGUI11, "orderGUI");
        cardLayout.add(revenueProductStatisticsGUI1, "revenueProductStatisticGUI");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ BÁN HÀNG");
        jPanel3.add(jLabel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardLayout, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        CardLayout cardLayout1 = (CardLayout) cardLayout.getLayout();
        cardLayout1.show(cardLayout, "customercard");
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void btnVegetableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVegetableActionPerformed
        CardLayout cardLayout2 = (CardLayout) cardLayout.getLayout();
        cardLayout2.show(cardLayout, "vegetablecard");
    }//GEN-LAST:event_btnVegetableActionPerformed

    private void btnCustomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomer1ActionPerformed
        CardLayout cardLayout2 = (CardLayout) cardLayout.getLayout();
        cardLayout2.show(cardLayout, "orderGUI");
    }//GEN-LAST:event_btnCustomer1ActionPerformed

    private void btnCustomer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomer2ActionPerformed
        CardLayout cardLayout2 = (CardLayout) cardLayout.getLayout();
        cardLayout2.show(cardLayout, "revenueProductStatisticGUI");
    }//GEN-LAST:event_btnCustomer2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyBanHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyBanHangGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnCustomer1;
    private javax.swing.JButton btnCustomer2;
    private javax.swing.JButton btnVegetable;
    private javax.swing.JPanel cardLayout;
    private GUI.CustomerGUI customerGUI11;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private GUI.OrderGUI1 orderGUI11;
    private GUI.RevenueProductStatisticsGUI revenueProductStatisticsGUI1;
    private GUI.VegetableGUI vegetableGUI1;
    // End of variables declaration//GEN-END:variables
}
