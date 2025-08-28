package br.unitins.pibiti.service.nit;

import java.util.List;
import java.util.Set;

import br.unitins.pibiti.dto.nit.NitDTO;
import br.unitins.pibiti.dto.nit.NitResponseDTO;
import br.unitins.pibiti.dto.nit.NitUpdateDTO;
import br.unitins.pibiti.dto.nit.ServicosFornecidoDTO;
import br.unitins.pibiti.dto.responsavel.ResponsavelDTO;
import br.unitins.pibiti.model.Nit;
import br.unitins.pibiti.model.Responsavel;
import br.unitins.pibiti.model.ServicoFornecido;
import br.unitins.pibiti.model.ServicoNit;
import br.unitins.pibiti.repository.NitRepository;
import br.unitins.pibiti.repository.ResponsavelRepository;
import br.unitins.pibiti.repository.ServicoFornecidoRepository;
import br.unitins.pibiti.repository.ServicoNitRepository;
import br.unitins.pibiti.service.auth.HashService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class NitServiceImpl implements NitService {
    
    @Inject
    NitRepository nitRepository;

    @Inject
    ResponsavelRepository responsavelRepository;

    @Inject
    ServicoFornecidoRepository servicoFornecidoRepository;

    @Inject
    ServicoNitRepository servicoNitRepository;

    @Inject
    Validator validator;

    @Inject
    HashService hashService;

    @Override
    public NitResponseDTO getNit(Long id) {

        Nit nit = nitRepository.findById(id);

        if (nit == null)
            throw new NotFoundException("Nit não encontrado");

        return new NitResponseDTO(nit);
    }

    @Override
    @Transactional
    public NitResponseDTO cadastrar(NitDTO nitDTO) throws ConstraintViolationException {

        // validando DTO

        validar(nitDTO);
        validar(nitDTO.responsavelDTO());

        // validando se a senha e a confirmação da senha são iguais
        String senha = nitDTO.senhaDTO().senha();
        String confirmarSenha = nitDTO.senhaDTO().confirmarSenha();

        if (!senha.equals(confirmarSenha))
            throw new BadRequestException("Os campos nova senha e confirmação da senha não correspondem");

        // validando se o CNPJ ou Email não são repetidos

        Nit validarCnpj = nitRepository.findByCnpj(nitDTO.cnpj());
        Nit validarEmail = nitRepository.findByEmail(nitDTO.email());

        if (validarCnpj != null)
            throw new BadRequestException("CNPJ fornecido já está cadastrado no sistema");
        
        if (validarEmail != null)
            throw new BadRequestException("Email fornecido já está cadastrado no sistema");

        // cadastrando atributos

        Nit nit = new Nit();

        nit.setCnpj(nitDTO.cnpj());

        nit.setEmail(nitDTO.email());

        nit.setTelefone(nitDTO.telefone());

        nit.setAnoInicioAtividades(nitDTO.anoInicioAtividades());

        nit.setIct(nitDTO.ict());

        nit.setPrivacidade(nitDTO.privacidade());

        nit.setSenha(hashService.getHashSenha(senha));
        
        nitRepository.persist(nit);

        Responsavel responsavel = new Responsavel(nitDTO.responsavelDTO());        
        
        responsavel.setNit(nit);

        responsavelRepository.persist(responsavel);
        
        nit.setResponsavel(responsavel);

        return new NitResponseDTO(nit);
    }

    @Override
    @Transactional
    public NitResponseDTO atualizar(String cnpj, NitUpdateDTO nitDTO) throws ConstraintViolationException {

        validar(nitDTO);
        validar(nitDTO.responsavelDTO());

        Nit nit = nitRepository.findByCnpj(cnpj);

        if (nitRepository.existsByCnpjAndNit(nitDTO.cnpj(), nit.getIdNit()))
            throw new BadRequestException("CNPJ fornecido já está cadastrado no sistema");
        
        if (nitRepository.existsByEmailAndNit(nitDTO.email(), nit.getIdNit()))
            throw new BadRequestException("Email fornecido já está cadastrado no sistema");

        // cadastrando novos valores

        nit.setCnpj(nitDTO.cnpj());
        nit.setEmail(nitDTO.email());
        nit.setTelefone(nitDTO.telefone());
        nit.setAnoInicioAtividades(nitDTO.anoInicioAtividades());
        nit.setIct(nitDTO.ict());
        nit.setPrivacidade(nitDTO.privacidade());

        Responsavel responsavel = responsavelRepository.findById(nit.getResponsavel().getIdResponsavel());

        nit.setResponsavel(atualizarResponsavel(responsavel, nitDTO.responsavelDTO()));

        return new NitResponseDTO(nit);
    }

    @Override
    @Transactional
    public void cadastrarServicosFornecidos(ServicosFornecidoDTO servicosFornecidoDTO) {

        Nit nit = nitRepository.findById(servicosFornecidoDTO.idNit());

        if (nit == null)
            throw new NotFoundException("Não existe um NIT com esse ID");

        gerarListaServicos(servicosFornecidoDTO.servicosFornecidos(), nit);

        nitRepository.persist(nit);
    }

    @Override
    public Nit getByLoginAndSenha(String login, String senha) {

        Nit nit;

        if (login.contains("@")) {

            nit = nitRepository.findByEmailAndSenha(login, senha);
        } else {
            
            nit = nitRepository.findByCnpjAndSenha(login, senha);
        }

        return nit;
    }

    @Override
    public NitResponseDTO getNitLogado(String login) {
        Nit nit;

        if (login.contains("@")) {
            nit = nitRepository.findByEmail(login);
        } else {
            nit = nitRepository.findByCnpj(login);
        }

        if (nit == null)
            throw new NotFoundException("Nit não encontrado");

        return new NitResponseDTO(nit);
    }

    private Responsavel atualizarResponsavel(Responsavel responsavel, ResponsavelDTO responsavelDTO) {

        responsavel.setNomeCompleto(responsavelDTO.nomeCompleto());

        responsavel.setCpf(responsavelDTO.cpf());

        responsavel.setEmail(responsavelDTO.email());

        responsavel.setTelefone(responsavelDTO.telefone());

        responsavel.setCargo(responsavelDTO.cargo());

        return responsavel;
    }

    private void validar(NitDTO nitDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<NitDTO>> violations = validator.validate(nitDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    private void validar(ResponsavelDTO responsavelDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<ResponsavelDTO>> violations = validator.validate(responsavelDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    private void validar(NitUpdateDTO nitDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<NitUpdateDTO>> violations = validator.validate(nitDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    private void gerarListaServicos(List<Long> listaIdsServicos, Nit nit) {
        // Converte ids para entidades
        List<ServicoFornecido> servicosFornecidos = listaIdsServicos.stream()
                .map(id -> servicoFornecidoRepository.findById(id))
                .toList();

        if (servicosFornecidos.isEmpty())
            throw new NotFoundException("Nenhum dos Serviços selecionados é válido");

        // Remove vínculos que não estão mais presentes
        nit.getServicos().removeIf(sn -> servicosFornecidos.stream()
                .noneMatch(s -> s.getIdServicoFornecido().equals(sn.getServico().getIdServicoFornecido())));

        // Adiciona novos vínculos
        for (ServicoFornecido servicoFornecido : servicosFornecidos) {
            boolean jaExiste = nit.getServicos().stream()
                    .anyMatch(sn -> sn.getServico().getIdServicoFornecido()
                            .equals(servicoFornecido.getIdServicoFornecido()));

            if (!jaExiste) {
                ServicoNit servicoNit = new ServicoNit();
                servicoNit.setServico(servicoFornecido);
                servicoNit.setNit(nit);

                servicoNitRepository.persist(servicoNit);
                nit.getServicos().add(servicoNit);
            }
        }
    }
}
