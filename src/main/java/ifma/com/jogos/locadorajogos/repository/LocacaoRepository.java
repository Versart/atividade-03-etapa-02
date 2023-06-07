package ifma.com.jogos.locadorajogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long>{
    
}
