package modelo.universo;

import modelo.nave.Nave;

public class EstacionDeCarga implements ElementoDelUniverso {
    final int RECARGA_COMBUSTIBLE = 50;
    final int RECARGA_ENERGIA = 50;

    public int getRecargaCombustible() {
        return RECARGA_COMBUSTIBLE;
    }

    public int getRecargaEnergia() {
        return RECARGA_ENERGIA;
    }

    @Override
    public void choque(ElementoDelUniverso elem) {
        if (elem instanceof Nave nave)
            nave.choque(elem);
    }
}
