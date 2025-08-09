package br.unitins.pibiti.service.avaliacao_maturidade;

import java.util.ArrayList;
import java.util.List;

import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeResponseDTO;
import br.unitins.pibiti.dto.variavel.VariavelResponseDTO;
import br.unitins.pibiti.model.Dimensao;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.model.ServicoNit;
import br.unitins.pibiti.repository.DimensaoRepository;
import br.unitins.pibiti.repository.NitRepository;
import br.unitins.pibiti.repository.ServicoNitRepository;
import br.unitins.pibiti.repository.VariavelRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class AvaliacaoMaturidadeServiceImpl implements AvaliacaoMaturidadeService {

    @Inject
    VariavelRepository variavelRepository;

    @Inject
    NitRepository nitRepository;

    @Inject
    ServicoNitRepository servicoNitRepository;

    @Inject
    DimensaoRepository dimensaoRepository;
 
    public List<VariavelResponseDTO> getVariaveis() {

        return variavelRepository.findAll().stream().map(VariavelResponseDTO::new).toList();
    }

    @Transactional
    public AvaliacaoMaturidadeResponseDTO cadastrarAvaliacaoMaturidade(AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO) {

        Nit nit = nitRepository.findById(avaliacaoMaturidadeDTO.idNit());

        nit.setServicos(gerarListaServicos(avaliacaoMaturidadeDTO.servicosFornecidos()));

        List<Double> arrayEscoreDimensao = gerarArrayEscoreDimensao();
        List<List<Double>> matrizEscoreVariaveis = gerarMatrizEscoreVariaveis();

        return null;
    }

    private List<ServicoNit> gerarListaServicos(List<Long> listaIdsServicos) {

        List<ServicoNit> servicos = new ArrayList<ServicoNit>();

        for (Long idServicoNit : listaIdsServicos) {
            
            servicos.add(servicoNitRepository.findById(idServicoNit));
        }

        return servicos;
    }

    private List<Double> gerarArrayEscoreDimensao() {

        return dimensaoRepository.listAll().stream().map(dimensao -> dimensao.getPeso()).toList();
    }

    private List<List<Double>> gerarMatrizEscoreVariaveis() {

        List<Dimensao> listDimensao = dimensaoRepository.findAll().list();

        List<List<Double>> matrizEscoreVariaveis = new ArrayList<>();
        
        for (Dimensao dimensao : listDimensao) {
            
            List<Double> linha = variavelRepository.findByDimensao(dimensao).stream().map(variavel -> variavel.getPeso()).toList();

            matrizEscoreVariaveis.add(linha);
        }

        return matrizEscoreVariaveis;
    }

    // private List<List<Integer>> gerarMatrizRespostaForm()
}
