package com.example.albumsong;

import com.example.albumsong.data.dbConnection;
import com.example.albumsong.models.Song;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        stage.setTitle("Hello!");
//        stage.show();
        dbConnection db = new dbConnection();
        db.getSongs();

        // Get
        ArrayList<Song> listSongs = db.getSongs();
        System.out.println("Size :"+ listSongs.size());

        // insert
        db.insertSongs(new Song(2,"https://nld.mediacdn.vn/291774122806476800/2021/12/31/hinh-ngang-vu-cat-tuong-16409365734341545314280.jpg"
                ,"Bài hát","Việt Nam","2019/02/12","Vũ Cát Tường","Pop"));
        // Delete
        //db.deleteSongs(43);

        // insert
//        db.updateSong(new Song(1,"https://nld.mediacdn.vn/291774122806476800/2021/12/31/hinh-ngang-vu-cat-tuong-16409365734341545314280.jpg"
//                ,"Bài hát","Việt Nam","2019/02/12","Vũ Cát Tường","Popop"));

;
        VBox vbox = new VBox();
        HBox hBoxStudent = new HBox();
        hBoxStudent.setSpacing(50);

//        lable(vbox);
        getSongs(vbox,db);
        Scene scenes = new Scene(vbox, 600, 600);
        stage.setScene(scenes);

        stage.show();
    }
    // Add songs
    private void addSongs (VBox vbox,dbConnection db) {
        HBox hBoxStudent = new HBox();

        hBoxStudent.setSpacing(23);

        Button btnAdd = new Button("Save");

        Label lName = new Label("Name: ");

        TextField txName = new TextField();
        txName.setMinWidth(100);


        Label lbImg = new Label("Image: ");
        lbImg.setMinWidth(20);

        TextField txImg = new TextField();
        txImg.setMinWidth(100);

        Label lbCountry = new Label("Country: ");
        lbCountry.setMinWidth(20);

        TextField txCountry = new TextField();
        txCountry.setMinWidth(100);

        Label lbDate = new Label("Date: ");
        TextField txDate = new TextField();

        Label lbSinger = new Label("Singer: ");
        TextField txSinger = new TextField();

        Label lbCategory = new Label("Category: ");
        TextField txCategory = new TextField();

        hBoxStudent.getChildren().addAll(lName, txName, lbImg, txImg, lbCountry,txCountry,
                lbDate,txDate,lbSinger,txSinger, lbCategory,txCategory, btnAdd);

        vbox.getChildren().add(hBoxStudent);


        btnAdd.setOnAction(e->{

            String bImg = txImg.getText();
            String bName = txName.getText();
            String bCountry = txCountry.getText();
            String bDate = txDate.getText();
            String bSinger = txSinger.getText();
            String bCategory = txCategory.getText();

            if(bImg == ""|| bName==""|| bCountry ==""|| bDate == ""||bSinger ==""||bCategory =="" ){
                AddError();
            }
            else {
                db.insertSongs(new Song(bImg,bName,bCountry,bDate,bSinger,bCategory));
                vbox.getChildren().removeAll(vbox.getChildren());
                lable(vbox);
                getSongs(vbox,db);
            }


        });

    }

    // Get data
    private void getSongs (VBox vbox,dbConnection db) {
        lable(vbox);
        ArrayList<Song> listSongs = db.getSongs();

        System.out.println("Size: "+listSongs.size());

        for (int i = 0; i < listSongs.size(); i++) {
            HBox hBoxStudent = new HBox();
            hBoxStudent.setSpacing(50);

            Label lbId = new Label("" + listSongs.get(i).getId());
            lbId.setMinWidth(50);



            Image lbImg = new Image(listSongs.get(i).getImg());
            ImageView imageView = new ImageView();
            imageView.setImage(lbImg);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);

            Label lbName = new Label("" + listSongs.get(i).getName());
            lbName.setMinWidth(200);

            Label lbCountry = new Label("" + listSongs.get(i).getCountry());
            lbCountry.setMinWidth(100);

            Label lbDate = new Label("" + listSongs.get(i).getDate());
            lbDate.setMinWidth(100);

            Label lbSinger = new Label("" + listSongs.get(i).getSinger());
            lbSinger.setMinWidth(100);

            Label lbCategory = new Label("" + listSongs.get(i).getCategory());
            lbCategory.setMinWidth(200);

            Button btnDelete = new Button("Delete");
            btnDelete.setMinWidth(50);

            Button btnUpdate = new Button("Update");
            btnUpdate.setMinWidth(50);
            Group root = new Group(imageView);


            hBoxStudent.getChildren().addAll(lbId,root,lbName, lbCountry,lbDate, lbSinger,lbCategory,btnDelete,btnUpdate);
            vbox.getChildren().add(hBoxStudent);



            btnDelete.setId(String.valueOf(listSongs.get(i).getId()));

            btnDelete.setOnAction((ActionEvent e)->{
                deleteSongs(Integer.parseInt(btnDelete.getId()), vbox, db);

            });
            int bUpdate = i;
            btnUpdate.setId(String.valueOf(listSongs.get(i).getId()));

            btnUpdate.setOnAction((ActionEvent e)->{
                System.out.println("Update finished");
                updateSong(vbox,db,listSongs.get(bUpdate).getId(),listSongs.get(bUpdate).getImg(),
                listSongs.get(bUpdate).getName(),listSongs.get(bUpdate).getCountry(),listSongs.get(bUpdate).getDate(),
                listSongs.get(bUpdate).getSinger(),listSongs.get(bUpdate).getCategory());
            });

        }
        addButton(vbox,db);

    }
    public void deleteSongs(int id,VBox vbox,dbConnection db){
            db.deleteSongs(id);
            vbox.getChildren().removeAll(vbox.getChildren());
            getSongs(vbox,db);
    }

    public void updateSong(VBox vbox,dbConnection db ,int id, String img, String name, String country, String date, String singer, String category ){
        HBox hBoxStudent = new HBox();

        hBoxStudent.setSpacing(23);

        Button btnSave = new Button("Save");

        Label lName = new Label("Name: ");
        TextField txName = new TextField();
        txName.setMinWidth(100);
        txName.setText(name);

        Label lbImg = new Label("Image: ");
        lbImg.setMinWidth(20);
        TextField txImg = new TextField();
        txImg.setMinWidth(100);
        txImg.setText(img);


        Label lbCountry = new Label("Country: ");
        lbCountry.setMinWidth(20);
        TextField txCountry = new TextField();
        txCountry.setMinWidth(100);
        txCountry.setText(country);


        Label lbDate = new Label("Date: ");
        TextField txDate = new TextField();
        txDate.setText(date);

        Label lbSinger = new Label("Singer: ");
        TextField txSinger = new TextField();
        txSinger.setText(singer);

        Label lbCategory = new Label("Category: ");
        TextField txCategory = new TextField();
        txCategory.setText(category);

        hBoxStudent.getChildren().addAll(lName, txName, lbImg, txImg, lbCountry,txCountry,
                lbDate,txDate,lbSinger,txSinger, lbCategory,txCategory, btnSave);

        vbox.getChildren().add(hBoxStudent);


        btnSave.setOnAction(e->{

            String bImg = txImg.getText();
            String bName = txName.getText();
            String bCountry = txCountry.getText();
            String bDate = txDate.getText();
            String bSinger = txSinger.getText();
            String bCategory = txCategory.getText();

            db.updateSong(new Song(id,bImg,bName,bCountry,bDate,bSinger,bCategory));
            vbox.getChildren().removeAll(vbox.getChildren());

            getSongs(vbox,db);

        });
    }

    public void addButton(VBox vbox, dbConnection db) {

        HBox hBoxStudent = new HBox();

        Button btnAdd = new Button("Add Song");
        hBoxStudent.getChildren().addAll(btnAdd);

        vbox.getChildren().add(hBoxStudent);
        btnAdd.setOnAction((ActionEvent e)->{

            addSongs(vbox, db);

        });
    }
    public  void lable(VBox vbox){
        HBox hBoxStudent = new HBox();
        hBoxStudent.setSpacing(50);
        Label lbId = new Label("ID");
        lbId.setMinWidth(50);

        Label lbImage = new Label("Image");
        lbImage.setMinWidth(200);

        Label lbName = new Label("Name" );
        lbName.setMinWidth(200);

        Label lbCountrys = new Label("Country" );
        lbCountrys.setMinWidth(100);

        Label lbDates = new Label("Date" );
        lbDates.setMinWidth(100);

        Label lbSingers = new Label("Singer: ");
        lbSingers.setMinWidth(100);

        Label lbCategorys = new Label("Category");
        lbCategorys.setMinWidth(200);

        Label lbDelete= new Label("Delete" );
        lbDelete.setMinWidth(50);

        Label lbUpdate= new Label("Update" );
        lbUpdate.setMinWidth(50);


        hBoxStudent.getChildren().addAll(lbId,lbImage, lbName,lbCountrys,lbDates, lbSingers,lbCategorys, lbDelete,lbUpdate);
        vbox.getChildren().add(hBoxStudent);
    }
    private void AddError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error!");
        alert.setContentText("Not be empty!!!");
        alert.show();
    }




    public static void main(String[] args) {
        launch();
    }
}