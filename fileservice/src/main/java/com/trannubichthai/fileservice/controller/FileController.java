package com.trannubichthai.fileservice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.trannubichthai.fileservice.constant.FileConstant.FORWARD_SLASH;
import static com.trannubichthai.fileservice.constant.FileConstant.IMAGE_FOLDER;
import com.trannubichthai.fileservice.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/saveImage")
    public ResponseEntity<String> saveImage(@RequestParam("file") MultipartFile image) {
        return ResponseEntity.ok(fileService.saveImage(image));
    }

    @GetMapping(path = "/image/{imageName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String imageName) throws IOException {
        return Files.readAllBytes(Paths.get(IMAGE_FOLDER + FORWARD_SLASH + imageName));
    }

    @DeleteMapping("/removeImage")
    public ResponseEntity<String> removeImage(@RequestParam("file") String imagePath) {
        return ResponseEntity.ok(fileService.removeImage(imagePath));
    }
}