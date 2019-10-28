package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Estacionamento {

	List<Carro> carros = new ArrayList<Carro>();
	
    public void estacionar( Carro carro ) {
      
    	if( carro.getMotorista() == null ) {
    		throw new EstacionamentoException( "Sem motorista" );
    	}
    	if( carro.getMotorista().getIdade() < 18 ) {
    		throw new EstacionamentoException( "Motorista menor de idade" );
    	}
    	if( carro.getMotorista().getPontos() > 20 ) {
    		throw new EstacionamentoException( "Motorista excedeu pontos da carteira" );
    	}
    	if( carros.size() == 10 ) {
    		verificarDisponibilidadeDeVagas();
    		removerPrimeiroCarroAoAtingirLimiteVagas();
    	}
 
    	carros.add( carro );
    }

    private void verificarDisponibilidadeDeVagas() {
		
    	if( carros.stream().filter( carro -> carro.getMotorista().getIdade() > 55 ).collect(Collectors.toList()).size() == 10 ) {
    		throw new EstacionamentoException( "Estacionamento lotado" );
    	}
		
	}

	private void removerPrimeiroCarroAoAtingirLimiteVagas() {
    	
    	carros.stream().filter( carro -> 
		carro.getMotorista().getIdade() < 55 ).findFirst().map( carro -> carros.remove(carro));
	}

	public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
    	if( carros.contains( carro ) ) {
            return true;
    	}
    	return false;
    }
}
