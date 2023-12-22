package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class ReportFormController {

    public JFXButton btnCustomerReport;
    public JFXButton btnOrrderReport;



    private ReportFormController(String form) throws JRException, SQLException {

    }



    public void btnCustomerReportOnAction(ActionEvent actionEvent) throws IOException, JRException, SQLException {


        setForm("/report/CustomerReport.jrxml");

    }

    private void setForm(String form) throws JRException, SQLException {
        InputStream resourceAsStream =
                getClass().getResourceAsStream(form);
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport compileReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        compileReport, //compiled report
                        null,
                        DbConnection.getInstance().getConnection() //database connection
                );

        JasperViewer.viewReport(jasperPrint, false);
    }

    public void btnOrderreportOnAction(ActionEvent actionEvent) throws IOException, JRException, SQLException {
        setForm("/report/OrderRepor1.jrxml");

    }
}
