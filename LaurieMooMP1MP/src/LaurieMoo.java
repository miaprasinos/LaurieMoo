// MP1
// 02-11-2021
// Mia Prasinos
// Randomly generates a secret 4 digit value. User inputs guesses.
// "MOO!" indicates a digit in your guess is in the right position.
// "moo." indicates a digit in your guess is a correct digit, but not in the correct position


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class LaurieMoo {
    private JPanel laurieMooPanel;
    private JButton randomButton;
    private JTextField guessTextField;
    private JLabel bigMoo1Label;
    private JLabel bigMoo2Label;
    private JLabel bigMoo3Label;
    private JLabel bigMoo4Label;
    private JLabel littleMoo1Label;
    private JLabel littleMoo2Label;
    private JLabel littleMoo3Label;
    private JLabel littleMoo4Label;
    private JLabel secretValueLabel;
    int secretValue;
    String secretValueString;
    String guessString;
    String frontZero = "0";
    int guessValue;
    int guessCount = 0;

    public LaurieMoo() {
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // sets a random secret variable
                secretValue = ThreadLocalRandom.current().nextInt(0, 10000);
                setSecretValue(secretValue);

                //resets or starts the game
                reset();
            }
        });
        guessTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //gets guess from the user and keeps track of the amount of guesses
                guessCount++;
                guessValue = Integer.parseInt(guessTextField.getText());
                guessString = Integer.toString(guessValue);

                System.out.println(guessCount);

                // sets guess value as a string for formatting
                if(guessValue == 0){
                    guessString = "0000";
                }
                else if (guessValue < 10){
                    guessString = frontZero + frontZero + frontZero + guessString;
                }
                else if (guessValue < 100){
                    guessString = frontZero + frontZero + guessString;
                }
                else if (guessValue < 1000){
                    guessString = frontZero + guessString;
                }

                // checks how many guesses user has gone through and checks the guess for correctness, if not correct and less than 10 continues as normal
                if (guessCount > 10){
                    JOptionPane.showMessageDialog(null, "Boo hoo – no LaurieMOO.");
                    secretValueLabel.setText(secretValueString);
                    if (isCorrectGuess(guessValue)){
                        JOptionPane.showMessageDialog(null, "WOO HOO YOU WON LAURIE MOO");
                        secretValueLabel.setText(secretValueString);
                    }
                }
                else{
                    if (isCorrectGuess(guessValue)){
                        JOptionPane.showMessageDialog(null, "WOO HOO YOU WON LAURIE MOO");
                        secretValueLabel.setText(secretValueString);
                    }
                    else{
                        if ((getBigMooCount(guessValue)) == 0 && getLittleMooCount(guessValue)==0){
                            JOptionPane.showMessageDialog(null, "All you hear are cowbells... nothing is being uddered...");
                        }
                    }
                }


            }
        });
    }

    public static void main(String[] args) {
        JFrame myFrame = new JFrame("Laurie Moo");
        myFrame.setContentPane(new LaurieMoo().laurieMooPanel);

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myFrame.pack();
        myFrame.setVisible(true);

    }

    /**
     * the setSecretValue method number takes the number and enters into secretValueTextField
     * @param n number that will be set as secret value
     * @return true if secret value was reset
     */
    public boolean setSecretValue (int n){
        secretValueString = Integer.toString(getSecretValue());

        //formats the secret value with zeros
        if(secretValue == 0){
            secretValueString = "0000";
        }
        else if (secretValue < 10){
            secretValueString = frontZero + frontZero + frontZero + secretValueString;
        }
        else if (secretValue < 100){
            secretValueString = frontZero + frontZero + secretValueString;
        }
        else if (secretValue < 1000){
            secretValueString = frontZero + secretValueString;
        }

        // confirms secret value is valid
        if(n <= 9999 && n>= 0000){
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * access the secret number user is trying to guess
     * @return secret value user was trying to guess
     */
    public int getSecretValue() {
        return secretValue;
    }

    /**
     * generates new secret value to be used
     * @return true always
     */
    public boolean setSecretValue(){

        return true;
    }


    /**
     * number of digits that are the right number and location in guess
     * @param guess the number user has guessed
     * @return 0-4 depending on how many correct numbers and locations
     */
    public int getBigMooCount (int guess){
        int beginningPosition = 0;
        int endingPosition = 1;
        int bigMooCount = 0;
        String substringSecretValue = secretValueString.substring(0, 1);
        String substringGuess = guessString.substring(0, 1);

        //loops through secret value string and compares each value at the same point
        for (int i = 0; i<4; i++){


            substringSecretValue = secretValueString.substring(beginningPosition,endingPosition);
            substringGuess = guessString.substring(beginningPosition,endingPosition);


            if (substringSecretValue.equals(substringGuess)){
                bigMooCount++;
            }

            beginningPosition++;
            endingPosition++;


        }

        // shows appropriate labels for amount of big moos
        if(bigMooCount == 1){
            bigMoo1Label.setVisible(true);
            bigMoo2Label.hide();
            bigMoo3Label.hide();
            bigMoo4Label.hide();
        }
        else if (bigMooCount == 2){
            bigMoo1Label.setVisible(true);
            bigMoo2Label.setVisible(true);
            bigMoo3Label.hide();
            bigMoo4Label.hide();
        }
        else if (bigMooCount == 3){
            bigMoo1Label.setVisible(true);
            bigMoo2Label.setVisible(true);
            bigMoo3Label.setVisible(true);
            bigMoo4Label.hide();
        }
        else if (bigMooCount == 4){
            bigMoo1Label.setVisible(true);
            bigMoo2Label.setVisible(true);
            bigMoo3Label.setVisible(true);
            bigMoo4Label.setVisible(true);
        }
        else{
            bigMoo1Label.hide();
            bigMoo2Label.hide();
            bigMoo3Label.hide();
            bigMoo4Label.hide();
        }
        System.out.println("big moo count: " + bigMooCount);

        return bigMooCount;
    }

    /**
     * number of digits that are the right number but not location in guess
     * @param guess the number the user has guessed
     * @return 0-4 depending on how many correct numbers
     */
    public int getLittleMooCount (int guess){
        int littleMooCount = 0;
        int v = 0;

        //loops through guess string
        for (int i = 0; i<4; i++){
            char g = guessString.charAt(i);
            char gg = guessString.charAt(i++);

            for( v = 0; v<4; v++){
                //loops through secret value string comparing the one value of guess at each position
                char s = secretValueString.charAt(v);

                if(i == v){
                    break;
                }
                else if (s == g){
                    littleMooCount++;
                }

            }
        }

        // sets appropriate labels for amount of little moos
        if(littleMooCount == 1){
            littleMoo1Label.setVisible(true);
            littleMoo2Label.hide();
            littleMoo3Label.hide();
            littleMoo4Label.hide();
        }
        else if (littleMooCount == 2){
            littleMoo1Label.setVisible(true);
            littleMoo2Label.setVisible(true);
            littleMoo3Label.hide();
            littleMoo4Label.hide();
        }
        else if (littleMooCount == 3){
            littleMoo1Label.setVisible(true);
            littleMoo2Label.setVisible(true);
            littleMoo3Label.setVisible(true);
            littleMoo4Label.hide();
        }
        else if (littleMooCount == 4){
            littleMoo1Label.setVisible(true);
            littleMoo2Label.setVisible(true);
            littleMoo3Label.setVisible(true);
            littleMoo4Label.setVisible(true);
        }
        else {
            littleMoo1Label.hide();
            littleMoo2Label.hide();
            littleMoo3Label.hide();
            littleMoo4Label.hide();
        }

        System.out.println("little moo count: " + littleMooCount);

        return littleMooCount;
    }

    /**
     * determines if user correctly guessed secret value
     * @param guess the number the user has guessed
     * @return true if guess is correct, false if otherwise
     */
    public boolean isCorrectGuess (int guess){

        int guessAccuracyCheck = getBigMooCount(guess);
        int littleMooAmount = getLittleMooCount(guess);

        if (guessAccuracyCheck == 4){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Resets the game, hides all the moo labels for the start of the game, resets the secret value label,
     * and sets guessCount back to 0;
     */
    public void reset (){
        guessCount = 0;

        bigMoo1Label.hide();
        bigMoo2Label.hide();
        bigMoo3Label.hide();
        bigMoo4Label.hide();

        littleMoo1Label.hide();
        littleMoo2Label.hide();
        littleMoo3Label.hide();
        littleMoo4Label.hide();

        secretValueLabel.setText("this is the secret number...");
    }

}
