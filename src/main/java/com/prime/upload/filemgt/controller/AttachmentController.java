package com.prime.upload.filemgt.controller;

import com.prime.upload.filemgt.ResponseData;
import com.prime.upload.filemgt.entity.Attachment;
import com.prime.upload.filemgt.service.AttachmentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AttachmentController {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public ResponseData uploadfile(@RequestParam("file") MultipartFile file) throws Exception {
        Attachment attachment = null;
        String downloadURL="";
        attachment=attachmentService.saveAttachecment(file);
        downloadURL= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();

        return new ResponseData(attachment.getFilename(),downloadURL, file.getContentType(), file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadfile(@PathVariable String fileId) throws Exception {
        Attachment attachment= null;
        attachment =attachmentService.getAttachment(fileId);

        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFilename()
                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}

