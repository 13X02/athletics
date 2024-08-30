package com.abhijith.eventservice.service;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class AwsService {

    private final AmazonS3 s3Client;
    BasicAWSCredentials credentials = new BasicAWSCredentials("d", "d//d/d");

    public AwsService() {
        this.s3Client = AmazonS3Client.builder()
                .withRegion("ap-south-1")
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    public String uploadFile(InputStream fileInputStream, String fileName) throws IOException {
        byte[] contentBytes = IOUtils.toByteArray(fileInputStream);
        Long contentLength = Long.valueOf(contentBytes.length);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);

        // Use a ByteArrayInputStream instead of the original InputStream
        try (InputStream byteArrayInputStream = new ByteArrayInputStream(contentBytes)) {
            s3Client.putObject(new PutObjectRequest("d", fileName, byteArrayInputStream, metadata));
            return s3Client.getUrl("d", fileName).toString();
        } catch (AmazonServiceException ase) {
            System.out.println("Error Message: " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code: " + ase.getErrorCode());
            System.out.println("Error Type: " + ase.getErrorType());
            System.out.println("Request ID: " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Error Message: " + ace.getMessage());
        }
        return "error";
    }


}
