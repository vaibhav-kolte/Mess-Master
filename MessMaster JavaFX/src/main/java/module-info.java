module org.mycode.messmaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires org.json;
    requires java.sql;


    opens org.mycode.messmaster to javafx.fxml;
    exports org.mycode.messmaster;
}