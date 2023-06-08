package ifma.com.jogos.locadorajogos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.ItemLocacao;

public interface ItemLocacaoRepository extends JpaRepository<ItemLocacao, Long> {
    
}
