package ifma.com.jogos.locadorajogos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long>{
    
}
