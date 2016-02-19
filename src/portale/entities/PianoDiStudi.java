package portale.entities;

import java.util.Date;

public class PianoDiStudi {

    private Materia mMateria;

    private String esito;

    private Date dataEsame;

    public PianoDiStudi(Materia mMateria, String esito, Date dataEsame) {
        this.mMateria = mMateria;
        this.esito = esito;
        this.dataEsame = dataEsame;
    }

    public Materia getmMateria() {
        return mMateria;
    }

    public void setmMateria(Materia mMateria) {
        this.mMateria = mMateria;
    }

    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }

    public Date getDataEsame() {
        return dataEsame;
    }

    public void setDataEsame(Date dataEsame) {
        this.dataEsame = dataEsame;
    }
}
