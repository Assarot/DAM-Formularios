package pe.edu.upeu.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upeu.excel.ReadAndWriteService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/excel")
public class ReadAndWriteController {

    private final ReadAndWriteService readAndWriteService;

    @Autowired
    public ReadAndWriteController(ReadAndWriteService readAndWriteService) {
        this.readAndWriteService = readAndWriteService;
    }

    @PostMapping("/upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        try {

            File tempFile = File.createTempFile("temp", ".xlsx");
            file.transferTo(tempFile);

            readAndWriteService.leerYSubirExcel(tempFile.getAbsolutePath());

            tempFile.delete();

            return "Archivo procesado exitosamente.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al procesar el archivo.";
        }
    }
}

