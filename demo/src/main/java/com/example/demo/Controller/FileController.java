package com.example.demo.Controller;

import com.example.demo.Service.FileStorageService;
import com.example.demo.dto.FileResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;

    @PutMapping("/files")
    public ResponseEntity<FileResponseDTO> uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/v1/user/")
                .path(fileName)
                .toUriString();

        FileResponseDTO fileResponse = new FileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
        return new ResponseEntity<FileResponseDTO>(fileResponse, HttpStatus.OK);
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){

        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch(IOException ex) {
            System.out.println("Could not determine fileType");
        }

        if(contentType==null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
