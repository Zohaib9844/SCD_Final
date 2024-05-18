package project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
public class AdminPanel  implements ActionListener {
    //Declaring variables
    JFrame frame;
    
    JPanel welcomePanel;
    JPanel bodyPanel;
    JPanel infoPanel;
    JPanel buttonPanel;

    JLabel introLabel1;
    JLabel introLabel2;
    JLabel introLabel3;
    JLabel descriptionLabel1;
    JLabel descriptionLabel2;
    JLabel descriptionLabel3;
    JLabel moodLabel1;
    JLabel moodLabel2;


    JButton happy;
    JButton sad;
    JButton romantic;
    JButton nostalgic;
    JButton back;

    JLabel welcomeLabel;

    AdminPanel(){

        Font labelFont = new Font("Bahnschrift", Font.BOLD, 20);
        Color backgroundColor = new Color(0xfee2b3);
        Color textColor = new Color(0x1F0802);

        //Main Frame
        frame = new JFrame("Mood-Movie");
        frame.setSize(1400,760);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Label for heading
        welcomeLabel = new JLabel("WELCOME TO ADMIN SECTION");
        welcomeLabel.setFont(new Font("Bahnschrift",Font.BOLD,72) );
        welcomeLabel.setForeground(new Color(0x1F0802));

        //Main Panel
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER,25,80));
        welcomePanel.setBounds(0,0,1400,210);
        welcomePanel.setBackground(new Color(0xfee2b3));
        welcomePanel.setOpaque(true);
        welcomePanel.add(welcomeLabel);

        //Main headings
        introLabel1 = createIntroLabel("Add", 90, 40);
        introLabel2 = createIntroLabel("Movie by", 90, 90);
        introLabel3 = createIntroLabel("Mood & Feeling", 90, 145);

        //Descriptions for the movie panel
        descriptionLabel1 = createDescriptionLabel("Dwell in any emotion. Tearfull sadness or absolute anger", 90, 225);
        descriptionLabel2 = createDescriptionLabel("driven horror? We got you covered with the very best", 90, 260);
        descriptionLabel3 = createDescriptionLabel("movies recommendations for any mood or feeling.", 90, 290);

        //Mood descriptions
        moodLabel1 = createMoodLabel("What's your Mood?",50, 50, 40);
        moodLabel2 = createMoodLabel("(Select one of them according to your mood)",50, 70, 60);

        //Buttons
        happy = createMoodButton("happy", 60,200 );
        sad = createMoodButton("Sad", 360, 200);
        sad = createMoodButton("Sad", 360, 200);
        romantic = createMoodButton("Romantic", 60, 320);
        nostalgic = createMoodButton("Nostalgic", 360, 320);
        back = new JButton("Back");
        back.setFont(labelFont);
        back.setForeground(backgroundColor);
        back.setBackground(textColor);
        back.setBounds(300,600,140,40);
        back.addActionListener(this);
        back.setFocusable(false);
        frame.add(back);

        //Setting up the panel responsible for information.
        infoPanel = new JPanel();
        infoPanel.setBackground(new Color(0xfee2b3));
        infoPanel.setOpaque(true);
        infoPanel.setLayout(null);
        infoPanel.add(introLabel1);
        infoPanel.add(introLabel2);
        infoPanel.add(introLabel3);
        infoPanel.add(descriptionLabel1);
        infoPanel.add(descriptionLabel2);
        infoPanel.add(descriptionLabel3);

        //Setting up the button panel which will hold the buttons
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xfee2b3));
        buttonPanel.setOpaque(true);
        buttonPanel.setLayout(null);
        buttonPanel.add(moodLabel1);
        buttonPanel.add(moodLabel2);
        buttonPanel.add(happy);
        buttonPanel.add(sad);
        buttonPanel.add(romantic);
        buttonPanel.add(nostalgic);

        //Setting up the main body of the application interface
        bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(1,2));
        bodyPanel.setBounds(0,200,1400,680);
        bodyPanel.setBackground(new Color(0xfee2b3));
        bodyPanel.setOpaque(true);
        bodyPanel.add(infoPanel);
        bodyPanel.add(buttonPanel);
        
        frame.add(welcomePanel);
        frame.add(bodyPanel);
        frame.setVisible(true);

    }

    //Function responsible for making buttons
    private JButton createMoodButton(String label, int x, int y) {
        JButton button = new JButton(label);
        button.setBounds(x, y, 200, 50);
        button.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        button.setForeground(new Color(0xfee2b3));
        button.setBackground(new Color(0x1F0802));
        button.addActionListener(this);
        button.setFocusable(false);
        return button;
    }

    //Function responsible for making labels
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

    //Extracted method for creating labels for the mood panel.
    private JLabel createMoodLabel(String text, int x, int y, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Bahnschrift", Font.BOLD, fontSize));
        label.setBounds(x, y, 500, 60); // Adjust width if needed
        label.setForeground(new Color(0x1F0802));
        return label;
    }

    // Main function for direct unit testing.
    public static void main(String[] args) {
        AdminPanel pro = new AdminPanel();
    }

    //Overridden methods for actionPerformed interfaces.
    @Override
    public void actionPerformed(ActionEvent e) {

        //if back is pressed, correct frame is removed and previous frame is viewed
        if(e.getSource()==back){
            frame.dispose();
            Login obj = new Login();
            obj.setVisible(true);
        }
        //For happy mood.
        if(e.getSource()==happy){
            frame.dispose();
            try {
                AdminInputForm obj= new AdminInputForm("happyMovies.txt");
                obj.setVisible(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        //For Sad mood.
        else if(e.getSource()==sad){
            frame.dispose();
            try {
                AdminInputForm obj = new AdminInputForm("sadMovies.txt");
                obj.setVisible(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        //For romantic mood.
        else if(e.getSource()==romantic){
            frame.dispose();
            try {
                AdminInputForm obj = new AdminInputForm("romanticMovies.txt");
                obj.setVisible(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        //For nostalgic mood.
        else if(e.getSource()==nostalgic){
            frame.dispose();
            try {
                AdminInputForm obj = new AdminInputForm("nostalgicMovies.txt");
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
