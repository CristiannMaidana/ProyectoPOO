public class Materias {
    private final String nombre;
    public boolean parcial, cursadaAprobada, examenFinal, promocion, tieneCorrelativa, optativa, obligatoria;
    private Materias correlativa;

    //Constructor
    public Materias(String nombre) {
        this.nombre = nombre;
        parcial=false;
        cursadaAprobada =false;
        examenFinal=false;
        promocion=false;
        tieneCorrelativa=false;
        optativa=false;
        obligatoria=false;
    }

    //Metodos get
    public String getNombreDeMateria(){
        return nombre;
    }
    public boolean getNotaExamenFinal(){
        return examenFinal;
    }
    public boolean getCursadaAprobada(){
        return cursadaAprobada;
    }
    public boolean getOptativa(){
        return optativa;
    }
    public boolean getObligatoria(){
        return obligatoria;
    }
    public Materias getCorrelativa(){
        return this.correlativa;
    }

    //Metodos set
    public void setOptativa (boolean valor){
        optativa = valor;
    }
    public void setObligatoria (boolean valor){
        obligatoria = valor;
    }
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
}
