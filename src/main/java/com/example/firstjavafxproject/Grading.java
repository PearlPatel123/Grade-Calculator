package com.example.firstjavafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.*;


public class Grading {

    @FXML
    private TextField studentname;
    @FXML
    private TextField subject1;
    @FXML
    private TextField subject2;
    @FXML
    private TextField subject3;
    @FXML
    private TextField subject4;
    @FXML
    private TextField subject5;
    @FXML
    private Text errormessage;

    @FXML
    private Text FinalGPA;
    @FXML
    private Text Avg ;


    @FXML
    public void showpassingstatus(){


        String SQL_SELECT = "Select passingstatus from students where studentname=?";
          String status;
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/grades", "root", null);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
            preparedStatement.setString(parameterIndex:1,studentname.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                status = resultSet.getString("passingstatus");


            }
            System.out.println("The passing status of student is"+status);


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void calculateAvg(){
        int s1=Integer.parseInt(subject1.getText());
        int s2=Integer.parseInt(subject2.getText());
        int s3=Integer.parseInt(subject3.getText());
        int s4=Integer.parseInt(subject4.getText());
        int s5=Integer.parseInt(subject5.getText());

        if(s1+s2+s3+s4+s5>500){
            errormessage.setVisible(true);
            FinalGPA.setVisible(false);
            Avg.setVisible(false);
        }

        double Average= (double) (s1 + s2 + s3 + s4 + s5) /5;

        Avg.setText("Your Average Score is "+Average);



    }
    @FXML
    public void calculateGPA(){

        int s1=Integer.parseInt(subject1.getText());
        int s2=Integer.parseInt(subject2.getText());
        int s3=Integer.parseInt(subject3.getText());
        int s4=Integer.parseInt(subject4.getText());
        int s5=Integer.parseInt(subject5.getText());
        int GPA=  0;
        if(s1+s2+s3+s4+s5>500){
            errormessage.setVisible(true);
            FinalGPA.setVisible(false);
            Avg.setVisible(false);
        }
        else if(s1+s2+s3+s4+s5>=400){
            GPA=(GPA)+4;
        }else if(s1+s2+s3+s4+s5>=300) {
            GPA = (GPA) + 3;
        }else if(s1+s2+s3+s4+s5>=200) {
            GPA = (GPA) + 2;
        }else if(s1+s2+s3+s4+s5>=100) {
            GPA = (GPA) + 1;
        }else {
             GPA=(GPA) + 0;
            }
        FinalGPA.setText("Your GPA Score is "+GPA);

    }

}
