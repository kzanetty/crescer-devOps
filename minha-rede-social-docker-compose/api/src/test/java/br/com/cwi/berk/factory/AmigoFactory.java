package br.com.cwi.berk.factory;

import br.com.cwi.berk.domain.Amigo;
import br.com.cwi.berk.domain.enums.AmizadeStatus;

public class AmigoFactory {

    public static Amigo get() {
        Amigo amigo = new Amigo();
        amigo.setId(SimpleFactory.getRandomLong());
        amigo.setRecebedor(UsuarioFactory.get());
        amigo.setSolicitante(UsuarioFactory.get());
        amigo.setStatus(AmizadeStatus.ACEITO);
        return amigo;
    }
}
