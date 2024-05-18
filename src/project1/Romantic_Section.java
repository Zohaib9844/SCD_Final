package project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class Romantic_Section implements ActionListener  {
    //Declaring variables
    JFrame frame;

    JPanel bodyPanel, buttonPanel; // New panel for buttons
    JLabel descLabel1;
    JLabel descLabel2;
    JLabel descLabel3;
    JLabel descLabel4;
    JLabel descLabel5;
    JLabel descLabel6;
    JLabel buttonLabel;

    JButton back;

    public ArrayList<Movie> readMovieData(String fileName) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        // Populates an ArrayList of Movie objects from the given file
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(" "); // Split by space
            Movie movie = new Movie();
            movie.id = Integer.parseInt(data[0]);
            movie.name = data[1];
            movie.price = Double.parseDouble(data[2]);
            movie.rating = Double.parseDouble(data[3]);
            movie.youtubeLink = data[4];
            movie.purchased = false; // Initially set purchased to false
            movies.add(movie);
        }
        reader.close();
        return movies;
    }
    Romantic_Section() throws IOException {
        String Filename = "romanticMovies.txt";
        frame = new JFrame("HAPPY");
        frame.setSize(1400,760);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        descLabel1 = createLabel("Movies for Romantic Mood", new Font("Bahnschrift",Font.BOLD,40),new Color(0x1F0802) , 500,40, 600, 60);
        descLabel2 = createLabel("(click the Movie button which one you wanna watch)", new Font("Bahnschrift",Font.BOLD,20),new Color(0x1F0802) , 480,80, 800, 60);
        descLabel3 = createLabel("Find the best Romantic movies to watch,", new Font("Bahnschrift",Font.BOLD,25),new Color(0x1F0802) , 80,250, 800, 60);
        descLabel4 = createLabel("from our mood category.", new Font("Bahnschrift",Font.BOLD,25),new Color(0x1F0802) , 80,300, 800, 60);
        descLabel5 = createLabel("Like everything on agoodmovietowatch,", new Font("Bahnschrift",Font.BOLD,25),new Color(0x1F0802) , 80,350, 800, 60);
        descLabel6 = createLabel(" these loving movies are highly-rated by viewers.", new Font("Bahnschrift",Font.BOLD,25),new Color(0x1F0802) , 80,400, 800, 60);
        buttonLabel = createLabel("(Suggestions)", new Font("Bahnschrift",Font.BOLD,34),new Color(0x1F0802) , 840,170, 800, 60);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10)); // 2x3 grid layout for buttons
        buttonPanel.setBounds(850, 260, 500, 200);

        ArrayList<Movie> movies = readMovieData("romanticMovies.txt");
        for (Movie movie : movies) {
            JButton button = new JButton(movie.name);
            button.setFont(new Font("Bahnschrift", Font.BOLD, 16));
            button.setForeground(new Color(0xfee2b3));
            button.setBackground(new Color(0x1F0802));
            button.addActionListener(this); // Add same action listener
            button.setFocusable(false);
            buttonPanel.add(button);
        }

        back = new JButton("Back");
        back.setFont(new Font("Bahnschrift",Font.BOLD,20));
        back.setBounds(570,600,200,50);
        back.setForeground(new Color(0xfee2b3));
        back.setBackground(new Color(0x1F0802));
        back.setFocusable(false);
        back.addActionListener(this);

        bodyPanel = new JPanel();
        bodyPanel.setLayout(null);
        bodyPanel.setBounds(0,0,1400,760);
        bodyPanel.setBackground(new Color(0xfee2b3));
        bodyPanel.setOpaque(true);
        bodyPanel.add(buttonPanel);
        bodyPanel.add(descLabel1);
        bodyPanel.add(descLabel2);
        bodyPanel.add(descLabel3);
        bodyPanel.add(descLabel4);
        bodyPanel.add(descLabel5);
        bodyPanel.add(descLabel6);
        bodyPanel.add(buttonLabel);
        bodyPanel.add(back);
        frame.add(bodyPanel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Movie> movies = null;
        try {
            movies = readMovieData("romanticMovies.txt");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Movie selectedMovie = null;
        for (Movie movie : movies) { // Find the clicked movie
            if (e.getSource() == buttonPanel.getComponent(movies.indexOf(movie))) {
                selectedMovie = movie;
                break;
            }
        }

        if (selectedMovie != null) {
            // Show movie information dialog
            showMovieInfoDialog(selectedMovie);
        } else if (e.getSource() == back) {
            frame.dispose();
            Project1 pro = new Project1();
        } else {
            System.out.println("Invalid Action-Performed");
        }

    }

    private JLabel createLabel(String text, Font font, Color color, int x, int y, int width, int height) {
        JLabel label = new JLabel(text); // Center alignment
        label.setFont(font);
        label.setForeground(color);
        label.setBounds(x, y, width, height);
        return label;
    }

    private void showMovieInfoDialog(Movie movie) {
        String message = "Movie: " + movie.name + "\n" + "Price: $" + movie.price + "\n" + "Rating: " + movie.rating + "\n";

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel movieLabel = new JLabel(message);
        JButton purchaseButton = new JButton("Buy ($" + movie.price + ")");
        JButton watchTrailerButton = new JButton("Watch Trailer");
        purchaseButton.addActionListener(e -> handlePurchaseAction(movie));

        watchTrailerButton.addActionListener(e -> watchTrailer(movie.youtubeLink)); // Use lambda for cleaner action listener

        panel.add(movieLabel);
        panel.add(purchaseButton);
        panel.add(watchTrailerButton);

        int option = JOptionPane.showConfirmDialog(frame, panel, "Movie Info",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

        // Handle button clicks based on option (not needed in this case)
    }

    private void handlePurchaseAction(Movie movie) {
        int confirmation = JOptionPane.showConfirmDialog(null,
                "This will buy the movie from your list. Are you sure?",
                "Purchase Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (confirmation == JOptionPane.YES_OPTION) {
            if (deleteMovieFromFile(movie)) {  // Call deleteMovieFromFile and handle return value
//                JOptionPane.showMessageDialog(null, movie.name + " removed successfully!");
                frame.dispose();
                try {
                    new Romantic_Section();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Close current dialog
            } else {
                JOptionPane.showMessageDialog(null, "An error occurred. Movie not removed.");
            }
        }
    }

    private boolean deleteMovieFromFile(Movie selectedMovie) {
        try {
            ArrayList<Movie> movies = readMovieData("romanticMovies.txt");
            ArrayList<Movie> filteredMovies = new ArrayList<>();

            for (Movie movie : movies) {
                if (movie.id != selectedMovie.id) { // Filter movies excluding the selected one
                    filteredMovies.add(movie);
                }
            }

            // Write filtered movies back to the file
            PrintWriter writer = new PrintWriter("romanticMovies.txt");
            for (Movie movie : filteredMovies) {
                writer.println(movie.id + " " + movie.name + " " + movie.price + " " + movie.rating + " " + movie.youtubeLink);
            }
            writer.close();
            return true; // Indicate successful deletion
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Indicate failure
        }
    }

    private void watchTrailer(String youtubeLink) {
        try {
            Desktop.getDesktop().browse(new URI(youtubeLink));
        } catch (IOException | URISyntaxException ex) {
            JOptionPane.showMessageDialog(null, "Error opening trailer. Please check the link.");
        }
    }


}
