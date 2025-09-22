package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Mini extends JFrame implements ActionListener {

    String pin;
    JButton btn;

    Mini(String pin){
        this.pin = pin;

        setUndecorated(true);
        getContentPane().setBackground(new Color(255,204,204));
        setSize(400,600);
        setLocation(20,20);
        setLayout(null);


        JLabel label1 = new JLabel();
        label1.setBounds(20,140,400,200);
        add(label1);

        JLabel label2 = new JLabel("MINI STATEMENT");
        label2.setFont(new Font("System",Font.BOLD,15));
        label2.setBounds(150,20,200,20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20,80,300,20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20,400,300,20);
        add(label4);

        try {
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("select * from login where pin ='"+pin+"'");
            while (resultSet.next()){
                label3.setText("Card Number: "+ resultSet.getString("card_number").substring(0,4) + "XXXXXXX" + resultSet.getString("card_number").substring(12));

            }

        }catch (Exception E){
            E.printStackTrace();
        }

        try {
            int balance = 0;
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin ='"+pin+"'");
            while (resultSet.next()){

                label1.setText(label1.getText() + "<html>" + resultSet.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("ammount")+"<br><br><html>");


                if (resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("ammount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("ammount"));
                }
            }
            label4.setText("Your Total Balance is Rs "+balance);


        }catch (Exception E){
            E.printStackTrace();
        }

        btn = new JButton("Exit");
        btn.setBounds(20,500,100,25);
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        add(btn);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new Mini("");
    }
}
