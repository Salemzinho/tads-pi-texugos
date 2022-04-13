package com.projetoIntegrador4Texugos.projetoIntegrador4.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.projetoIntegrador4Texugos.projetoIntegrador4.model.ImagemModel;
import com.projetoIntegrador4Texugos.projetoIntegrador4.repository.ImagemRepository;

@Service
public class UploadImagemService {
	private final Path pasta = Paths.get("src/main/resources/static/assets/uploads");
	private final Path temp = Paths.get("src/main/resources/static/assets/uploads/temp");
	
	@Autowired
	private ImagemRepository imgRepo;

	public void init() { 
		try {
			Files.createDirectory(pasta);
			Files.createDirectory(temp);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível criar a pasta para guardar as imagens.");
		}
	}

	public void armazenar(List<ImagemModel> imagens) {
		try {
			
			FileSystemUtils.copyRecursively(this.temp, Paths.get(pasta.toString()+"/"+imagens.get(0).getIdProduto()));
			
			for(ImagemModel img : imagens) {
				img.setPathImagem(pasta.toString()+"\\"+imagens.get(0).getIdProduto()+"\\"+img.getPathImagem());
				imgRepo.save(img);
			}
			
			deleteAllTemp();
			
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível armazenar as imagens. Erro: " + e.getMessage());
		}
	}

	public void armazenarTemp(MultipartFile arquivo) {
		try {
			String fileName = StringUtils.cleanPath(arquivo.getOriginalFilename());

		    int index = fileName.lastIndexOf('.');
		    if(index > 0) {
		      String extensao = fileName.substring(index + 1);
		      String newFileName= "prod-"+new Date().getTime()+"."+extensao;
				
		      Files.copy(arquivo.getInputStream(), this.temp.resolve(newFileName));
		    }
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
	public void deleteAllTemp() throws IOException {
		if (temp != null) {
			FileSystemUtils.deleteRecursively(temp.toFile());
			Files.createDirectory(temp);
		}
	}
	public void deleteAll() throws IOException {
		if (temp != null) {
			FileSystemUtils.deleteRecursively(pasta.toFile());
		}
	}
}
