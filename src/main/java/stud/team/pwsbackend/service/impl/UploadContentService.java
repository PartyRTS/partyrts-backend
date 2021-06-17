package stud.team.pwsbackend.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stud.team.pwsbackend.domain.User;
import stud.team.pwsbackend.domain.Video;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.repository.UserRepository;
import stud.team.pwsbackend.repository.VideoRepository;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class UploadContentService {
    private UserRepository userRepository;
    private VideoRepository videoRepository;
    private final String awsS3Logo = "https://logouserbucket.s3-us-west-2.amazonaws.com/";
    private final String awsS3Video = "https://videousersbucket.s3-us-west-2.amazonaws.com/";
    private String bucketLogoName = "logouserbucket";
    private String bucketVideoName = "videousersbucket";
    private AmazonS3 s3client;

    public String updateUserLogo(long userId, MultipartFile file) throws IOException, UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        String pathKey = userId + "/" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        s3client.putObject(new PutObjectRequest(bucketLogoName,
                pathKey,
                file.getInputStream(), metadata)
                .withAccessControlList(s3client.getBucketAcl(bucketLogoName)));
        String urlLogo = awsS3Logo + URLEncoder.encode(pathKey, StandardCharsets.UTF_8.toString());
        user.setLogoUrl(urlLogo);
        userRepository.save(user);
        return urlLogo;
    }

    public String uploadPreviewVideo(long videoId, MultipartFile file) throws IOException {
        Video video = videoRepository.findById(videoId).orElseThrow();
        String pathKey = videoId + "/preview/" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        s3client.putObject(new PutObjectRequest(bucketVideoName,
                pathKey,
                file.getInputStream(), metadata)
                .withAccessControlList(s3client.getBucketAcl(bucketVideoName)));
        String urlpreview = awsS3Video + URLEncoder.encode(pathKey, StandardCharsets.UTF_8.toString());
        video.setPreviewUrl(urlpreview);
        videoRepository.save(video);
        return urlpreview;
    }

    @Autowired
    public void setS3client(@Value("${endpoint.accessKey}") String accessKey, @Value("${endpoint.secretKey}") String secretKey) {
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                        accessKey,
                        secretKey
                )))
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setVideoRepository(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }
}
