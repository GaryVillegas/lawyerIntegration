package com.integration.lawyer.controller;

import com.integration.lawyer.model.Documento;
import com.integration.lawyer.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;


    // ✅ 2. Crear documento desde JSON (sin archivo físico)
    @PostMapping("/documento/nuevo")
    public ResponseEntity<?> crearDesdeJson(@RequestBody Documento documento) {
        try {
            Documento guardado = documentoService.guardarDesdeJson(documento);
            return ResponseEntity.status(201).body(guardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar documento desde JSON");
        }
    }
    // ✅ 4. Obtener documento por ID
    @GetMapping("/documento/{id}")
    public ResponseEntity<?> obtenerDocumento(@PathVariable Integer id) {
        Documento documento = documentoService.obtenerPorId(id);
        if (documento != null) {
            return ResponseEntity.ok(documento);
        }
        return ResponseEntity.status(404).body("Documento no encontrado");
    }

    // ✅ 1. Subir documento desde archivo (form-data con clave "file")
    @PostMapping("/documento/subir")
    public ResponseEntity<?> subirDocumento(@RequestParam("file") MultipartFile archivo) {
        try {
            Documento documento = documentoService.guardarDocumento(archivo);
            return ResponseEntity.ok(documento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al subir documento: " + e.getMessage());
        }
    }

    // ✅ 3. Listar todos los documentos
    @GetMapping("/documentos")
    public ResponseEntity<?> listarDocumentos() {
        List<Documento> documentos = documentoService.obtenerTodos();
        if (documentos.isEmpty()) {
            return ResponseEntity.status(204).body("No se encontraron documentos.");
        }
        return ResponseEntity.ok(documentos);
    }



    // ✅ 5. Eliminar documento
    @DeleteMapping("/documento/{id}")
    public ResponseEntity<?> eliminarDocumento(@PathVariable Integer id) {
        try {
            documentoService.eliminarDocumento(id);
            return ResponseEntity.ok("Documento eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error al eliminar el documento");
        }
    }
}
