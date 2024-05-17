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
import java.util.logging.Level;
import java.util.logging.Logger;



public class Happy_Section implements ActionListener  {
    JFrame f1;
    JPanel p1, buttonPanel; // New panel for buttons
    private ArrayList<Movie> movies;

    JButton btn1;
    JButton btn2;
    JButton btn3;
    JButton btn4;
    JButton btn5;
    JButton btn6;
    JButton back;

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JLabel label7;

    public ArrayList<Movie> readMovieData(String fileName) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
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
    Happy_Section() throws IOException {
        f1 = new JFrame("HAPPY");
        f1.setSize(1400,760);
        f1.setLocationRelativeTo(null);
        f1.setResizable(false);
        f1.setLayout(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("Movies for Happy Mood");
        label1.setFont(new Font("Bahnschrift",Font.BOLD,40));
        label1.setBounds(500,40,600,60);
        label1.setForeground(new Color(0x1F0802));

        label2 = new JLabel("(click the Movie button which one you wanna watch)");
        label2.setFont(new Font("Bahnschrift",Font.BOLD,20));
        label2.setBounds(480,80,800,60);
        label2.setForeground(new Color(0x1F0802));

        label3 = new JLabel("Find the best funny movies to watch,");
        label3.setFont(new Font("Bahnschrift",Font.BOLD,25));
        label3.setBounds(80,250,800,60);
        label3.setForeground(new Color(0x1F0802));

        label4 = new JLabel("from our mood category.");
        label4.setFont(new Font("Bahnschrift",Font.BOLD,25));
        label4.setBounds(80,300,800,60);
        label4.setForeground(new Color(0x1F0802));

        label5 = new JLabel("Like everything on agoodmovietowatch,");
        label5.setFont(new Font("Bahnschrift",Font.BOLD,25));
        label5.setBounds(80,350,800,60);
        label5.setForeground(new Color(0x1F0802));

        label6 = new JLabel(" these funny movies are highly-rated by viewers.");
        label6.setFont(new Font("Bahnschrift",Font.BOLD,25));
        label6.setBounds(80,400,800,60);
        label6.setForeground(new Color(0x1F0802));

        label7 = new JLabel("(Suggestions)");
        label7.setFont(new Font("Bahnschrift",Font.BOLD,34));
        label7.setBounds(840,170,800,60);
        label7.setForeground(new Color(0x1F0802));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10)); // 2x3 grid layout for buttons
        buttonPanel.setBounds(850, 260, 500, 200);

        ArrayList<Movie> movies = readMovieData("happyMovies.txt"); // Replace with your file name
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

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0,0,1400,760);
        p1.setBackground(new Color(0xfee2b3));
        p1.setOpaque(true);
        p1.add(buttonPanel);
        p1.add(label1);
        p1.add(label2);
        p1.add(label3);
        p1.add(label4);
        p1.add(label5);
        p1.add(label6);
        p1.add(label7);
        p1.add(back);
        f1.add(p1);
        f1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Movie> movies = null;
        try {
            movies = readMovieData("happyMovies.txt");
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
            f1.dispose();
            Project1 pro = new Project1();
        } else {
            System.out.println("Invalid Action-Performed");
        }

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

        int option = JOptionPane.showConfirmDialog(f1, panel, "Movie Info",
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
                f1.dispose();
                try {
                    new Happy_Section();

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
            ArrayList<Movie> movies = readMovieData("happyMovies.txt");
            ArrayList<Movie> filteredMovies = new ArrayList<>();

            for (Movie movie : movies) {
                if (movie.id != selectedMovie.id) { // Filter movies excluding the selected one
                    filteredMovies.add(movie);
                }
            }

            // Write filtered movies back to the file
            PrintWriter writer = new PrintWriter("happyMovies.txt");
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
