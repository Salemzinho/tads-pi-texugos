package com.projetoIntegrador4Texugos.projetoIntegrador4.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImagemService {
	private final Path pasta = Paths.get("src/main/resources/static/assets/uploads");
	private final Path temp = Paths.get("src/main/resources/static/assets/uploads/temp");

	public void init() { 
		try {
			Files.createDirectory(pasta);
			Files.createDirectory(temp);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível criar a pasta para guardar as imagens.");
		}
	}

	public void armazenar(MultipartFile arquivo, Integer codProduto) {
		try {
			String fileName= StringUtils.cleanPath(arquivo.getOriginalFilename());
			Files.copy(arquivo.getInputStream(), Paths.get(pasta.toString()+"/"+codProduto+"/"+fileName));
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível armazenar a imagem. Erro: " + e.getMessage());
		}
	}

	public void armazenarTemp(MultipartFile arquivo) {
		try {
			String fileName= StringUtils.cleanPath(arquivo.getOriginalFilename());
			Files.copy(arquivo.getInputStream(), this.temp.resolve(fileName));
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível armazenar a imagem. Erro: " + e.getMessage());
		}
	}
	
	public Stream<Path> loadAllTemp() {
		try {
			return Files.walk(this.temp, 1)
					.filter(path -> !path.equals(this.temp))
					.map(path -> this.temp.relativize(path));
		} catch (IOException e) {
			throw new RuntimeException("Não foi possivel ler as imagens", e);
		}

	}
	
	public Stream<Path> loadAll(int codProduto) {
		try {
			final Path pastaProd = Paths.get(pasta.toString()+codProduto);
			return Files.walk(pastaProd, 1)
					.filter(path -> !path.equals(this.pasta))
					.map(path -> pastaProd.relativize(path));
		} catch (IOException e) {
			throw new RuntimeException("Não foi possivel ler as imagens", e);
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

	// TODO: Não sei se precisa
	public void deleteAllTemp() {
		if (temp != null) {
			FileSystemUtils.deleteRecursively(pasta.toFile());
		}
	}
}
