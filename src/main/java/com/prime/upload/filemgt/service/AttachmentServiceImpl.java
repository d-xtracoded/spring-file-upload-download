package com.prime.upload.filemgt.service;

import com.prime.upload.filemgt.entity.Attachment;
import com.prime.upload.filemgt.repository.AttachementRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private AttachementRepository attachementRepository;

    public AttachmentServiceImpl(AttachementRepository attachementRepository) {
        this.attachementRepository = attachementRepository;
    }

    @Override
    public Attachment saveAttachecment(MultipartFile file) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
         if (filename.contains("..")) {
             throw  new Exception("Filename contain invalid path sequence "+ filename);
         }
            Attachment attachment
                    = new Attachment(filename,file.getContentType(),file.getBytes());

            return attachementRepository.save(attachment);

        }catch (Exception e){
         throw new Exception("Could not save file" + filename);
        }

           // return null;
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {


        return attachementRepository.findById(fileId)
                .orElseThrow(()-> new Exception("File not find with id" +fileId));
    }
}
