/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;



import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author villar
 */
public class ManageStudents extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManageStudents.class.getName());

    /**
     * Creates new form ManageBooks
     */
    String studentName, department, program;
    int studentId;
    DefaultTableModel model;
    public ManageStudents() {
        initComponents();
        setStudentDetailsToTable();
    }
    //to set book details to the table
    public void setStudentDetailsToTable(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
            Statement st =  con.createStatement();
            ResultSet rs = st.executeQuery("select * from student_details");
            
            while(rs.next()){
                int StudentId = rs.getInt("student_id");
                String StudentName = rs.getString("name");
                String department = rs.getString("department");
                String program =  rs.getString("program");
                
                Object [] obj = {StudentId,StudentName,department,program};
                model = (DefaultTableModel)tbl_bookDetails.getModel();
                model.addRow(obj);
                
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
    //add student
    public boolean addStudent(){
        boolean isAdded = false;
        studentId = Integer.parseInt(txt_studentId.getText());
        studentName = txt_studentName.getText();
        department = combo_courseName.getSelectedItem().toString();
        program = combo_branch.getSelectedItem().toString();
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into student_details values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,studentId);
            pst.setString(2, studentName);
            pst.setString(3, department);
            pst.setString(4,program);
            
            int rowCount = pst.executeUpdate();
            if (rowCount>0) {
                isAdded = true;
                
            }else{
                isAdded = false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return isAdded;
    
    }
    
    //UPDATE STUDENT
    public boolean updateStudent(){
        boolean isUpdated = false;
        studentId = Integer.parseInt(txt_studentId.getText());
        studentName = txt_studentName.getText();
        department = combo_courseName.getSelectedItem().toString();
        program = combo_branch.getSelectedItem().toString();
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "update Student_details set name = ?,department = ?, program = ? where student_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, studentName);
            pst.setString(2, department);
            pst.setString(3,program);
            pst.setInt(4, studentId);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                isUpdated = true;
                
            }else{
                isUpdated = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isUpdated;
    }
    
    //Delete
    public boolean deleteStudent(){
        boolean isDeleted = false;
        studentId = Integer.parseInt(txt_studentId.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from student_details where student_id = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, studentId);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0){
                isDeleted = true;
            }else{
                isDeleted = false;
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return isDeleted;
    }
    //clear table
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0);
    }
    //search
    public void search(String stats) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "");
            Statement st = con.createStatement();
            String query;

        if (stats.isEmpty()) {
            // If no text entered, show all records
            query = "SELECT * FROM student_details";
        } else {
            // Search by book name, student name, or status
            query = "SELECT * FROM student_details WHERE "
                + "student_id LIKE '%" + stats + "%' OR "
                + "name LIKE '%" + stats + "%' OR "
                + "department LIKE '%" + stats + "%' OR "
                + "program LIKE '%" + stats + "%'";

        }

        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String student_id = rs.getString("student_id");
            String name = rs.getString("name");
            String department = rs.getString("department");
            String program = rs.getString("program");
            

            Object[] obj = {student_id, name, department, program};
            model = (DefaultTableModel) tbl_bookDetails.getModel();
            model.addRow(obj);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSButtonMetroBeanInfo1 = new rojerusan.RSButtonMetroBeanInfo();
        rSButtonMetroBeanInfo2 = new rojerusan.RSButtonMetroBeanInfo();
        jPanel3 = new javax.swing.JPanel();
        txt_search = new app.bolivia.swing.JCTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
        jPanel1 = new javax.swing.JPanel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_studentName = new app.bolivia.swing.JCTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        combo_branch = new javax.swing.JComboBox<>();
        combo_courseName = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        rSButtonHover7 = new rojeru_san.complementos.RSButtonHover();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 728));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(1280, 728));
        jPanel3.setMinimumSize(new java.awt.Dimension(1280, 728));
        jPanel3.setPreferredSize(new java.awt.Dimension(1280, 728));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_search.setBackground(new java.awt.Color(92, 112, 117));
        txt_search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_search.setForeground(new java.awt.Color(202, 222, 226));
        txt_search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_search.setPlaceholder("Search...");
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_searchMouseClicked(evt);
            }
        });
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
        });
        jPanel3.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 80, -1, -1));

        tbl_bookDetails.setBackground(new java.awt.Color(38, 52, 56));
        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STUDENT ID", "NAME", "DEPARTMENT", "PROGRAM"
            }
        ));
        tbl_bookDetails.setColorBackgoundHead(new java.awt.Color(95, 179, 200));
        tbl_bookDetails.setColorBordeFilas(new java.awt.Color(27, 37, 40));
        tbl_bookDetails.setColorBordeHead(new java.awt.Color(27, 37, 40));
        tbl_bookDetails.setColorFilasBackgound1(new java.awt.Color(220, 240, 244));
        tbl_bookDetails.setColorFilasBackgound2(new java.awt.Color(220, 240, 244));
        tbl_bookDetails.setColorFilasForeground1(new java.awt.Color(95, 179, 200));
        tbl_bookDetails.setColorFilasForeground2(new java.awt.Color(95, 179, 200));
        tbl_bookDetails.setColorForegroundHead(new java.awt.Color(27, 37, 40));
        tbl_bookDetails.setColorSelBackgound(new java.awt.Color(95, 179, 200));
        tbl_bookDetails.setColorSelForeground(new java.awt.Color(220, 240, 244));
        tbl_bookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bookDetails);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 640, 460));

        jPanel1.setBackground(new java.awt.Color(27, 37, 40));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_studentId.setBackground(new java.awt.Color(92, 112, 117));
        txt_studentId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_studentId.setForeground(new java.awt.Color(202, 222, 226));
        txt_studentId.setPlaceholder("Enter Book Id");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        jPanel1.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 290, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(220, 240, 244));
        jLabel5.setText("STUDENT ID");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(220, 240, 244));
        jLabel6.setText("NAME");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        txt_studentName.setBackground(new java.awt.Color(92, 112, 117));
        txt_studentName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_studentName.setForeground(new java.awt.Color(202, 222, 226));
        txt_studentName.setPlaceholder("Enter Book Name");
        jPanel1.add(txt_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 290, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(220, 240, 244));
        jLabel7.setText("DEPARTMENT");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(220, 240, 244));
        jLabel8.setText("PROGRAM");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, -1, -1));

        jPanel4.setBackground(new java.awt.Color(26, 111, 224));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jButton4.setBackground(new java.awt.Color(95, 179, 200));
        jButton4.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(27, 37, 40));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/icons8_Exit_edited.png"))); // NOI18N
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jButton1.setBackground(new java.awt.Color(95, 179, 200));
        jButton1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(27, 37, 40));
        jButton1.setText("Add");
        jButton1.setToolTipText("");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 90, 40));

        jButton2.setBackground(new java.awt.Color(95, 179, 200));
        jButton2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(27, 37, 40));
        jButton2.setText("Update");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 510, 90, 40));

        jButton3.setBackground(new java.awt.Color(95, 179, 200));
        jButton3.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(27, 37, 40));
        jButton3.setText("Delete");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, 90, 40));

        combo_branch.setBackground(new java.awt.Color(92, 112, 117));
        combo_branch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        combo_branch.setForeground(new java.awt.Color(202, 222, 226));
        combo_branch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BSCPE", "BSIE", "BSECE", "BSED", "BSN", "BSA", "BSBA", "BSIT", "BSCS", " ", " " }));
        combo_branch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_branchActionPerformed(evt);
            }
        });
        jPanel1.add(combo_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 290, 40));

        combo_courseName.setBackground(new java.awt.Color(92, 112, 117));
        combo_courseName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        combo_courseName.setForeground(new java.awt.Color(202, 222, 226));
        combo_courseName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COE", "COED", "CBAA", "CAS", "CHAS", "CCS" }));
        combo_courseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_courseNameActionPerformed(evt);
            }
        });
        jPanel1.add(combo_courseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 290, 40));

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 34)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 224, 250));
        jLabel3.setText("MANAGE STUDENTS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 728));

        rSButtonHover7.setBackground(new java.awt.Color(95, 179, 200));
        rSButtonHover7.setText("X");
        rSButtonHover7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonHover7MouseClicked(evt);
            }
        });
        jPanel3.add(rSButtonHover7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 0, 50, 25));

        jLabel4.setBackground(new java.awt.Color(95, 179, 200));
        jLabel4.setForeground(new java.awt.Color(27, 37, 40));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/Bg_Dashboard.png"))); // NOI18N
        jLabel4.setToolTipText("");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 730));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 728));

        setSize(new java.awt.Dimension(1280, 729));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost

    }//GEN-LAST:event_txt_studentIdFocusLost

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
        int rowNo = tbl_bookDetails.getSelectedRow();
        TableModel model = tbl_bookDetails.getModel();
        txt_studentId.setText(model.getValueAt(rowNo, 0).toString());
        txt_studentName.setText(model.getValueAt(rowNo, 1).toString());
        combo_courseName.setSelectedItem(model.getValueAt(rowNo, 2).toString());
        combo_branch.setSelectedItem(model.getValueAt(rowNo, 3).toString());
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (addStudent() == true){
            JOptionPane.showMessageDialog(this, "Student Added");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Student Addition Failed");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (updateStudent() == true){
            JOptionPane.showMessageDialog(this, "Student Updated");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Student Updation Failed");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (deleteStudent() == true){
            JOptionPane.showMessageDialog(this, "Student Deleted");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Student Deletion Failed");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void combo_branchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_branchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_branchActionPerformed

    private void combo_courseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_courseNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_courseNameActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void rSButtonHover7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonHover7MouseClicked
        System.exit(0);
    }//GEN-LAST:event_rSButtonHover7MouseClicked

    private void txt_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchMouseClicked

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        String selectedStatus = txt_search.getText().trim(); // read user input
        clearTable(); // clear current data
        search(selectedStatus);
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchKeyPressed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ManageStudents().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_branch;
    private javax.swing.JComboBox<String> combo_courseName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover7;
    private rojerusan.RSButtonMetroBeanInfo rSButtonMetroBeanInfo1;
    private rojerusan.RSButtonMetroBeanInfo rSButtonMetroBeanInfo2;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private app.bolivia.swing.JCTextField txt_search;
    private app.bolivia.swing.JCTextField txt_studentId;
    private app.bolivia.swing.JCTextField txt_studentName;
    // End of variables declaration//GEN-END:variables
}
