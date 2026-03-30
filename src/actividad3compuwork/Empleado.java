package actividad3compuwork;

public abstract class Empleado {
private String id;
private String nombre;
private double salarioBase;
private Departamento departamento; // Asignación a departamento
public Empleado(String id, String nombre, double salarioBase) {
this.id = id;
this.nombre = nombre;
this.salarioBase = salarioBase;
this.departamento = null;
}
// Getters y setters básicos
public String getId() {
return id; }
public String getNombre() {
return nombre; }
public void setNombre(String nombre) {
this.nombre = nombre; }
public double getSalarioBase() {
return salarioBase; }
public void setSalarioBase(double salarioBase) {
if (salarioBase > 0) this.salarioBase = salarioBase;
}
public Departamento getDepartamento() {
return departamento; }
// Asignar/cambiar departamento
public void asignarDepartamento(Departamento dept) {
this.departamento = dept;
dept.agregarEmpleado(this); // Agrega automáticamente
System.out.println(" " + nombre + " asignado a " + dept.getNombre());
}
// Método abstracto polimórfico (cada tipo lo implementa)
public abstract double calcularSalario();
public abstract String getTipo();
@Override
public String toString() {
return "ID: " + id + ", " + nombre + " (" + getTipo() + "), Salario: $" +
calcularSalario() + (departamento != null ? ", Dept: " + departamento.getNombre() : "");
}

}
