package br.unitins.pibiti.service.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.repository.NitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class NitFileServiceImpl implements NitFileService {

    private final String PATH_USER = System.getProperty("user.home")
        + File.separator + "pibiti"
        + File.separator + "images"
        + File.separator + "nit" + File.separator;
    
    @Inject
    NitRepository nitRepository;

    @Override
    @Transactional
    public void salvar(Long id, String nomeImagem, byte[] imagem) throws IOException {

        Nit nit = nitRepository.findById(id);

        try {

            String novoNomeImagem = salvarImagem(imagem, nomeImagem);

            nit.setFotoPerfil(novoNomeImagem);
        } catch (IOException e) {

            throw e;
        }
    }

    @Override
    public File download(String nomeArquivo) {

        File file = new File(PATH_USER+nomeArquivo);

        return file;
    }

    private String salvarImagem(byte[] imagem, String nomeImagem) throws IOException {

        // verificando o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif");
        
        if (!listMimeType.contains(mimeType)) {

            throw new IOException("tipo de mensagem não suportado.");
        }

        // verificando o tamanho do arquivo, não permitor maior que 10 megas
        if (imagem.length > (1024 * 1024 * 10))
            throw new IOException("Arquivo muito grande.");

        // criando as pastas quando não existir
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists())
            diretorio.mkdirs();

        // gerando o nome do arquivo
        String nomeArquivo = UUID.randomUUID()
        +"."+mimeType.substring(mimeType.lastIndexOf("/")+1);

        String path = PATH_USER + nomeArquivo;

        // salvando o arquivo
        File file = new File(path);
        if (file.exists())
            throw new IOException("O nome gerado da imagem está repetido.");

        // criando um arquivo no S.O.
        file.createNewFile();
        
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

        return nomeArquivo;
    }
}
