package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquriy extends JFrame implements ActionListener {

    JLabel lable2;
    JButton b1;
    String pin;

    BalanceEnquriy(String pin){
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/newatm.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1550,830);
        add(image);


        JLabel lable1 = new JLabel("Your Current Balance is Rs ");
        lable1.setFont(new Font("System",Font.BOLD,16));
        lable1.setBounds(430,180,400,35);
        lable1.setForeground(Color.WHITE);
        image.add(lable1);

        lable2 = new JLabel();
        lable2.setFont(new Font("System",Font.BOLD,16));
        lable2.setBounds(430,220,400,35);
        lable2.setForeground(Color.WHITE);
        image.add(lable2);

        b1 = new JButton("BACK");
        b1.setBounds(700,406,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        image.add(b1);

        int balance =0;
        try {
            Con c = new Con();
            ResultSet resultSet =c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
            while (resultSet.next()){
                if(resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("ammount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("ammount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        lable2.setText(""+balance);



        setUndecorated(true);
        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_Class(pin);
    }

    public static void main(String[] args){
        new BalanceEnquriy("");
    }
}
