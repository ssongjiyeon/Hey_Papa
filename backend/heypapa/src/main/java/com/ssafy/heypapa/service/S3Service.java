package com.ssafy.heypapa.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class S3Service {

	private AmazonS3 s3Client;
	
	@Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;
    
    public static final String CLOUD_FRONT_DOMAIN_NAME = "d11bfjty6ba1yx.cloudfront.net";
    
    @PostConstruct
    public void setSC3Client() {
    	AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
    	// accessKey와 secretKey를 이용하여 자격증명 객체를 얻음
    	
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                // 자격증명을 통해 S3 Client를 가져옴
                .withRegion(this.region) // region 설정
                .build();
    }
    
//    public String upload(MultipartFile file) throws IOException {
//        String fileName = file.getOriginalFilename();
//
//        // 의존성 주입 이후 초기화 메서드
//        s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
//                .withCannedAcl(CannedAccessControlList.PublicRead));
//        return s3Client.getUrl(bucket, fileName).toString();
//        // 업로드를 한 후, 해당 URL을 DB에 저장할 수 있도록 컨트롤러로 URL을 반환
//    }
    
    public String upload(String currentFilePath, MultipartFile file) throws IOException {
        // 고유한 key 값을 갖기위해 현재 시간을 postfix로 붙여줌
//        SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
//        String fileName = file.getOriginalFilename() + "-" + date.format(new Date());
        String fileName = currentFilePath;

        // key가 존재하면 기존 파일은 삭제
        if ("".equals(currentFilePath) == false && currentFilePath != null) {
            boolean isExistObject = s3Client.doesObjectExist(bucket, currentFilePath);

            if (isExistObject == true) {
                s3Client.deleteObject(bucket, currentFilePath);
            }
        }


        // 파일 업로드
        s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return fileName;
    }
    
    // 코치님 코드 참고
    public String uploadFile(MultipartFile file) throws IOException {
        // 고유한 key 값을 갖기위해 현재 시간을 prefix 로 붙여줌
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = date.format(new Date()) + "_" + file.getOriginalFilename();

        // 파일 업로드
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        s3Client.putObject(new PutObjectRequest(bucket, fileName, byteArrayInputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return  "https://d6sx5vd3amky9.cloudfront.net/" + fileName;
    }
    
}