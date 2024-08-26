import java.util.Random;

public class Materias {
    private final String nombre;
    public boolean parcial, cursadaAprobada, examenFinal, promocion, tieneCorrelativa, optativa, obligatoria;
    private Materias correlativa;

    public Materias(String nombre) {
        this.nombre = nombre;
        parcial=false;
        cursadaAprobada =false;
        examenFinal=false;
        promocion=false;
        tieneCorrelativa=false;
        Random random = new Random();
        obligatoria=random.nextBoolean();
        optativa=random.nextBoolean();
    }

    //BOORAR porque solo lo uso en pruebas, deberia ir correctamente la cantidad en cada carrera
    public void setOptativa (boolean valor){
        optativa = valor;
    }
    public void setObligatoria (boolean valor){
        obligatoria = valor;
    }
    //BOORAR

    public void setNotaParcial(double nota){
        if (nota >= 8) {
            parcial = true;
            cursadaAprobada = true;
            promocion= true;
            examenFinal=true;
        }
        else if (nota >=4){
            parcial = true;
            cursadaAprobada = true;
            promocion = false;
            examenFinal = false;
        }
        else {
            parcial = false;
            cursadaAprobada = false;
            promocion = false;
            examenFinal = false;
        }
    }

    public void setExamenFinal(double nota){
        examenFinal= nota >= 4;
        cursadaAprobada = nota >= 4;
    }

    public void setCorrelativa(Materias correlativa){
        this.correlativa=correlativa;
        tieneCorrelativa=true;
    }

    public boolean getNotaExamenFinal(){
        return examenFinal;
    }

    public boolean getCursadaAprobada(){
        return cursadaAprobada;
    }

    public String getNombreDeMateria(){
        return nombre;
    }

    public Materias getCorrelativa(){
        return this.correlativa;
    }
}
