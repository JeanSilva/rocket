package com.rocket.services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

    @Service
    public class UploadService {
        private static final String UPLOAD_DIRECTORY = "Uploads";

        public UploadService() {
        }

        public String fazerUpload(MultipartFile fotoImg) throws IOException {
            // Cria a pasta "Uploads" se não existir para salvar as imagens
            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Realiza o upload de cada arquivo e obtém o caminho

            return salvarArquivo(fotoImg);
        }

        private String salvarArquivo(MultipartFile file) throws IOException {
            if (file != null && !file.isEmpty()) {
                Path caminhoCompleto = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
                String caminho = caminhoCompleto.toString();
                File arquivo = new File(caminho);
                file.transferTo(arquivo);
                return caminho;
            }
            return null;
        }
    }



