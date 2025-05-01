package helpers;

import web.data.model.Store;

import java.util.Arrays;

public class StoreProvider {
    public static final Store[] stores = {
            new Store("parndorf99944@seidensticker.de", "Seide99944!#", "MASTERMATE", "Seidensticker Outlet Parndorf"),
            new Store("bielefeld99930@seidensticker.de", "Seide99930!#", "MASTERMATE", "Seidensticker Outlet Bielefeld"),
            new Store("trainingstrasbourg89001@seidensticker.de", "Seide89001!#", "MASTERMATE", "Seidensticker Store Strasbourg"),
            new Store("trainingparis89005@seidensticker.de", "Seide89005!#", "MASTERMATE", "Seidensticker Store Paris"),
            new Store("popuptest89849@seidensticker.de", "Seide89849!#", "MASTERMATE", "POP-UP Atelier Direktv. TEST"),
            new Store("wertheim89859@seidensticker.de", "Seide89859!#", "MASTERMATE", "Seidensticker Outlet Wertheim"),
            new Store("wustermark89871@seidensticker.de", "Seide89871!#", "MASTERMATE", "Seid. Outlet Berlin-Wustermark"),
            new Store("salzburg89872@seidensticker.de", "Seide89872!#", "MASTERMATE", "Seidensticker Outlet Salzburg"),
            new Store("metzingen89873@seidensticker.de", "Seide89873!#", "MASTERMATE", "Seidensticker Outlet Metzingen"),
            new Store("bremen89876@seidensticker.de", "Seide89876!#", "MASTERMATE", "Seidensticker Outlet Bremen"),
            new Store("trier89877@seidensticker.de", "Seide89877!#", "MASTERMATE", "Seidensticker Store Trier"),
            new Store("erfurt89880@seidensticker.de", "Seide89880!#", "MASTERMATE", "Seidensticker Store Erfurt"),
            new Store("halle89890@seidensticker.de", "Seide89890!#", "MASTERMATE", "Seidensticker Outlet GW Halle"),
            new Store("trainingroppenheim89894@seidensticker.de", "Seide89894!#", "MASTERMATE", "Seidensticker Outlet Roppenheim"),
            new Store("ochtrup89895@seidensticker.de", "Seide89895!#", "MASTERMATE", "Seidensticker Outlet Ochtrup"),
            new Store("neumunster89896@seidensticker.de", "Seide89896!#", "MASTERMATE", "Seidensticker Outlet Neumünster"),
            new Store("wolfsburg89897@seidensticker.de", "Seide89897!#", "MASTERMATE", "Seidensticker Outlet Wolfsburg"),
            new Store("soltau89899@seidensticker.de", "Seide89899!#", "MASTERMATE", "Seidensticker Outlet Soltau"),
            new Store("brenner89905@seidensticker.de", "Seide89905!#", "MASTERMATE", "Seidensticker Outlet Brenner"),
            new Store("montabaur89906@seidensticker.de", "Seide89906!#", "MASTERMATE", "Seidensticker Outlet Montabaur"),
            new Store("sandersdorfbrehna89907@seidensticker.de", "Seide89907!#", "MASTERMATE", "Seidensticker Outlet Brehna"),
            new Store("trainingtalange89911@seidensticker.de", "Seide89911!#", "MASTERMATE", "Seidensticker Outlet Talange"),
            new Store("herford89912@seidensticker.de", "Seide89912!#", "MASTERMATE", "Seidensticker Outlet Herford"),
            new Store("rheda99931@seidensticker.de", "Seide99931!#", "MASTERMATE", "Seidensticker Outlet Rheda"),
            new Store("roermond99932@seidensticker.de", "Seide99932!#", "MASTERMATE", "Seidensticker Outlet Roermond"),
            new Store("sonthofen99934@seidensticker.de", "Seide99934!#", "MASTERMATE", "Seidensticker Outlet Sonthofen")
    };

    public static Store getStoreByName(String storeName) {
        return Arrays.stream(stores).filter(store -> store.getName().equals(storeName)).findFirst().get();
    }
}
