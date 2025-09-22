package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pin extends JFrame implements ActionListener {

    JButton b1, b2;
    JPasswordField p1, p2;
    String pin;

    Pin(String pin){
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/newatm.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1550,830);
        add(image);


        JLabel lable1 = new JLabel("CHANGE YOUR PIN");
        lable1.setFont(new Font("System",Font.BOLD,16));
        lable1.setBounds(570,170,400,35);
        lable1.setForeground(Color.WHITE);
        image.add(lable1);

        JLabel lable2 = new JLabel("New PIN :");
        lable2.setFont(new Font("System",Font.BOLD,16));
        lable2.setBounds(430,250,400,35);
        lable2.setForeground(Color.WHITE);
        image.add(lable2);

        p1 = new JPasswordField();
        p1.setBackground(new Color(79, 130, 133));
        p1.setForeground(Color.WHITE);
        p1.setBounds(590,250,200,30);
        p1.setFont(new Font("Raleway",Font.BOLD,22));
        image.add(p1);

        JLabel lable3 = new JLabel("Re-Enter New PIN :");
        lable3.setFont(new Font("System",Font.BOLD,16));
        lable3.setBounds(430,290,400,35);
        lable3.setForeground(Color.WHITE);
        image.add(lable3);

        p2 = new JPasswordField();
        p2.setBackground(new Color(79, 130, 133));
        p2.setForeground(Color.WHITE);
        p2.setBounds(590,290,200,30);
        p2.setFont(new Font("Raleway",Font.BOLD,22));
        image.add(p2);



        b1 = new JButton("CHANGE");
        b1.setBounds(700,385,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        image.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700,428,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        image.add(b2);



        setUndecorated(true);
        setSize(1550,1080);
        setLayout(null);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            String pin1 = p1.getText();
            String pin2 = p2.getText();

            if (!pin1.equals(pin2)){
                JOptionPane.showMessageDialog(null,"Entered PIN does not match");
                p1.setText("");
                p2.setText("");
                return;
            }
            if(e.getSource() == b1){
                if (p1.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please Enter New PIN");

                    return;
                }
                if (p2.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please Re-Enter New PIN");

                    return;

                }

                Con c = new Con();
                String q1 ="update bank set pin = '"+pin1+"' where pin = '"+pin+"'";
                String q2 ="update login set pin = '"+pin1+"' where pin = '"+pin+"'";
                String q3 ="update signupthree set pin = '"+pin1+"' where pin = '"+pin+"'";

                c.statement.executeUpdate(q1);
                c.statement.executeUpdate(q2);
                c.statement.executeUpdate(q3);

                JOptionPane.showMessageDialog(null,"PIN changed successfully");
                setVisible(false);
                new main_Class(pin);

            } else if(e.getSource() ==b2) {
                new main_Class(pin);
                setVisible(false);
            }


        }catch (Exception E){
            E.printStackTrace();
        }


    }

    public static void main(String[] args){
        new Pin("");
    }
}
