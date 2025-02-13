import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Stopwatch class implements a simple stopwatch with Start, Stop, and Reset functionality.
 * The class uses a Timer to track the time elapsed in seconds and displays it in a digital clock format.
 * It includes a graphical user interface (GUI) with two buttons and a time display.
 */
public class Stopwatch implements ActionListener {

    // JFrame to hold the entire GUI of the Stopwatch
    JFrame frame = new JFrame();

    // Buttons for starting/stopping and resetting the timer
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");

    // Label to display the time in hours:minutes:seconds format
    JLabel timeLabel = new JLabel();

    // Variables to track the elapsed time and break it down into hours, minutes, and seconds
    int elapsedTime = 0;  // Holds the amount of milliseconds after the timer starts
    int seconds = 0;
    int minutes = 0;
    int hours = 0;

    // Flag to check if the stopwatch has started
    boolean started = false;

    // Strings to store formatted time for displaying
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    // ImageIcon to set the application's icon
    ImageIcon image = new ImageIcon("stopwatch2.png");

    // Timer object that triggers every second to update the time
    Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // This method is called every second to update the time

            // Increase elapsed time by 1000ms (1 second)
            elapsedTime += 1000;

            // Calculate hours, minutes, and seconds from the elapsed time
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime / 60000) % 60;  // Modulo 60 to ensure minutes don't exceed 59
            seconds = (elapsedTime / 1000) % 60;

            // Format the time to always show two digits
            hours_string = String.format("%02d", hours);
            minutes_string = String.format("%02d", minutes);
            seconds_string = String.format("%02d", seconds);

            // Update the time display with the formatted time
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
    });

    /**
     * Stopwatch constructor initializes the GUI components, configures their properties,
     * and sets up the actions for buttons.
     */
    Stopwatch() {

        // Set the initial time display
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100, 100, 200, 100);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 45));
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
        timeLabel.setOpaque(true);
        timeLabel.setBackground(new Color(30, 30, 30));  // Dark background for the clock
        timeLabel.setForeground(new Color(0, 255, 255));  // Light cyan for the text
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setVerticalAlignment(JTextField.CENTER);

        // Configure the Start button
        startButton.setBounds(100, 200, 100, 50);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setFocusable(false);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(0, 153, 51));  // Green color for start
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setFocusPainted(false);
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        startButton.addActionListener(this);

        // Configure the Reset button
        resetButton.setBounds(200, 200, 100, 50);
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.setFocusable(false);
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(204, 51, 51));  // Red color for reset
        resetButton.setBorder(BorderFactory.createEmptyBorder());
        resetButton.setFocusPainted(false);
        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resetButton.addActionListener(this);

        // Add components to the frame
        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setIconImage(image.getImage());  // Set the window's icon
        frame.getContentPane().setBackground(new Color(40, 40, 40));  // Dark background for the frame
        frame.setVisible(true);
    }

    /**
     * Action listener for the buttons. 
     * Handles the start/stop and reset functionality.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // If the stopwatch hasn't started, start it; otherwise, stop it
            if (!started) {
                start();
                started = true;
                startButton.setText("STOP");  // Change the button text to STOP
            } else {
                started = false;
                startButton.setText("START");  // Change the button text back to START
                stop();
            }
        }

        // Handle reset button press
        if (e.getSource() == resetButton) {
            started = false;  // Stop the stopwatch
            startButton.setText("START");  // Reset the start button text
            reset();
        }
    }

    /**
     * Start the timer to begin updating the time every second.
     */
    void start() {
        timer.start();
    }

    /**
     * Stop the timer and prevent further updates to the time.
     */
    void stop() {
        timer.stop();
    }

    /**
     * Reset the stopwatch back to 0:00:00 and update the display.
     */
    void reset() {
        timer.stop();  // Stop the timer
        elapsedTime = 0;  // Reset elapsed time
        hours = 0;
        minutes = 0;
        seconds = 0;

        // Update the time display after resetting
        hours_string = String.format("%02d", hours);
        minutes_string = String.format("%02d", minutes);
        seconds_string = String.format("%02d", seconds);

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }
}
