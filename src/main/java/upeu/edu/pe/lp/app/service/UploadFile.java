package upeu.edu.pe.lp.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class UploadFile {

    // Carpeta externa donde Docker almacenará las imágenes
    private static final String FOLDER = "/app/images/";
    private static final String IMG_DEFAULT = "default.jpg";

    public String upload(MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            // Generar nombre único para evitar colisiones
            String nombreArchivo = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
            Path path = Paths.get(FOLDER);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Files.copy(multipartFile.getInputStream(), path.resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
            return nombreArchivo;
        }

        return IMG_DEFAULT;
    }

    public void delete(String nombreArchivo) {
        try {
            Path path = Paths.get(FOLDER + nombreArchivo);
            if (Files.exists(path)) {
                Files.delete(path);
            }
        } catch (IOException e) {
            // Loguear error si se desea
            System.err.println("Error al eliminar archivo: " + e.getMessage());
        }
    }
}
