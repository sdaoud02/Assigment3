module Assignment_3 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens mru.tsc.controller to javafx.graphics, javafx.fxml;
}
