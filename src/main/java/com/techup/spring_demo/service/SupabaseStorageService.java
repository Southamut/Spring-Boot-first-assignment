package com.techup.spring_demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class SupabaseStorageService {

  @Value("${supabase.url}")
  private String supabaseUrl;

  @Value("${supabase.bucket}")
  private String bucket;

  @Value("${supabase.apiKey}")
  private String apiKey;

  private final WebClient webClient = WebClient.builder().build();

  /** อัปโหลดไฟล์ขึ้น Supabase แล้วคืน public URL */
  public String uploadFile(MultipartFile file) {
    String original = file.getOriginalFilename() != null ? file.getOriginalFilename() : "file.bin";
    String fileName = System.currentTimeMillis() + "_" + original;
    String uploadUrl = String.format("%s/storage/v1/object/%s/%s", supabaseUrl, bucket, fileName);

    byte[] bytes;
    try {
      bytes = file.getBytes();
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot read file bytes", e);
    }

    try {
      webClient.put()
          .uri(uploadUrl)
          .header("Authorization", "Bearer " + apiKey)     // Service Role Key
          .header("Content-Type", file.getContentType() != null ? file.getContentType() : "application/octet-stream")
          .bodyValue(bytes)
          .retrieve()
          .onStatus(HttpStatusCode::isError, res ->
              res.bodyToMono(String.class).defaultIfEmpty("Upload failed").flatMap(msg ->
                  Mono.error(new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Supabase upload failed: " + msg))
              )
          )
          .toBodilessEntity()
          .block();

      // public URL สำหรับ access ไฟล์ได้ทันที
      return String.format("%s/storage/v1/object/public/%s/%s", supabaseUrl, bucket, fileName);

    } catch (ResponseStatusException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Unexpected error while uploading to Supabase", ex);
    }
  }
}