
package carservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarClean extends javax.swing.JFrame {

    /**
     * Creates new form CarClean
     */
    public CarClean() {
        initComponents();
    }

     // Make the connection of the database
     Connection con;
     public Connection connect(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/carcleans","root","");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarClean.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return con;
    }

    // create the sales method

    DefaultTableModel df;
    
    public void sales(){
        con=connect();
        int total=Integer.parseInt(txttotal.getText());
        int pay=Integer.parseInt(txtpay.getText());
        String carno=txtno.getText();
        int balance=0;
        if(total<=pay){
            balance=pay-total;
            txtbalance.setText(Integer.toString(balance));
        }
        
        try {
            PreparedStatement pre=con.prepareStatement("insert into cartable(carno,total,pay,balance) values(?,?,?,?)");
            pre.setString(1, carno);
            pre.setInt(2,total);
            pre.setInt(3,pay);
            pre.setInt(4,balance);
            pre.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CarClean.class.getName()).log(Level.SEVERE, null, ex);
        }
        int keyid=0;
        try {
            PreparedStatement pl=con.prepareStatement("select * from cartable");
            ResultSet re=pl.executeQuery();
            
            while(re.next()){
                keyid=re.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CarClean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       String str="insert into car_product(clean_id,clean_type,price) values(?,?,?)";
        try {
            PreparedStatement pt=con.prepareStatement(str);
            
            for(int j=0;j<jTable1.getRowCount();j++){
                pt.setInt(1, keyid);
                pt.setString(2,jTable1.getValueAt(j,0).toString());
                pt.setInt(3,Integer.parseInt(jTable1.getValueAt(j,1).toString()));
                pt.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CarClean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // HashMap a=new HashMap();
        // a.put("invo",keyid);
        // try {
        //     JasperDesign jdesign=JRXmlLoader.load("C:\\Users\\akila\\Documents\\NetBeansProjects\\CarClean\\src\\CarCleanShop\\carReport.jrxml");
        //     JasperReport jreport=JasperCompileManager.compileReport(jdesign);
        //     JasperPrint jprint=JasperFillManager.fillReport(jreport, a, con);
        //     JasperViewer.viewReport(jprint,false);
            
            
            
            
        // } catch (JRException ex) {
        //     Logger.getLogger(CarClean.class.getName()).log(Level.SEVERE, null, ex);
        // }
        
        
    }
    
    



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(CarClean.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarClean.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarClean.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarClean.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CarClean().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
