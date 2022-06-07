package fr.lmf.test_mod_forge.capability;

public interface ITestCapability {
    int getPower();

    void setPower(int value);

    default void reducePower(int value){
        this.setPower(this.getPower() - value);
    }

    default void increasePower(int value){
        this.setPower(this.getPower() + value);
    }
}
