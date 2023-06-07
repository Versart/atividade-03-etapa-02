package ifma.com.jogos.locadorajogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.model.ItemLocacao;

public interface ItemLocacaoRepository extends JpaRepository<ItemLocacao, Long> {
    
}
