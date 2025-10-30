module com.una.githubclient {
    requires java.net.http;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    // Jackson (desde 2.14+ tiene módulos explícitos)
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    // UI (FXML Controllers)
    opens com.una.githubclient.ui to javafx.fxml;
    exports com.una.githubclient.ui;

    // Permitir reflexión para deserialización JSON
    opens com.una.githubclient.domain to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.una.githubclient.domain;
}
