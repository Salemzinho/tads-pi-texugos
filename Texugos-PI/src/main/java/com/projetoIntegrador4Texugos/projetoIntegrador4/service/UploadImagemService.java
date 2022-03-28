package com.projetoIntegrador4Texugos.projetoIntegrador4.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImagemService {
	private final Path pasta = Paths.get("uploads");

	  public void init() {
	    try {
	      Files.createDirectory(pasta);
	    } catch (IOException e) {
	      throw new RuntimeException("Não foi possível criar a pasta para guardar as imagens.");
	    }
	  }

	  public void armazenar(MultipartFile arquivo) {
	    try {
	      Files.copy(arquivo.getInputStream(), this.pasta.resolve(arquivo.getOriginalFilename()));
	    } catch (Exception e) {
	      throw new RuntimeException("Não foi possível armazenar a imagem. Erro: " + e.getMessage());
	    }
	  }

	  public Resource carregar(String nomeArquivo) {
	    try {
	      Path file = pasta.resolve(nomeArquivo);
	      Resource resource = new UrlResource(file.toUri());
	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Não foi possível carregar o arquivo!");  
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Erro: " + e.getMessage());
	    }
	  }

	  //TODO: Não sei se precisa
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(pasta.toFile());
	  }
}
