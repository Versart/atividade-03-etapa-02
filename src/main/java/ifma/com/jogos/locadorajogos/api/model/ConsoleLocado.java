package ifma.com.jogos.locadorajogos.api.model;

import ifma.com.jogos.locadorajogos.domain.model.UtilizacaoConsoleCliente;

public record ConsoleLocado(String nomeCliente, String console) {
    
    public ConsoleLocado(UtilizacaoConsoleCliente consoleCliente){
        this(consoleCliente.getCliente().getNome(),consoleCliente.getConsole().getNome());
    }
}
