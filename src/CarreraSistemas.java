public class CarreraSistemas extends  Carreras{
    public CarreraSistemas(int annio, int cuatri) {
        super(annio,cuatri);
        carrera[0][0]=new Materias("materia1");
        carrera[0][1]=new Materias("materia2");
        carrera[0][2]=new Materias("materia3");
        carrera[0][3]=new Materias("materia4");
        carrera[0][4]=new Materias("materia5");
        carrera[0][5]=new Materias("materia6");

        //Aca empieza las materias con correlativas
        Materias materiasX=new Materias("materia21");
        materiasX.setCorrelativa(carrera[0][0]);
        carrera[1][0]=materiasX;

        materiasX=new Materias("materia22");
        materiasX.setCorrelativa(carrera[0][1]);
        carrera[1][1]=materiasX;

        materiasX=new Materias("materia23");
        materiasX.setCorrelativa(carrera[0][2]);
        carrera[1][2]=materiasX;

        materiasX=new Materias("materia24");
        materiasX.setCorrelativa(carrera[0][3]);
        carrera[1][3]=materiasX;

        materiasX=new Materias("materia25");
        materiasX.setCorrelativa(carrera[0][4]);
        carrera[1][4]=materiasX;

        materiasX=new Materias("materia26");
        materiasX.setCorrelativa(carrera[0][5]);
        carrera[1][5]=materiasX;



        materiasX=new Materias("materia31");
        materiasX.setCorrelativa(carrera[1][0]);
        carrera[2][0]=materiasX;
        materiasX=new Materias("materia32");
        materiasX.setCorrelativa(carrera[1][1]);
        carrera[2][1]=materiasX;
        materiasX=new Materias("materia33");
        materiasX.setCorrelativa(carrera[1][2]);
        carrera[2][2]=materiasX;
        materiasX=new Materias("materia34");
        materiasX.setCorrelativa(carrera[1][3]);
        carrera[2][3]=materiasX;
        materiasX=new Materias("materia35");
        materiasX.setCorrelativa(carrera[1][4]);
        carrera[2][4]=materiasX;
        materiasX=new Materias("materia36");
        materiasX.setCorrelativa(carrera[1][5]);
        carrera[2][5]=materiasX;


        materiasX=new Materias("materia41");
        materiasX.setCorrelativa(carrera[2][0]);
        carrera[3][0]=materiasX;
        materiasX=new Materias("materia42");
        materiasX.setCorrelativa(carrera[2][1]);
        carrera[3][1]=materiasX;
        materiasX=new Materias("materia43");
        materiasX.setCorrelativa(carrera[2][2]);
        carrera[3][2]=materiasX;
        materiasX=new Materias("materia44");
        materiasX.setCorrelativa(carrera[2][3]);
        carrera[3][3]=materiasX;
        materiasX=new Materias("materia45");
        materiasX.setCorrelativa(carrera[2][4]);
        carrera[3][4]=materiasX;
        materiasX=new Materias("materia46");
        materiasX.setCorrelativa(carrera[2][5]);
        carrera[3][5]=materiasX;


        materiasX=new Materias("materia51");
        materiasX.setCorrelativa(carrera[3][0]);
        carrera[4][0]=materiasX;
        materiasX=new Materias("materia52");
        materiasX.setCorrelativa(carrera[3][1]);
        carrera[4][1]=materiasX;
        materiasX=new Materias("materia53");
        materiasX.setCorrelativa(carrera[3][2]);
        carrera[4][2]=materiasX;
        materiasX=new Materias("materia54");
        materiasX.setCorrelativa(carrera[3][3]);
        carrera[4][3]=materiasX;
        materiasX=new Materias("materia55");
        materiasX.setCorrelativa(carrera[3][4]);
        carrera[4][4]=materiasX;
        materiasX=new Materias("materia56");
        materiasX.setCorrelativa(carrera[3][5]);
        carrera[4][5]=materiasX;
    }

    public float getCantMateriasOptativas (){
        float cant_materias= (getAnniosCarrera()*2)*3;
        cant_materias= (float) (cant_materias*.50);
        return cant_materias;
    }
}
