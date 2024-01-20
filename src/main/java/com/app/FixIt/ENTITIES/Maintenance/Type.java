package com.app.FixIt.ENTITIES.Maintenance;

public enum Type {
    ordinateurs("Informatique-Ordinateur"),
	machineALaver("Electromenage"),
    telephone("Informatique-Telephone"),
    voiture("Mecanique"),
    cuisiniereFour("Electromenage"),
    television("Electromenage");


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
