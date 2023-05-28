package com.prime.upload.filemgt.service;

import com.prime.upload.filemgt.entity.Attachment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface AttachmentService {
    Attachment saveAttachecment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;
}
