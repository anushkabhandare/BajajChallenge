import java.io.*;
import java.nio.file.*;
import java.security.*;
import com.google.gson.*;

class Student 
{
    String first_name;
    String roll_number;
}

class Data 
{
    String institute_name;
    Student student;
}

public class Main 
{
    public static void main(String[] args) 
    {
        try 
        {
            // Step 1: Read the input.json file
            String content = new String(Files.readAllBytes(Paths.get("input.json")));
            Gson gson = new Gson();
            Data data = gson.fromJson(content, Data.class);

            // Step 2: Extract first_name and roll_number (convert to lowercase)
            String firstName = data.student.first_name.toLowerCase();
            String rollNumber = data.student.roll_number.toLowerCase();

            // Step 3: Concatenate values and compute MD5 hash
            String concatenatedString = firstName + rollNumber;
            String hash = generateMD5(concatenatedString);

            // Step 4: Write the hash to output.txt
            Files.write(Paths.get("output.txt"), hash.getBytes());

            System.out.println("MD5 Hash Generated: " + hash);

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    // Method to generate MD5 hash
    public static String generateMD5(String input) throws NoSuchAlgorithmException 
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) 
        {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
