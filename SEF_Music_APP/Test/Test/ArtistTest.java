package Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import com.musicapp.Artist;
import java.lang.reflect.Method;


public class ArtistTest {


    @Test
    public void testInvalidArtistBio() {
        // Arrange
        Artist artist = new Artist("555ABCDEF!", "John Doe", "City|State|Country", "20-05-1995",
                "Short bio.",  // Invalid bio with fewer than ten words
                new ArrayList<>(Arrays.asList("singer", "composer")),
                new ArrayList<>(Arrays.asList("rock", "jazz")),
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        // Act
        boolean result = artist.addArtist();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testEmptyOccupations() {
        // Arrange
        Artist artist = new Artist("555ABCDE!!", "John Doe", "City|State|Country", "20-05-1995",
                "This is a valid artist bio with more than ten words.",
                new ArrayList<>(),  // Empty occupations list
                new ArrayList<>(Arrays.asList("rock", "jazz")),
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        // Act
        boolean result = artist.addArtist();

        // Assert
        assertFalse(result);
    }


    @Test
    public void testUpdateArtist() {
        //Testing Update Artist
        Artist artist = new Artist("555ABCDE$$", "John Doe", "City|State|Country", "20-05-2001",
                "This is a valid artist bio with more than ten words.",
                new ArrayList<>(Arrays.asList("Rockstar")),
                new ArrayList<>(Arrays.asList("rock")),
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        // Act
        boolean updateResult = artist.updateArtist(new ArrayList<>(Arrays.asList("singer", "composer")),
                new ArrayList<>(Arrays.asList("2020, Best Male Artist")));

        // Assert
        assertTrue(updateResult);
    }


    @Test
    public void testAddValidArtist() {
        Artist artist = new Artist("567ABCDE$$", "John Doe", "City|State|Country", "20-05-1995",
                "This is a valid artist bio with more than ten words.",
                new ArrayList<>(Arrays.asList("Singer")),
                new ArrayList<>(Arrays.asList("Rock")),
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        boolean result = artist.addArtist();

        assertFalse(result);
    }

    @Test
    public void testAddInvalidArtistID() {
        Artist artist = new Artist("InvalidID!@#", "John Doe", "City|State|Country", "20-05-1995",
                "This is a valid artist bio with more than ten words.",
                new ArrayList<>(Arrays.asList("Singer")),
                new ArrayList<>(Arrays.asList("Rock")),
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        boolean result = artist.addArtist();

        assertFalse(result);
    }

    @Test
    public void testAddInvalidArtistBio() {
        Artist artist = new Artist("555ABCDE$$", "John Doe", "City|State|Country", "20-05-1995",
                "Short bio.",  // Invalid bio with fewer than ten words
                new ArrayList<>(Arrays.asList("Singer")),
                new ArrayList<>(Arrays.asList("Rock")),
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        boolean result = artist.addArtist();

        assertFalse(result);
    }

    @Test
    public void testAddEmptyOccupations() {
        Artist artist = new Artist("555ABCDE$$", "John Doe", "City|State|Country", "20-05-1995",
                "This is a valid artist bio with more than ten words.",
                new ArrayList<>(),  // Empty occupations list
                new ArrayList<>(Arrays.asList("Rock")),
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        boolean result = artist.addArtist();

        assertFalse(result);
    }

    @Test
    public void testAddEmptyGenres() {
        Artist artist = new Artist("555ABCDE$$", "John Doe", "City|State|Country", "20-05-1995",
                "This is a valid artist bio with more than ten words.",
                new ArrayList<>(Arrays.asList("Singer")),
                new ArrayList<>(),  // Empty genres list
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        boolean result = artist.addArtist();

        assertFalse(result);
    }

    @Test
    public void testUpdateValidArtistOccupationBefore2000() {
        Artist artist = new Artist("555ABCDE$$", "John Doe", "City|State|Country", "20-05-1995",
                "This is a valid artist bio with more than ten words.",
                new ArrayList<>(Arrays.asList("Singer")),
                new ArrayList<>(Arrays.asList("Rock")),
                new ArrayList<>(Arrays.asList("1999, Best Old Artist")));

        boolean result = artist.updateArtist(new ArrayList<>(Arrays.asList("Composer")),
                new ArrayList<>(Arrays.asList("2022, Best Updated Artist")));

        assertFalse(result);
    }

    @Test
    public void testUpdateValidArtistOccupationAfter2000() {
        Artist artist = new Artist("555ABCDE$$", "John Doe", "City|State|Country", "20-05-2001",
                "This is a valid artist bio with more than ten words.",
                new ArrayList<>(Arrays.asList("Rockstar")),
                new ArrayList<>(Arrays.asList("Rock")),
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        boolean result = artist.updateArtist(new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("2020, Best Male Artist")));

        assertTrue(result);
    }

    @Test
    public void testUpdateInvalidArtistID() {
        Artist artist = new Artist("InvalidID!@#", "John Doe", "City|State|Country", "20-05-1995",
                "This is a valid artist bio with more than ten words.",
                new ArrayList<>(Arrays.asList("Singer")),
                new ArrayList<>(Arrays.asList("Rock")),
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        boolean result = artist.updateArtist(new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("2020, Best Male Artist")));

        assertFalse(result);
    }

    @Test
    public void testUpdateInvalidArtistBio() {
        Artist artist = new Artist("555ABCDE$$", "John Doe", "City|State|Country", "20-05-1995",
                "Short bio.",  // Invalid bio with fewer than ten words
                new ArrayList<>(Arrays.asList("Singer")),
                new ArrayList<>(Arrays.asList("Rock")),
                new ArrayList<>(Arrays.asList("2021, Best New Artist")));

        boolean result = artist.updateArtist(new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("2020, Best Male Artist")));

        assertFalse(result);
    }
}







    /*Testing my code to see for any issues

    // Test for isValidID method
    @Test
    void testIsValidIDValid() throws Exception {
        Artist artist = new Artist("555ABCDE!!", "Test Name", "123 Test St.", "12-12-2000", "This is a valid bio with more than 10 words.", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Method method = Artist.class.getDeclaredMethod("isValidID", String.class);
        method.setAccessible(true);
        assertTrue((Boolean) method.invoke(artist, "555ABCDE!!"));
    }

    @Test
    void testIsValidIDInvalid() throws Exception {
        Artist artist = new Artist("123ABCDE!!", "Test Name", "123 Test St.", "12-12-2000", "This is a valid bio with more than 10 words.", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Method method = Artist.class.getDeclaredMethod("isValidID", String.class);
        method.setAccessible(true);
        assertFalse((Boolean) method.invoke(artist, "123ABCDE!!"));
    }

    // Test for isValidAddress method
    @Test
    void testIsValidAddressValid() throws Exception {
        Artist artist = new Artist("555ABCDE!!", "Test Name", "123 Test St.", "12-12-2000", "This is a valid bio with more than 10 words.", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Method method = Artist.class.getDeclaredMethod("isValidAddress", String.class);
        method.setAccessible(true);
        assertTrue((Boolean) method.invoke(artist, "123 Test St."));
    }

    @Test
    void testIsValidAddressInvalid() throws Exception {
        Artist artist = new Artist("555ABCDE!!", "Test Name", "123", "12-12-2000", "This is a valid bio with more than 10 words.", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Method method = Artist.class.getDeclaredMethod("isValidAddress", String.class);
        method.setAccessible(true);
        assertFalse((Boolean) method.invoke(artist, "123"));
    }

    // Test for isValidBio method
    @Test
    void testIsValidBioValid() throws Exception {
        Artist artist = new Artist("555ABCDE!!", "Test Name", "123 Test St.", "12-12-2000", "This is a valid bio with more than 10 words.", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Method method = Artist.class.getDeclaredMethod("isValidBio", String.class);
        method.setAccessible(true);
        assertTrue((Boolean) method.invoke(artist, "This is a valid bio with more than 10 words."));
    }

    @Test
    void testIsValidBioInvalid() throws Exception {
        Artist artist = new Artist("555ABCDE!!", "Test Name", "123 Test St.", "12-12-2000", "Short bio.", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Method method = Artist.class.getDeclaredMethod("isValidBio", String.class);
        method.setAccessible(true);
        assertFalse((Boolean) method.invoke(artist, "Short bio."));
    }

    // Test for isValidOccupations method
    @Test
    void testIsValidOccupationsValid() throws Exception {
        Artist artist = new Artist("555ABCDE!!", "Test Name", "123 Test St.", "12-12-2000", "This is a valid bio with more than 10 words.", new ArrayList<>(Arrays.asList("Singer")), new ArrayList<>(), new ArrayList<>());
        Method method = Artist.class.getDeclaredMethod("isValidOccupations", ArrayList.class);
        method.setAccessible(true);
        assertTrue((Boolean) method.invoke(artist, new ArrayList<>(Arrays.asList("Singer"))));
    }


    @Test
    void testIsValidOccupationsInvalid() throws Exception {
        Artist artist = new Artist("555ABCDE!!", "Test Name", "123 Test St.", "12-12-2000", "This is a valid bio with more than 10 words.", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Method method = Artist.class.getDeclaredMethod("isValidOccupations", ArrayList.class);
        method.setAccessible(true);
        assertFalse((Boolean) method.invoke(artist, new ArrayList<>()));
    }
}

*/