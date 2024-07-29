package org.mycode.messmaster;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mycode.messmaster.entities.Department;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class MainController {
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
        enrollmentDateField.setValue(LocalDate.now());

        enrollmentDateField.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // Disable all future dates
                        if (item.isAfter(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #817474;"); // Optional: Add a custom style for disabled dates
                        }
                    }
                };
            }
        });

        List<Department> departments = getDepartmentNames();
        departmentIdField.getItems().addAll(getDepartmentName(departments));
        departmentIdField.getSelectionModel().selectFirst();

        departmentIdField.valueProperty().addListener((observable, oldValue, newValue) -> {
            // This method is called whenever the selected item changes
            System.out.println("Selected item: " + newValue);
            departments.forEach(department -> {
                if (department.getDepartmentName().equalsIgnoreCase(newValue)) {
                    List<String> courses = new ArrayList<>();
                    courses.add("Select Course");
                    courses.addAll(department.getCourses());
                    classNameField.getItems().clear();
                    classNameField.getItems().addAll(courses);
                }
            });
            classNameField.getSelectionModel().selectFirst();
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
        String department = departmentIdField.getValue();
        if(department.equalsIgnoreCase("Select Department")) return;
        String className = classNameField.getValue();

        if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()
                || String.valueOf(enrollmentDate).isEmpty()
                || address.isEmpty() || className.isEmpty() || department.isEmpty()
                || department.equalsIgnoreCase("Select Department")
                || className.equalsIgnoreCase("Select Course")
        ) {
            return;
        }

        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Address: " + address);
        System.out.println("Department: " + department);
        System.out.println("Class: " + className);


        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelAction() {
        // Close the form without saving
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();

    }

    private List<String> getDepartmentName(List<Department> departments) {
        List<String> departmentNames = new ArrayList<>();
        departmentNames.add("Select Department");
        departments.forEach(department -> {
            departmentNames.add(department.getDepartmentName());
        });
        return departmentNames;
    }

    private List<Department> getDepartmentNames() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8080/department/getAll")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseBody = response.body().string();
                System.out.println(responseBody);

                return getDepartments(responseBody);
            } else {
                System.out.println("Request not successful");
            }
        } catch (IOException e) {
            System.out.println("Request not successful, Exception: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    private static @NotNull List<Department> getDepartments(String responseBody) {
        JSONArray jsonArray = new JSONArray(responseBody);

        List<Department> departments = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            Set<String> courses = new HashSet<>();

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Department department = new Department();
            department.setDepartmentId(jsonObject.getInt("departmentId"));
            department.setDepartmentName(jsonObject.getString("departmentName"));
            JSONArray classObject = jsonObject.getJSONArray("courses");
            for (int j = 0; j < classObject.length(); j++) {
                String course = classObject.getString(j);
                courses.add(course);
            }
            department.setCourses(courses);
            departments.add(department);
        }
        return departments;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}