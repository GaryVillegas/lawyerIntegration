package com.integration.lawyer.service;

import com.integration.lawyer.model.Documento;
import com.integration.lawyer.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class DocumentoService {

    private final String uploadDir = "uploads/";
    
    @Autowired
    private DocumentoRepository documentoRepository;
    
    public Documento guardarDocumento(MultipartFile archivo) throws IOException {
        // Crear directorio si no existe
        Path directorioPath = Paths.get(uploadDir);
        if (!Files.exists(directorioPath)) {
            Files.createDirectories(directorioPath);
        }
        
        // Generar nombre único
        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
        Path rutaCompleta = directorioPath.resolve(nombreArchivo);
        
        // Guardar archivo
        Files.copy(archivo.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);
        
        // Crear y guardar documento
        Documento documento = new Documento();
        documento.setNombre(archivo.getOriginalFilename());
        documento.setTipo(archivo.getContentType());
        documento.setRuta(rutaCompleta.toString());
        documento.setTamaño(archivo.getSize());
        
        return documentoRepository.save(documento);
    }
    
    public List<Documento> obtenerTodos() {
        return documentoRepository.findAll();
    }
    
    public Documento obtenerPorId(Integer id) {
        return documentoRepository.findById(id).orElse(null);
    }

    public void eliminarDocumento(Integer id){
        Documento documento = documentoRepository.findById(id).orElse(null);
        if (documento != null) {
            // Eliminar archivo del sistema
            Path rutaArchivo = Paths.get(documento.getRuta());
            try {
                Files.deleteIfExists(rutaArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Eliminar registro de la base de datos
            documentoRepository.delete(documento);
        }
    }
}