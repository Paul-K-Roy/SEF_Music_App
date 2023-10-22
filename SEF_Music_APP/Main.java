import com.musicapp.Artist;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create some sample artists
        Artist artist1 = new Artist("579MMMRR_%", "John Doe", "Melbourne|Victoria|Australia", "05-11-1985",
                "A renowned artist with more than ten words in the bio.", new ArrayList<String>(){{add("Singer"); add("Songwriter");}},
                new ArrayList<String>(){{add("pop"); add("classical");}},
                new ArrayList<String>(){{add("2022, Best Song Written For Visual Media");}});

        Artist artist2 = new Artist("123ABCD", "Jane Smith", "New York|NY|USA", "15-07-1990",
                "Another talented artist with a lengthy bio.", new ArrayList<String>(){{add("Singer");}},
                new ArrayList<String>(){{add("rock"); add("jazz");}},
                new ArrayList<String>(){{add("2019, Best Female Artist");}});

        // Add artists
        boolean artist1Added = artist1.addArtist();
        boolean artist2Added = artist2.addArtist();

        // Display the results of adding artists
        System.out.println("Artist 1 Added: " + artist1Added);
        System.out.println("Artist 2 Added: " + artist2Added);

        // Update an artist's information
        boolean artist1Updated = artist1.updateArtist(
                new ArrayList<String>(){{add("Singer"); add("Songwriter"); add("Composer");}},
                new ArrayList<String>(){{add("2022, Best Song Written For Visual Media"); add("2023, Best Composer");}});

        // Display the result of updating an artist
        System.out.println("Artist 1 Updated: " + artist1Updated);

        // You can continue adding more artists and testing other functionalities here
    }
}
