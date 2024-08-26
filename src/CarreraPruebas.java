public class CarreraPruebas extends Carreras{
    public CarreraPruebas(int annio, int cuatri) {
        super("Turismo", annio, cuatri);
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

        carrera[0][0].setOptativa(true);
        carrera[0][1].setOptativa(true);
        carrera[0][2].setOptativa(true);
        carrera[0][3].setOptativa(true);
        carrera[0][4].setOptativa(true);
        carrera[0][5].setOptativa(true);


        carrera[1][0].setOptativa(true);
        carrera[1][1].setOptativa(true);
        carrera[1][2].setOptativa(true);
        carrera[1][3].setOptativa(true);
        carrera[1][4].setOptativa(true);
        carrera[1][5].setOptativa(true);



        carrera[0][0].setObligatoria(true);
        carrera[0][1].setObligatoria(true);
        carrera[0][2].setObligatoria(true);
        carrera[0][3].setObligatoria(false);
        carrera[0][4].setObligatoria(false);
        carrera[0][5].setObligatoria(false);


        carrera[1][0].setObligatoria(false);
        carrera[1][1].setObligatoria(false);
        carrera[1][2].setObligatoria(false);
        carrera[1][3].setObligatoria(false);
        carrera[1][4].setObligatoria(false);
        carrera[1][5].setObligatoria(false);



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





        carrera[2][0].setObligatoria(false);
        carrera[2][1].setObligatoria(false);
        carrera[2][2].setObligatoria(false);
        carrera[2][3].setObligatoria(false);
        carrera[2][4].setObligatoria(false);
        carrera[2][5].setObligatoria(false);


        carrera[3][0].setObligatoria(false);
        carrera[3][1].setObligatoria(false);
        carrera[3][2].setObligatoria(false);
        carrera[3][3].setObligatoria(false);
        carrera[3][4].setObligatoria(false);
        carrera[3][5].setObligatoria(false);
    }

    public float getCantMateriasOptativas (){
        float cant_materias = 0;
        for (Materias[] fila : carrera) {
            for (Materias materia : fila) {
                if (materia.optativa){
                    cant_materias++;
                }
            }
        }
        cant_materias= (float) (cant_materias*.10);
        return cant_materias;
    }
}
