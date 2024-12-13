package br.api.errors;

public class CursoErrors {
    public static class RegisterError extends Exception {
        public RegisterError(String message) {
            super(message);
        }
    }
}
