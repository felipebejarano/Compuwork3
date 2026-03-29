package actividad3compuwork;

import java.util.Scanner;


public class Main {
public static void main(String[] args) {
GestorEmpleados gestor = new GestorEmpleados();
Scanner scanner = new Scanner(System.in);
int opcion;
do {
System.out.println("\n=== GESTIÓN EMPLEADOS ===");
System.out.println("1. Crear Empleado");
System.out.println("2. Actualizar Empleado");
System.out.println("3. Eliminar Empleado");
System.out.println("4. Crear Departamento");
System.out.println("5. Asignar Empleado a Depto");
System.out.println("6. Mostrar Empleados");
System.out.println("7. Mostrar Departamentos");
System.out.println("0. Salir");
System.out.print("Opción: "); opcion = scanner.nextInt();
scanner.nextLine();
switch (opcion) {
case 1: gestor.crearEmpleado(); break;
case 2: gestor.actualizarEmpleado(); break;
case 3: gestor.eliminarEmpleado(); break;
case 4: gestor.crearDepartamento(); break;
case 5: gestor.asignarDepartamento(); break;
case 6: gestor.mostrarEmpleados(); break;
case 7: gestor.mostrarDepartamentos();
for (Departamento d : gestor.getDepartamentos()) {
d.mostrarEmpleados();
} break;
}
} while (opcion != 0);
}
}