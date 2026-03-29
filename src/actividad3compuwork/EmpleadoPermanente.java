package actividad3compuwork;

public class EmpleadoPermanente extends Empleado {
private double bonoAnual;
public EmpleadoPermanente(String id, String nombre, double salarioBase, double bonoAnual) {
super(id, nombre, salarioBase); 
this.bonoAnual = bonoAnual;
}
@Override
public double calcularSalario() {
return getSalarioBase() + (bonoAnual / 12); // Mensual
}
@Override
public String getTipo() {
return "Permanente";
}

}
