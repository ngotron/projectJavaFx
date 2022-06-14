package com.example.albumsong.data;

import com.example.albumsong.models.Song;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class dbConnection {
    private Connection connect;

    public dbConnection() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/albumsong", "root", "");
            System.out.println("connect succeed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Get data
    public ArrayList<Song> getSongs() {
        String sql = "SELECT * FROM song";

        ArrayList<Song> listSongs = new ArrayList<>();
        try {
            ResultSet rs = connect.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                System.out.println("id : " + rs.getInt("id"));
                System.out.println("img : " + rs.getString("img"));
                System.out.println("name : " + rs.getString("name"));
                System.out.println("country : " + rs.getString("country"));
                System.out.println("date : " + rs.getString("date"));
                System.out.println("singer :" + rs.getString("singer"));
                System.out.println("category :" + rs.getString("category"));
                Song song = new Song(
                        rs.getInt("id"),
                        rs.getString("img"),
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getString("date"),
                        rs.getString("singer"),
                        rs.getString("category")
                );
                listSongs.add(song);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listSongs;
    }

    //Insert data
    public void insertSongs(Song song){
        String sql =
            "INSERT INTO song(img,name, country, date, singer, category) VALUES (" +
             "'"+song.getImg()+"'" +
             ",'"+song.getName()+"'" +
             ",'"+song.getCountry()+"'" +
             " ,'"+song.getDate()+"'" +
             ",'"+song.getSinger()+"'" +
             ",'"+song.getCategory()+"')";
        System.out.println(sql);
        try {
            connect.prepareStatement(sql).executeUpdate();
            System.out.println("Insert a new student successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Detele data
    public void deleteSongs(int id){
        String sql = "DELETE FROM song WHERE id = "+ id;

        System.out.println(sql);

        try {
            connect.prepareStatement(sql).executeUpdate();
            System.out.println("Delete a student successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Update
    public void updateSong(Song song){
        String sql = "UPDATE song SET img ='"+song.getImg()
        +"', name = '"+song.getName()+"', country = '"+song.getCountry()
        +"' ,date = '"+song.getDate()+"', singer = '"+song.getSinger()
        + "', category = '"+song.getCategory() + "' WHERE id = "+ song.getId();
        System.out.println(sql);
        try {
            connect.prepareStatement(sql).executeUpdate();
            System.out.println("Update a new student successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
