package org.mycode.messmaster;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mycode.messmaster.entities.Department;
import org.mycode.messmaster.entities.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelloController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private DatePicker enrollmentDateField;

    @FXML
    private TextField addressField;

    @FXML
    private ComboBox<String> departmentIdField;

    @FXML
    private ComboBox<String> classNameField;


    @FXML
    public void initialize() {
        classNameField.getItems().addAll("Option 1", "Option 2", "Option 3");
        enrollmentDateField.setValue(LocalDate.now());

        enrollmentDateField.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // Disable all future dates
                        if (item.isAfter(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Optional: Add a custom style for disabled dates
                        }
                    }
                };
            }
        });

        departmentIdField.getItems().addAll(getDepartmentNames());
        departmentIdField.getSelectionModel().selectFirst();

        departmentIdField.valueProperty().addListener((observable, oldValue, newValue) -> {
            // This method is called whenever the selected item changes
            System.out.println("Selected item: " + newValue);
        });

    }
    @FXML
    private void handleSaveAction() {
        // Retrieve data from fields
        String name = nameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        LocalDate enrollmentDate = enrollmentDateField.getValue();
        String address = addressField.getText();
        String className = classNameField.getItems().getFirst();
        int departmentId = 1;//Integer.parseInt(departmentIdField.getText());

        // Convert LocalDate to Date if needed
        Date date = java.sql.Date.valueOf(enrollmentDate);

        // Create a new Student object or save data to the database
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setPhoneNumber(phoneNumber);
        student.setEnrollmentDate(date);
        student.setAddress(address);
        student.setClassName(className);
        student.setDepartment(new Department(departmentId, null));

        // Call service to save student to the database
//         studentService.saveStudent(student);

        // Close the form
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelAction() {
        // Close the form without saving
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();

    }

    private List<String> getDepartmentNames() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8080/department/getAll")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseBody = response.body().string();
                System.out.println(responseBody);

                // Parse JSON response to a JSONArray
                JSONArray jsonArray = new JSONArray(responseBody);
                List<Department> departments = new ArrayList<>();
                List<String> departmentNames = new ArrayList<>();
                departmentNames.add("Select Department");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Department department = new Department();
                    department.setDepartmentId(jsonObject.getInt("departmentId"));
                    department.setDepartmentName(jsonObject.getString("departmentName"));
                    departments.add(department);
                    departmentNames.add(jsonObject.getString("departmentName"));
                }

                // Print the list of Department objects
                for (Department department : departments) {
                    System.out.println(department);
                }
                return departmentNames;
            } else {
                System.out.println("Request not successful");
            }
        } catch (IOException e) {
            System.out.println("Request not successful, Exception: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}