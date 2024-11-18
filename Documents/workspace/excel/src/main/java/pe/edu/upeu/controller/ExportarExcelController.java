package pe.edu.upeu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upeu.excel.ExportarExcel;

@RestController
@RequestMapping("api/exportar")
@CrossOrigin(origins = "http://localhost:4200")
public class ExportarExcelController {
	private final ExportarExcel exportarExcel;

    @Autowired
    public ExportarExcelController(ExportarExcel exportarExcel) {
        this.exportarExcel = exportarExcel;
    }

    @GetMapping
    public String exportarDatos(@RequestParam String nombreTabla, @RequestParam String nombreArchivo) {
        exportarExcel.exportarDatos(nombreTabla, nombreArchivo);
        return "Archivo Excel generado correctamente en: " + nombreArchivo;
    }
}
