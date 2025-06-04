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
    
    @PostMapping("/documento/subir")
    public ResponseEntity<Documento> subirDocumento(@RequestParam("file") MultipartFile archivo) {
        try {
            Documento documento = documentoService.guardarDocumento(archivo);
            return ResponseEntity.ok(documento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/documentos")
    public ResponseEntity<?> listarDocumentos() {
        List<Documento> documentos = documentoService.obtenerTodos();
        if(documentos.isEmpty()){
            return ResponseEntity.status(203).body("No se encontraron documentos.");
        }
        return ResponseEntity.ok(documentos);
    }
    
    @GetMapping("/documento/{id}")
    public ResponseEntity<Documento> obtenerDocumento(@PathVariable Integer id) {
        Documento documento = documentoService.obtenerPorId(id);
        if (documento != null) {
            return ResponseEntity.ok(documento);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/documento/{id}")
    public ResponseEntity<Void> eliminarDocumento(@PathVariable Integer id) {
        try {
            documentoService.eliminarDocumento(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}