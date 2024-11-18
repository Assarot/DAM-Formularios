package pe.edu.upeu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upeu.excel.ReadAndWriteService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/excel")
@CrossOrigin(origins = "http://localhost:4200")
public class ReadAndWriteController {

    private final ReadAndWriteService readAndWriteService;

    @Autowired
    public ReadAndWriteController(ReadAndWriteService readAndWriteService) {
        this.readAndWriteService = readAndWriteService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            File tempFile = File.createTempFile("temp", ".xlsx");
            file.transferTo(tempFile);

            readAndWriteService.leerYSubirExcel(tempFile.getAbsolutePath());

            tempFile.delete();

            // Devolver código 200 (OK) con el mensaje de éxito
            return ResponseEntity.ok("Archivo procesado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            // Devolver código 500 (Error Interno del Servidor) con el mensaje de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo.");
        }
    }

}

