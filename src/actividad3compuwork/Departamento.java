package actividad3compuwork;

import java.util.ArrayList;
public class Departamento {
private String nombre;
private ArrayList<Empleado> empleados;
public Departamento(String nombre) {
this.nombre = nombre;
this.empleados = new ArrayList<>();
}
public String getNombre()
{ return nombre; }
public void setNombre(String nombre)
{ this.nombre = nombre; }
public ArrayList<Empleado> getEmpleados() { return empleados; }
public void agregarEmpleado(Empleado emp) {
if (!empleados.contains(emp)) {
empleados.add(emp);
}
}
public void eliminarEmpleado(Empleado emp) {
empleados.remove(emp);
}
public void mostrarEmpleados() {
System.out.println("Empleados en " + nombre + ":");
if (empleados.isEmpty()) {
System.out.println(" Ninguno");
} else {
for (Empleado emp : empleados) {
System.out.println(" - " + emp);
}
}
}
@Override
public String toString() {
return nombre + " (" + empleados.size() + " empleados)";
}
}