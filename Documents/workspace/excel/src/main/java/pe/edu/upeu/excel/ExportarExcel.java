package pe.edu.upeu.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


@Service
public class ExportarExcel {
	
	private final DataSource dataSource;

    @Autowired
    public ExportarExcel(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void exportarDatos(String nombreTabla, String nombreArchivo) {
        String query = "SELECT * FROM " + nombreTabla;

        try (Connection conexion = dataSource.getConnection();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet(nombreTabla);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Nombre de la tabla en la primera fila
            Row tableNameRow = sheet.createRow(0);
            Cell tableNameCell = tableNameRow.createCell(0);
            tableNameCell.setCellValue(nombreTabla);

            // Encabezados de columna en la segunda fila
            Row headerRow = sheet.createRow(1);
            for (int i = 1; i <= columnCount; i++) {
                Cell headerCell = headerRow.createCell(i - 1);
                headerCell.setCellValue(metaData.getColumnName(i));
            }

            // Datos de la tabla en filas subsecuentes
            int rowNum = 2;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(rs.getString(i));
                }
            }

            // Guardar el archivo
            try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo)) {
                workbook.write(fileOut);
            }
            System.out.println("Archivo Excel generado correctamente en: " + nombreArchivo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
