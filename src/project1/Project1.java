package project1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
public class Project1  implements ActionListener {
    //Declaring variables
    JFrame frame;

    JPanel welcomePanel;
    JPanel bodyPanel;
    JPanel infoPanel;
    JPanel buttonPanel;

    JLabel introLabel1;
    JLabel introLabel2;
    JLabel introLabel3;
    JLabel descLabel1;
    JLabel descLabel2;
    JLabel descLabel3;
    JLabel moodLabel1;
    JLabel moodLabel2;

    JButton happyButton;
    JButton sadButton;
    JButton romanticButton;
    JButton nostalgicButton;
    JButton backButton;

    Project1(){
        //FRAME
        frame = new JFrame("Mood-Movie");
        frame.setSize(1400,760);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LABELS
        introLabel1 = new JLabel("WELCOME TO MOOD-MOVIE");
        introLabel1.setFont(new Font("Bahnschrift",Font.BOLD,72) );
        introLabel1.setForeground(new Color(0x1F0802));

        //PANEL 1
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER,25,80));
        welcomePanel.setBounds(0,0,1400,210);
        welcomePanel.setBackground(new Color(0xfee2b3));
        welcomePanel.setOpaque(true);
        welcomePanel.add(introLabel1);

        //Main headings
        introLabel1 = createIntroLabel("Watch", 90, 40);
        introLabel2 = createIntroLabel("Movie by", 90, 90);
        introLabel3 = createIntroLabel("Mood & Feeling", 90, 145);

        //Descriptions for the movie panel
        descLabel1 = createDescriptionLabel("Dwell in any emotion. Tearfull sadness or absolute anger", 90, 225);
        descLabel2 = createDescriptionLabel("driven horror? We got you covered with the very best", 90, 260);
        descLabel3 = createDescriptionLabel("movies recommendations for any mood or feeling.", 90, 290);

        //Mood descriptions
        moodLabel1 = createMoodLabel("What's your Mood?",50, 50, 30);
        moodLabel2 = createMoodLabel("(Select one of them according to your mood)",50, 70, 20);

        happyButton = createButton("Happy", new Font("Bahnschrift", Font.BOLD, 20), new Color(0xfee2b3), new Color(0x1F0802));
        happyButton.setBounds(60, 200, 200, 50);
        happyButton.addActionListener(this);


        sadButton = createButton("Sad", new Font("Bahnschrift", Font.BOLD, 20), new Color(0xfee2b3), new Color(0x1F0802));
        sadButton.setBounds(360,200,200,50);
        sadButton.addActionListener(this);
        sadButton.setFocusable(false);

        romanticButton = createButton("Romantic", new Font("Bahnschrift", Font.BOLD, 20), new Color(0xfee2b3), new Color(0x1F0802));
        romanticButton.setBounds(60,320,200,50);
        romanticButton.addActionListener(this);
        romanticButton.setFocusable(false);

        nostalgicButton = createButton("Nostalgic", new Font("Bahnschrift", Font.BOLD, 20), new Color(0xfee2b3), new Color(0x1F0802));
        nostalgicButton.setBounds(360,320,200,50);
        nostalgicButton.addActionListener(this);
        nostalgicButton.setFocusable(false);

        infoPanel = new JPanel();
        infoPanel.setBackground(new Color(0xfee2b3));
        infoPanel.setOpaque(true);
        infoPanel.setLayout(null);
        infoPanel.add(introLabel2);
        infoPanel.add(introLabel3);
        infoPanel.add(descLabel1);
        infoPanel.add(descLabel2);
        infoPanel.add(descLabel3);


        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xfee2b3));
        buttonPanel.setOpaque(true);
        buttonPanel.setLayout(null);
        buttonPanel.add(moodLabel1);
        buttonPanel.add(moodLabel2);
        buttonPanel.add(happyButton);
        buttonPanel.add(sadButton);
        buttonPanel.add(romanticButton);
        buttonPanel.add(nostalgicButton);

        bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(1,2));
        bodyPanel.setBounds(0,200,1400,680);
        bodyPanel.setBackground(new Color(0xfee2b3));
        bodyPanel.setOpaque(true);
        bodyPanel.add(infoPanel);
        bodyPanel.add(buttonPanel);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        backButton.setForeground(new Color(0xfee2b3));
        backButton.setBackground(new Color(0x1F0802));
        backButton.setBounds(300,600,140,40);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        frame.add(backButton);
        frame.add(welcomePanel);
        frame.add(bodyPanel);
        frame.setVisible(true);

    }

    private JLabel createIntroLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Bahnschrift", Font.BOLD, 40));
        label.setBounds(x, y, 300, 60);
        label.setForeground(new Color(0x1F0802));
        return label;
    }

    // Extracted methods for description labels
    private JLabel createDescriptionLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        label.setBounds(x, y, 600, 60);
        label.setForeground(new Color(0x1F0802));
        return label;
    }

    private JLabel createMoodLabel(String text, int x, int y, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Bahnschrift", Font.BOLD, fontSize));
        label.setBounds(x, y, 500, 60); // Adjust width if needed
        label.setForeground(new Color(0x1F0802));
        return label;
    }


    private JButton createButton(String text, Font font, Color foregroundColor, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(foregroundColor);
        button.setBackground(backgroundColor);
        button.setFocusable(false);
        return button;
    }

    public static void main(String[] args) {
        new Project1();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            frame.dispose();
            new Login().setVisible(true);
        }
        if(e.getSource()==happyButton){
            frame.dispose();
            try {
                new Happy_Section();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if(e.getSource()==sadButton){
            frame.dispose();
            try {
                new Sad_Section();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if(e.getSource()==romanticButton){
            frame.dispose();
            try {
                new Romantic_Section();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if(e.getSource()==nostalgicButton){
            frame.dispose();
            try {
                new Nostalgic_Section();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else{
            System.out.println("Invalid Action-Performed");
        }
    }
}
