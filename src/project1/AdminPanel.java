package project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
public class AdminPanel  implements ActionListener {
    JFrame frame;
    JPanel panel1;
    JPanel panel2;
    JPanel panel2a;
    JLabel heading1;
    JLabel heading2;
    JLabel heading3;
    JLabel heading4;
    JLabel heading5;
    JLabel heading6;
    JLabel heading7;
    JLabel heading8;

    JPanel panel2b;

    JButton happy;
    JButton sad;
    JButton romantic;
    JButton nostalgic;

    JPanel panel4;
    JLabel label1;

    AdminPanel(){
        //FRAME
        frame = new JFrame("Mood-Movie");
        frame.setSize(1400,760);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LABELS
        label1 = new JLabel("WELCOME TO ADMIN SECTION");
        label1.setFont(new Font("Bahnschrift",Font.BOLD,72) );
        label1.setForeground(new Color(0x1F0802));

        //PANEL 1
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,25,80));
        panel1.setBounds(0,0,1400,210);
        panel1.setBackground(new Color(0xfee2b3));
        panel1.setOpaque(true);
        panel1.add(label1);

        heading1 = new JLabel("Add");
        heading1.setFont(new Font("Bahnschrift",Font.BOLD,40));
        heading1.setBounds(90,40,300,60);
        heading1.setForeground(new Color(0x1F0802));

        heading2 = new JLabel("Movie by");
        heading2.setFont(new Font("Bahnschrift",Font.BOLD,40));
        heading2.setBounds(90,90,300,60);
        heading2.setForeground(new Color(0x1F0802));

        heading3 = new JLabel("Mood & Feeling");
        heading3.setFont(new Font("Bahnschrift",Font.BOLD,40));
        heading3.setBounds(90,145,300,60);
        heading3.setForeground(new Color(0x1F0802));

        heading4 = new JLabel("Dwell in any emotion. Tearfull sadness or absolute anger");
        heading4.setFont(new Font("Bahnschrift",Font.BOLD,20));
        heading4.setBounds(90,225,600,60);
        heading4.setForeground(new Color(0x1F0802));

        heading5 = new JLabel("driven horror? We got you covered with the very best");
        heading5.setFont(new Font("Bahnschrift",Font.BOLD,20));
        heading5.setBounds(90,260,600,60);
        heading5.setForeground(new Color(0x1F0802));

        heading6 = new JLabel("movies recommendations for any mood or feeling.");
        heading6.setFont(new Font("Bahnschrift",Font.BOLD,20));
        heading6.setBounds(90,290,600,60);
        heading6.setForeground(new Color(0x1F0802));

        heading7 = new JLabel("What's your Mood?");
        heading7.setFont(new Font("Bahnschrift",Font.BOLD,40));
        heading7.setBounds(50,50,400,60);
        heading7.setForeground(new Color(0x1F0802));

        heading8 = new JLabel("(Select one of them according to your mood)");
        heading8.setFont(new Font("Bahnschrift",Font.BOLD,18));
        heading8.setBounds(50,70,500,60);
        heading8.setForeground(new Color(0x1F0802));

        happy = new JButton("Happy");
        happy.setBounds(60,200,200,50);
        happy.setFont(new Font("Bahnschrift",Font.BOLD,20));
        happy.setForeground(new Color(0xfee2b3));
        happy.setBackground(new Color(0x1F0802));
        happy.addActionListener(this);
        happy.setFocusable(false);

        sad = new JButton("Sad");
        sad.setFont(new Font("Bahnschrift",Font.BOLD,20));
        sad.setBounds(360,200,200,50);
        sad.setForeground(new Color(0xfee2b3));
        sad.setBackground(new Color(0x1F0802));
        sad.addActionListener(this);
        sad.setFocusable(false);

        romantic = new JButton("Romantic");
        romantic.setFont(new Font("Bahnschrift",Font.BOLD,20));
        romantic.setBounds(60,320,200,50);
        romantic.setForeground(new Color(0xfee2b3));
        romantic.setBackground(new Color(0x1F0802));
        romantic.addActionListener(this);
        romantic.setFocusable(false);

        nostalgic = new JButton("Nostalgic");
        nostalgic.setFont(new Font("Bahnschrift",Font.BOLD,20));
        nostalgic.setBounds(360,320,200,50);
        nostalgic.setForeground(new Color(0xfee2b3));
        nostalgic.setBackground(new Color(0x1F0802));
        nostalgic.addActionListener(this);
        nostalgic.setFocusable(false);

        panel2a = new JPanel();
        panel2a.setBackground(new Color(0xfee2b3));
        panel2a.setOpaque(true);
        panel2a.setLayout(null);
        panel2a.add(heading1);
        panel2a.add(heading2);
        panel2a.add(heading3);
        panel2a.add(heading4);
        panel2a.add(heading5);
        panel2a.add(heading6);

        panel2b = new JPanel();
        panel2b.setBackground(new Color(0xfee2b3));
        panel2b.setOpaque(true);
        panel2b.setLayout(null);
        panel2b.add(heading7);
        panel2b.add(heading8);
        panel2b.add(happy);
        panel2b.add(sad);
        panel2b.add(romantic);
        panel2b.add(nostalgic);

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,2));
        panel2.setBounds(0,200,1400,680);
        panel2.setBackground(new Color(0xfee2b3));
        panel2.setOpaque(true);
        panel2.add(panel2a);
        panel2.add(panel2b);

        frame.add(panel1);
        frame.add(panel2);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        AdminPanel pro = new AdminPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==happy){
            frame.dispose();
            try {
                HappyInputForm obj= new HappyInputForm();
                obj.setVisible(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if(e.getSource()==sad){
            frame.dispose();
            try {
                SadInputForm obj = new SadInputForm();
                obj.setVisible(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if(e.getSource()==romantic){
            frame.dispose();
            try {
                RomanticInputForm obj = new RomanticInputForm();
                obj.setVisible(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if(e.getSource()==nostalgic){
            frame.dispose();
            try {
                NostalgicInputForm obj = new NostalgicInputForm();
                obj.setVisible(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else{
            System.out.println("Invalid Action-Performed");
        }
    }
}
