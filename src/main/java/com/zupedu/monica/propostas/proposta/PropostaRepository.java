package com.zupedu.monica.propostas.proposta;

import com.zupedu.monica.propostas.api_externa.dto_solicitacao.StatusSolicitacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaRepository  extends JpaRepository<Proposta, Long> {

    public List<Proposta> findByStatus(StatusSolicitacaoEnum statusSolicitacao);

}
