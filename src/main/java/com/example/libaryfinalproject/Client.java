package com.example.libaryfinalproject;

import com.example.libaryfinalproject.model.Request;
import com.example.libaryfinalproject.model.Book; // Ensure this import matches the location of your Book class
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class Client {

    static Gson gson = new Gson();

    public static void saveToServer(Request request) {
        try (Socket myServer = new Socket("localhost", 12346);
             PrintWriter writer = new PrintWriter(myServer.getOutputStream(), true)) {
            String requestJson = gson.toJson(request);
            writer.println(requestJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean sendRequestAndGetResponse2(Request request) {
        // Initialize connection to the server...
        try (Socket socket = new Socket("localhost", 12346);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Convert request to JSON and send
            Gson gson = new Gson();
            String jsonRequest = gson.toJson(request);
            out.println(jsonRequest);

            // Await response from the server
            String jsonResponse = in.readLine();

            // Assuming the server responds with a simple JSON boolean for remove operations
            Boolean response = gson.fromJson(jsonResponse, Boolean.class);
            return response != null && response;

        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false on exception
        }
    }
    public static boolean sendRequestAndGetResponse3(Request request) {
        // Serialization of request to JSON
        Gson gson = new Gson();
        String requestJson = gson.toJson(request);

        try (Socket socket = new Socket("localhost", 12346);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.write(requestJson);
            out.newLine();
            out.flush();

            // Await server response
            String response = in.readLine();
            // Assuming server sends back a simple boolean response as JSON
            return Boolean.parseBoolean(response);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean sendRequestAndGetResponse4(Request request) {
        Gson gson = new Gson();
        try (Socket socket = new Socket("localhost", 12346);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Serialize the request to JSON and send it
            String jsonRequest = gson.toJson(request);
            writer.write(jsonRequest);
            writer.newLine(); // Make sure to end the line to flush the buffer on the server side
            writer.flush();

            // Await the response from the server
            String jsonResponse = reader.readLine(); // Assumes the response is a single line (JSON)

            // Deserialize the response from JSON back to a boolean
            Boolean response = gson.fromJson(jsonResponse, Boolean.class);
            return response != null && response;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of any exception
        }
    }

    public static List<Book> sendRequestAndGetResponse(Request request) {
        try (Socket socket = new Socket("localhost", 12346);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send the request
            out.println(gson.toJson(request));

            // Wait for and read the response
            String response = in.readLine();
            if (response == null) {
                return Collections.emptyList(); // Return an empty list if response is null
            }

            // Deserialize the response into a list of books
            Type listType = new TypeToken<List<Book>>() {}.getType();
            List<Book> books = gson.fromJson(response, listType);
            if (books == null) {
                return Collections.emptyList(); // Return an empty list if deserialization results in null
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list in case of any exception
        }
    }
}
