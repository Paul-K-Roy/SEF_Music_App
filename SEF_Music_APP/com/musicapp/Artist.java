package com.musicapp;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Artist {

    private static final String FILE_PATH = "Artists.txt";

    private String ID;
    private String Name;
    private String Address;
    private String Birthdate;
    private String Bio;
    private ArrayList<String> Occupations;
    private ArrayList<String> Genres;
    private ArrayList<String> Awards;

    public Artist(String id, String name, String address, String birthdate, String bio,
                  ArrayList<String> occupations, ArrayList<String> genres, ArrayList<String> awards) {
        this.ID = id;
        this.Name = name;
        this.Address = address;
        this.Birthdate = birthdate;
        this.Bio = bio;
        this.Occupations = occupations;
        this.Genres = genres;
        this.Awards = awards;
    }

    // Validator for ID format
    protected boolean isValidID(String id) {
        boolean valid = Pattern.matches("^[5-9]{3}[A-Z]{5}[^A-Za-z0-9]{2}$", id);
        if(!valid) {
            System.out.println("Invalid ID provided: " + id);
        }
        return valid;
    }




    // Validator for Address format
    protected boolean isValidAddress(String address) {
        return address.length() >= 10 && address.length() <= 100;
    }

    // Validator for Birthdate in format DD-MM-YYYY
    protected boolean isValidBirthDate(String date) {
        return Pattern.matches("^[0-9]{2}-[0-9]{2}-[0-9]{4}$", date);
    }


    // Validator for Bio to ensure word count between 10 to 30
    protected boolean isValidBio(String bio) {
        int wordCount = bio.split("\\s+").length;
        return wordCount >= 10 && wordCount <= 30;
    }

    // Validator for Occupations to ensure at least 1 and at most 5
    protected boolean isValidOccupations(ArrayList<String> occupations) {
        return occupations.size() >= 1 && occupations.size() <= 5;
    }


    // Validator for Genres
    protected boolean isValidGenres(ArrayList<String> genres) {
        return !genres.isEmpty();
    }

    // Validator for Awards
    protected boolean isValidAwards(ArrayList<String> awards) {
        return !awards.isEmpty();
    }

    protected boolean isValidArtist() throws IllegalArgumentException {
        if (!isValidID(ID)) throw new IllegalArgumentException("Invalid ID format.");
        if (!isValidAddress(Address)) throw new IllegalArgumentException("Invalid address format.");
        if (!isValidBirthDate(Birthdate)) throw new IllegalArgumentException("Invalid birthdate format.");
        if (!isValidBio(Bio)) throw new IllegalArgumentException("Bio must have between 10 to 30 words.");
        if (!isValidOccupations(Occupations)) throw new IllegalArgumentException("Invalid number of occupations.");
        if (!isValidGenres(Genres)) throw new IllegalArgumentException("Invalid genres.");
        if (!isValidAwards(Awards)) throw new IllegalArgumentException("Invalid awards format.");
        return true;
    }

    protected boolean doesArtistExist() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(ID + ",")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return ID + "," + Name + "," + Address + "," + Birthdate + "," + String.join(";", Occupations) + "," +
                String.join(";", Genres) + "," + String.join(";", Awards);
    }

    public boolean addArtist() {
        System.out.println("Attempting to add artist with ID: " + ID);
        if (!isValidID(ID)) {
            System.out.println("Failed ID validation");
            return false;
        }
        if (!isValidAddress(Address)) {
            System.out.println("Failed Address validation");
            return false;
        }
        if (!isValidBirthDate(Birthdate)) {
            System.out.println("Failed Birthdate validation");
            return false;
        }
        if (!isValidBio(Bio)) {
            System.out.println("Failed Bio validation");
            return false;
        }
        if (!isValidOccupations(Occupations)) {
            System.out.println("Failed Occupations validation");
            return false;
        }
        if (!isValidGenres(Genres)) {
            System.out.println("Failed Genres validation");
            return false;
        }
        if (!isValidAwards(Awards)) {
            System.out.println("Failed Awards validation");
            return false;
        }

        // Debugging line to indicate attempt to write to the file
        System.out.println("Attempting to write artist to file...");


        try {
            if (isValidArtist() && !doesArtistExist()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                    writer.write(this.toString());
                    writer.newLine();

                    // Debugging line to indicate successful write operation
                    System.out.println("Successfully wrote artist to file.");


                } catch (IOException e) {


                    // Debugging line to indicate failure in writing to file
                    System.out.println("Failed to write artist to file.");


                    e.printStackTrace();
                    return false;
                }
                System.out.println("Successfully added artist");
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean updateArtist(ArrayList<String> newOccupations, ArrayList<String> newAwards) {
        System.out.println("Updating artist with ID: " + ID);
        System.out.println("Current Occupations: " + Occupations.toString());
        if(!isValidID(ID) || !isValidAddress(Address) || !isValidBirthDate(Birthdate) ||
                !isValidBio(Bio) || !isValidGenres(Genres) || !isValidAwards(newAwards)) {
            return false;
        }

        int birthYear = Integer.parseInt(Birthdate.split("-")[2]);
        if (birthYear < 2000 && !newOccupations.equals(Occupations)) {
            return false;
        }

        for (String award : Awards) {
            int awardYear = Integer.parseInt(award.split(", ")[0]);
            if (awardYear < 2000 && !newAwards.contains(award)) {
                return false;
            }
        }

        this.Occupations = newOccupations;

        // Debugging line to indicate an attempt to update the artist
        System.out.println("Attempting to update artist in file...");

        this.Awards = newAwards;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(ID + ",")) {
                    line = ID + "," + Name + "," + Address + "," + Birthdate + "," + String.join(";", Occupations) + "," +
                            String.join(";", Genres) + "," + String.join(";", Awards);
                }
                output.append(line).append("\n");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

                // Debugging line to indicate what's about to be written to the file
                System.out.println("Writing updated information to file: " + output.toString());

                writer.write(output.toString());

                // Debugging line to indicate successful write operation
                System.out.println("Successfully wrote updated information to file.");

            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}

