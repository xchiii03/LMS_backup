/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;



import java.awt.Component;
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
public class ManageBooks extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManageBooks.class.getName());

    /**
     * Creates new form ManageBooks
     */
    String bookName, author;
    int bookId, quantity;
    DefaultTableModel model;
    public ManageBooks() {
        initComponents();
        setBookDetailsToTable();
        


        
    }
    
    
    //Table
    
    
    //to set book details to the table
    public void setBookDetailsToTable(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
            Statement st =  con.createStatement();
            ResultSet rs = st.executeQuery("select * from book_details");
            
            while(rs.next()){
                String bookId = rs.getString("book_id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                int quantity =  rs.getInt("quantity");
                
                Object [] obj = {bookId,bookName,author,quantity};
                model = (DefaultTableModel)tbl_bookDetails.getModel();
                model.addRow(obj);
                
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
    //add book
    public boolean addBook(){
        boolean isAdded = false;
        bookId = Integer.parseInt(txt_bookId.getText());
        bookName = txt_bookName.getText();
        author = txt_authorName.getText();
        quantity = Integer.parseInt(txt_quantity.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO book_details (book_id, book_name, author, quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,bookId);
            pst.setString(2, bookName);
            pst.setString(3, author);
            pst.setInt(4,quantity);
            
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
    
    //UPDATE BOOK
    public boolean updateBook(){
        boolean isUpdated = false;
        bookId = Integer.parseInt(txt_bookId.getText());
        bookName = txt_bookName.getText();
        author = txt_authorName.getText();
        quantity = Integer.parseInt(txt_quantity.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "update book_details set book_name = ?,author = ?, quantity = ? where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, bookName);
            pst.setString(2, author);
            pst.setInt(3,quantity);
            pst.setInt(4, bookId);
            
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
    public boolean deleteBook(){
        boolean isDeleted = false;
        bookId = Integer.parseInt(txt_bookId.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from book_details where book_id = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            
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
            query = "SELECT * FROM book_details";
        } else {
            // Search by book name, student name, or status
            query = "SELECT * FROM book_details WHERE "
                + "book_id LIKE '%" + stats + "%' OR "
                + "book_name LIKE '%" + stats + "%' OR "
                + "author LIKE '%" + stats + "%' OR "
                + "quantity LIKE '%" + stats + "%'";

        }

        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String book_id = rs.getString("book_id");
            String bookName = rs.getString("book_name");
            String author = rs.getString("author");
            Integer quantity = rs.getInt("quantity");
            

            Object[] obj = {book_id, bookName, author, quantity};
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
        jPanel1 = new javax.swing.JPanel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_bookName = new app.bolivia.swing.JCTextField();
        txt_authorName = new app.bolivia.swing.JCTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_quantity = new app.bolivia.swing.JCTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel2 = new javax.swing.JLabel();
        rSButtonHover7 = new rojeru_san.complementos.RSButtonHover();
        txt_search = new app.bolivia.swing.JCTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 728));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(1280, 728));
        jPanel3.setMinimumSize(new java.awt.Dimension(1280, 728));
        jPanel3.setPreferredSize(new java.awt.Dimension(1280, 728));
        jPanel3.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(27, 37, 40));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_bookId.setBackground(new java.awt.Color(92, 112, 117));
        txt_bookId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_bookId.setForeground(new java.awt.Color(202, 222, 226));
        txt_bookId.setPlaceholder("Enter Book Id");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        jPanel1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 290, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(220, 240, 244));
        jLabel5.setText("ENTER BOOK ID");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(220, 240, 244));
        jLabel6.setText("TITLE");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        txt_bookName.setBackground(new java.awt.Color(92, 112, 117));
        txt_bookName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_bookName.setForeground(new java.awt.Color(202, 222, 226));
        txt_bookName.setPlaceholder("Enter Book Name");
        jPanel1.add(txt_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 290, 40));

        txt_authorName.setBackground(new java.awt.Color(92, 112, 117));
        txt_authorName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_authorName.setForeground(new java.awt.Color(202, 222, 226));
        txt_authorName.setPlaceholder("Enter Author");
        txt_authorName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_authorNameFocusLost(evt);
            }
        });
        txt_authorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_authorNameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_authorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 290, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(220, 240, 244));
        jLabel7.setText("AUTHOR");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(220, 240, 244));
        jLabel8.setText("QUANTITY");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, -1, -1));

        txt_quantity.setBackground(new java.awt.Color(92, 112, 117));
        txt_quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_quantity.setForeground(new java.awt.Color(202, 222, 226));
        txt_quantity.setPlaceholder("Quantity...");
        jPanel1.add(txt_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 290, 40));

        jButton1.setBackground(new java.awt.Color(95, 179, 200));
        jButton1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(27, 37, 40));
        jButton1.setText("Add");
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
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, 90, 40));

        jPanel5.setBackground(new java.awt.Color(26, 111, 224));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 34)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(119, 224, 250));
        jLabel3.setText("MANAGE BOOKS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 260, 40));

        jPanel3.add(jPanel1);
        jPanel1.setBounds(0, 0, 560, 728);

        tbl_bookDetails.setBackground(new java.awt.Color(38, 52, 56));
        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BOOK ID", "TITLE", "AUTHOR", "QUANTITY"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        if (tbl_bookDetails.getColumnModel().getColumnCount() > 0) {
            tbl_bookDetails.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbl_bookDetails.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbl_bookDetails.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(600, 120, 640, 360);

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Book", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BACK");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel2);
        jLabel2.setBounds(0, 0, 40, 19);

        rSButtonHover7.setBackground(new java.awt.Color(95, 179, 200));
        rSButtonHover7.setText("X");
        rSButtonHover7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonHover7MouseClicked(evt);
            }
        });
        jPanel3.add(rSButtonHover7);
        rSButtonHover7.setBounds(1230, 0, 50, 25);

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
        jPanel3.add(txt_search);
        txt_search.setBounds(1040, 80, 200, 32);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background/Bg_Dashboard.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1280, 728));
        jLabel1.setMinimumSize(new java.awt.Dimension(1280, 728));
        jLabel1.setPreferredSize(new java.awt.Dimension(1280, 728));
        jPanel3.add(jLabel1);
        jLabel1.setBounds(0, 0, 1280, 728);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 728));

        setSize(new java.awt.Dimension(1281, 729));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost

    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_authorNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_authorNameFocusLost

    }//GEN-LAST:event_txt_authorNameFocusLost

    private void txt_authorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_authorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_authorNameActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
        int rowNo = tbl_bookDetails.getSelectedRow();
        TableModel model = tbl_bookDetails.getModel();
        txt_bookId.setText(model.getValueAt(rowNo, 0).toString());
        txt_bookName.setText(model.getValueAt(rowNo, 1).toString());
        txt_authorName.setText(model.getValueAt(rowNo, 2).toString());
        txt_quantity.setText(model.getValueAt(rowNo, 3).toString());
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (addBook() == true){
            JOptionPane.showMessageDialog(this, "Book Added");
            clearTable();
            setBookDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Book Addition Failed");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (updateBook() == true){
            JOptionPane.showMessageDialog(this, "Book Updated");
            clearTable();
            setBookDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Book Updation Failed");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (deleteBook() == true){
            JOptionPane.showMessageDialog(this, "Book Deleted");
            clearTable();
            setBookDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Book Deletion Failed");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new ManageBooks().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover7;
    private rojerusan.RSButtonMetroBeanInfo rSButtonMetroBeanInfo1;
    private rojerusan.RSButtonMetroBeanInfo rSButtonMetroBeanInfo2;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private app.bolivia.swing.JCTextField txt_authorName;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_bookName;
    private app.bolivia.swing.JCTextField txt_quantity;
    private app.bolivia.swing.JCTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
