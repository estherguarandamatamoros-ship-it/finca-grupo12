package Semana2.finca;

public sealed interface Activo permits Producto, Cultivo, Animal {
    String codigo();
}
