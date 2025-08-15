package br.unitins.pibiti.service.avaliacao_maturidade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeResponseDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.VariavelAvaliacaoDTO;
import br.unitins.pibiti.dto.variavel.VariavelResponseDTO;
import br.unitins.pibiti.model.AvaliacaoMaturidade;
import br.unitins.pibiti.model.DimensaoAvaliacao;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.model.ServicoFornecido;
import br.unitins.pibiti.model.ServicoNit;
import br.unitins.pibiti.model.Variavel;
import br.unitins.pibiti.model.VariavelAvaliacao;
import br.unitins.pibiti.repository.AvaliacaoMaturidadeRepository;
import br.unitins.pibiti.repository.DimensaoAvaliacaoRepository;
import br.unitins.pibiti.repository.DimensaoRepository;
import br.unitins.pibiti.repository.NitRepository;
import br.unitins.pibiti.repository.ServicoFornecidoRepository;
import br.unitins.pibiti.repository.ServicoNitRepository;
import br.unitins.pibiti.repository.VariavelAvaliacaoRepository;
import br.unitins.pibiti.repository.VariavelRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class AvaliacaoMaturidadeServiceImpl implements AvaliacaoMaturidadeService {

    @Inject
    VariavelRepository variavelRepository;

    @Inject
    NitRepository nitRepository;

    @Inject
    ServicoFornecidoRepository servicoFornecidoRepository;

    @Inject
    ServicoNitRepository servicoNitRepository;

    @Inject
    DimensaoRepository dimensaoRepository;

    @Inject
    AvaliacaoMaturidadeRepository avaliacaoMaturidadeRepository;

    @Inject
    DimensaoAvaliacaoRepository dimensaoAvaliacaoRepository;

    @Inject
    VariavelAvaliacaoRepository variavelAvaliacaoRepository;
 
    public List<VariavelResponseDTO> getVariaveis() {

        return variavelRepository.findAll().stream().map(VariavelResponseDTO::new).toList();
    }

    @Transactional
    public AvaliacaoMaturidadeResponseDTO cadastrarAvaliacaoMaturidade(AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO) {

        // -------------------- fazendo o calculo da maturidade ------------------------ //

        Double img = 0.0;
        Double imd;

        Map<Long, Double> listImds = new HashMap<>();

        for (VariavelAvaliacaoDTO variavelAvaliacaoDTO : avaliacaoMaturidadeDTO.variaveis()) {
            
            Variavel variavel = variavelRepository.findById(variavelAvaliacaoDTO.idVariavel());
            
            imd = listImds.get(variavel.getDimensao().getIdDimensaoMaturidadeNIT());

            if (imd == null) {

                imd = 0.0;
            }
            
            img += (variavelAvaliacaoDTO.selecionado() * variavel.getPeso()) * variavel.getDimensao().getPeso();
            imd += (variavelAvaliacaoDTO.selecionado() * variavel.getPeso()) * variavel.getDimensao().getPeso();

            listImds.put(variavel.getDimensao().getIdDimensaoMaturidadeNIT(), imd);
        }

        // -------------------------- salvando os dados ---------------------------------------- //

        // salvando os dados acerca dos Serviços do Nit

        Nit nit = nitRepository.findById(avaliacaoMaturidadeDTO.idNit());

        nit.setServicos(gerarListaServicos(avaliacaoMaturidadeDTO.servicosFornecidos(), nit));

        // salvando o IMG (Índice de Maturidade Geral) total da avaliação

        AvaliacaoMaturidade avaliacaoMaturidade = new AvaliacaoMaturidade();

        avaliacaoMaturidade.setImg(img);
        avaliacaoMaturidade.setNit(nit);

        avaliacaoMaturidadeRepository.persist(avaliacaoMaturidade);

        // salvando o IMD (Índice de Maturidade da Dimensão) de cada uma das dimensões

        List<DimensaoAvaliacao> listDimensaoAvaliacao = new ArrayList<>();

        listImds.forEach((idDimensao, imdDimensao) -> {
            
            DimensaoAvaliacao dimensaoAvaliacao = new DimensaoAvaliacao();
    
            dimensaoAvaliacao.setAvaliacao(avaliacaoMaturidade);
            dimensaoAvaliacao.setDimensao(dimensaoRepository.findById(idDimensao));
            dimensaoAvaliacao.setImd(imdDimensao);

            dimensaoAvaliacaoRepository.persist(dimensaoAvaliacao);

            listDimensaoAvaliacao.add(dimensaoAvaliacao);
        });

        // salvando as variáveis que foram selecionadas para fins de histórico

        for (VariavelAvaliacaoDTO variavelAvaliacaoDTO : avaliacaoMaturidadeDTO.variaveis()) {
            
            VariavelAvaliacao variavelAvaliacao = new VariavelAvaliacao();
    
            variavelAvaliacao.setAvaliacao(avaliacaoMaturidade);
            variavelAvaliacao.setVariavel(variavelRepository.findById(variavelAvaliacaoDTO.idVariavel()));
            variavelAvaliacao.setSelecionado(variavelAvaliacaoDTO.selecionado() == 0 ? false : true);

            variavelAvaliacaoRepository.persist(variavelAvaliacao);
        }

        return new AvaliacaoMaturidadeResponseDTO(avaliacaoMaturidade, listDimensaoAvaliacao);
    }

    private List<ServicoNit> gerarListaServicos(List<Long> listaIdsServicos, Nit nit) {

        List<ServicoFornecido> servicosFornecidos = new ArrayList<ServicoFornecido>();
        List<ServicoNit> servicosNit = new ArrayList<ServicoNit>();

        for (Long idServico : listaIdsServicos) {
            
            servicosFornecidos.add(servicoFornecidoRepository.findById(idServico));
        }

        for (ServicoFornecido servicoFornecido : servicosFornecidos) {
            
            ServicoNit servicoNit = new ServicoNit();

            servicoNit.setServico(servicoFornecido);
            servicoNit.setNit(nit);

            servicoNitRepository.persist(servicoNit);

            servicosNit.add(servicoNit);
        }

        return servicosNit;
    }
}
