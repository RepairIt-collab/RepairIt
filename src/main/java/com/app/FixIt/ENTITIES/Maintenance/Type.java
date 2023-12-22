package com.app.FixIt.ENTITIES.Maintenance;

public enum Type {
    ordinateurs("informatique-Ordinateur"),
	machineALaver("electromenager"),
    telephone("informatique-Telephone"),
    voiture("mecanique"),
    cuisiniereFour("electromenager"),
    television("electromenager");


	Type(String key) {
	    this.value = key;
    }
    
    private String value;

    public String value() {
        return value;
    }
    public String getKey() {
        int startIndex = value.indexOf("(");
        int endIndex = value.indexOf(")");
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex + 1) {
            return value.substring(startIndex + 1, endIndex);
        }
        return null;
    }
}
