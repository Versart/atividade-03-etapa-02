package ifma.com.jogos.locadorajogos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
