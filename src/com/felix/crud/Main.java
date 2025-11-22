package com.felix.crud;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        int option;
        do {
            showMenu();
            option = readInt("Elige una opción: ");
            handleOption(option);
        } while (option != 5);

        System.out.println("Saliendo del sistema... ¡Bye!");
    }

    private static void showMenu() {
        System.out.println("\n==== CRUD de Usuarios ====");
        System.out.println("1. Crear usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Actualizar usuario");
        System.out.println("4. Eliminar usuario");
        System.out.println("5. Salir");
    }

    private static void handleOption(int option) {
        switch (option) {
            case 1 -> createUser();
            case 2 -> listUsers();
            case 3 -> updateUser();
            case 4 -> deleteUser();
            case 5 -> {
                // salir
            }
            default -> System.out.println("Opción inválida.");
        }
    }

    private static void createUser() {
        System.out.println("\n-- Crear usuario --");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        var user = userService.createUser(name, email);
        System.out.println("✔ Usuario creado exitosamente: " + user);
    }

    private static void listUsers() {
        System.out.println("\n📋 Lista de usuarios registrados:");
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("⚠ No hay usuarios registrados.");
        } else {
            users.forEach(u -> System.out.println("🔸 " + u));
        }
    }

    private static void updateUser() {
        System.out.println("\n✏️ Actualizar usuario");
        int id = readInt("ID del usuario a actualizar: ");
        System.out.print("Nuevo nombre: ");
        String name = scanner.nextLine();
        System.out.print("Nuevo email: ");
        String email = scanner.nextLine();

        boolean updated = userService.updateUser(id, name, email);
        if (updated) {
            System.out.println("✔ Usuario actualizado correctamente.");
        } else {
            System.out.println("⚠ No se encontró un usuario con ese ID.");
        }
    }

    private static void deleteUser() {
        System.out.println("\n🗑 Eliminar usuario");
        int id = readInt("ID del usuario a eliminar: ");

        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            System.out.println("✔ Usuario eliminado correctamente.");
        } else {
            System.out.println("⚠ No se encontró un usuario con ese ID.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String line = scanner.nextLine();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
            }
        }
    }
}
