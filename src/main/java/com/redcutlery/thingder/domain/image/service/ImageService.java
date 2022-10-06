package com.redcutlery.thingder.domain.image.service;

import com.redcutlery.thingder.domain.image.entity.ImageFile;
import com.redcutlery.thingder.domain.image.entity.ResizedImageFile;
import com.redcutlery.thingder.domain.image.repository.ImageFileRepository;
import com.redcutlery.thingder.domain.image.repository.ResizedImageFileRepository;
import com.redcutlery.thingder.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.imgscalr.Scalr;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageFileRepository imageFileRepository;
    private final ResizedImageFileRepository resizedImageFileRepository;

    public ImageFile uploadImage(MultipartFile file) throws NoSuchAlgorithmException, IOException {
        if (!Objects.requireNonNull(file.getContentType()).startsWith("image/"))
            throw new BaseException.BadRequest("이미지 파일이 아닙니다.");

        var originalFilename = file.getOriginalFilename();
        var sha256 = getSha256(file);
        var format = file.getContentType().substring("image/".length());
//        var fileExtension = FilenameUtils.getExtension(file.getName());

        var path = "src/main/resources/file/image";

        var exist = imageFileRepository.findBySha256(sha256);
        if (exist.isPresent())
            return exist.get();

        var url = String.format("/%s/%s", sha256, originalFilename);
        var uri = String.format("%s%s", path, url);
        var savedFile = saveMultipartFile(file, uri);

        var imageFile = ImageFile.builder()
                .originalName(originalFilename)
                .url(url)
                .sha256(sha256)
                .size(file.getSize())
                .build();

        Arrays.asList(1043, 750, 640, 480, 320, 240).forEach(width -> {
            var resizedUrl = String.format("/%s/%dw__%s", sha256, width, originalFilename);
            var resizedUri = String.format("%s%s", path, resizedUrl);
            var resizedFile = resizeAndSaveImage(savedFile, width, resizedUri, format);
            var savedResizedFile = ResizedImageFile.builder()
                    .width(width)
                    .url(resizedUrl)
                    .size(resizedFile.length())
                    .build();
            imageFile.addResizedImageFile(savedResizedFile);
        });

        return imageFileRepository.save(imageFile);
    }

    public ResponseEntity<Resource> getImageFileByPath(String fileName) {
        try {
            var path = "src/main/resources/file/image";
            var file = path + "/" + URLDecoder.decode(fileName);
            System.out.println(file);
            var resource = new FileSystemResource(file);
            if (!resource.exists())
                throw new BaseException.NotFound("존재하지 않는 이미지입니다.");

            var header = new HttpHeaders();
            var filePath = Paths.get(file);
            header.add("Content-Type", Files.probeContentType(filePath));
            return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
        } catch (Exception e) {
            throw new BaseException.NotFound("존재하지 않는 이미지입니다.");
        }
    }

    private File saveMultipartFile(MultipartFile file, String path) throws IOException {
        var absolutePath = Paths.get(path).toAbsolutePath();

        var destinationFile = absolutePath.toFile();

        destinationFile.getParentFile().mkdirs();
        file.transferTo(absolutePath);
        return destinationFile;
    }

    private String getSha256(MultipartFile file) throws NoSuchAlgorithmException, IOException {
        var messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(file.getBytes());
        return bytesToHex(messageDigest.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    private File resizeAndSaveImage(File file, Integer width, String url, String format) {
        try {
            var image = ImageIO.read(file);
            var resizedImage = Scalr.resize(image, width, image.getHeight() * width / image.getWidth());

            var resizedFile = new File(url);
            ImageIO.write(resizedImage, format, resizedFile);
            return resizedFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
