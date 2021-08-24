package com.zupedu.monica.propostas.proposta;

import com.zupedu.monica.propostas.solicitacao.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository  extends JpaRepository<Proposta, Long> {

    public List<Proposta> findByStatus(StatusSolicitacao statusSolicitacao);

}
