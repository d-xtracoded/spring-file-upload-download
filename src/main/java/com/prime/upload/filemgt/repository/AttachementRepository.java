package com.prime.upload.filemgt.repository;

import com.prime.upload.filemgt.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachementRepository extends JpaRepository<Attachment,String > {

}
