import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which website would you want to play with? ");
        System.out.println("1. Mako");
        System.out.println("2. Ynet");
        System.out.println("3. Walla");
        System.out.print("--> ");
        int userChoice = scanner.nextInt();
        int numOfTries = 5;
        int points = 0;
        switch (userChoice) {
            case 1:
                MakoBot makoBot = new MakoBot();
                Map<String, Integer> makoMap = makoBot.getWordsStatistics();
                System.out.println("You will get points for the number which the word found in the articles.");
                System.out.print("As a clue, this is the title of the longest article: ");
                System.out.println(makoBot.getLongestArticleTitle());
                while (numOfTries > 0) {
                    System.out.println("You have 5 tries --> tries left: " + numOfTries);
                    System.out.print("Your word: ");
                    String userWord = scanner.next();
                    if (makoMap.containsKey(userWord)) {
                        points += makoMap.get(userWord);
                    }
                    if (makoMap.get(userWord) != null) {
                        System.out.println("You get " + makoMap.get(userWord) + " point(s)!");
                    } else {
                        System.out.println("No points earned!");
                    }
                    numOfTries--;
                }
                System.out.println("Total amount of points for now: " + points);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("You have a chance to gain even more points!");
                System.out.println("Enter a text [1-20 characters], it will be searched at the site's articles");
                System.out.println("You will enter a number, if it is the number of appearances of your text [approx close by 2] ");
                System.out.println("Guessing right will give you another 250 points!");
                System.out.print("your text: ");
                String userTextForArticlesMako = scanner.next();
                int numOfAppearsInTitlesMako = makoBot.countInArticlesTitles(userTextForArticlesMako);
                System.out.print("Guess how many times it appeared: ");
                int userNumForArticlesMako = scanner.nextInt();
                if (userTextForArticlesMako.length() < 1 || userTextForArticlesMako.length() > 20) {
                    System.out.println("Your text is again the rules! no points this time!");
                    System.out.println("Your total score: " + points);
                    System.out.println("Better luck next time!, see you soon!");
                } else if (userNumForArticlesMako < 0) {
                    System.out.println("Sadly you guess negative number, seems logical to you?");
                    System.out.println("Your total score: " + points);
                    System.out.println("No bonus points this time!, see you soon!");
                } else if (userNumForArticlesMako > numOfAppearsInTitlesMako - 3 && userNumForArticlesMako < numOfAppearsInTitlesMako + 3) {
                    System.out.println("You have made it! 250 points added!");
                    points += 250;
                    System.out.println("Your total score: " + points);
                    System.out.println("See you soon!");
                } else {
                    System.out.println("Oof you were close, but not enough!");
                    System.out.println("Your total score: " + points);
                    System.out.println("You might get more lucky next time!, see you soon!");
                }
                break;

            case 2:
                YnetBot ynetBot = new YnetBot();
                Map<String, Integer> ynetMap = ynetBot.getWordsStatistics();
                System.out.println("You will get points for the number which the word found in the articles.");
                System.out.print("As a clue, this is the title of the longest article: ");
                System.out.println(ynetBot.getLongestArticleTitle());
                points = 0;
                while (numOfTries > 0) {
                    System.out.println("You have 5 tries --> tries left: " + numOfTries);
                    System.out.print("Your word: ");
                    String userWord = scanner.next();
                    if (ynetMap.containsKey(userWord)) {
                        points += ynetMap.get(userWord);
                    }
                    if (ynetMap.get(userWord) != null) {
                        System.out.println("You get " + ynetMap.get(userWord) + " point(s)!");
                    } else {
                        System.out.println("No points earned!");
                    }
                    numOfTries--;
                }
                System.out.println("Total amount of points for now: " + points);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("You have a chance to gain even more points!");
                System.out.println("Enter a text [1-20 characters], it will be searched at the site's articles");
                System.out.println("You will enter a number, if it is the number of appearances of your text [approx close by 2] ");
                System.out.println("Guessing right will give you another 250 points!");
                System.out.print("your text: ");
                String userTextForArticles = scanner.next();
                int numOfAppearsInTitles = ynetBot.countInArticlesTitles(userTextForArticles);
                System.out.print("Guess how many times it appeared: ");
                int userNumForArticles = scanner.nextInt();
                if (userTextForArticles.length() < 1 || userTextForArticles.length() > 20) {
                    System.out.println("Your text is again the rules! no points this time!");
                    System.out.println("Your total score: " + points);
                    System.out.println("Better luck next time!, see you soon!");
                } else if (userNumForArticles < 0) {
                    System.out.println("Sadly you guess negative number, seems logical to you?");
                    System.out.println("Your total score: " + points);
                    System.out.println("No bonus points this time!, see you soon!");
                } else if (userNumForArticles > numOfAppearsInTitles - 3 && userNumForArticles < numOfAppearsInTitles + 3) {
                    System.out.println("You have made it! 250 points added!");
                    points += 250;
                    System.out.println("Your total score: " + points);
                    System.out.println("See you soon!");
                } else {
                    System.out.println("Oof you were close, but not enough!");
                    System.out.println("Your total score: " + points);
                    System.out.println("You might get more lucky next time!, see you soon!");
                }
                break;
            case 3:
                WallaBot wallaBot = new WallaBot();
                Map<String, Integer> wallaMap = wallaBot.getWordsStatistics();
                System.out.println("You will get points for the number which the word found in the articles.");
                System.out.print("As a clue, this is the title of the longest article: ");
                System.out.println(wallaBot.getLongestArticleTitle());
                points = 0;
                while (numOfTries > 0) {
                    System.out.println("You have 5 tries --> tries left: " + numOfTries);
                    System.out.print("Your word: ");
                    String userWord = scanner.next();
                    if (wallaMap.containsKey(userWord)) {
                        points += wallaMap.get(userWord);
                    }
                    if (wallaMap.get(userWord) != null) {
                        System.out.println("You get " + wallaMap.get(userWord) + " point(s)!");
                    } else {
                        System.out.println("No points earned!");
                    }
                    numOfTries--;
                }
                System.out.println("Total amount of points for now: " + points);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("You have a chance to gain even more points!");
                System.out.println("Enter a text [1-20 characters], it will be searched at the site's articles");
                System.out.println("You will enter a number, if it is the number of appearances of your text [approx close by 2] ");
                System.out.println("Guessing right will give you another 250 points!");
                System.out.print("your text: ");
                String userTextForArticlesWalla = scanner.next();
                int numOfAppearsInTitlesWalla = wallaBot.countInArticlesTitles(userTextForArticlesWalla);
                System.out.print("Guess how many times it appeared: ");
                int userNumForArticlesWalla = scanner.nextInt();
                if (userTextForArticlesWalla.length() < 1 || userTextForArticlesWalla.length() > 20) {
                    System.out.println("Your text is again the rules! no points this time!");
                    System.out.println("Your total score: " + points);
                    System.out.println("Better luck next time!, see you soon!");
                } else if (userNumForArticlesWalla < 0) {
                    System.out.println("Sadly you guess negative number, seems logical to you?");
                    System.out.println("Your total score: " + points);
                    System.out.println("No bonus points this time!, see you soon!");
                } else if (userNumForArticlesWalla > numOfAppearsInTitlesWalla - 3 && userNumForArticlesWalla < numOfAppearsInTitlesWalla + 3) {
                    System.out.println("You have made it! 250 points added!");
                    points += 250;
                    System.out.println("Your total score: " + points);
                    System.out.println("See you soon!");
                } else {
                    System.out.println("Oof you were close, but not enough!");
                    System.out.println("Your total score: " + points);
                    System.out.println("You might get more lucky next time!, see you soon!");
                }
                break;

            default:
                System.out.println("Not a valid option, try again");
                main(args);
        }
    }
}