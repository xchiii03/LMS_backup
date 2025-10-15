/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author villar
 */
public class IssueBook extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(IssueBook.class.getName());

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }
    
    //catch book details from database
    public void getBookDetails(){
        int bookId = Integer.parseInt(txt_bookId.getText());

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ?");
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));

            // ✅ Clear error message if valid
                lbl_bookError.setText("");
            } else {
            // ❌ Show error message
                lbl_bookError.setText("Invalid book id");

            // ✅ Clear previous details
                lbl_bookId.setText("");
                lbl_bookName.setText("");
                lbl_author.setText("");
                lbl_quantity.setText("");

            // ⏳ Hide error message automatically after 3 seconds
                Timer timer = new Timer(3000, e -> lbl_bookError.setText(""));
                timer.setRepeats(false);
                timer.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void getStudentDetails(){
        int studentId = Integer.parseInt(txt_studentId.getText());

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from student_details where student_id = ?");
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lbl_studentId.setText(rs.getString("student_id"));
                lbl_studentName.setText(rs.getString("name"));
                lbl_department.setText(rs.getString("department"));
                lbl_program.setText(rs.getString("program"));

            // ✅ Clear error message if valid
                lbl_studentError.setText("");
            } else {
            // ❌ Show error message
                lbl_studentError.setText("Invalid student id");

            // ✅ Clear previous details
                lbl_studentId.setText("");
                lbl_studentName.setText("");
                lbl_department.setText("");
                lbl_program.setText("");

            // ⏳ Hide error message automatically after 3 seconds
                Timer timer = new Timer(3000, e -> lbl_studentError.setText(""));
                timer.setRepeats(false); // Only run once
                timer.start();
            }

        } catch (Exception e) {
        e.printStackTrace();
        }
    }
 
     
     //insert issued book details
     public boolean issueBook(){
         boolean isIssued = false;
         int bookId = Integer.parseInt(txt_bookId.getText());
         int studentId = Integer.parseInt(txt_studentId.getText());
         String bookName = lbl_bookName.getText();
         String studentName =lbl_studentName.getText();
         
         Date uIssueDate = date_issueDate.getDate();
         Date uDueDate = date_dueDate.getDate();
         
         Long l1 = uIssueDate.getTime();
         long l2 = uDueDate.getTime();
         
         java.sql.Date sIssueDate = new java.sql.Date(l1);
         java.sql.Date sDueDate = new java.sql.Date(l2);
         
         try{
             Connection con = DBConnection.getConnection();
             String sql = "insert into issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status)values(?,?,?,?,?,?,?)";
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1, bookId);
             pst.setString(2,bookName);
             pst.setInt(3, studentId);
             pst.setString(4, studentName);
             pst.setDate(5, sIssueDate);
             pst.setDate(6, sDueDate);
             pst.setString(7, "pending");
             
             int rowCount = pst.executeUpdate();
             if (rowCount > 0){
                 isIssued = true;
             }else{
                 isIssued = false;
                 
             }
             
         }catch(Exception e){
             e.printStackTrace();
         }
         return isIssued;
     }
     
     //updatimg book count
     public void updateBookCount(){
         int bookId = Integer.parseInt(txt_bookId.getText());
         try{
             Connection con = DBConnection.getConnection();
             String sql = "update book_details set quantity = quantity - 1 where book_id = ?";
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1, bookId);
             
             int rowCount = pst.executeUpdate();
             if (rowCount > 0){
                 JOptionPane.showMessageDialog(this, "book count updated");
                 int initialCount = Integer.parseInt(lbl_quantity.getText());
                 lbl_quantity.setText(Integer.toString(initialCount -1 ));
             }else{
                 JOptionPane.showMessageDialog(this, "can't update book count ");
                 
             }
             
         }catch (Exception e){
             e.printStackTrace();
         }
     }
     //checck book duplicate
     public boolean isAlreadyIssued(){
         boolean isAlreadyIssued = false;
         int bookId = Integer.parseInt(txt_bookId.getText());
         int studentId = Integer.parseInt(txt_studentId.getText());
         
         try{
             Connection con = DBConnection.getConnection();
             String sql = "select * from Issue_book_details where book_id = ? and student_id = ? and status =?";
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1, bookId);
             pst.setInt(2, studentId);
             pst.setString(3, "pending");
             
             ResultSet rs = pst.executeQuery();
             if (rs.next()){
                 isAlreadyIssued = true;
             }else{
                 isAlreadyIssued = false;
             }
             
         }catch (Exception e){
             e.printStackTrace();
         }
         return isAlreadyIssued;
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        date_dueDate = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        date_issueDate = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_studentId = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        lbl_department = new javax.swing.JLabel();
        lbl_program = new javax.swing.JLabel();
        lbl_studentError = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_bookError = new javax.swing.JLabel();
        rSButtonHover7 = new rojeru_san.complementos.RSButtonHover();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 728));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel3.setBackground(new java.awt.Color(27, 37, 40));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jButton2.setBackground(new java.awt.Color(95, 179, 200));
        jButton2.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(27, 37, 40));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/icons8_Exit_edited.png"))); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 34)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(119, 224, 250));
        jLabel16.setText("ISSUE BOOKS");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(220, 240, 244));
        jLabel8.setText("STUDENT ID");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txt_studentId.setBackground(new java.awt.Color(92, 112, 117));
        txt_studentId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_studentId.setForeground(new java.awt.Color(202, 222, 226));
        txt_studentId.setPlaceholder("Enter Student Id");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        jPanel3.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 290, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(220, 240, 244));
        jLabel9.setText("DUE DATE:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        txt_bookId.setBackground(new java.awt.Color(92, 112, 117));
        txt_bookId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_bookId.setForeground(new java.awt.Color(202, 222, 226));
        txt_bookId.setPlaceholder("Enter Book Id");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        jPanel3.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 290, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(220, 240, 244));
        jLabel10.setText("BOOK ID");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        date_dueDate.setBackground(new java.awt.Color(92, 112, 117));
        date_dueDate.setForeground(new java.awt.Color(202, 222, 226));
        jPanel3.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, 290, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(220, 240, 244));
        jLabel17.setText("ISSUE DATE:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, -1, -1));

        date_issueDate.setBackground(new java.awt.Color(92, 112, 117));
        date_issueDate.setForeground(new java.awt.Color(202, 222, 226));
        jPanel3.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 290, 40));

        jButton1.setBackground(new java.awt.Color(95, 179, 200));
        jButton1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(27, 37, 40));
        jButton1.setText("ISSUE BOOK");
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
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 530, 120, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 728));

        jPanel5.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(47, 64, 68));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 28)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(202, 222, 226));
        jLabel11.setText("STUDENT DETAILS");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, 20));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(202, 222, 226));
        jLabel12.setText("STUDENT ID:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 90, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(202, 222, 226));
        jLabel13.setText("STUDENT NAME:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 110, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(202, 222, 226));
        jLabel14.setText("DEPARTMENT:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 100, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(202, 222, 226));
        jLabel15.setText("PROGRAM:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 80, -1));

        lbl_studentId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_studentId.setForeground(new java.awt.Color(202, 222, 226));
        jPanel2.add(lbl_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 120, 20));

        lbl_studentName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_studentName.setForeground(new java.awt.Color(202, 222, 226));
        jPanel2.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 120, 20));

        lbl_department.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_department.setForeground(new java.awt.Color(202, 222, 226));
        jPanel2.add(lbl_department, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 120, 20));

        lbl_program.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_program.setForeground(new java.awt.Color(202, 222, 226));
        jPanel2.add(lbl_program, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 120, 20));

        lbl_studentError.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_studentError.setForeground(new java.awt.Color(202, 222, 226));
        jPanel2.add(lbl_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 270, 50));

        jPanel5.add(jPanel2);
        jPanel2.setBounds(470, 90, 390, 528);

        jPanel1.setBackground(new java.awt.Color(38, 52, 56));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(202, 222, 226));
        jLabel1.setText("BOOK DETAILS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, 20));

        lbl_bookId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bookId.setForeground(new java.awt.Color(202, 222, 226));
        jPanel1.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 120, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(202, 222, 226));
        jLabel5.setText("BOOK NAME:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 90, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(202, 222, 226));
        jLabel6.setText("AUTHOR:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 70, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(202, 222, 226));
        jLabel7.setText("BOOK ID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 70, -1));

        lbl_quantity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(202, 222, 226));
        jPanel1.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 120, 20));

        lbl_bookName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(202, 222, 226));
        jPanel1.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 120, 20));

        lbl_author.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(202, 222, 226));
        jPanel1.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 120, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(202, 222, 226));
        jLabel4.setText("QUANTITY:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 70, -1));

        lbl_bookError.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(202, 222, 226));
        jPanel1.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 260, 40));

        jPanel5.add(jPanel1);
        jPanel1.setBounds(860, 90, 390, 528);

        rSButtonHover7.setBackground(new java.awt.Color(95, 179, 200));
        rSButtonHover7.setText("X");
        rSButtonHover7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonHover7MouseClicked(evt);
            }
        });
        jPanel5.add(rSButtonHover7);
        rSButtonHover7.setBounds(1230, 0, 50, 25);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/Bg_Dashboard.png"))); // NOI18N
        jPanel5.add(jLabel3);
        jLabel3.setBounds(0, 0, 1280, 728);

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 728));

        setSize(new java.awt.Dimension(1280, 727));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
        if (!txt_studentId.getText().equals("")){
            getStudentDetails();
        }
       
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        if (!txt_bookId.getText().equals("")){
            getBookDetails();
        }
       
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            if (lbl_quantity.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Book is not available");
        } 
        else {
            if (isAlreadyIssued() == false) {
                if (issueBook() == true) {
                JOptionPane.showMessageDialog(this, "Book issued successfully");
                updateBookCount();
                } 
                else {
                    JOptionPane.showMessageDialog(this, "Can't issue the book");
                }
            } 
            else {
                JOptionPane.showMessageDialog(this, "This student already has this book");
            }
        }
 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void rSButtonHover7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonHover7MouseClicked
        System.exit(0);
    }//GEN-LAST:event_rSButtonHover7MouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new IssueBook().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date_dueDate;
    private com.toedter.calendar.JDateChooser date_issueDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_department;
    private javax.swing.JLabel lbl_program;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentError;
    private javax.swing.JLabel lbl_studentId;
    private javax.swing.JLabel lbl_studentName;
    private javax.swing.JPanel panel_main;
    private rojeru_san.complementos.RSButtonHover rSButtonHover7;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
