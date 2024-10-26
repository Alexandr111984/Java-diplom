package sobinda.javadiplomcloud.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sobinda.javadiplomcloud.dto.CloudFileDto;
import sobinda.javadiplomcloud.service.CloudService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CloudController {
    private final CloudService cloudService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_WRITE')")
    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@NotNull @RequestParam("file") MultipartFile multipartFile,
                                           @RequestParam("filename") String fileName) {
        log.info("Получили файл на загрузку: {}", fileName);
        if (cloudService.uploadFile(multipartFile, fileName)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_WRITE')")
    @DeleteMapping("/file")
    public ResponseEntity<String> deleteFile(@RequestParam String filename) {
        log.info("Начинаем искать файл {} для удаления", filename);
        if (cloudService.deleteFile(filename)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
//"hasRole('ROLE_WRITE') or hasRole('DELETE')"
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_READ')")
    @GetMapping("/file")
    public ResponseEntity<byte[]> getFile(@RequestParam String filename) {
        log.info("Запрос на получение скачивания файла {}", filename);
        var cloudFileDto = cloudService.getFile(filename);
        log.info("Запрос на скачивание файла {} получен. Начинаем скачивание...", filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + cloudFileDto.getFileName() + "\"")
                .body(cloudFileDto.getResource());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_WRITE')")
    @PutMapping("/file")
    public ResponseEntity<String> putFile(@RequestParam String filename, @RequestBody CloudFileDto cloudFileDto) {
        if (cloudService.putFile(filename, cloudFileDto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_READ')")
    @GetMapping("/list")
    public ResponseEntity<List<CloudFileDto>> getAllFile() {
        var result = cloudService.getAllFile();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
