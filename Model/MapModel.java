public class MapModel{
    public int Width; //длина поля
    public int Height; //высота
    public char[][] Map = new char[Height][Width];

    MapModel(){

    }
    MapModel(){

    }

    public void clearMap(){
        Map = new char[Height][Width];
    }

}