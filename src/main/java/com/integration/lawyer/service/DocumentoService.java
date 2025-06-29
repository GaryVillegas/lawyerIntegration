package com.integration.lawyer.service;

import com.integration.lawyer.model.Documento;
import com.integration.lawyer.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class DocumentoService {

    private final String uploadDir = "uploads/";

    @Autowired
    private DocumentoRepository documentoRepository;

    /**
     * Guarda un documento subido como archivo físico.
     */
    public Documento guardarDocumento(MultipartFile archivo) throws IOException {
        Path directorioPath = Paths.get(uploadDir);
        if (!Files.exists(directorioPath)) {
            Files.createDirectories(directorioPath);
        }

        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
        Path rutaCompleta = directorioPath.resolve(nombreArchivo);

        Files.copy(archivo.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

        Documento documento = new Documento();
        documento.setNombre(archivo.getOriginalFilename());
        documento.setTipo(archivo.getContentType());
        documento.setRuta(rutaCompleta.toString());
        documento.setTamaño(archivo.getSize());

        return documentoRepository.save(documento);
    }

    /**
     * Guarda un documento desde un JSON enviado (sin archivo físico).
     */
    public Documento guardarDesdeJson(Documento documento) {
        return documentoRepository.save(documento);
    }

    /**
     * Devuelve todos los documentos.
     */
    public List<Documento> obtenerTodos() {
        return documentoRepository.findAll();
    }

    /**
     * Busca un documento por ID.
     */
    public Documento obtenerPorId(Integer id) {
        return documentoRepository.findById(id).orElse(null);
    }

    /**
     * Elimina el documento físico y su entrada en la base de datos.
     */
    public void eliminarDocumento(Integer id) {
        Documento documento = documentoRepository.findById(id).orElse(null);
        if (documento != null) {
            try {
                if (documento.getRuta() != null) {
                    Files.deleteIfExists(Paths.get(documento.getRuta()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            documentoRepository.delete(documento);
        }
    }
}

