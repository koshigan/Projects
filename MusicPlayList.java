import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MusicPlayList {
    static Stack<String> pl = new Stack<>();
    static Scanner sc = new Scanner(System.in);

    static Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z ]{2,}$");
    static String fi = "playlist.txt";

    static void addSong() {
        System.out.print("Enter song name: ");
        String s = sc.nextLine();

        Matcher matcher = pattern.matcher(s);

        if (matcher.matches()) {
            pl.push(s);
            System.out.println("Song added successfully ");
        } else {
            System.out.println("Invalid song name ");
            System.out.println("Rule: Start with capital & only letters allowed");
        }
    }
    static void playSong() {
        if (pl.isEmpty()) {
            System.out.println("Playlist is empty ");
        } else {
            System.out.println("Now Playing: " + pl.peek());
        }
    }
    static void removeSong() {
    if (pl.isEmpty()) {
        System.out.println("Playlist is empty ❌");
    } else {
        String r = pl.pop();
        System.out.println("Removed Song: ❌ " + r);
    }
}
    static void viewCurrentSong() {
        if (pl.isEmpty()) {
            System.out.println("Playlist is empty ");
        } else {
            System.out.println("Current Song: " + pl.peek());
        }
    }
    static void viewAllSongs() {
        if (pl.isEmpty()) {
            System.out.println("Playlist is empty ");
        } else {
            System.out.println("All Songs in Playlist:");
            for (String s : pl) {
                System.out.println( s);
            }
        }
    }
    static void saveToFile() {
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter(fi));

            for (String song : pl) {
                wr.write(song);
                wr.newLine();
            }
            wr.close();
            System.out.println("Playlist saved to file ");
        } catch (IOException e) {
            System.out.println("Error saving file ");
        }
    }
    static void loadFromFile() {
        try {
            BufferedReader re = new BufferedReader(new FileReader(fi));
            String l;
            while ((l = re.readLine()) != null) {
                pl.push(l);
            }
            re.close();
        } catch (IOException e) {
            System.out.println("No previous playlist ");
        }
    }

    public static void main(String[] args) {
        loadFromFile(); 
        int choice;
        do {
            System.out.println("\n ---MUSIC PLAYLIST--- ");
            System.out.println("1. Add Song");
            System.out.println("2. Play Song");
            System.out.println("3. Remove song");
            System.out.println("4. View Current Song");
            System.out.println("5. View All Songs");
            System.out.println("6. Save Playlist to File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addSong();
                    break;
                case 2:
                    playSong();
                    break;
                case 3:
                    removeSong();
                case 4:
                    viewCurrentSong();
                    break;
                case 5:
                    viewAllSongs();
                    break;
                case 6:
                    saveToFile();
                    break;
                case 7:
                    saveToFile(); 
                    System.out.println("Exiting... Playlist Saved ");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);
    }
    
}
