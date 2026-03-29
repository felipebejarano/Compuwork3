package actividad3compuwork;

import actividad3compuwork.Empleado;

public class EmpleadoTemporal extends Empleado {
private int horasMensuales;
private double tarifaHora;
public EmpleadoTemporal(String id, String nombre, int horasMensuales, double tarifaHora) {
super(id, nombre, 0); // Salario base 0, se calcula
this.horasMensuales = horasMensuales;
this.tarifaHora = tarifaHora;
}
@Override
public double calcularSalario() {
return horasMensuales * tarifaHora;
}
@Override
public String getTipo() {
return "Temporal";
}
}