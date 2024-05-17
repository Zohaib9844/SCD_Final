package project1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class NostalgicInputForm extends JFrame implements ActionListener {

    private static final String FILE_NAME = "nostalgicMovies.txt"; // Change this if your file name is different

    private int nextID;

    private JPanel mainPanel, topPanel, centerPanel, bottomPanel;
    private JLabel titleLabel, movieNameLabel, moviePriceLabel, movieRatingLabel, movieTrailerLabel;
    private JTextField movieNameField, moviePriceField, movieRatingField, movieTrailerField;
    private JButton submitButton, backButton;

    public NostalgicInputForm() throws IOException {
        super("Movie Input Form");

        // Get the latest ID from the movie file
        nextID = getLatestID();

        // Set design elements
        Font titleFont = new Font("Bahnschrift", Font.BOLD, 72);
        Font labelFont = new Font("Bahnschrift", Font.BOLD, 20);
        Color backgroundColor = new Color(0xfee2b3);
        Color textColor = new Color(0x1F0802);

        // Create main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        // Top panel with title
        topPanel = new JPanel();
        topPanel.setBackground(backgroundColor);
        titleLabel = new JLabel("Movie Input");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        topPanel.add(titleLabel);

        // Center panel with input fields
        centerPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        centerPanel.setBackground(backgroundColor);

        movieNameLabel = new JLabel("Movie Name:");
        movieNameLabel.setFont(labelFont);
        movieNameLabel.setForeground(textColor);
        centerPanel.add(movieNameLabel);

        movieNameField = new JTextField(20);
        movieNameField.setFont(labelFont);
        centerPanel.add(movieNameField);

        moviePriceLabel = new JLabel("Price:");
        moviePriceLabel.setFont(labelFont);
        moviePriceLabel.setForeground(textColor);
        centerPanel.add(moviePriceLabel);

        moviePriceField = new JTextField(10);
        moviePriceField.setFont(labelFont);
        centerPanel.add(moviePriceField);

        movieRatingLabel = new JLabel("Rating:");
        movieRatingLabel.setFont(labelFont);
        movieRatingLabel.setForeground(textColor);
        centerPanel.add(movieRatingLabel);

        movieRatingField = new JTextField(10);
        movieRatingField.setFont(labelFont);
        centerPanel.add(movieRatingField);

        movieTrailerLabel = new JLabel("Trailer Link:");
        movieTrailerLabel.setFont(labelFont);
        movieTrailerLabel.setForeground(textColor);
        centerPanel.add(movieTrailerLabel);

        movieTrailerField = new JTextField(30);
        movieTrailerField.setFont(labelFont);
        centerPanel.add(movieTrailerField);

        // Bottom panel with submit button
        bottomPanel = new JPanel();
        bottomPanel.setBackground(backgroundColor);

        submitButton = new JButton("Submit");
        submitButton.setFont(labelFont);
        submitButton.setForeground(backgroundColor);
        submitButton.setBackground(textColor);
        submitButton.addActionListener(this);
        bottomPanel.add(submitButton);

        backButton = new JButton("Back");
        backButton.setFont(labelFont);
        backButton.setForeground(backgroundColor);
        backButton.setBackground(textColor);
        backButton.addActionListener(this);
        bottomPanel.add(backButton);

        // Add panels to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Set frame properties
        this.setContentPane(mainPanel);
        this.setSize(600, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int getLatestID() throws IOException {
        int latestID = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                int id = Integer.parseInt(data[0]);
                if (id > latestID) {
                    latestID = id;
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file doesn't exist
            System.err.println("Error: Movie file not found! Creating a new one.");
            try (Writer writer = new FileWriter(FILE_NAME)) {
                writer.write(""); // Create an empty file
            }
        }
        return latestID+1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
            new AdminPanel();
        }
        if (e.getSource() == submitButton) {
            String movieName = movieNameField.getText().trim();
            String moviePriceStr = moviePriceField.getText().trim();
            String movieRatingStr = movieRatingField.getText().trim();
            String movieTrailerLink = movieTrailerField.getText().trim();

            // Validate user input (optional)
            if (movieName.isEmpty() || moviePriceStr.isEmpty() || movieRatingStr.isEmpty() || movieTrailerLink.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convert price and rating to double
            double moviePrice;
            double movieRating;
            try {
                moviePrice = Double.parseDouble(moviePriceStr);
                movieRating = Double.parseDouble(movieRatingStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price or rating format!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Write data to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write(nextID + " " + movieName + " " + moviePrice + " " + movieRating + " " + movieTrailerLink);
                writer.newLine();
                nextID++; // Increment nextID for the next entry

                JOptionPane.showMessageDialog(this, "Movie information added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear input fields after successful submission (optional)
                movieNameField.setText("");
                moviePriceField.setText("");
                movieRatingField.setText("");
                movieTrailerField.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error writing to file!", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            HappyInputForm form = new HappyInputForm();
            form.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}