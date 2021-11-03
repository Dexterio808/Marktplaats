package nl.belastingdienst.util;

import nl.belastingdienst.gebruiker.Gebruiker;

public class CurrentUser {
    private Gebruiker gebruiker;

    public void setGebruiker(Gebruiker gebruiker){
        this.gebruiker = gebruiker;
    }

    public Gebruiker getGebruiker(){
        return this.gebruiker;
    }
}
