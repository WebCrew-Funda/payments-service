package fundamentosBackendTF.pe.edu.upc.common.application;

public enum RequestBodyType {
    VALID {
        public String toString() {
            return "VALID";
        }
    },
    INVALID {
        public String toString() {
            return "INVALID";
        }
    },
}
