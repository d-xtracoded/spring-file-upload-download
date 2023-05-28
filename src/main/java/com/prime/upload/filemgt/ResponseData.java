package com.prime.upload.filemgt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

    private  String filename;
    private  String downloadURL;
    private String filetype;
    private long fileSize;
}
