package br.unitins.pibiti.service.file;

import java.io.File;
import java.io.IOException;

public interface NitFileService {
    
    void salvar(Long id, String nomeImagem, byte[] imagem) throws IOException;

    File download(String nomeArquivo);
}
