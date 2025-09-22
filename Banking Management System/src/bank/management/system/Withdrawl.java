package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    String pin;
    TextField textField;
    JButton b1, b2;

    Withdrawl(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/newatm.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1550,830);
        add(image);


        JLabel lable1 = new JLabel("MAXIMUM WITHDRAWL IS RS.10,000");
        lable1.setFont(new Font("System",Font.BOLD,16));
        lable1.setBounds(488,180,400,35);
        lable1.setForeground(Color.WHITE);
        image.add(lable1);

        JLabel lable2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        lable2.setFont(new Font("System",Font.BOLD,16));
        lable2.setBounds(500,215,400,35);
        lable2.setForeground(Color.WHITE);
        image.add(lable2);

        textField = new TextField();
        textField.setBackground(new Color(79, 130, 133));
        textField.setForeground(Color.WHITE);
        textField.setBounds(478,260,300,25);
        textField.setFont(new Font("Raleway",Font.BOLD,22));
        image.add(textField);

        b1 = new JButton("WITHDRAWL");
        b1.setBounds(700,340,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        image.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700,390,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        image.add(b2);


        setUndecorated(true);
        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1) {
            try {
                String amount = textField.getText();
                Date date = new Date();
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "PLEASE ENTER THE AMOUNT YOU WANT TO WITHDRAWL");
                } else {
                    Con c = new Con();
                    ResultSet resultSet = c.statement.executeQuery("select * from bank where pin ='"+pin+"'");
                    int balance = 0;
                    while (resultSet.next()) {
                        if (resultSet.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("ammount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("ammount"));
                        }

                    }

                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "INSUFFIENT BALANCE");
                        return;
                    }

                    c.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                    setVisible(false);
                    new main_Class(pin);
                }

            } catch (Exception E) {
                E.printStackTrace();
            }

        } else if (e.getSource() == b2) {
            setVisible(false);
            new main_Class(pin);
        }

    }

    public static void main(String[] args) {
        new Withdrawl("");
    }
}
