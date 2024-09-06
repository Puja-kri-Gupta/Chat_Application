import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;


public class Server implements ActionListener {
        
   JTextField text;
   JPanel a1;
    static Box vertical = Box.createVerticalBox(); // to set msg vertically one by one
    static JFrame f = new JFrame();
    static DataOutputStream dout;

    Server() {

     f.setLayout(null);

     // to set panel in the top on the frame 
       JPanel p1 = new JPanel();
       p1.setBackground(new Color(7,94,84)); // rgb val of green col
       p1.setBounds(0, 0, 450, 70);
       p1.setLayout(null);
       f.add(p1); 
          
     // to set arrow image on panel 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png")); // address of image
        Image i2 = i1.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT); // to set the image size
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);

     // add action when user click on back arrow - 
         back.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
              System.exit(0); // Also write - setVisible(false);
            }       
           }
         );

     // to set profile pic  on panel 
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png")); // address of image
        Image i5 = i4.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT); // to set the image size
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);

     // to set vide0 icon  on panel 
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png")); // address of image
        Image i8 = i7.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT); // to set the image size
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(290,20,30,30);
        p1.add(video);

     // to set phone icon  on panel 
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png")); // address of image
        Image i11 = i10.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT); // to set the image size
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(350,20,30,30);
       p1.add(phone);

     // to set 3dot icon  on panel 
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png")); // address of image
        Image i14 = i13.getImage().getScaledInstance(10,25, Image.SCALE_DEFAULT); // to set the image size
        ImageIcon i15 = new ImageIcon(i14);
        JLabel more = new JLabel(i15);
        more.setBounds(390,20,30,30);
        p1.add(more);

     // to set the name
        JLabel name = new JLabel("Michel ");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

     // to set the staus under name
        JLabel status = new JLabel("Active Now ");
        status.setBounds(110,40,100,14);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        p1.add(status);

     // to set chatarea 
        a1 = new JPanel();
        a1.setBounds(5,75,440,500);
        f. add(a1);

     // to set the text area
        text = new JTextField();
        text.setBounds(100,590,280,45); 
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
         f.add(text);
          
     // create a send btn (original) - 
        JButton send = new JButton("Send"); 
        send.setBounds(365, 590, 80, 45); 
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE); 
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        f.add(send);

     // To set send icon btn -
         /*  ImageIcon i16 = new ImageIcon(ClassLoader.getSystemResource("icons/send2.png")); // address of image)
          Image i17 = i16.getImage().getScaledInstance(45,45, Image.SCALE_DEFAULT); // to set the image size
          ImageIcon i18 = new ImageIcon(i17);
          JLabel send = new JLabel(i18);
          send.setBounds(390, 590, 45, 45);
          add(send);  */

     // emoji icon btn -
        ImageIcon i19 = new ImageIcon(ClassLoader.getSystemResource("icons/emoji.jpeg")); // address of image)
        Image i20 = i19.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT); // to set the image size
        ImageIcon i21 = new ImageIcon(i20);
        JLabel emoji = new JLabel(i21);
        emoji.setBounds(0, 590, 50, 50);
        f.add(emoji);
         
     //attach icon btn -
        ImageIcon i22 = new ImageIcon(ClassLoader.getSystemResource("icons/attach.jpeg")); // address of image)
        Image i23 = i22.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT); // to set the image size
        ImageIcon i24 = new ImageIcon(i23);
        JLabel attach = new JLabel(i24);
        attach.setBounds(55, 600, 30, 30);
        f.add(attach);

     // main frame 
        f.setSize(450, 650);
        f.setLocation(200, 50);  
        f.setUndecorated(true); // to remove the header area of frame where shows the minimize, or close icons.
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true); 
        f.setLayout(null);
    }

   @Override 
   // if we implement interface which have any abstract method , must override that method in class.
     public void actionPerformed(ActionEvent e) {

       try {
          String out = text.getText(); // to read the msg

          JPanel p2 = formatLabel(out);

          a1.setLayout(new BorderLayout());// to set the element at top,bottom,left,right or centre.
         
          JPanel right = new JPanel(new BorderLayout());
          right.add(p2, BorderLayout.LINE_END); // to set msg at the end of the line
          vertical.add(right); // send msg shown in right side
          vertical.add(Box.createVerticalStrut(10)); // adding space b/w two msges.

          a1.add(vertical, BorderLayout.PAGE_START); 

           dout.writeUTF(out);
 
          text.setText(""); // empty the text field after send msg
          f.repaint(); //reload the page
          f.invalidate();
          f.validate();
         } catch (Exception ae){
             ae.printStackTrace();
            }

      }  

      public static JPanel formatLabel(String out) {
          JPanel Panel = new JPanel();
          Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));

          JLabel output = new JLabel(out); // to show the msg on green panel
          output.setFont(new Font("Tahoma", Font.PLAIN, 17));
          output.setBackground(new Color(37, 211, 102));
          output.setOpaque(true);
          output.setBorder(new EmptyBorder(15, 10, 10, 40)); //padding
          Panel.add(output);

          Calendar cal = Calendar.getInstance();
          SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // to set time

          JLabel time = new JLabel();
          time.setText(sdf.format(cal.getTime())); // to set  the val dynamically
          Panel.add(time);

          return Panel;
      }
    
      
        

    public static void main(String args[]){
      new Server();

     try {
        ServerSocket skt = new ServerSocket(6001);
        while(true) {
            Socket s = skt.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
             dout = new DataOutputStream(s.getOutputStream());

            while(true){
               String msg = din.readUTF();
               JPanel panel = formatLabel(msg);

              JPanel left = new JPanel(new BorderLayout());//received msg
              left.add(panel, BorderLayout.LINE_START);
              vertical.add(left);
              f.validate();
            }
         }
      } catch (Exception e) {
           e.printStackTrace();
          }

      }


   }
