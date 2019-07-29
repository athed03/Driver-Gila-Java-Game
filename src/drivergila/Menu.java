package drivergila;

import com.mysql.jdbc.Connection;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author aditj
 */
public class Menu extends javax.swing.JFrame implements AksesDB{
    
    static JLabel[] rint=new JLabel[5];//jenis rintangan
    static JLabel[] rintb=new JLabel[5];//jenis rintangan sejajar rint[i]

    AudioClip bg; // suara background
    
    public static String pemainnya, phigh; //yang main dan pemain highscore
    
    public static int hgscore=0;
    
    public static ArrayList<String> pemain=new ArrayList<String>();// list pemain di combobox
    private Connection conn;
    private Statement stm;
    private ResultSet sql;
    public static int jmobil;//jenis mobil
            
    public Menu() {//arraylist dikosongin dulu buat nampung semua nama yang ada di database
        pemain.removeAll(pemain);
        jmobil=1;
        initComponents();
        jTextField2.setText("");
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/"+jmobil+".png")));
         try{
            conn= Database.koneksiDB();
            stm = conn.createStatement();
            sql = stm.executeQuery("SELECT nama,waktu,score FROM pemain order by score ASC");
            while(sql.next()){ //proses buat nyari highscore diurutin query nya
                phigh=sql.getString("nama");
                user.setText(sql.getString("nama")+" {"+sql.getString("waktu")+"}");
                hgscore=Integer.parseInt(sql.getString("score"));
                pemain.add(sql.getString("nama"));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        // set lokasi aplikasi di tengah layar
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double ScreenHeight = screenSize.getHeight();
        int x = ((int)screenWidth-getWidth())/2;
        int y = ((int)ScreenHeight-getHeight())/2;

        setLocation(x,y-30);
        
        score.setText(""+hgscore);// nampilin highscore
        for(int i=0;i<pemain.size();i++){ //nampung pemain di combobox
            jComboBox1.addItem(pemain.get(i).toString());
        }
        bg = Applet.newAudioClip(getClass().getResource("bg1.wav"));
        bg.play();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        score = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        user = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setFont(new java.awt.Font("Simplifica", 1, 24)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(34, 34, 64));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 280, 30));

        jComboBox1.setFont(new java.awt.Font("Simplifica", 1, 18)); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 300, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/border.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 240));

        jPanel1.setBackground(new java.awt.Color(34, 34, 64));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(252, 82, 88));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 680, 60));

        jButton1.setFont(new java.awt.Font("Simplifica", 1, 38)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/start.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, -1, -1));

        jPanel3.setBackground(new java.awt.Color(252, 82, 88));
        jPanel3.setPreferredSize(new java.awt.Dimension(262, 2));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 267, 232, -1));

        jPanel2.setBackground(new java.awt.Color(252, 82, 88));
        jPanel2.setPreferredSize(new java.awt.Dimension(262, 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 268, 232, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/1.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 191, 84));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/kanan.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setOpaque(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, 43, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/kiri.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, 46));

        score.setBackground(new java.awt.Color(252, 82, 88));
        score.setFont(new java.awt.Font("Simplifica", 1, 48)); // NOI18N
        score.setForeground(new java.awt.Color(255, 255, 255));
        score.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        score.setText("SCORE");
        score.setBorder(null);
        score.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        score.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreActionPerformed(evt);
            }
        });
        jPanel1.add(score, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 334, 57));

        jPanel4.setBackground(new java.awt.Color(252, 82, 88));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 604, 680, -1));

        user.setFont(new java.awt.Font("Simplifica", 0, 24)); // NOI18N
        user.setForeground(new java.awt.Color(255, 255, 255));
        user.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        user.setText("jLabel3");
        jPanel1.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 546, 334, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 680, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //pas neken tombol start
    //int delay = 1000;
    //ActionListner taskPerformer = new ActionListner (){
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pemainnya=jComboBox1.getSelectedItem().toString();
        bg.stop();
        bg = Applet.newAudioClip(getClass().getResource("buttoneffect.wav"));
        bg.play();
        Thread t=new Thread();
        try{
            Thread.sleep(2000);
        }catch(InterruptedException dvt){
            
        }
        //pindah ke arena.java
        new ArenaDuel().setVisible(true);
        dispose();      
    }//GEN-LAST:event_jButton1ActionPerformed
    //}
    //        new Timer(delay, taskPerformer).start(); 
   //pas ngisi nama pemain true teken enter
    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        boolean ada =true;
        
        switch (evt.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    if(!jTextField2.getText().equals("")){
                        for(int i=0;i<jComboBox1.getItemCount();i++){
                            if(jTextField2.getText().equals(jComboBox1.getItemAt(i))){
                                ada=false;
                                jComboBox1.setSelectedIndex(i);
                                JOptionPane.showMessageDialog(null,"Nama sudah tersedia");
                                continue;}
                        }
                        //dicek dulu kalo nama udah beleum dipake, dia ga bakal simpen ke db
                        if(ada){
                            pemain.add(jTextField2.getText());
                            insert(jTextField2.getText());
                            jComboBox1.addItem(jTextField2.getText());
                            jComboBox1.setSelectedIndex(jComboBox1.getItemCount()-1);
                        }
                    }
                    jTextField2.setText("");
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    //buat milih emain di combobox
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
//        jTextField2.setText((String) jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        
    }//GEN-LAST:event_jTextField2ActionPerformed

    //milih mobil
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    bg = Applet.newAudioClip(getClass().getResource("slide.wav"));
    bg.play();
        if(jmobil!=4){
            jmobil++;
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/"+jmobil+".png")));
        }
        else if(jmobil==4)
            jmobil=1;
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/"+jmobil+".png")));
    }//GEN-LAST:event_jButton2ActionPerformed

    //milih mobil
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        bg = Applet.newAudioClip(getClass().getResource("slide.wav"));
        bg.play();
        if(jmobil!=1){
            jmobil--;
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/"+jmobil+".png")));
        }
        else if (jmobil==1)
            jmobil=4;
            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drivergila/"+jmobil+".png")));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void scoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scoreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField score;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables

    @Override
    public void insert(String nama) {
        try {
            String s = "INSERT INTO pemain "
                    + "VALUES ('"+jTextField2.getText()+"', NULL, '0')";
            stm.executeUpdate(s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
