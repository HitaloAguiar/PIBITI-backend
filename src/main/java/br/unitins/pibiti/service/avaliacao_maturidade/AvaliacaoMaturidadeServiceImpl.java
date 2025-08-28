package br.unitins.pibiti.service.avaliacao_maturidade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeGraficoResponseDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.AvaliacaoMaturidadeResponseDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.VariavelAvaliacaoDTO;
import br.unitins.pibiti.dto.avaliacao_maturidade.VariavelAvaliacaoResponseDTO;
import br.unitins.pibiti.dto.variavel.VariavelResponseDTO;
import br.unitins.pibiti.model.AvaliacaoMaturidade;
import br.unitins.pibiti.model.DimensaoAvaliacao;
import br.unitins.pibiti.model.Nit;
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
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
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

    @Override
    public List<VariavelResponseDTO> getVariaveis() {

        return variavelRepository.findAll().stream().map(VariavelResponseDTO::new).toList();
    }

    @Override
    @Transactional
    public AvaliacaoMaturidadeResponseDTO cadastrarAvaliacaoMaturidade(String cnpj, AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO) {

        // -------------------- fazendo o calculo da maturidade ------------------------

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

        img = img * 5;

        // -------------------------- salvando os dados ---------------------------------------- //

        // salvando os dados acerca dos Serviços do Nit

        Nit nit = nitRepository.findByCnpj(cnpj);

        //gerarListaServicos(avaliacaoMaturidadeDTO.servicosFornecidos(), nit);

        // nitRepository.persist(nit);

        // salvando o IMG (Índice de Maturidade Geral) total da avaliação

        AvaliacaoMaturidade avaliacaoMaturidade = new AvaliacaoMaturidade();

        avaliacaoMaturidade.setImg(img);
        avaliacaoMaturidade.setNit(nit);

        int imgArredondado = (int) Math.round(img);

        switch (imgArredondado) {
            case 5:
                avaliacaoMaturidade.setNivelMaturidade("Consolidado");
                break;
            
            case 4:
                avaliacaoMaturidade.setNivelMaturidade("Maduro");
                break;
            
            case 3:
                avaliacaoMaturidade.setNivelMaturidade("Desenvolvido");
                break;
            
            case 2:
                avaliacaoMaturidade.setNivelMaturidade("Emergente");
                break;
        
            default:
                avaliacaoMaturidade.setNivelMaturidade("Inicial");
                break;
        }

        avaliacaoMaturidadeRepository.persist(avaliacaoMaturidade);

        // salvando o IMD (Índice de Maturidade da Dimensão) de cada uma das dimensões

        List<DimensaoAvaliacao> listDimensaoAvaliacao = new ArrayList<>();

        listImds.forEach((idDimensao, imdDimensao) -> {

            DimensaoAvaliacao dimensaoAvaliacao = new DimensaoAvaliacao();

            dimensaoAvaliacao.setAvaliacao(avaliacaoMaturidade);
            dimensaoAvaliacao.setDimensao(dimensaoRepository.findById(idDimensao));
            dimensaoAvaliacao.setImd(imdDimensao * 5);

            dimensaoAvaliacaoRepository.persist(dimensaoAvaliacao);

            listDimensaoAvaliacao.add(dimensaoAvaliacao);
        });

        // salvando as variáveis que foram selecionadas para fins de histórico

        List<VariavelAvaliacao> listVariavelAvaliacao = new ArrayList<>();

        for (VariavelAvaliacaoDTO variavelAvaliacaoDTO : avaliacaoMaturidadeDTO.variaveis()) {

            VariavelAvaliacao variavelAvaliacao = new VariavelAvaliacao();

            variavelAvaliacao.setAvaliacao(avaliacaoMaturidade);
            variavelAvaliacao.setVariavel(variavelRepository.findById(variavelAvaliacaoDTO.idVariavel()));
            variavelAvaliacao.setSelecionado(variavelAvaliacaoDTO.selecionado() == 0 ? false : true);

            variavelAvaliacaoRepository.persist(variavelAvaliacao);

            listVariavelAvaliacao.add(variavelAvaliacao);
        }

        return new AvaliacaoMaturidadeResponseDTO(avaliacaoMaturidade, listDimensaoAvaliacao, listVariavelAvaliacao.stream().map(VariavelAvaliacaoResponseDTO::new).toList());
    }

    @Override
    @Transactional
    public AvaliacaoMaturidadeResponseDTO atualizarAvaliacaoMaturidade(Long idAvaliacao, AvaliacaoMaturidadeDTO avaliacaoMaturidadeDTO) {

        // -------------------- fazendo o calculo da maturidade ------------------------

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

        img = img * 5;

        // -------------------------- salvando os dados ---------------------------------------- //

        // salvando os dados acerca dos Serviços do Nit

        Nit nit = nitRepository.findById(avaliacaoMaturidadeDTO.idNit());

        //gerarListaServicos(avaliacaoMaturidadeDTO.servicosFornecidos(), nit);

        // nitRepository.persist(nit);

        // salvando o IMG (Índice de Maturidade Geral) total da avaliação

        AvaliacaoMaturidade avaliacaoMaturidade = avaliacaoMaturidadeRepository.findById(idAvaliacao);

        if (avaliacaoMaturidade == null)
            throw new NotFoundException("Nenhuma avaliação encontrada");

        avaliacaoMaturidade.setImg(img);
        avaliacaoMaturidade.setNit(nit);

        int imgArredondado = (int) Math.round(img);

        switch (imgArredondado) {
            case 5:
                avaliacaoMaturidade.setNivelMaturidade("Consolidado");
                break;
            
            case 4:
                avaliacaoMaturidade.setNivelMaturidade("Maduro");
                break;
            
            case 3:
                avaliacaoMaturidade.setNivelMaturidade("Desenvolvido");
                break;
            
            case 2:
                avaliacaoMaturidade.setNivelMaturidade("Emergente");
                break;
        
            default:
                avaliacaoMaturidade.setNivelMaturidade("Inicial");
                break;
        }

        avaliacaoMaturidadeRepository.persist(avaliacaoMaturidade);

        // salvando o IMD (Índice de Maturidade da Dimensão) de cada uma das dimensões

        List<DimensaoAvaliacao> listDimensaoAvaliacao = dimensaoAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

        for (DimensaoAvaliacao dimensaoAvaliacao : listDimensaoAvaliacao) {
            
            dimensaoAvaliacao.setImd(listImds.get(dimensaoAvaliacao.getIdDimensaoAvaliacaoNit()) * 5);

            dimensaoAvaliacaoRepository.persist(dimensaoAvaliacao);
        }

        // salvando as variáveis que foram selecionadas para fins de histórico

        List<VariavelAvaliacao> listVariaveisAvaliacao = variavelAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

        for (VariavelAvaliacao variavelAvaliacao : listVariaveisAvaliacao) {
            
            for (VariavelAvaliacaoDTO variavelAvaliacaoDTO : avaliacaoMaturidadeDTO.variaveis()) {

                if (variavelAvaliacaoDTO.idVariavel() == variavelAvaliacao.getIdVariavelAvaliacao()) {

                    variavelAvaliacao.setSelecionado(variavelAvaliacaoDTO.selecionado() == 0 ? false : true);
    
                    variavelAvaliacaoRepository.persist(variavelAvaliacao);
                }

            }
        }

        // listVariaveisAvaliacao.sort(Comparator.comparing(VariavelAvaliacao::getIdVariavelAvaliacao));

        return new AvaliacaoMaturidadeResponseDTO(avaliacaoMaturidade, listDimensaoAvaliacao, listVariaveisAvaliacao.stream().map(VariavelAvaliacaoResponseDTO::new).toList());
    }

    @Override
    @Transactional
    public void deletarAvaliacaoMaturidade(String cnpj, Long idAvaliacao) {

        Nit nit = nitRepository.findByCnpj(cnpj);

        if (idAvaliacao == null)
            throw new IllegalArgumentException("Id inválido");

        AvaliacaoMaturidade avaliacaoMaturidade = avaliacaoMaturidadeRepository.findById(idAvaliacao);

        if (avaliacaoMaturidade == null)
            throw new NotFoundException("Nenhuma avaliação encontrada");

        if (avaliacaoMaturidade.getNit().getIdNit() != nit.getIdNit())
            throw new BadRequestException("A avaliação selecionada não pertence ao Nit cadastrado");

        List<DimensaoAvaliacao> listDimensaoAvaliacao = dimensaoAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

        for (DimensaoAvaliacao dimensaoAvaliacao : listDimensaoAvaliacao) {
            
            if (dimensaoAvaliacaoRepository.isPersistent(dimensaoAvaliacao))
                dimensaoAvaliacaoRepository.delete(dimensaoAvaliacao);
        }

        List<VariavelAvaliacao> listVariavelAvaliacao = variavelAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

        for (VariavelAvaliacao variavelAvaliacao : listVariavelAvaliacao) {
            
            if (variavelAvaliacaoRepository.isPersistent(variavelAvaliacao))
                variavelAvaliacaoRepository.delete(variavelAvaliacao);
        }

        if (avaliacaoMaturidadeRepository.isPersistent(avaliacaoMaturidade))
            avaliacaoMaturidadeRepository.delete(avaliacaoMaturidade);

        else
            throw new NotFoundException("Nenhuma avaliação encontrada");
    }

    @Override
    public AvaliacaoMaturidadeResponseDTO getLastAvaliacaoMaturidade(Long idNit) {

        Nit nit = nitRepository.findById(idNit);

        if (nit == null)
            throw new NotFoundException("Não existe um NIT com esse ID");

        AvaliacaoMaturidade avaliacaoMaturidade = avaliacaoMaturidadeRepository.findByNitAndLastInserted(nit);

        if (avaliacaoMaturidade == null)
            throw new NotFoundException("Não existe nenhuma avaliação cadastrada");

        List<DimensaoAvaliacao> listDimensaoAvaliacao = dimensaoAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

        List<VariavelAvaliacao> listVariavelAvaliacao = variavelAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

        List<VariavelAvaliacaoResponseDTO> listVariavelAvaliacaoResponseDTO = listVariavelAvaliacao.stream().map(variavelAvaliacao -> new VariavelAvaliacaoResponseDTO(variavelAvaliacao.getVariavel(), variavelAvaliacao.getSelecionado() == false? 0 : 1)).toList();

        return new AvaliacaoMaturidadeResponseDTO(avaliacaoMaturidade, listDimensaoAvaliacao, listVariavelAvaliacaoResponseDTO);
    }

    @Override
    public List<AvaliacaoMaturidadeGraficoResponseDTO> getDadosGrafico(Long idNit) {

        Sort sort = Sort.by("idAvaliacaoMaturidade").descending();

        Nit nit = nitRepository.findById(idNit);

        if (nit == null)
            throw new NotFoundException("Não existe um NIT com esse ID");

        List<AvaliacaoMaturidade> avaliacoes = avaliacaoMaturidadeRepository.findListByNit(nit, sort).list();

        if (avaliacoes.isEmpty())
            throw new NotFoundException("Não existe nenhuma avaliação cadastrada");

        return avaliacoes.stream().map(AvaliacaoMaturidadeGraficoResponseDTO::new).toList();
    }

    @Override
    public List<AvaliacaoMaturidadeResponseDTO> getHistoricoAvaliacoes(Long idNit, int page, int pageSize, Boolean isAscending) {

        Sort sort;

        Nit nit = nitRepository.findById(idNit);

        if (nit == null)
            throw new NotFoundException("Não existe um NIT com esse ID");

        if (isAscending) {
            
            sort = Sort.by("idAvaliacaoMaturidade").ascending();
        } else {

            sort = Sort.by("idAvaliacaoMaturidade").descending();
        }

        List<AvaliacaoMaturidade> listAvaliacaoMaturidade = avaliacaoMaturidadeRepository.findListByNit(nit, sort).page(page, pageSize).list();

        if (listAvaliacaoMaturidade.isEmpty())
            throw new NotFoundException("Não existe nenhuma avaliação cadastrada");

        List<AvaliacaoMaturidadeResponseDTO> listAvaliacaoMaturidadeResponseDTO = listAvaliacaoMaturidade.stream()
        .map(avaliacaoMaturidade -> {

            List<DimensaoAvaliacao> listDimensaoAvaliacao = dimensaoAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

            List<VariavelAvaliacao> listVariavelAvaliacao = variavelAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

            List<VariavelAvaliacaoResponseDTO> listVariavelAvaliacaoResponseDTO = listVariavelAvaliacao.stream().map(variavelAvaliacao -> new VariavelAvaliacaoResponseDTO(variavelAvaliacao.getVariavel(), variavelAvaliacao.getSelecionado() == false? 0 : 1)).toList();

            return new AvaliacaoMaturidadeResponseDTO(avaliacaoMaturidade, listDimensaoAvaliacao, listVariavelAvaliacaoResponseDTO);
        }).toList();

        return listAvaliacaoMaturidadeResponseDTO;
    }

    @Override
    public List<AvaliacaoMaturidadeResponseDTO> getHistoricoAvaliacoesByNivelMaturidade(Long idNit, String nivelMaturidade, int page, int pageSize, Boolean isAscending) {

        Sort sort;

        Nit nit = nitRepository.findById(idNit);

        if (nit == null)
            throw new NotFoundException("Não existe um NIT com esse ID");

        if (isAscending) {
            
            sort = Sort.by("idAvaliacaoMaturidade").ascending();
        } else {

            sort = Sort.by("idAvaliacaoMaturidade").descending();
        }

        List<AvaliacaoMaturidade> listAvaliacaoMaturidade = avaliacaoMaturidadeRepository.findListByNitAndNivelMaturidade(nit, nivelMaturidade, sort).page(page, pageSize).list();

        if (listAvaliacaoMaturidade.isEmpty())
            throw new NotFoundException("Não existe nenhuma avaliação cadastrada");

        List<AvaliacaoMaturidadeResponseDTO> listAvaliacaoMaturidadeResponseDTO = listAvaliacaoMaturidade.stream()
        .map(avaliacaoMaturidade -> {

            List<DimensaoAvaliacao> listDimensaoAvaliacao = dimensaoAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

            List<VariavelAvaliacao> listVariavelAvaliacao = variavelAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

            List<VariavelAvaliacaoResponseDTO> listVariavelAvaliacaoResponseDTO = listVariavelAvaliacao.stream().map(variavelAvaliacao -> new VariavelAvaliacaoResponseDTO(variavelAvaliacao.getVariavel(), variavelAvaliacao.getSelecionado() == false? 0 : 1)).toList();

            return new AvaliacaoMaturidadeResponseDTO(avaliacaoMaturidade, listDimensaoAvaliacao, listVariavelAvaliacaoResponseDTO);
        }).toList();

        return listAvaliacaoMaturidadeResponseDTO;
    }

    @Override
    public byte[] criarRelatorioAvaliacao() {

        return null;
    }

    @Override
    public byte[] gerarPdf() {

        return null;
    }

    @Override
    public AvaliacaoMaturidadeResponseDTO getAvaliacaoMaturidade(Long idAvaliacao) {

        AvaliacaoMaturidade avaliacaoMaturidade = avaliacaoMaturidadeRepository.findById(idAvaliacao);

        if (avaliacaoMaturidade == null)
            throw new NotFoundException("Avaliação não encontrada");

        List<DimensaoAvaliacao> listDimensaoAvaliacao = dimensaoAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

        List<VariavelAvaliacao> listVariavelAvaliacao = variavelAvaliacaoRepository.findByAvaliacao(avaliacaoMaturidade);

        List<VariavelAvaliacaoResponseDTO> listVariavelAvaliacaoResponseDTO = listVariavelAvaliacao.stream().map(variavelAvaliacao -> new VariavelAvaliacaoResponseDTO(variavelAvaliacao.getVariavel(), variavelAvaliacao.getSelecionado() == false? 0 : 1)).toList();

        return new AvaliacaoMaturidadeResponseDTO(avaliacaoMaturidade, listDimensaoAvaliacao, listVariavelAvaliacaoResponseDTO);
    }
}
