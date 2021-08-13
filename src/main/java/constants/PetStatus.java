package constants;

public enum PetStatus {
    available("available"),
    pending("pending"),
    sold("sold");

    private String pet;

    PetStatus(String pet){
        this.pet = pet;
    }

    public String get() {
        return pet;
    }
}
