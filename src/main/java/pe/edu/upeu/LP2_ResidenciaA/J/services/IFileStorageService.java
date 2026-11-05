package pe.edu.upeu.LP2_ResidenciaA.J.services;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
    void init();
    String store(MultipartFile file);
    void delete(String filename);
}

