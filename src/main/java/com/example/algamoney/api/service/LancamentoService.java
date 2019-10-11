package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	private PessoaRepository pessoaRepository;
	private LancamentoRepository lancamentoRepository;

	public LancamentoService(PessoaRepository pessoaRepository, LancamentoRepository lancamentoRepository) {
		super();
		this.pessoaRepository = pessoaRepository;
		this.lancamentoRepository = lancamentoRepository;
	}

	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if (pessoa == null || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}

}
