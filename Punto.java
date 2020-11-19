package ProyectoU2;

public class Punto {

    private int x, y;

    /**
    Generar un punto en las coordenadas (0,0)
    */
    public Punto() {
        this.x = 0;
        this.y = 0;
    }
/**
 * Generar un punto en las coordenadas (x, y)
 * @param x Coordenada en x del punto
 * @param y Coordenada en y del punto
 */
    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }   

    /**
     * Asignar valor de la Coordenada x
     * @param x Coordenada x
     */
    public void SetX(int x){
        this.x = x;
    }
    /**
     * Obtener el valor de la Coordenada en x
     * @return Coordenada en x del punto
     */
    public int GetX(){
        return this.x;
    }
    /**
     * Asignar valor de la Coordenada y
     * @param x Coordenada x
     */
    public void SetY(int y){
        this.y = y;
    }
    /**
     * Obtener el valor de la Coordenada en y
     * @return Coordenada en y del punto
     */
    public int GetY(){
        return this.y;
    }        
}
