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
    requires org.testng;

    opens com.example.moneymate2 to javafx.fxml;
    exports com.example.moneymate2;
    exports TestsUnitaires;
    opens TestsUnitaires to javafx.fxml;
}