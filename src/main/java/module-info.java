module com.example.moneymate {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    requires com.opencsv;
    requires com.rometools.rome;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.jfoenix;
    requires alphavantage.java;
    requires com.google.gson;

    opens com.example.moneymate2 to javafx.fxml;
    exports com.example.moneymate2;
}