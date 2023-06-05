package ifma.com.jogos.locadorajogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
